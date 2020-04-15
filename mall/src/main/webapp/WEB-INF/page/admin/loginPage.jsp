<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/10
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8;charset=UTF-8"%><%--language="java"--%>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/res/js/admin/admin_login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/admin/admin_login.css"/>
    <title>mall 后台管理-登录</title>
</head>
<body>
<%--大背景--%>
<div id="div_background">
    <%--peel栏div--%>
    <div id="div_nav">
        <%--两个span在peeldiv里面--%>
        <span id="txt_date"></span>
        <span id="txt_peel">换肤</span>
        <ul id="div_peelPanel">
            <%--jsp绝对路径的获取方式和绝对路径--%>
             <li value="url(${PageContent.request.contextPath}/res/img/admin/loginPage/background-1.jpg)">
                <img src="${PageContext.request.contentPath}/res/img/admin/loginPage/background-mini-1.jpg">
             </li>
             <li value="url(${PageContent.request.contextPath}/res/img/admin/loginPage/background-2.jpg)">
                <img src="${PageContext.request.contentPath}/res/img/admin/loginPage/background-mini-2.jpg">
             </li>
             <li value="url(${PageContent.request.contextPath}/res/img/admin/loginPage/background-3.jpg)">
                 <img src="${PageContext.request.contentPath}/res/img/admin/loginPage/background-mini-3.jpg">
             </li>
             <li value="url(${PageContent.request.contextPath}/res/img/admin/loginPage/background-4.jpg)">
                 <img src="${PageContext.request.contentPath}/res/img/admin/loginPage/background-mini-4.jpg">
             </li>
             <li value="url(${PageContent.request.contextPath}/res/img/admin/loginPage/background-5.jpg)">
                 <img src="${PageContext.request.contentPath}/res/img/admin/loginPage/background-mini-5.jpg">
             </li>
        </ul>

    </div>
    <div id="div_main">
        <div id="div_head"><p>天猫<span>管理后台</span></p></div>
        <div id="div_content">
            <img id="img_profile_picture"
                src="${pageContext.request.contextPath}/res/img/admin/loginPage/default_profile_picture-128x128.png"
                <%--当图片无法加载时，用文本代替显示--%>
                alt="头像" title="头像"
                <%--图片装载失败时，触发onerror事件，图片不存在将显示logoError.png。以下图片--%>
                onerror="this.url='${pageContext.request.contextPath}/res/img/admin/loginPage/default_profile_picture-128x128.png'"/>
            <form id="form_login">
                <%--placeholder和value的区别是当鼠标focus时value（此时未输入值，只是聚焦状态）值会消失，而placeholder的值不会消失，当输入值不为空时才会消失--%>
                <input type="text" class="form-control form_control" placeholder="用户名" id="input_username" title="请输入用户名！">
                <input type="password" class="form-control form_control" placeholder="密码" id="input_password" title="请输入密码！">
                <span id="txt_error_msg"></span>
                <input type="button" class="btn btn-danger" id="btn_login" value="登录"/>
            </form>
        </div>
    </div>
    <div id="div_foot">
        <span>毕业设计<a href="#" target="_blank">xihuauniversity</a> </span>
    </div>
</div>

</body>
</html>
