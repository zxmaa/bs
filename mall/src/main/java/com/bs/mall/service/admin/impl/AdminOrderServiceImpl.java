package com.bs.mall.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bs.mall.dao.*;
import com.bs.mall.entity.Address;
import com.bs.mall.entity.Product;
import com.bs.mall.entity.ProductOrder;
import com.bs.mall.entity.ProductOrderItem;
import com.bs.mall.service.admin.BaseService;
import com.bs.mall.service.admin.IAdminOrderService;
import com.bs.mall.util.OrderUtil;
import com.bs.mall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * author:xs
 * date:2020/4/9 15:22
 * description:后台订单管理service
 */
@Service
public class AdminOrderServiceImpl extends BaseService implements IAdminOrderService {
    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductOrderItemMapper productOrderItemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private LastIDMapper lastIDMapper;

    @Override
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("获取前10条订单列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<ProductOrder> productOrderList=productOrderMapper.select(null,
                null, new OrderUtil("product_order_id"), pageUtil);
        map.put("productOrderList",productOrderList);
        logger.info("获取订单总数量");
        Integer productOrderCount=productOrderMapper.selectTotal(null, null);
        map.put("productOrderCount", productOrderCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productOrderCount);
        map.put("pageUtil", pageUtil);

        logger.info("转到后台管理-订单页-ajax方式");
        return "admin/orderManagePage";
    }

    @Override
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, Integer oid) {
        logger.info("获取order_id为{}的订单信息",oid);
        ProductOrder order=productOrderMapper.selectOneProductOrder(oid);
        logger.info("获取订单详情-地址信息");
        Address address = addressMapper.selectOne(order.getProductOrderAddress().getAddressAreaId());
        Stack<String> addressStack = new Stack<>();
        //详细地址
        addressStack.push(order.getProductOrderDetailAddress());
        //最后一级地址
        addressStack.push(address.getAddressName()+" ");
        //如果不是第一级地址，循环拼接地址信息
        while (!address.getAddressAreaId().equals(address.getAddressRegionId().getAddressAreaId())){
            address=addressMapper.selectOne(address.getAddressRegionId().getAddressAreaId());
            addressStack.push(address.getAddressName()+" ");
        }
        StringBuilder builder = new StringBuilder();
        while (!addressStack.empty()) {
            builder.append(addressStack.pop());
        }
        logger.info("订单地址字符串：{}", builder);
        order.setProductOrderDetailAddress(builder.toString());
        logger.info("获取订单详情-用户信息");
        order.setProductOrderUser(userMapper.selectOneUser(order.getProductOrderUser().getUserId()));
        logger.info("获取订单详情-订单项信息");
        List<ProductOrderItem> productOrderItemList=productOrderItemMapper.selectByOrderId(oid, null);
        if (productOrderItemList != null){
            logger.info("获取订单详情-订单项对应的产品信息");
            for (ProductOrderItem productOrderItem : productOrderItemList){
                Integer productId=productOrderItem.getProductOrderItemProduct().getProductId();
                logger.info("获取产品ID为{}的产品信息", productId);
                Product product = productMapper.selectOne(productId);
                if (product != null){
                    logger.info("获取产品ID为{}的第一张预览图片信息", productId);
                    product.setSingleProductImageList(productImageMapper.select(productId, (byte) 0, new PageUtil(0, 1)));
                }
                productOrderItem.setProductOrderItemProduct(product);
            }
        }
        order.setProductOrderItemList(productOrderItemList);
        map.put("order", order);

        logger.info("转到后台管理-订单详情页-ajax方式");
        return "admin/include/orderDetails";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateOrder(String order_id) {
        JSONObject jsonObject = new JSONObject();//做返回值
        logger.info("整合订单信息");
        ProductOrder productOrder=new ProductOrder();
        productOrder.setProductOrderId(Integer.parseInt(order_id));
        productOrder.setProductOrderStatus((byte) 2);
        productOrder.setProductOrderDeliveryDate(new Date());
        logger.info("更新订单信息，订单ID值为：{}", order_id);
        boolean bool=productOrderMapper.updateOne(productOrder)>0;
        if (bool){
            logger.info("更新成功！");
            jsonObject.put("success", true);
        }else {
            logger.info("更新失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String getOrderBySearch(String productOrder_code, String productOrder_post, Byte[] productOrder_status_array, String orderBy, Boolean isDesc, Integer index, Integer count) {
        //移除不必要条件
        if (productOrder_status_array != null && (productOrder_status_array.length <= 0 || productOrder_status_array.length >=5)) {
            productOrder_status_array = null;
        }
        if (productOrder_code != null){
            productOrder_code = "".equals(productOrder_code) ? null : productOrder_code;
        }
        if(productOrder_post != null){
            productOrder_post = "".equals(productOrder_post) ? null : productOrder_post;
        }
        if (orderBy != null && "".equals(orderBy)) {
            orderBy = null;
        }
        //封装查询条件
        ProductOrder productOrder=new ProductOrder();
        productOrder.setProductOrderCode(productOrder_code);
        productOrder.setProductOrderPost(productOrder_post);
        OrderUtil orderUtil = null;
        if (orderBy==null){
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }else {
            orderUtil=new OrderUtil("product_order_id", true);
        }
        JSONObject object = new JSONObject();//做返回值
        logger.info("按条件获取第{}页的{}条订单", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<ProductOrder> productOrderList=productOrderMapper.select(productOrder, productOrder_status_array, orderUtil, pageUtil);
        object.put("productOrderList", JSONArray.parseArray(JSON.toJSONString(productOrderList)));
        logger.info("按条件获取订单总数量");
        Integer productOrderCount=productOrderMapper.selectTotal(productOrder, productOrder_status_array);
        object.put("productOrderCount", productOrderCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productOrderCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);
        return object.toJSONString();
    }
}
