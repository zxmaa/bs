<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/user/fore_register.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_register.css" rel="stylesheet">
    <title>网上注册</title>
    <style rel="stylesheet">
        .nav {
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
</head>
<body>
<%--<div class="container">--%>
    <nav>
        <div class="header col-xs-12">
            <div id="mallLogo">
                <a href="${pageContext.request.contextPath}">
                    <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoA.png">
                    <span class="span_tmallRegister">用户注册</span>
                </a>
            </div>
        </div>
    </nav>
    <div class=" container content" id="regi_content">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2  control-label tsls">设置会员名</label>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="user_name">用户名：</label>
                <div class="col-sm-7 ">
                    <input name="user_name" id="user_name" class="form-control form-text err-input" placeholder="请输入用户名" maxlength="20">
                    <span class="form_span"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label tsls">设置登录密码</label>
                <label class="col-sm-3  control-label">登录时验证，保护账号信息</label>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="user_password">登录密码：</label>
                <div class="col-sm-7">
                    <input name="user_password" type="password" id="user_password" class="form-control form-text err-input" placeholder="请设置登录密码" maxlength="20">
                    <span class="form_span"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="user_password_one">确认密码：</label>
                <div class="col-sm-7">
                    <input name="user_password_one" type="password" id="user_password_one" class="form-control form-text err-input" placeholder="请再次输入你的密码" maxlength="20">
                    <span class="form_span"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label tsls">填写基本信息</label>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">联系电话：</label>
                <div class="col-sm-7">
                    <input name="userTel" id="userTel" class="form-control form-text err-input" placeholder="请输入联系电话" maxlength="20">
                    <span class="form_span"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">性别：</label>
                <div class="col-sm-7">
                    <input name="user_gender" type="radio" id="form_radion" value="0" checked="checked">男
                    <input name="user_gender" type="radio" id="form_radions" value="1">女
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="user_birthday">出生日期：</label>
                <div class="col-sm-7">
                    <input type="date" name="user_birthday" id="user_birthday" class="form-control form-text err-input"/>
                    <span class="form_span"></span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-8 col-sm-offset-1">
                    <input type="button" id="register_sub" class="btns btn-large tsl" value="注 册"/>
                </div>
            </div>
        </form>
    </div>

<%@include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/res/css/user/fore_foot_special.css" rel="stylesheet"/>
<div class="msg">
    <span>注册成功，跳转到登陆页面</span>
</div>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>


