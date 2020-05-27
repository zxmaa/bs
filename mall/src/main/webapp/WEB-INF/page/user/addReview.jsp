<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/css/user/fore_addReview.css" rel="stylesheet"/>
    <title>添加评论</title>
    <script>
        $(function () {
            $("#review_form").submit(function () {
                var text = $.trim($("#text-review").val());
                if (text === "") {
                    $(this).css("border", "1px solid #FF0036");
                    return false;
                }
            });
            $("#text-review").focus(function () {
                $("#review_form").css("border", "1px solid #d1ccc8");
            });
        });
    </script>
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
            <div class="input-group shopSearchInput">
                <input type="text" class="searchInput" name="productName" placeholder="搜索 商品/品牌/店铺"
                       maxlength="50">
                <span class="input-group-btn">
                        <input type="submit" value="搜 索" class="searchBtn">
                </span>
            </div>
        </form>
    </div>
</div>
<div class="container content">
    <div class="details_box">
        <div class="row">
            <div class="col-md-5 col-xs-12 db-showpanel">
                <a href="${pageContext.request.contextPath}/product/${reviewPageInfo.product.productId}" target="_blank">
                    <img src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${reviewPageInfo.productImage.productImageSrc}">
                </a>
            </div>
            <div class="col-md-6 col-xs-12 db-icbu">
                <ol class="ui-form-bd">
                    <li class="ui-form-row"><h3>${reviewPageInfo.product.productName}</h3></li>
                    <li class="ui-form-row superstar-price">
                        <label class="ui-form-label">价格</label>
                        <div class="ui-form_right">
                            <em>${reviewPageInfo.product.productSalePrice}</em>
                            <span>元</span>
                        </div>
                    </li>
                    <li class="ui-from-row">
                        <label class="ui-form-label">配送</label>
                        <div class="ui-form_right"><span class="ul_right_special">快递：0.00</span></div>
                    </li>
                </ol>
            </div>
        </div>
        <div class="banner-totalevolute">
            <div class="tv-leftbox">
                <div class="tv-lb-head"></div>
                <div class="tv-lb-content">
                    <span>累计评价</span>
                    <em class="superstar-ratetotal">${reviewPageInfo.product.productReviewCount}</em>
                </div>
                <div class="tv-lb-bottom"></div>
            </div>
        </div>
        <div class="rate-compose">
            <form method="post" action="${pageContext.request.contextPath}/review" id="review_form">
                <input type="hidden" class="orderItemId" value="${reviewPageInfo.productOrderItem.productOrderItemId}" name="orderItemId">
                <div class="compose-main">
                    <div class="compose-header">
                        <span>其他买家，也需要你的建议哦！</span>
                    </div>
                    <div class="compose-order">
                        <div class="J_rateInputArea">评价商品</div>
                        <div class="text-input-box">
                            <textarea id="text-review" name="reviewConent"></textarea>
                        </div>
                    </div>
                </div>
                <div class="compose-submit">
                    <input type="submit" value="提交评价"/>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
