<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <script src="${pageContext.request.contextPath}/res/js/user/fore_productBuy.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_userAddressPage.css" rel="stylesheet"/>
    <title>管理收货地址</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div id="mallLogo">
        <a href="${pageContext.request.contextPath}">
            <img src="${pageContext.request.contextPath}/res/img/user/WebsiteImage/tmallLogoA.png">
        </a>
    </div>
</nav>
<div class="order_address">
    <h2>输入收货地址</h2>
    <form action="" method="post">
        <label for="select_order_address_province">所在地区</label><span class="mustValue">*</span>
        <select class="selectpicker" id="select_order_address_province" data-size="8" data-live-search="true"
                style="width:100px;">
            <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">
                <option value="${address.addressAreaId}"
                        <c:if test="${requestScope.addressId==address.addressAreaId}">selected</c:if>>${address.addressName}</option>
            </c:forEach>
        </select>
        <select class="selectpicker" id="select_order_address_city" data-size="8" data-live-search="true">
            <c:forEach items="${requestScope.cityList}" var="address" varStatus="i">
                <option value="${address.addressAreaId}"
                        <c:if test="${requestScope.cityAddressId==address.addressAreaId}">selected</c:if>>${address.addressName}</option>
            </c:forEach>
        </select>
        <select class="selectpicker" id="select_order_address_district" data-size="8" data-live-search="true">
            <c:forEach items="${requestScope.districtList}" var="address" varStatus="i">
                <option value="${address.addressAreaId}"
                        <c:if test="${requestScope.districtAddressId==address.addressAreaId}">selected</c:if>>${address.addressName}</option>
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
        <input type="checkbox" name="default_address" id = "default_address" ><span class="flag">设置为默认收货地址</span>
        <input type="submit" class="submit" value="保存" onclick="submitAddress()">
    </form>
</div>
<div class="order_bar">
    已经保存了 ${fn:length(requestScope.userAllAddress)}条地址
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
        <c:forEach items="${requestScope.userAllAddress}" var="address">
            <tr>
                <td>${address.receiver}</td>
                <td>${address.province} ${address.city} ${address.district}</td>
                <td>${address.detailAddress}</td>
                <td>${address.postCode}</td>
                <td>${address.tel}</td>
                <td>
                    <a href='/mall/delUserAddress?userAddressId=${address.userAddressId}'>删除</a>
                </td>
                <td>
                    <a href='/mall/setDefaultAddress?userAddressId=${address.userAddressId}' >
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
    //增加地址
    function submitAddress() {
        var addressId = $("#select_order_address_province").val();
        var cityAddressId = $("#select_order_address_city").val();
        var districtAddressId = $("#select_order_address_district").val();
        var productOrder_detail_address = $.trim($("#textarea_details_address").val());
        var productOrder_post = $.trim($("#input_order_post").val());
        var productOrder_receiver = $.trim($("#input_order_receiver").val());
        var productOrder_mobile = $.trim($("#input_order_phone").val());
        var flag;
        if(document.getElementById("default_address").checked == true){
            flag = 1;
        }else{
            flag = 0;
        }

        var yn = true;
        if (productOrder_detail_address === "") {
            styleUtil.specialBasicErrorShow($("#label_details_address"));
            yn = false;
        }
        if (productOrder_receiver === "") {
            styleUtil.specialBasicErrorShow($("#label_order_receiver"));
            yn = false;
        }
        var re = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
        if (!re.test(productOrder_mobile)) {
            styleUtil.specialBasicErrorShow($("#label_order_phone"));
            yn = false;
        }
        re = /^[1-9][0-9]{5}$/;
        if (!re.test(productOrder_post) && productOrder_post !== "") {
            styleUtil.specialBasicErrorShow($("#label_order_post"));
            yn = false;
        }

        if (!yn) {
            window.scrollTo(0, 0);
            return false;
        }

        var obj = {};
        obj['addressAreaId'] = districtAddressId;
        obj['detailAddress'] = productOrder_detail_address;
        obj['receiver'] = productOrder_receiver;
        obj['tel'] = productOrder_mobile;
        obj['flag'] = flag;
        obj['postCode'] =productOrder_post;

        $.ajax({
            type:"POST",
            url: "/mall/addUserAddress",
            async:false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(obj),
            success: function (data) {
                    window.location.href  = "/mall/userAddress";
            }
        });
    }
</script>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap-select.min.js"></script>
</body>
</html>
