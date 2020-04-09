<%--
  个人中心
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/user/fore_userDetails.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_userDetails.css" rel="stylesheet">
    <title>个人中心</title>
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

        .tabBarBox {
            float: left;
            width: 860px;
            height: 40px;
            text-align: center;
            line-height: 40px;
            border: 1px solid #ddd;
        }

        .tabBarBox li {
            float: left;
            width: 100px;
            height: 100%;
            font-size:12px;
            /*background-color: #ff4863;*/
        }

        .tabBarBox li a {
            display: inline-block;
            text-decoration: none;
            width: 100%;
            height: 100%;
            color: #333;
        }

        .tabBar {
            border-top: 2px solid #FF0036;
            border-left: 1px solid rgba(255, 0, 54, 0.3);
            border-right: 1px solid rgba(255, 0, 54, 0.3);
            box-sizing: border-box;
            font-weight: 700;
            position: relative;
        }

        li.tabBar a {
            color: #FF0036;
        }

        .tabBar:before {
            content: "";
            position: absolute;
            top: 0;
            left: 45px;
            border-top: 5px solid #FF0036;
            border-left: 5px solid transparent;
            border-right: 5px solid transparent;
        }

        .account {
            display: none;
            padding-top:20px;
        }
    </style>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}">
                <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoA.png">
                <span class="span_tmallRegister">个人中心</span></a>
        </div>
    </div>
</nav>
<div class="content">
    <div class="mt-menu" id="J_MtSideMenu">
        <div class="mt-avatar">
            <img src="${pageContext.request.contextPath}/res/img/item/userProfilePicture/${requestScope.user.user_profile_picture_src}"
                 onerror="this.src='${pageContext.request.contextPath}/res/img/admin/loginPage/default_profile_picture-128x128.png'"
                 width="128px" height="128px">
        </div>
        <div class="mt-menu-tree">
            <p>积分：</p>
        </div>
    </div>
    <div class="J_TabBarBox tabBarBox" id="J_TabBarBox">
        <ul>
            <li class="tabBar" id="J_PersonnalData">
                <a href="javascript:void(0)" class="DataClick" onclick="getDataPage(this,'J_data')">个人资料</a>
            </li>
            <li class="" id="J_AccountSecurity">
                <a href="javascript:void(0)" onclick="getDataPage(this,'J_account')">账户安全</a>
            </li>
        </ul>
    </div>
    <div class="J_choose" id="profile">
        <div class="J_data">
            <div id="tips-box">
                <label class="font_we">亲爱的</label>
                <b>${requestScope.user.user_name}</b>，
                <label class="font_we">填写真实的资料，有助于好友找到你哦。</label>
            </div>
            <form action="${pageContext.request.contextPath}/user/update" method="post" id="register_form1">
                <div class="form-item">
                    <label class="form-label tsl">当前头像：</label>
                    <ul class="details_picList" id="product_single_list">
                        <li class="details_picList_fileUpload">
                            <img src="${pageContext.request.contextPath}/res/img/item/userProfilePicture/${requestScope.user.user_profile_picture_src}"
                                 onerror="this.src='${pageContext.request.contextPath}/res/img/admin/loginPage/default_profile_picture-128x128.png'"
                                 id="header_image" width="128px" height="128px">
                            <input type="file" onchange="uploadImage(this)" id="user_profile_picture_src"
                                   accept="image/*">
                            <input name="user_profile_picture_src" id="user_profile_picture_src_value" type="hidden"/>
                        </li>
                    </ul>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">昵称：</label>
                    <input name="user_nickname" value="${requestScope.user.user_nickname}" id="user_nickname"
                           class="form-text err-input" maxlength="20" readonly="readonly">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">真实姓名：</label>
                    <input name="user_realname" value="${requestScope.user.user_realname}" id="user_realname"
                           class="form-text err-input" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">性别：</label>
                    <input name="user_gender" type="radio" id="form_radion" value="0"
                           <c:if test="${requestScope.user.user_gender == 0}">checked="checked"</c:if>><span
                        class="radio_value">男</span>
                    <input name="user_gender" type="radio" id="form_radions" value="1"
                           <c:if test="${requestScope.user.user_gender == 1}">checked="checked"</c:if>><span
                        class="radio_value">女</span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">生日：</label>
                    <input type="date" name="user_birthday" id="user_birthday" class="form-text err-input"
                           value="${requestScope.user.user_birthday}" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <%--居住地址--%>
                <%--<div class="form-item last-item">
                    <label class="form-label tsl">居住地址：</label>
                    <select class="selectpicker" id="select_user_address_province" data-size="8"
                            data-live-search="true">
                        <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">
                            <option value="${address.address_areaId}"
                                    <c:if test="${requestScope.addressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                        </c:forEach>
                    </select>
                    <select class="selectpicker" id="select_user_address_city" data-size="8" data-live-search="true">
                        <c:forEach items="${requestScope.cityList}" var="address" varStatus="i">
                            <option value="${address.address_areaId}"
                                    <c:if test="${requestScope.cityAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                        </c:forEach>
                    </select>
                    <select name="user_address" class="selectpicker" id="select_user_address_district" data-size="8"
                            data-live-search="true">
                        <c:forEach items="${requestScope.districtList}" var="address" varStatus="i">
                            <option value="${address.address_areaId}"
                                    <c:if test="${requestScope.districtAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                        </c:forEach>
                    </select>
                </div>--%>
                <div class="form-item">
                    <input type="submit" id="register_sub" class="btns btn-large tsl" value="提 交"/>
                </div>
            </form>
        </div>

        <div class="J_account account">
            <form action="${pageContext.request.contextPath}/user/update" method="post" id="register_form2">
                <div class="form-item">
                    <label class="form-label tsl">用户昵称：</label>
                    <input name="user_password" type="password" id="user_password" class="form-text err-input"
                           placeholder="请设置昵称" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <input name="user_password_one" type="password" id="user_password_one" class="form-text err-input"
                           placeholder="请输入你的密码" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <input type="submit" id="register_sub1" class="btns btn-large tsl" value="提 交"/>
                </div>
            </form>
        </div>
    </div>
    <%--  <div class="sns-config" id="profile">
          <div class="sns-tab tab-app">
              <span>个人资料</span>
          </div>
          <div class="sns-main">
              <div id="tips-box">
                  <label class="font_we">亲爱的</label>
                  <b>${requestScope.user.user_name}</b>，
                  <label  class="font_we">填写真实的资料，有助于好友找到你哦。</label>
              </div>
              <form action="${pageContext.request.contextPath}/user/update" method="post" id="register_form">
                  <div class="form-item">
                      <label class="form-label tsl">当前头像：</label>
                      <ul class="details_picList" id="product_single_list">
                          <li class="details_picList_fileUpload">
                              <img src="${pageContext.request.contextPath}/res/img/item/userProfilePicture/${requestScope.user.user_profile_picture_src}"
                                   onerror="this.src='${pageContext.request.contextPath}/res/img/admin/loginPage/default_profile_picture-128x128.png'"
                                   id="header_image" width="128px" height="128px">
                              <input type="file" onchange="uploadImage(this)" id="user_profile_picture_src" accept="image/*">
                              <input name="user_profile_picture_src" id="user_profile_picture_src_value" type="hidden"/>
                          </li>
                      </ul>
                  </div>
                  <div class="form-item">
                      <label class="form-label tsl">昵称：</label>
                      <input name="user_nickname" value="${requestScope.user.user_nickname}" id="user_nickname"
                             class="form-text err-input" maxlength="20">
                      <span class="form_span"></span>
                  </div>
                  <div class="form-item">
                      <label class="form-label tsl">真实姓名：</label>
                      <input name="user_realname" value="${requestScope.user.user_realname}" id="user_realname"
                             class="form-text err-input" maxlength="20">
                      <span class="form_span"></span>
                  </div>
                  <div class="form-item">
                      <label class="form-label tsl">登录密码：</label>
                      <input name="user_password" type="password" id="user_password" class="form-text err-input"
                             placeholder="请设置登录密码" maxlength="20">
                      <span class="form_span"></span>
                  </div>
                  <div class="form-item">
                      <label class="form-label tsl">确认密码：</label>
                      <input name="user_password_one" type="password" id="user_password_one" class="form-text err-input"
                             placeholder="请再次输入你的密码" maxlength="20">
                      <span class="form_span"></span>
                  </div>
                  <div class="form-item">
                      <label class="form-label tsl">性别：</label>
                      <input name="user_gender" type="radio" id="form_radion" value="0"
                             <c:if test="${requestScope.user.user_gender == 0}">checked="checked"</c:if>><span
                          class="radio_value">男</span>
                      <input name="user_gender" type="radio" id="form_radions" value="1"
                             <c:if test="${requestScope.user.user_gender == 1}">checked="checked"</c:if>><span
                          class="radio_value">女</span>
                  </div>
                  <div class="form-item">
                      <label class="form-label tsl">生日：</label>
                      <input type="date" name="user_birthday" id="user_birthday" class="form-text err-input"
                             value="${requestScope.user.user_birthday}" maxlength="20">
                      <span class="form_span"></span>
                  </div>
                  <div class="form-item last-item">
                      <label class="form-label tsl">居住地址：</label>
                      <select class="selectpicker" id="select_user_address_province" data-size="8" data-live-search="true">
                          <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">
                              <option value="${address.address_areaId}"
                                      <c:if test="${requestScope.addressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                          </c:forEach>
                      </select>
                      <select class="selectpicker" id="select_user_address_city" data-size="8" data-live-search="true">
                          <c:forEach items="${requestScope.cityList}" var="address" varStatus="i">
                              <option value="${address.address_areaId}"
                                      <c:if test="${requestScope.cityAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                          </c:forEach>
                      </select>
                      <select name="user_address" class="selectpicker" id="select_user_address_district" data-size="8" data-live-search="true">
                          <c:forEach items="${requestScope.districtList}" var="address" varStatus="i">
                              <option value="${address.address_areaId}"
                                      <c:if test="${requestScope.districtAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <div class="form-item">
                      <input type="submit" id="register_sub" class="btns btn-large tsl" value="提 交"/>
                  </div>
              </form>
          </div>
      </div>  --%>
</div>
<script>
    var lists = document.getElementById("J_TabBarBox").getElementsByTagName("li");
    for (var i = 0; i < lists.length; i++) {
        lists[i].onclick = function () {
            for (var j = 0; j < lists.length; j++) {
                lists[j].className = "";
            }
            this.className = "tabBar";
        }
    }
</script>
<%@include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/res/css/user/fore_foot_special.css" rel="stylesheet"/>
<div class="msg">
    <span>修改成功，请重新登录！</span>
</div>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>