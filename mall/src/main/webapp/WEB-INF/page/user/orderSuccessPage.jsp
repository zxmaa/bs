<%--
 确认收货后的交易成功页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/css/user/fore_orderSuccessPage.css" rel="stylesheet"/>
    <title>交易成功</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
</nav>
<div class="header">
    <div id="mallLogo">
        <a href="${pageContext.request.contextPath}"><img
                src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoA.png"></a>
    </div>
    <div class="shopSearchHeader">
        <form action="${pageContext.request.contextPath}/product" method="get">
            <div class="shopSearchInput">
                <input type="text" class="searchInput" name="productName" placeholder="搜索 商品/品牌/店铺"
                       maxlength="50">
                <span class="input-group-btn">
                        <input type="submit" value="搜 索" class="searchBtn">
                </span>
            </div>
        </form>
    </div>
</div>
<div class="content">
    <div class="take-delivery">
        <div class="summary-status">
            <h2>交易已经成功，卖家将收到您的货款。</h2>
            <c:if test="${requestScope.orderSuccessInfo.product != null}">
                <div class="successInfo">
                    <ul class="info-rate-coin">
                        <li>
                            <span class="review_msg">认真填写商品评价，就有机会获得20点购物达人经验值！</span>
                            <a class="J_makePoint"
                               href="${pageContext.request.contextPath}/review/${requestScope.orderSuccessInfo.productOrderItem.productOrderItemId}">
                                <img src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${requestScope.orderSuccessInfo.productImage.productImageSrc}"
                                     width="100px" height="100px"/>
                                <p class="product_name"
                                   title="${requestScope.orderSuccessInfo.product.productName}">${requestScope.orderSuccessInfo.product.productName}</p>
                                <span class="vi-btn">立即评价</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </c:if>
            <p>您可以查看：<a href="${pageContext.request.contextPath}/order/1/10">已买到的宝贝</a></p>
        </div>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
