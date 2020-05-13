package com.bs.mall.service.fore.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.*;
import com.bs.mall.dao.pojo.*;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeCreateOrderByListReqDto;
import com.bs.mall.dto.req.ForeCreateOrderByListSimpleReqDto;
import com.bs.mall.dto.req.ForeCreateOrderByOneReqDto;
import com.bs.mall.dto.req.ForeOrderShowReqDto;
import com.bs.mall.dto.res.*;
import com.bs.mall.service.fore.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductOrderServiceImpl implements IProductOrderService {

    @Autowired
    private IAddressService addressService;
    @Autowired
    private IUserAddressService userAddressService;
    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductOrderItemService productOrderItemService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IReviewService reviewService;
    @Autowired
    private IUserService userService;

    /**
     * 生成订单：用于立即购买时，生成订单
     * @param createOrderByOneReqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public ForeCreateOrderResDto createOrderByOne(ForeCreateOrderByOneReqDto createOrderByOneReqDto) {
        ForeCreateOrderResDto result = new ForeCreateOrderResDto();
        //得到product
        Product product = productService.getProductById(createOrderByOneReqDto.getProductId());
        //判断库存是否充足
        if(product.getProductQuantity()<createOrderByOneReqDto.getProductNum()){
            result.setFlag(false);
            result.setProductName(product.getProductName());
            return result;
        }

        //生成订单编码
        StringBuffer productOrderCode = new StringBuffer()
                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .append(RandomStringUtils.randomNumeric(8));
        //得到用户地址的信息
        UserAddress userAddress = userAddressService.getUserAddressById(createOrderByOneReqDto.getUserAddressId());


        ProductOrder productOrder = new ProductOrder();
        productOrder.setUserId(createOrderByOneReqDto.getUserId());
        productOrder.setProductOrderCode(productOrderCode.toString());
        productOrder.setProductOrderAddress(userAddress.getAddressAreaId());
        productOrder.setProductOrderDetailAddress(userAddress.getDetailAddress());
        productOrder.setProductOrderPost(userAddress.getPostCode());
        productOrder.setProductOrderReceiver(userAddress.getReceiver());
        productOrder.setProductOrderMobile(userAddress.getTel());
        productOrder.setProductOrderStatus(0);
        productOrder.setProductOrderReserveDate(new Date());
        productOrderMapper.insert(productOrder);
        //得到刚刚插入的id
        Integer productOrderId = productOrder.getProductOrderId();

        //插入订单项表中
        ProductOrderItem productOrderItem  = new ProductOrderItem();
        productOrderItem.setProductOrderItemNumber(createOrderByOneReqDto.getProductNum());
        productOrderItem.setProductOrderItemPrice(product.getProductSalePrice()*createOrderByOneReqDto.getProductNum());
        productOrderItem.setProductId(createOrderByOneReqDto.getProductId());
        productOrderItem.setProductOrderId(productOrderId);
        productOrderItem.setUserId(createOrderByOneReqDto.getUserId());
        productOrderItem.setProductOrderItemUserMessage(createOrderByOneReqDto.getUserMessage());
        productOrderItem.setProductSinglePrice(product.getProductSalePrice());
        productOrderItemService.addOrderItem(productOrderItem);

        //修改商品的库存量
        Product updateProduct = new Product();
        updateProduct.setProductId(product.getProductId());
        updateProduct.setProductQuantity(product.getProductQuantity()-createOrderByOneReqDto.getProductNum());
        productService.updateProduct(updateProduct);

        result.setFlag(true);
        result.setProductOrderCode(productOrder.getProductOrderCode());
        return result;
    }

    /**
     * 生成订单：用于购物车购买生成订单
     * @param createOrderByListReqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public ForeCreateOrderResDto createOrderByList(ForeCreateOrderByListReqDto createOrderByListReqDto) {
        ForeCreateOrderResDto result = new ForeCreateOrderResDto();
        //购买的商品的List
        List<ForeCreateOrderByListSimpleReqDto> list = createOrderByListReqDto.getCreateOrderByListSimpleReqDtos();

        //判断每个产品的库存是否充足
        for (ForeCreateOrderByListSimpleReqDto createOrderByListSimpleReqDto : list) {
            ProductOrderItem temp = productOrderItemService.selectOrderItemById(createOrderByListSimpleReqDto.getOrderItemId());
            Product product = productService.getProductById(temp.getProductId());
            if(product.getProductQuantity()<temp.getProductOrderItemNumber()){
                result.setFlag(false);
                result.setProductName(product.getProductName());
                return result;
            }

        }

        //生成订单编码
        StringBuffer productOrderCode = new StringBuffer()
                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .append(RandomStringUtils.randomNumeric(8));
        //得到用户地址的信息
        UserAddress userAddress = userAddressService.getUserAddressById(createOrderByListReqDto.getUserAddressId());

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductOrderCode(productOrderCode.toString());
        productOrder.setProductOrderAddress(userAddress.getAddressAreaId());
        productOrder.setProductOrderDetailAddress(userAddress.getDetailAddress());
        productOrder.setProductOrderPost(userAddress.getPostCode());
        productOrder.setProductOrderReceiver(userAddress.getReceiver());
        productOrder.setProductOrderMobile(userAddress.getTel());
        productOrder.setProductOrderStatus(0);
        productOrder.setProductOrderReserveDate(new Date());
        productOrder.setUserId(createOrderByListReqDto.getUserId());
        //1、生成订单
        productOrderMapper.insert(productOrder);
        //得到刚刚插入的id
        Integer productOrderId = productOrder.getProductOrderId();


        ProductOrderItem productOrderItem;
        for (ForeCreateOrderByListSimpleReqDto createOrderByListSimpleReqDto : list) {
            //2、修改订单项
            productOrderItem = new ProductOrderItem();
            productOrderItem.setProductOrderItemId(createOrderByListSimpleReqDto.getOrderItemId());
            productOrderItem.setProductOrderItemUserMessage(createOrderByListSimpleReqDto.getUserMessage());
            productOrderItem.setProductOrderId(productOrderId);
            //在订单项中，设置下订单时，产品的单价
            ProductOrderItem temp = productOrderItemService.selectOrderItemById(createOrderByListSimpleReqDto.getOrderItemId());
            Product product = productService.getProductById(temp.getProductId());
            productOrderItem.setProductSinglePrice(product.getProductSalePrice());
            productOrderItem.setProductOrderItemPrice(product.getProductSalePrice()*temp.getProductOrderItemNumber());
            productOrderItemService.updateOrderItem(productOrderItem);

            //3、修改商品库存
            Product updateProduct = new Product();
            updateProduct.setProductId(product.getProductId());
            updateProduct.setProductQuantity(product.getProductQuantity()-temp.getProductOrderItemNumber());
            productService.updateProduct(updateProduct);
        }



        result.setFlag(true);
        result.setProductOrderCode(productOrder.getProductOrderCode());
        return result;
    }

    @Override
    public ProductOrder getProductOrderByOrderCode(String orderCode) {
        QueryWrapper<ProductOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_code",orderCode);
        return productOrderMapper.selectOne(wrapper);
    }

    /**
     * 根据OrderCode，返回支付页面，需要显示的信息
     * @param orderCode
     * @return
     */
    @Override
    public ForePayResDto getPayPageInfoByOrderCode(String orderCode) {
        ForePayResDto result = new ForePayResDto();
        Double totalPrice = 0.0;
        ProductOrder productOrde = getProductOrderByOrderCode(orderCode);
        List<ProductOrderItem> orderItems = productOrderItemService.getOrderItemsByOrderId(productOrde.getProductOrderId());
       //若该订单，只有一个订单项
        if(orderItems.size()==1){
            Product product = productService.getProductById(orderItems.get(0).getProductId());
            totalPrice +=  orderItems.get(0).getProductOrderItemPrice();
            String categoryName = productService.getCategoryNameByProductId(product.getProductId());

            result.setCategoryName(categoryName);
            result.setProduct(product);
        }else{
            for (ProductOrderItem orderItem : orderItems) {
                totalPrice += orderItem.getProductOrderItemPrice();
            }
        }

        result.setProductOrder(productOrde);
        result.setProductOrderItemList(orderItems);
        result.setTotalPrice(totalPrice);

        return result;
    }

    /**
     * 点击确认支付的操作
     * @param orderCode
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void payOrder(String orderCode) {
        ProductOrder productOrder = getProductOrderByOrderCode(orderCode);
      /*  //得到该订单的订单项
        List<ProductOrderItem> orderItems = productOrderItemService.getOrderItemsByOrderId(productOrder.getProductOrderId());

        //若结算时，产品数量不够，则返回该产品名
        for (ProductOrderItem orderItem : orderItems) {
            Integer productId = orderItem.getProductId();
            Product product = productService.getProductById(productId);
            if(product.getProductQuantity()<orderItem.getProductOrderItemNumber()){
                return product.getProductName();
            }
        }*/

    /*    Product updateProduct;
        for (ProductOrderItem orderItem : orderItems) {
            updateProduct= new Product();
            Integer productId = orderItem.getProductId();
            Product product = productService.getProductById(productId);
            Integer productOrderItemNumber = orderItem.getProductOrderItemNumber();
            updateProduct.setProductId(orderItem.getProductId());
            updateProduct.setProductSaleCount(product.getProductSaleCount()+productOrderItemNumber);
            updateProduct.setProductQuantity(product.getProductQuantity()-productOrderItemNumber);
            productService.updateProduct(product);
        }*/

        //支付后，修改订单状态
        /*ProductOrder updateProductOrder = new ProductOrder();
        updateProductOrder.setProductOrderId(productOrder.getProductOrderId());
        updateProductOrder.setProductOrderPayDate(new Date());
        updateProductOrder.setProductOrderStatus(1);
        productOrderMapper.updateById(updateProductOrder);*/
        updateOrderStatus(orderCode,1);
    }

    /**
     * 根据orderCode，得到支付成功显示的信息
     * @param orderCode
     * @return
     */
    @Override
    public ForePaySuccessResDto getPaySuccessInfoByOrderCode(String orderCode) {
        ForePaySuccessResDto paySuccessResDto = new ForePaySuccessResDto();

        //得到订单
        ProductOrder productOrder = getProductOrderByOrderCode(orderCode);
        //得到该订单的订单项
        List<ProductOrderItem> orderItems = productOrderItemService.getOrderItemsByOrderId(productOrder.getProductOrderId());
        //得到省/市/县
        ForeAddressDetailResDto details = addressService.getDetails(productOrder.getProductOrderAddress());
        Double totalPrice = 0.0;
        for (ProductOrderItem orderItem : orderItems) {
            totalPrice += orderItem.getProductOrderItemPrice();
        }

        paySuccessResDto.setProductOrder(productOrder);
        paySuccessResDto.setAddressDetail(details);
        paySuccessResDto.setTotalPrice(totalPrice);
        return paySuccessResDto;
    }

    /**
     * 更改订单状态
     *
     *      1：已支付,待发货
     *     2:已发货
     *      3：已确认收货（即交易成功）
     *      4：交易关闭（即取消订单）
     * @param orderCode
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void updateOrderStatus(String orderCode,Integer orderStatus) {
        QueryWrapper<ProductOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_code",orderCode);
        ProductOrder prodctOrder = getProductOrderByOrderCode(orderCode);
        List<ProductOrderItem> orderItems = productOrderItemService.getOrderItemsByOrderId(prodctOrder.getProductOrderId());

        ProductOrder productOrder = new ProductOrder();
        //若更改状态为已支付，则需修改用户的积分字段
        if(orderStatus == 1) {
            //积分
            int integral = 0;
            for (ProductOrderItem orderItem : orderItems) {
                integral += orderItem.getProductOrderItemPrice() / 10;
            }
            ForeUserDto user = userService.findUserByUserId(prodctOrder.getUserId());
            User updateUser = new User();
            updateUser.setUserId(prodctOrder.getUserId());
            updateUser.setIntegral(user.getIntegral() + integral);
            userService.updateUser(updateUser);

            //订单状态
            productOrder.setProductOrderStatus(orderStatus);
            productOrder.setProductOrderPayDate(new Date());
            productOrderMapper.update(productOrder,wrapper);
        }

        //已发货：修改订单状态
        if(orderStatus == 2){
            productOrder.setProductOrderStatus(orderStatus);
            productOrder.setProductOrderDeliveryDate(new Date());
            productOrderMapper.update(productOrder,wrapper);
        }

        //确认收货：修改订单状态
        if(orderStatus == 3){
            productOrder.setProductOrderStatus(orderStatus);
            productOrder.setProductOrderConfirmDate(new Date());
            productOrderMapper.update(productOrder,wrapper);
        }

        //若取消订单，则将产品的库存复原
        if(orderStatus == 4){
            for (ProductOrderItem orderItem : orderItems) {
                Product product = productService.getProductById(orderItem.getProductId());
                Product productUpdate = new Product();
                productUpdate.setProductId(product.getProductId());
                productUpdate.setProductQuantity(product.getProductQuantity()+orderItem.getProductOrderItemNumber());
                productService.updateProduct(productUpdate);
            }
            //订单状态
            productOrder.setProductOrderStatus(orderStatus);
            productOrderMapper.update(productOrder,wrapper);
        }
    }



    /**
     * 得到前端确认收货时，跳转到确认收货页，需要显示的信息
     * @param orderCode
     * @return
     */
    @Override
    public ForeConfirmReceiptResDto getConfirmReceiptInfo(String orderCode) {
        ForeConfirmReceiptResDto result = new ForeConfirmReceiptResDto();

        List<ForeOrderItemReceiptResDto> list = new ArrayList<>();
        //得到订单
        ProductOrder productOrder = getProductOrderByOrderCode(orderCode);

        List<ProductOrderItem> orderItems = productOrderItemService.getOrderItemsByOrderId(productOrder.getProductOrderId());
        ForeOrderItemReceiptResDto temp;
        Double totalPrice = 0.0;
        for (ProductOrderItem orderItem : orderItems) {
            temp = new ForeOrderItemReceiptResDto();

            totalPrice += orderItem.getProductOrderItemPrice();
            Product product= productService.getProductById(orderItem.getProductId());
            //得到概述图
            List<ProductImage> productImages = productImageService.getImagesByType(orderItem.getProductId(),0);

            temp.setProductName(product.getProductName());
            temp.setProductImage(productImages.get(0));
            temp.setProductOrderItem(orderItem);
            list.add(temp);
        }
        result.setOrderItemDetails(list);
        result.setProductOrder(productOrder);
        result.setTotalPrice(totalPrice);
        return result;
    }

    /**
     * 订单完成页需显示的内容
     * @param orderCode
     * @return
     */
    @Override
    public ForeOrderSuceessResDto getOrderSuccessInfo(String orderCode) {
        ForeOrderSuceessResDto result = new ForeOrderSuceessResDto();
        Product product = null;
        ProductImage productImage = null;
        ProductOrderItem productOrderItem = null;

        //得到订单
        ProductOrder productOrder = getProductOrderByOrderCode(orderCode);
        List<ProductOrderItem> orderItems = productOrderItemService.getOrderItemsByOrderId(productOrder.getProductOrderId());
        //若该订单只有一个订单项
        if(orderItems.size()==1){
            Integer count = reviewService.getTotalByOrderItemId(orderItems.get(0).getProductOrderItemId());
            //且该订单项还未评价产品
            if(count == 0){
                productOrderItem = orderItems.get(0);
                product = productService.getProductById(productOrderItem.getProductId());
                productImage = productImageService.getImagesByType(productOrderItem.getProductId(),0).get(0);
            }
        }
        result.setProduct(product);
        result.setProductImage(productImage);
        result.setProductOrderItem(productOrderItem);
        return result;
    }

    /**
     * 获取我的订单显示信息
     * @param orderShowReqDto
     * @return
     */
    @Override
    public PageInfo<ForeOrderShowResDto> getOrderShow(ForeOrderShowReqDto orderShowReqDto) {
        PageHelper.startPage(orderShowReqDto.getPageNumber(),orderShowReqDto.getPageSize());
        //得到该用户的所有订单
        List<ProductOrder> orderShowFore = productOrderMapper.getOrderShowFore(orderShowReqDto);

        List<ForeOrderShowResDto> resultList = null;

        //若该用户存在订单：则对订单以及订单详情进行处理
        if(null != orderShowFore){
            resultList = new ArrayList<>();
            ForeOrderShowResDto foreOrderShowResDto = null;
            List<ForeShowOrderItemResDto> orderItemList = null;

            for (ProductOrder productOrder : orderShowFore) {
                //1、设置订单的信息
                foreOrderShowResDto = new ForeOrderShowResDto();
                BeanUtils.copyProperties(productOrder,foreOrderShowResDto);

                //2、设置订单项相关的信息
                orderItemList  = new ArrayList<>();
                ForeShowOrderItemResDto showOrderItemResDto = null;
                List<ProductOrderItem> orderItems = productOrderItemService.getOrderItemsByOrderId(productOrder.getProductOrderId());
                for (ProductOrderItem orderItem : orderItems) {
                    showOrderItemResDto = new ForeShowOrderItemResDto();

                    Product product = productService.getProductById(orderItem.getProductId());
                    Integer count = reviewService.getTotalByOrderItemId(orderItem.getProductOrderItemId());

                    if(count == 0){
                        showOrderItemResDto.setIsReview(false);
                    }else{
                        showOrderItemResDto.setIsReview(true);
                    }
                    showOrderItemResDto.setProductOrderItem(orderItem);
                    showOrderItemResDto.setProduct(product);
                    showOrderItemResDto.setProductImage(productImageService.getImagesByType(orderItem.getProductId(),0).get(0));

                    orderItemList.add(showOrderItemResDto);
                }
                foreOrderShowResDto.setOrderItemList(orderItemList);

                resultList.add(foreOrderShowResDto);

            }
        }

        PageInfo pageInfo = new PageInfo(orderShowFore);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    /**
     * 根据orderId，得到订单信息
     * @param orderId
     * @return
     */
    @Override
    public ProductOrder getProductOrderById(Integer orderId) {
        ProductOrder productOrder = productOrderMapper.selectById(orderId);
        return productOrder;
    }

    /***
     * 根据订单项id,得到需要填写评价页，需要的信息
     * @param orderItemId
     * @return
     */
    @Override
    public ForeWriteReviewResDto getWriteReviewInfo(Integer orderItemId) {
        ForeWriteReviewResDto result = new ForeWriteReviewResDto();

        ProductOrderItem productOrderItem = productOrderItemService.selectOrderItemById(orderItemId);
        Product product = productService.getProductById(productOrderItem.getProductId());
        ProductImage productImage = productImageService.getImagesByType(product.getProductId(), 0).get(0);
        result.setProduct(product);
        result.setProductOrderItem(productOrderItem);
        result.setProductImage(productImage);
        return result;
    }


}
