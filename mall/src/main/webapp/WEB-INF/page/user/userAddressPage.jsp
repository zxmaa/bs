<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/4/4
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <title>管理收货地址</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/css/user/fore_userAddressPage.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/res/js/user/fore_productBuy.js"></script>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
</nav>

<div class="order_address">
    <h2>输入收货地址</h2>
    <form action="" method="post">
        <label for="select_order_address_province">所在地区</label><span class="mustValue">*</span>
        <select class="selectpicker" id="select_order_address_province" data-size="8" data-live-search="true"
                style="width:100px;">
            <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">
                <option value="${address.address_areaId}"
                        <c:if test="${requestScope.addressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
            </c:forEach>
        </select>
        <select class="selectpicker" id="select_order_address_city" data-size="8" data-live-search="true">
            <c:forEach items="${requestScope.cityList}" var="address" varStatus="i">
                <option value="${address.address_areaId}"
                        <c:if test="${requestScope.cityAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
            </c:forEach>
        </select>
        <select class="selectpicker" id="select_order_address_district" data-size="8" data-live-search="true">
            <c:forEach items="${requestScope.districtList}" var="address" varStatus="i">
                <option value="${address.address_areaId}"
                        <c:if test="${requestScope.districtAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
            </c:forEach>
        </select>
        <div class="br"></div>
        <label for="textarea_details_address" id="label_details_address">详细地址</label><span class="mustValue">*</span>
        <textarea id="textarea_details_address" placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息"></textarea>
        <div class="br"></div>
        <label for="input_order_post" id="label_order_post">邮政编码</label><span class="mustValue">*</span>
        <input id="input_order_post" type="text" placeholder="请填写邮编" maxlength="6"/>
        <div class="br"></div>
        <label for="input_order_receiver" id="label_order_receiver">收货人姓名</label><span class="mustValue">*</span>
        <input id="input_order_receiver" type="text" placeholder="长度不超过25个字符" maxlength="20"/>
        <div class="br"></div>
        <label for="input_order_phone" id="label_order_phone">手机号码</label><span class="mustValue">*</span>
        <input id="input_order_phone" type="text" placeholder="请填写正常的手机号码" maxlength="11"/>
        <input type="checkbox" name="flag" ><span class="flag">设置为默认收货地址</span>
        <input type="submit" class="submit" value="保存">
    </form>
</div>

<div class="order_bar">
    已经保存了3条地址
</div>

<div class="order_table">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <th>收货人</th>
            <th>所在地区</th>
            <th>详细地址</th>
            <th>邮编</th>
            <th>手机</th>
            <th>操作</th>
            <th></th>
        </tr>
        <c:forEach var="a" begin="1" end="5">
            <tr>
                <td>tom</td>
                <td>四川省 成都市 青羊区 草市街</td>
                <td>人民中路二段51号附一</td>
                <td>000000</td>
                <td>13********88</td>
                <td>
                    <a href="#">删除</a>
                </td>
                <td>
                    <a href="#">
                        设为默认
                    </a>
                    <span style="display: none">1</span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
    var firstTrObj = document.getElementsByClassName("order_table")[0].getElementsByTagName("table")[0].getElementsByTagName("tr")[1];

    var defaultAddr=firstTrObj.children[firstTrObj.children.length-1];
    var spanObj=defaultAddr.getElementsByTagName("span")[0];
    console.log(spanObj.innerText);
    console.log(defaultAddr);
    var flag=spanObj.innerText;
    if(flag == 1){
        defaultAddr.innerHTML="<div class='defaultAddr'>默认地址</div>";
    }



</script>


<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
</body>
</html>
