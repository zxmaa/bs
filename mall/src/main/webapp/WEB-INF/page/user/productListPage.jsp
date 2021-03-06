<%-- 商品列表 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/user/fore_productList.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_productList.css" rel="stylesheet">
<title>
    <c:choose>
        <c:when test="${requestScope.searchValue != null}">${requestScope.searchValue}</c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${requestScope.productPageInfo.list != null && fn:length(requestScope.productPageInfo.list)>0}">
                    ${requestScope.productPageInfo.list[0].productCategoryId}
                </c:when>
                <c:otherwise>没找到相关商品</c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>-网上购物智能商城
</title>
</head>
<body>
<nav class="navbar navbar-default " id="nav_product">
    <%@ include file="include/navigator.jsp" %>
    <div class="container header">
        <div class="navbar-header " id="mallLogo">
            <button type="button" id="coll_btn" class="navbar-toggle collapsed " data-toggle="collapse" data-target="#nav_prosearch" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoA.png">
            </a>
        </div>
        <div class="collapse navbar-collapse shopSearchHeader" id="nav_prosearch">
            <form class="navbar-form" action="${pageContext.request.contextPath}/product" method="get">
                <div class="form-group shopSearchInput">
                    <input type="text" class="form-control searchInput" name="productName" placeholder="搜索 商品/品牌/店铺"
                           value="${requestScope.searchValue}" maxlength="50">
                    <input type="submit" value="搜 索" class="btn searchBtn">
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
<div class="context">
    <c:choose>
        <c:when test="${requestScope.productPageInfo.list != null && fn:length(requestScope.productPageInfo.list)>0}">
            <div class="context_menu">
                <ul <c:choose>
                    <c:when test="${requestScope.searchValue != null}"> data-value="${requestScope.searchValue}"</c:when>
                    <c:otherwise>data-type = ${requestScope.searchType}</c:otherwise>
                </c:choose>>
                    <li data-name="product_name"
                        <c:if test="${requestScope.orderBy =='productName' || requestScope.orderBy ==null}">class="orderBySelect"</c:if>>
                        <span>综合</span>
                        <span class="orderByAsc"></span>
                    </li>
                    <li data-name="product_create_date"
                        <c:if test="${requestScope.orderBy =='productCreateDate'}">class="orderBySelect"</c:if>>
                        <span>新品</span>
                        <span class="orderByAsc"></span>
                    </li>
                    <li data-name="product_sale_count"
                        <c:if test="${requestScope.orderBy =='productSaleCount'}">class="orderBySelect"</c:if>>
                        <span>销量</span>
                        <span class="orderByAsc"></span>
                    </li>
                    <li data-name="product_sale_price"
                        <c:if test="${requestScope.orderBy =='productSalePrice'}">class="orderBySelect"</c:if>>
                        <span style="position: relative;left: 3px">价格</span>
                        <span class="orderByDesc <c:if test="${requestScope.isDesc == true}">orderBySelect</c:if>"
                              style="bottom: 5px; left: 6px;"></span>
                        <span class="orderByAsc <c:if test="${requestScope.isDesc == false}">orderBySelect</c:if>"
                              style="top:4px;right: 5px;"></span>
                    </li>
                </ul>
            </div>
            <div class="context_main">
                <c:forEach items="${requestScope.productPageInfo.list}" var="product">
                    <div class="context_productStyle">
                        <div class="context_product">
                            <a href="${pageContext.request.contextPath}/product/${product.productId}" target="_blank">
                                <img class="context_product_imgMain" src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${product.previewPicture[0].productImageSrc}"/>
                            </a>
                            <ul class="context_product_imgList">
                                <c:forEach items="${product.previewPicture}" var="img">
                                    <li>
                                        <img src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${img.productImageSrc}"/>
                                    </li>
                                </c:forEach>
                            </ul>
                            <p class="context_product_price"><span>¥</span>${product.productSalePrice}</p>
                            <p class="context_product_name"><a href="/mall/product/${product.productId}"
                                                               target="_blank">${product.productName}</a></p>
                            <p class="context_product_shop"><span>${product.categoryName}旗舰店</span>
                            </p>
                            <p class="context_product_status">
                                <span class="status_left">总成交<em><c:choose><c:when
                                        test="${product.productSaleCount != null}">${product.productSaleCount}</c:when><c:otherwise>0</c:otherwise></c:choose>笔</em></span>
                                <span class="status_middle">评价<em>${product.productReviewCount}</em></span>
                                <span class="status_right">
                                    <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/T11lggFoXcXXc1v.nr-93-93.png"/></span>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="error">
                <h2>没找到与“${requestScope.searchValue}”相关的商品哦，要不您换个关键词我帮您再找找看</h2>
                <h3>建议您：</h3>
                <ol>
                    <li>看看输入的文字是否有误</li>
                    <li>调整关键词，如“全铜花洒套件”改成“花洒”或“花洒 套件”</li>
                    <li>
                        <form action="${pageContext.request.contextPath}/product" method="get">
                            <input title="查询产品" type="text" class="errorInput" name="product_name"
                                   value="${requestScope.searchValue}">
                            <input type="submit" value="去搜索" class="errorBtn">
                        </form>
                    </li>
                </ol>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="include/footer_two.jsp" %>
<%@ include file="include/footer.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
