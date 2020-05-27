<%-- 购物车 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/user/fore_productShopCart.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_productShopCartPage.css" rel="stylesheet"/>
    <title>购物车</title>
    <script>
        $(function () {
            $('#btn-ok').click(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/orderItem/" + $("#order_id_hidden").val(),
                    type: "DELETE",
                    data: null,
                    dataType: "json",
                    success: function (data) {
                        if (data.success !== true) {
                            alert("购物车商品删除异常，请稍候再试！");
                        }
                        location.href = "/mall/cart";
                    },
                    beforeSend: function () {
                    },
                    error: function () {
                        alert("购物车产品删除异常，请稍后再试！");
                        location.href = "/mall/cart";
                    }
                });
            });
        });

        function removeItem(orderItem_id) {//删除一条购物车商品信息
            if (isNaN(orderItem_id) || orderItem_id === null) {
                return;
            }
            $("#order_id_hidden").val(orderItem_id);
            $('#modalDiv').modal();//模态框提示是否删除
        }
    </script>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}"><img
                    src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoA.png"><span
                    class="span_tmallBuyCar">购物车</span></a>
        </div>
        <div class="shopSearchHeader">
            <form action="${pageContext.request.contextPath}/product" method="get">
                <div class="input-group shopSearchInput">
                    <input type="text" class="searchInput" name="productName" placeholder="搜索 商品/品牌/店铺"
                           value="${requestScope.searchValue}" maxlength="50">
                    <span class="input-group-btn">
                        <input type="submit" value="搜 索" class="searchBtn">
                    </span>
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li>
                        <a href="${pageContext.request.contextPath}/product?category_id=${category.category_id}">${category.category_name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="content">
    <c:choose>
        <c:when test="${fn:length(requestScope.orderItemList)<=0}">
            <div id="crumbs">
                <span class="cart-tip">购物车帮您一次性完成批量购买与付款，下单更便捷，付款更简单！<a
                        href="http://service.taobao.com/support/help-11746.htm?spm=a1z0d.1.0.0.ogEwpx" target="_blank">如何使用购物车</a></span>
            </div>
            <div id="empty">
                <h2>您的购物车还是空的，赶紧行动吧！您可以：</h2>
                <ul>
                    <li>看看<a href="${pageContext.request.contextPath}/order">已买到的宝贝</a></li>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <div id="J_FilterBar">
                <ul id="J_CartSwitch">
                    <li>
                        <a href="${pageContext.request.contextPath}/cart" class="J_MakePoint">
                            <em>全部商品</em>
                            <span class="number">${requestScope.orderItemNumber}</span>
                        </a>
                    </li>
                </ul>
                <div class="cart-sum">
                    <span class="pay-text">已选商品（不含运费）</span>
                    <strong class="price"><em id="J_SmallTotal"><span
                            class="total-symbol">&nbsp;</span>0.00</em></strong>
                    <a id="J_SmallSubmit" class="submit-btn submit-btn-disabled">结&nbsp;算</a>
                </div>
                <div class="wrap-line">
                    <div class="floater"></div>
                </div>
            </div>
            <table id="J_CartMain">
                <thead>
                <tr>
                    <th class="selectAll_th"><input type="checkbox" class="cbx_select" id="cbx_select_all"><label
                            for="cbx_select_all">全选</label></th>
                    <th width="150px" class="productInfo_th"><span>商品信息</span></th>
                    <th width="120px"><span>单价</span></th>
                    <th width="120px"><span>数量</span></th>
                    <th width="120px"><span>金额</span></th>
                    <th width="84px"><span>操作</span></th>
                    <th hidden>ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.orderItemList}" var="orderItem">
                    <tr class="orderItem_category">
                        <td colspan="6"><span class="shop_logo"></span><span
                                class="category_shop">店铺：${orderItem.categoryName}旗舰店</span>
                        </td>
                    </tr>
                    <tr class="orderItem_info">
                        <td class="tbody_checkbox">
                            <input type="checkbox" class="cbx_select" id="cbx_orderItem_select_${orderItem.productOrderItem.productOrderItemId}" name="orderItem_id">
                            <label for="cbx_orderItem_select_${orderItem.productOrderItem.productOrderItemId}"></label>
                        </td>
                        <td><img class="orderItem_product_image" src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${orderItem.productImage.productImageSrc}" style="width: 80px;height: 80px;"/>
                            <span class="orderItem_product_name">
                                <a href="${pageContext.request.contextPath}/product/${orderItem.product.productId}">${orderItem.product.productName}</a>
                            </span>
                        </td>
                        <td>
                            <span class="orderItem_product_price">￥${orderItem.product.productSalePrice}</span>
                        </td>
                        <td>
                            <div class="item_amount">
                                <a href="javascript:void(0)" onclick="up(this)" class="J_Minus <c:if test="${orderItem.productOrderItem.productOrderItemNumber<=1}">no_minus</c:if>">-</a>
                                <input type="text" value="${orderItem.productOrderItem.productOrderItemNumber}"/>
                                <a href="javascript:void(0)" onclick="down(this)" class="J_Plus">+</a>
                            </div>
                        </td>
                        <td>
                            <span class="orderItem_product_realPrice">￥${orderItem.productOrderItem.productOrderItemPrice}</span>
                        </td>
                        <td>
                            <a href="javascript:void(0)" onclick="removeItem('${orderItem.productOrderItem.productOrderItemId}')" class="remove_order">删除</a>
                        </td>
                        <td>
                            <input type="hidden" class="input_orderItem" name="${orderItem.productOrderItem.productOrderItemId}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div id="J_FloatBar" class="row">
                <div id="J_SelectAll2" class="col-md-1 col-xs-4">
                    <div class="cart_checkbox">
                        <input class="J_checkboxShop" id="J_SelectAllCbx2" type="checkbox" value="true"/>
                        <label for="J_SelectAllCbx2" title="勾选购物车内所有商品"></label>
                    </div>
                    <span class="span_selectAll">&nbsp;全选</span>
                </div>
                <div class="col-md-11 col-xs-8">
                    <div class="row float-bar-right ">
                        <div id="J_ShowSelectedItems" class="col-md-1 col-md-offset-7">
                            <span class="txt">已选商品</span>
                            <em id="J_SelectedItemsCount">0</em>
                            <span class="txt">件</span>
                        </div>
                        <div class="price_sum col-md-2">
                            <span class="txt">合计（不含运费）:</span>
                            <strong class="price">
                                <em id="J_Total">
                                    <span class="total_symbol">&nbsp;  ￥</span>
                                    <span class="total_value"> 0.00</span>
                                </em>
                            </strong>
                        </div>
                        <div class="btn_area col-md-1">
                            <a href="javascript:void(0)" id="J_Go" onclick="create(this)">
                                <span>结&nbsp;算</span>
                            </a>
                        </div>
                </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%-- 模态框 --%>
<div class="modal fade" id="modalDiv" tabindex="-1" role="dialog" aria-labelledby="modalDiv" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">您确定要取消该宝贝吗？</div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="btn-ok">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btn-close">关闭</button>
                <input type="hidden" id="order_id_hidden">
            </div>
        </div><%-- /.modal-content --%>
    </div><%-- /.modal --%>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
