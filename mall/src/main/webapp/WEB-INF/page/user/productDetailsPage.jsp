<%-- 商品详情 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/css/user/fore_foot_two.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/user/fore_login.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/user/fore_productDetails.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_productDetails.css" rel="stylesheet">
    <title>${requestScope.product.product.productName}-网上购物商城</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <a href="${pageContext.request.contextPath}" class="mall_logo">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoB.png">
        </a>
        <span class="shopNameHeader">${requestScope.product.category.categoryName}官方旗舰店</span>
        <input id="tid" type="hidden" value="${requestScope.product.category.categoryId}"/>
        <img style="padding-left: 10px" src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/detailsHeaderA.png" class="shopAssessHeader">
        <div class="shopSearchHeader">
            <form action="${pageContext.request.contextPath}/product" method="get">
                <div class="input-group input_g">
                    <input type="text" class="form-control search_input" name="productName" value="${requestScope.searchValue}" placeholder="搜索 商品/品牌/店铺">
                    <div class="input-group-btn group_btn">
                        <button class="btn btn-default btn_mall" type="submit">搜商品</button>
                        <button class="btn btn-default btn_store" type="submit">搜本店</button>
                    </div>
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li>
                        <a href="${pageContext.request.contextPath}/product?categoryId=${category.categoryId}">${category.categoryName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="loginModel">
    <div class="loginDiv">
        <div class="loginDivHeader">
            <a href="javascript:void(0)" class="closeLoginDiv"></a>
        </div>
        <div class="loginSwitch" id="loginSwitch"></div>
        <div class="loginMessage">
            <div class="loginMessageMain">
                <div class="poptip-arrow"><em></em><span></span></div>
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/scan-safe.png"/><span>扫码登录更安全</span>
            </div>
        </div>
        <div class="pwdLogin">
            <span class="loginTitle">密码登录</span>
            <form method="post" class="loginForm">
                <div class="loginInputDiv">
                    <label for="name" class="loginLabel">
                        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/2018-04-27_235518.png"
                            width="38px" height="39px" title="会员名"/></label>
                    <input type="text" name="name" id="name" class="loginInput" placeholder="会员名/邮箱/手机号">
                </div>
                <div class="loginInputDiv">
                    <label for="password" class="loginLabel">
                        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/2018-04-27_235533.png"
                            width="38px" height="39px" title="登录密码"/>
                    </label>
                    <input type="password" name="password" id="password" class="loginInput">
                </div>
                <input type="submit" class="loginButton" value="登 录">
            </form>
            <div class="loginLinks">
                <a href="#">忘记密码</a>
                <a href="#">忘记会员名</a>
                <a href="${pageContext.request.contextPath}/register" target="_blank">免费注册</a>
            </div>
            <div class="error_message">
                <p id="error_message_p"></p>
            </div>
        </div>
        <div class="qrcodeLogin">
            <span class="loginTitle">手机扫码，安全登录</span>
            <div class="qrcodeMain">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/login_qrcode.png"
                     id="qrcodeA"/>
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/login_qrcodeB.png"
                     id="qrcodeB"/>
            </div>
            <div class="qrcodeFooter">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/scan_icon2.png">
                <p>打开 <a href="https://www.tmall.com/wow/portal/act/app-download">手机天猫</a> | <a
                        href="https://www.taobao.com/m">手机淘宝</a>扫一扫登录</p>
            </div>
            <div class="loginLinks">
                <a href="JavaScript:void(0)" id="pwdLogin">密码登录</a>
                <a href="${pageContext.request.contextPath}/register" target="_blank">免费注册</a>
            </div>
        </div>
    </div>
</div>
<div class="shopImg">
    <img  style="width: 100%" src="${pageContext.request.contextPath}/res/img/item/categoryPicture/${requestScope.product.category.categoryImageSrc}">
</div>
<div class="context container-fluid" id="context_container">
    <div class="row">
        <div class="context_left col-xs-12 col-md-5">
            <div class="context_img_ks">
                <img src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${requestScope.product.previewPicture[0].productImageSrc}"
                     width="800px" height="800px" class="img-responsive">
            </div>
            <div class="context_img">
                <img src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${requestScope.product.previewPicture[0].productImageSrc}"
                     class="context_img_main" width="400px" height="400px"/>
                <div class="context_img_winSelector"></div>
            </div>
            <ul class="context_img_ul">
                <c:forEach var="img" items="${requestScope.product.previewPicture}">
                    <li class="context_img_li"><img
                            src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${img.productImageSrc}"/>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="context_info col-xs-12 col-md-6">
            <div class="context_info_name_div">
                <p class="context_info_name">${requestScope.product.product.productName}</p><span class="context_info_title">${requestScope.product.product.productTitle}</span>
            </div>
            <div class="context_info_main">
                <div class="context_info_main_ad">
                    <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/context_ad.png">
                    <span>全网实物商品通用</span>
                    <a href="#">去领券
                        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallItemContentB.png"></a>
                </div>
                <dl class="context_price_panel">
                    <dt>价格</dt>
                    <dd><em>¥</em><span>${requestScope.product.product.productPrice}0</span></dd>
                </dl>
                <dl class="context_promotePrice_panel">
                    <dt>促销价</dt>
                    <dd><em>¥</em><span>${requestScope.product.product.productSalePrice}0</span></dd>
                </dl>
            </div>
            <ul class="context_other_panel">
                <li>总销量<span><c:choose>
                    <c:when test="${requestScope.product.product.productSaleCount != null}">
                        ${requestScope.product.product.productSaleCount}
                    </c:when>
                    <c:otherwise>0</c:otherwise>
                </c:choose></span>
                </li>
                <li>累计评价<span>${requestScope.product.product.productReviewCount}</span></li>
                <li class="tmall_points">送天猫积分<span><fmt:formatNumber type="number"
                                                                      value="${requestScope.product.product.productSalePrice/10}"
                                                                      maxFractionDigits="0"/></span></li>
            </ul>
            <dl class="context_info_member">
                <dt>数量</dt>
                <dd>
                    <input type="text" value="1" maxlength="8" title="请输入购买量" class="context_buymember">
                    <input type="hidden" id="stock" value="1000">
                    <span class="amount-btn">
                        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/up.png"
                             class="amount_value_up">
                        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/down.png"
                             class="amount_value-down">
                    </span>
                    <span class="amount_unit">件</span>
                    <em>库存${requestScope.product.product.productQuantity}件</em>
                </dd>
            </dl>
            <div class="context_buy">
                <script>
                    $(function () {
                        //点击购买按钮时
                        $(".context_buy_form").submit(function () {
                            if ('${sessionScope.userName}' === "") {//未登录不能购买
                                $(".loginModel").show();
                                $(".loginDiv").show();
                                return false;
                            }
                            var number = isNaN($.trim($(".context_buymember").val()));
                            if (number) {
                                location.reload();
                            } else {
                                location.href = "${pageContext.request.contextPath}/order/create/${requestScope.product.product.productId}?productNumber=" + $.trim($(".context_buymember").val());
                            }
                            return false;
                        });
                        //点击加入购物车按钮时
                        $(".context_buyCar_form").submit(function () {
                            if ('${sessionScope.userName}' === "") {//未登录不能加入购物车
                                $(".loginModel").show();
                                $(".loginDiv").show();
                                return false;
                            }
                            var number = isNaN($.trim($(".context_buymember").val()));//NAN：非数字值的特殊值
                            if (number) {
                                location.reload();
                            } else {
                                $.ajax({
                                    url: "${pageContext.request.contextPath}/orderItem/create/${requestScope.product.product.productId}?productNumber=" + $.trim($(".context_buymember").val()),
                                    type: "POST",
                                    data: {"productNumber": number},
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.success) {
                                            $(".msg").stop(true, true).animate({//显示已加入购物车
                                                opacity: 1
                                            }, 550, function () {
                                                $(".msg").animate({
                                                    opacity: 0
                                                }, 1500);
                                            });
                                        } else {
                                            if (data.url != null) {
                                                location.href = "/mall" + data.url;
                                            } else {
                                                alert("加入购物车失败，请稍后再试！");
                                            }
                                        }
                                    },
                                    beforeSend: function () {
                                    },
                                    error: function () {
                                        alert("加入购物车失败，请稍后再试！");
                                    }
                                });
                                return false;
                            }
                        });
                    });
                </script>
                <form method="get" class="context_buy_form">
                    <input class="context_buyNow" type="submit" value="立即购买"/>
                </form>
                <form method="get" class="context_buyCar_form">
                    <input class="context_addShopCart" type="submit" value="加入购物车"/>
                </form>
            </div>
            <div class="context_clear">
                <span>服务承诺</span>
                <a href="#">正品保证</a>
                <a href="#">极速退款</a>
                <a href="#">七天无理由退换</a>
            </div>
        </div>
    </div>
</div>
<%--商品推荐--%>
<div class="context_ul ">
    <div class="context_ul_head">
        <s></s>
        <span>猜你喜欢</span>
    </div>
    <div class="context_ul_goodsList">
        <ul class="list-inline">
            <c:forEach items="${requestScope.guessLikeList}" var="product">
                <li class="context_ul_main">
                    <div class="context_ul_img">
                        <a href="/mall/product/${product.productId}">
                            <img src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${product.productImageSrc}">
                        </a>
                        <p>¥${product.productSalePrice}0</p>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <input type="hidden" id="guessNumber" value="${requestScope.guessNumber}">
    </div>
</div>
<div class="mainwrap">
    <div class="P_TabBarBox">
        <ul>
            <li class="P_GoodsDetails">
                <a href="javascript:void(0)" class="detailsClick" onclick="getDetailsPage(this,'P_details')">商品详情</a>
            </li>
            <li class="P_GoodsReviews">
                <a href="javascript:void(0)"
                   onclick="getDetailsPage(this,'P_reviews')">累计评价<span>${requestScope.reviewRes.count}</span></a>
            </li>
        </ul>
    </div>
    <div class="P_choose">
        <%@include file="include/P_details.jsp" %>
        <%@include file="include/P_review.jsp" %>
    </div>
    <div class="P_img">
        <c:forEach items="${requestScope.product.detailPicture}" var="image">
            <img src="${pageContext.request.contextPath}/res/img/item/productDetailsPicture/${image.productImageSrc}"/>
        </c:forEach>
    </div>
</div>
<div class="msg" >
    <span>商品已添加</span>
</div>
<%@ include file="include/footer_two.jsp" %>
<%@ include file="include/footer.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
