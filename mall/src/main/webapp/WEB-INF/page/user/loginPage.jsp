<%--
  用户登录页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/user/fore_login.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_login.css" rel="stylesheet">
    <style rel="stylesheet">
        #baseNavigator {
            padding: 22px 0;
            width: 1190px;
            height: 44px;
            margin: auto;
        }
        #baseNavigator img {
            width: 190px;
            margin-top: 8px;
        }
        #nav {
            width: auto;
            height: 32px;
            font-family: "Microsoft YaHei UI", Tahoma, serif;
            font-size: 12px;
            position: relative !important;
            background: #f2f2f2;
            z-index: 999;
            border-bottom: 1px solid #e5e5e5;
        }
    </style>
    <title>购物商场-登录</title>
</head>
<body>
<nav id="baseNavigator">
    <a href="${pageContext.request.contextPath}" target="_self">
        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoA.png"/>
    </a>
</nav>
<div class="content">
    <div class="contentMain"></div>
    <div class="loginDiv">
        <div class="loginSwitch" id="loginSwitch"></div>
        <div class="loginMessage">
            <div class="loginMessageMain">
                <div class="poptip-arrow"><em></em><span></span></div>
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/scan-safe.png" alt="扫码登录"/><span>扫码登录更安全</span>
            </div>
        </div>
        <div class="pwdLogin">
            <span class="loginTitle">密码登录</span>
            <form method="post" class="loginForm">
                <div class="loginInputDiv">
                    <label for="name" class="loginLabel">
                        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/2018-04-27_235518.png"
                            width="38px" height="39px" title="会员名" alt=""/></label>
                    <input type="text" name="name" id="name" class="loginInput" placeholder="会员名/邮箱/手机号">
                </div>
                <div class="loginInputDiv">
                    <label for="password" class="loginLabel">
                        <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/2018-04-27_235533.png"
                            width="38px" height="39px" title="登录密码" alt=""/></label>
                    <input type="password" name="password" id="password" class="loginInput">
                </div>
                <input type="submit" class="loginButton" value="登 录">
            </form>
            <div class="loginLinks">
                <a href="#">忘记密码</a>
                <a href="#">忘记会员名</a>
                <a href="${pageContext.request.contextPath}/register">免费注册</a>
            </div>
            <div class="error_message">
                <p id="error_message_p"></p>
            </div>
        </div>
        <div class="qrcodeLogin">
            <span class="loginTitle">手机扫码，安全登录</span>
            <div class="qrcodeMain">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/login_qrcode.png"
                     id="qrcodeA" alt=""/>
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/login_qrcodeB.png"
                     id="qrcodeB" alt=""/>
            </div>
            <div class="qrcodeFooter">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/scan_icon2.png">
                <p>打开 <a href="https://www.tmall.com/wow/portal/act/app-download">手机天猫</a> | <a
                        href="https://www.taobao.com/m">手机淘宝</a>扫一扫登录</p>
            </div>
            <div class="loginLinks">
                <a href="JavaScript:void(0)" id="pwdLogin">密码登录</a>
                <a href="#">免费注册</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/res/css/user/fore_foot_special.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
