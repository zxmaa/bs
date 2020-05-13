<%--
  主页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/jquery-color-2.1.2.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/user/fore_home.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_home.css" rel="stylesheet"/>
    <title>网上购物智能商城</title>
</head>
<body>
<%--最顶端导航登录的那一条--%>
<%@ include file="include/navigator.jsp" %>
<%--首页搜索导航--%>
<nav class="navbar navbar-default " id="nav_mall">
    <div class="container nav_contain">
        <div class="navbar-header">
            <button type="button" id="coll_btn" class="navbar-toggle collapsed " data-toggle="collapse" data-target="#nav_search" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/HomeLogoA.png" width="240px" height:="130px">
            </a>
        </div>
        <div class="collapse navbar-collapse mallSearch" id="nav_search">
            <form class="navbar-form form_search" action="${pageContext.request.contextPath}/product" method="get">
                <div class="form-group form_div">
                    <input type="text" name="productName"  class="form-control form_input" placeholder="搜索 商品/品牌/店铺"><button type="submit" class="btn btn-default nav_btn">搜索</button>
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <c:if test="${i.index<9}">
                        <li><a href="${pageContext.request.contextPath}/product?categoryId=${category.categoryId}"
                                <c:if test="${i.index % 2 != 0}"> style="color: #FF0036"</c:if>>${fn:substring(category.categoryName,0,fn:indexOf(category.categoryName,' /'))}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<nav class="navbar navbar-default  " id="nav_all" role="navigation">
    <div class="container" id="home_nav">
        <div class="navbar-header nav_myhead">
            <button type="button" class="navbar-toggle collapsed link_btn" data-toggle="collapse" data-target="#head_nav" aria-expanded="false" aria-controls="head_nav">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="dropdown home_nav_title ">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/header_nav_title.png">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">商品分类<span class="caret"></span></a>
                <ul class="dropdown-menu banner_nav" style="min-width: 170px;">
                    <c:forEach items="${requestScope.categoryList}" var="category">
                        <li data-toggle="${category.categoryId}" data-status="" >
                            <%--<img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/small/${category.categoryId}.png">--%>
                            <a href="${pageContext.request.contextPath}/product?categoryId=${category.categoryId}"><img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/small/${category.categoryId}.png">
                                    ${category.categoryName}
                            </a>
                            <div class="banner_div" name="${category.categoryName}">
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="collapse navbar-collapse nav_col" id="head_nav" aria-expanded="false">
            <ul class="nav navbar-nav li_nav">
                <li><a href="https://chaoshi.tmall.com/" target="_blank" class="m_img">
                    <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/TB1ztBlaMMPMeJjy1XbXXcwxVXa-200-60.png" width="100px" height="30px">
                    <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li><a href="https://www.tmall.hk/" target="_blank" class="m_img">
                    <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/TB1t5ObaBxRMKJjy0FdXXaifFXa-200-60.png" width="100px" height="30px"></a>
                </li>
                <li><a href="http://vip.tmall.com/" target="_blank">商城会员</a></li>
                <li><a href="https://3c.tmall.com/" target="_blank">电器城</a></li>
                <li><a href="https://miao.tmall.com/" target="_blank">生鲜馆</a></li>
                <li><a href="http://yao.tmall.com/" target="_blank">医药馆</a></li>
                <li><a href="http://wt.tmall.com/" target="_blank">营业厅</a></li>
                <li><a href="https://meilihui.tmall.com/" target="_blank">魅力惠</a></li>
                <li><a href="https://www.alitrip.com/" target="_blank">飞猪旅行</a></li>
                <li><a href="https://suning.tmall.com/" target="_blank">苏宁易购</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 轮播图展示活动产品 -->
<div id="banner_slider" class="carousel slide" data-ride="carousel">
    <!-- 指示器 -->
    <ol class="carousel-indicators ban_sli">
        <li data-target="#banner_slider" data-slide-to="0" class="active" id="slider_1"></li>
        <li data-target="#banner_slider" data-slide-to="1" id="slider_2"></li>
        <li data-target="#banner_slider" data-slide-to="2" id="slider_3"></li>
        <li data-target="#banner_slider" data-slide-to="3" id="slider_4"></li>
        <li data-target="#banner_slider" data-slide-to="4" id="slider_5"></li>
        <li data-target="#banner_slider" data-slide-to="5" id="slider_6"></li>
    </ol>
    <!-- 滑动内容 -->
    <div class="carousel-inner inner_img" role="listbox" >
        <div class="item active">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/banner/42.jpg" alt="...">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/banner/43.jpg" alt="...">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/banner/44.jpg" alt="...">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/banner/45.jpg" alt="...">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/banner/46.jpg" alt="...">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/banner/47.jpg" alt="...">
        </div>
    </div>
</div>
<div class="container" id="mycontainer">
    <div class="banner_do">
        <div class="banner_goods">
            <c:forEach items="${requestScope.productList}" var="categoryAndProduct">
                <c:if test="${fn:length(categoryAndProduct.foreProductSimpleDtos)>0}">
                    <div class="row banner_goods_type">
                        <div class="banner_goods_title">
                            <span></span>
                            <p>${categoryAndProduct.categoryName}</p>
                        </div>
                            <%--每个分类显示的大图片，由category来显示--%>
                        <%--<a href="${pageContext.request.contextPath}/product?categoryId=${categoryAndProduct.categoryId}">--%>
                            <%--<img class="banner_goods_show"--%>
                                <%--src="res/img/user/WebsiteImage/show/${categoryAndProduct.categoryId}.jpg">--%>
                        <%--</a>--%>
                        <div class="row">
                            <div class="col-md-12 col-sm-6 col-xs-12 banner_goods_items">
                                <c:forEach items="${categoryAndProduct.foreProductSimpleDtos}" var="product" varStatus="i">
                                    <c:if test="${i.index<8}">
                                        <div class="banner_goods_item ">
                                            <a href="product/${product.productId}" class="goods_link"></a>
                                            <img src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${product.productImageSrc}">
                                            <a href="product/${product.productId}"
                                               class="goods_name">${product.productName}</a>
                                            <span class="goods_price">￥${product.productSalePrice}</span>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="row">
            <div class="col-lg-12  endDiv"></div>
        </div>
    </div>
</div>
<%@ include file="include/footer_two.jsp" %>
<%@ include file="include/footer.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
