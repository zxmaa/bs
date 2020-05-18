<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  页面导航
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/user/fore_nav.css"/>
    <script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
    <title>页面导航</title>
</head>
<body>
<nav class="navbar navbar-default " id="nav_main">
    <div class="container " id="nav_container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed nav_button" data-toggle="collapse" data-target="#nav_head" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <p id="container_login" >
                <c:choose>
                    <c:when test="${sessionScope.userName==null}">
                        <em>Hi，欢迎来购物</em>
                        <a href="${pageContext.request.contextPath}/login">请登录</a>
                        <a href="${pageContext.request.contextPath}/register">免费注册</a>
                    </c:when>
                    <c:otherwise>
                        <em>Hi，</em>
                        <a href="${pageContext.request.contextPath}/userDetails" class="userName"
                           target="_blank">${sessionScope.userName}</a>
                        <a href="${pageContext.request.contextPath}/login/logout">退出</a>
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
        <div class="collapse navbar-collapse" id="nav_head">
            <ul class="nav navbar-nav navbar-right nav_right">
                <li class="dropdown quick_li_MyTaobao">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的淘宝<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/userDetails">个人中心</a></li>
                        <li><a href="${pageContext.request.contextPath}/order/1/10">我的订单</a></li>
                    </ul>
                </li>
                <li class="quick_li_cart">
                    <a href="${pageContext.request.contextPath}/cart">购物车</a>
                </li>
                <li class="dropdown quick_li_menuItem">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">收藏夹 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">收藏的宝贝</a></li>
                        <li><a href="#">收藏的店铺</a></li>
                    </ul>
                </li>
                <li class="quick_li_separator"></li>
                <li class="quick_li_address">
                    <a href="${pageContext.request.contextPath}/userAddress">地址管理</a>
                </li>
                <li class="quick_home"><a href="${pageContext.request.contextPath}">淘宝网</a></li>
                <li class="dropdown quick_DirectPromo">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">商家支持 <span class="caret"></span></a>
                    <ul class="dropdown-menu list-inline">
                        <p class="dropdown-header">商家：</p>
                        <li><a href="">商家中心</a></li>
                        <li><a href="">商家入驻</a></li>
                        <li><a href="">运营服务</a></li>
                        <li><a href="">商家品控</a></li>
                        <li><a href="">商家工具</a></li>
                        <li><a href="">天猫智库</a></li>
                        <li><a href="">喵言喵语</a></li>
                        <p class="dropdown-header">帮助：</p>
                        <li><a href="">帮助中心</a></li>
                        <li><a href="">问商友</a></li>
                    </ul>
                </li>
                <li class="dropdown quick_sitemap">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">网站导航 <span class="caret"></span></a>
                    <ul class="dropdown-menu list-inline" style="min-width:250px">
                        <p class="dropdown-header">热点推荐Hot</p>
                        <li><a href="">天猫超市</a></li>
                        <li><a href="">喵鲜生</a></li>
                        <li><a href="">科技新品</a></li>
                        <li><a href="">女装新品</a></li>
                        <li><a href="">酷玩街</a></li>
                        <li><a href="">内衣新品</a></li>
                        <li><a href="">运动新品</a></li>
                        <li><a href="">时尚先生</a></li>
                        <li><a href="">精明妈咪</a></li>
                        <li><a href="">吃乐会</a></li>
                        <li><a href="">企业采购</a></li>
                        <li><a href="">会员积分</a></li>
                        <li><a href="">天猫国际</a></li>
                        <li><a href="">品质频道</a></li>
                        <p class="dropdown-header">行业市场Market</p>
                        <li><a href="">美妆</a></li>
                        <li><a href="">电器</a></li>
                        <li><a href="">女装</a></li>
                        <li><a href="">男装</a></li>
                        <li><a href="">女鞋</a></li>
                        <li><a href="">男鞋</a></li>
                        <li><a href="">内衣</a></li>
                        <li><a href="">箱包</a></li>
                        <li><a href="">运动</a></li>
                        <li><a href="">母婴</a></li>
                        <li><a href="">家装</a></li>
                        <li><a href="">医药</a></li>
                        <li><a href="">食品</a></li>
                        <li><a href="">配饰</a></li>
                        <li><a href="">汽车</a></li>
                        <p class="dropdown-header">服务指南Help</p>
                        <li><a href="">帮助中心</a></li>
                        <li><a href="">品质保障</a></li>
                        <li><a href="">特色服务</a></li>
                        <li><a href="">7天退换货</a></li>
                    </ul>

                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

</body>
</html>
