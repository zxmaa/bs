package com.bs.mall.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bs.mall.dao.*;
import com.bs.mall.entity.Address;
import com.bs.mall.entity.Product;
import com.bs.mall.entity.ProductOrderItem;
import com.bs.mall.entity.User;
import com.bs.mall.service.admin.BaseService;
import com.bs.mall.service.admin.IAdminUserService;
import com.bs.mall.util.OrderUtil;
import com.bs.mall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * author:xs
 * date:2020/4/6 14:10
 * description:管理端用户service
 */
@Service("adminUserService")
public class AdminUserServiceImpl extends BaseService implements IAdminUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ProductOrderItemMapper productOrderItemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;

    //转到后台管理-用户页-ajax
    @Override
    public String goUserManagePage(HttpSession session, Map<String, Object> map) {
        logger.info("获取前十条用户信息");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<User> userList=userMapper.selectList(null, null, pageUtil);
        map.put("userList", userList);

        logger.info("获取用户总数量");
        Integer userCount=userMapper.selectTotal(null);
        map.put("userCount", userCount);

        logger.info("获取分页信息");
        pageUtil.setTotal(userCount);
        map.put("pageUtil", pageUtil);

        return "admin/userManagePage";
    }

    @Override
    public String getUserById(HttpSession session, Map<String, Object> map, Integer uid) {
        logger.info("获取user_id为{}的用户信息",uid);
        User user=userMapper.selectOneUser(uid);
        logger.info("获取用户详情-所在地地址信息");
        //Address address=addressMapper.selectOne(user.getUserAddress().getAddressAreaId());
//        Stack<String> addressStack = new Stack<>();
//        //最后一级地址
//        addressStack.push(address.getAddressName() + " ");
//        //如果不是一级地址
//        while (!address.getAddressAreaId().equals(address.getAddressRegionId().getAddressAreaId())){
//            address=addressMapper.selectOne(address.getAddressRegionId().getAddressAreaId());
//            addressStack.push(address.getAddressName() + " ");
//        }
//        StringBuilder builder = new StringBuilder();
//        while (!addressStack.empty()) {
//            builder.append(addressStack.pop());
//        }
//
//        logger.info("所在地地址字符串：{}", builder);
//        Address tempAddress=new Address();
//        tempAddress.setAddressName(builder.toString());
//        user.setUserAddress(tempAddress);
//
//        logger.info("获取用户详情-家乡地址信息");
//        address=addressMapper.selectOne(user.getUserHomeplace().getAddressAreaId());
//        //最后一级地址
//        addressStack.push(address.getAddressName()+" ");
//        //如果不是第一级地址
//        while (!address.getAddressAreaId().equals(address.getAddressRegionId().getAddressAreaId())){
//            address=addressMapper.selectOne(address.getAddressRegionId().getAddressAreaId());
//            addressStack.push(address.getAddressName()+" ");
//        }
//        builder = new StringBuilder();
//        while (!addressStack.empty()){
//            builder.append(addressStack.pop());
//        }
//
//        logger.info("家乡地址字符串：{}", builder);
//        tempAddress=new Address();
//        tempAddress.setAddressName(builder.toString());
//        user.setUserHomeplace(tempAddress);

        logger.info("获取用户详情-购物车订单项信息");
        List<ProductOrderItem> productOrderItemList=productOrderItemMapper.selectByUserId(user.getUserId(), null);
        if (productOrderItemList != null){
            logger.info("获取用户详情-购物车订单项对应的产品信息");
            for (ProductOrderItem productOrderItem : productOrderItemList){
                Integer productId=productOrderItem.getProductOrderItemProduct().getProductId();
                logger.warn("获取产品ID为{}的产品信息", productId);
                Product product=productMapper.selectOne(productId);
                if (product != null){
                    logger.warn("获取产品ID为{}的第一张预览图片信息", productId);
                    product.setSingleProductImageList(productImageMapper.select(productId, (byte) 0, new PageUtil(0, 1)));
                }
                productOrderItem.setProductOrderItemProduct(product);
            }
        }
        user.setProductOrderItemList(productOrderItemList);

        if (user.getUserRealname()!=null){
            logger.info("用户隐私加密");
            user.setUserRealname(user.getUserRealname().substring(0, 1)+"*");
        }else {
            user.setUserRealname("未命名");
        }

        map.put("user",user);
        logger.info("转到后台管理-用户详情页-ajax方式");
        return "admin/include/userDetails";
    }

    @Override
    public String getUserBySearch(String user_name, Byte[] user_gender_array, String orderBy, Boolean isDesc, Integer index, Integer count) throws UnsupportedEncodingException {

        //移除不必要条件
        Byte gender = null;
        if (user_gender_array != null && user_gender_array.length == 1) {
            gender = user_gender_array[0];
        }

        if (user_name != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            user_name = "".equals(user_name) ? null : URLDecoder.decode(user_name, "UTF-8");
        }

        if (orderBy != null && "".equals(orderBy)) {
            orderBy = null;
        }
        //封装查询条件
        User user=new User();
        user.setUserName(user_name);
        user.setUserGender(gender);

        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }
        JSONObject object = new JSONObject();//做返回值
        logger.info("按条件获取第{}页的{}条用户", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<User> userList=userMapper.selectList(user, orderUtil, pageUtil);
        object.put("userList", JSONArray.parseArray(JSON.toJSONString(userList)));
        logger.info("按条件获取用户总数量");
        Integer userCount=userMapper.selectTotal(user);
        object.put("userCount", userCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(userCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);

        return object.toJSONString();
    }
}
