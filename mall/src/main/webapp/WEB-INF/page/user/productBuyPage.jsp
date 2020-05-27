<%-- 从购物车页面结算后转至确认订单 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/header.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/user/fore_productBuy.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/user/fore_productBuyPage.css" rel="stylesheet"/>
    <title>确认订单 </title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
</nav>
<div class="headerLayout">
    <div class="headerContext">
        <div class="mallLogo">
            <span class="mlogo">
                <a href="${pageContext.request.contextPath}">
                    <s></s>
                </a>
            </span>
        </div>
    </div>
    <ol class="header-extra">
        <li class="step-done">
            <div class="step-name">拍下商品</div>
            <div class="step-no_first"></div>
        </li>
        <li class="step-no">
            <div class="step-name">付款到支付宝</div>
            <div class="step-no">2</div>
        </li>
        <li class="step-no">
            <div class="step-name">确认收货</div>
            <div class="step-no">3</div>
        </li>
        <li class="step-no">
            <div class="step-name">评价</div>
            <div class="step-no_last">4</div>
        </li>
    </ol>
</div>
<div class="content">
    <div class="order_address">
        <h2>选择收货地址</h2>
        <div class="address-list">
            <c:if test="${requestScope.allUserAddress == null || fn:length(requestScope.allUserAddress) == 0}">
                您还没有添加地址，请点击 <a href="/mall/userAddress">管理收货地址</a> 添加
            </c:if>
            <c:forEach  items="${requestScope.allUserAddress}" var="address" >
                <input type="hidden" id="userAddressId" value="${address.userAddressId}">
                <div class="addr-item">
                    <div class="addr-hd">
                        <span>${address.province} ${address.city} ${address.district}(</span>
                        <span>${address.receiver}</span>
                        <span>收）</span>
                    </div>
                    <div class="addr-bd">
                        ${address.detailAddress}  ${address.tel}
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="manageAddr">
            <a href="${pageContext.request.contextPath}/userAddress">管理收货地址</a>
        </div>
    </div>
    <div class="order_info">
        <h2>确认订单信息</h2>
        <table class="table_order_orderItem">
            <thead>
            <tr>
                <th>店铺宝贝</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orderItemList}" var="orderItem" varStatus="i">
                <tr class="tr_shop">
                    <td>
                        <span class="span_shopTitle">店铺：</span><span class="span_shopName">${orderItem.categoryName}旗舰店</span>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="tr_product_info">
                    <td><img
                            src="${pageContext.request.contextPath}/res/img/item/productSinglePicture/${orderItem.productImage.productImageSrc}"
                            style="width: 50px;height: 50px;"/><span class="span_product_name"><a
                            href="${pageContext.request.contextPath}/product/${orderItem.product.productId}">${orderItem.product.productName}</a></span>
                    </td>
                    <td><span
                            class="span_product_sale_price">${orderItem.product.productSalePrice}0</span>
                    </td>
                    <td><span class="span_productOrderItem_number">${orderItem.productOrderItem.productOrderItemNumber}</span></td>
                    <td><span class="span_productOrderItem_price">${orderItem.productOrderItem.productOrderItemPrice}0</span></td>
                </tr>
                <tr class="tr_userMessage">
                    <td><label for="input_userMessage_${i.count}">给卖家留言：</label><textarea maxlength="500" id="input_userMessage_${i.count}" placeholder="选填:填写内容已和卖家协商确认" class="input_userMessage"></textarea>
                    </td>
                    <td></td>
                    <td></td>
                    <td colspan="4"><input type="hidden" class="input_orderItem_id"
                                           value="${orderItem.productOrderItem.productOrderItemId}"/>
                </tr>
                <tr class="tr_orderCount">
                    <td colspan="4"><span class="span_price_name">店铺合计(含运费)</span><span
                            class="span_price_value">￥${requestScope.orderTotalMoney}0</span></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="order_count_div">
        <div class="order_count_div_main">
            <div class="order_count_div_content">
                <h1 class="order_count_div_price">
                    <span class="order-title">实付款：</span>
                    <span class="realPay-price_unit">￥</span>
                    <span class="realPay-price">${requestScope.orderTotalMoney}0</span>
                </h1>
                <h1 class="order_count_div_address">
                    <span class="order-title">寄送至：</span>
                </h1>
                <h1 class="order_count_div_phone">
                    <span class="order-title">收货人：</span>
                </h1>
            </div>
        </div>
    </div>

    <script>
        var listCards = document.querySelector(".address-list").getElementsByClassName("addr-item");
        var orderAccpetAddr = document.getElementsByClassName("order_count_div_address")[0].getElementsByTagName("span")[0];
        var orderAcceptUser = document.getElementsByClassName("order_count_div_phone")[0].getElementsByTagName("span")[0];
        var defaultUrl = "${pageContext.request.contextPath}/res/img/user/WebsiteImage/blackWhiteBorder.png";
        var url = "${pageContext.request.contextPath}/res/img/user/WebsiteImage/colorfulBorder.png";
        listCards[0].style.backgroundImage = "url(" + url + ")";
        var tips = document.createElement("div");
        tips.className = "tips";
        (function(){
            listCards[0].appendChild(tips);
            var hdText = listCards[0].children[0].getElementsByTagName("span");
            var bdText = listCards[0].children[1].innerText;
            var firstName = hdText[1].innerText;
            var firstAddr = hdText[0].innerText;
            var detailAddress = bdText.substring(0, bdText.lastIndexOf(" "));
            var firstPhone = bdText.substring(bdText.lastIndexOf(" "));
            orderAccpetAddr.innerText = "寄送至：" + firstAddr.slice(0, firstAddr.length - 1) + " " + detailAddress;
            orderAcceptUser.innerText = "收货人：" + firstName + " " + firstPhone;
        }());
        for (var i = 0; i < listCards.length; i++) {
            listCards[i].onclick = function () {
                for (var j = 0; j < listCards.length; j++) {
                    listCards[j].style.backgroundImage = "url(" + defaultUrl + ")";
                    if (listCards[j].getElementsByClassName("tips")[0]) {
                        listCards[j].removeChild(listCards[j].getElementsByClassName("tips")[0]);
                    }
                }
                this.style.backgroundImage = "url(" + url + ")";
                this.appendChild(tips);
                var name = this.children[0].getElementsByTagName("span")[1].innerText;
                var addr = this.children[1].innerText;
                var phone = addr.substring(addr.lastIndexOf(" "));
                orderAccpetAddr.innerText = "寄送至：" + addr.substring(0, addr.lastIndexOf(" "));
                orderAcceptUser.innerText = "收货人：" + name + " " + phone;
            };
        }
        // 只有一个订单项时
        function payOne() {
            var userAddressId = $.trim($("#userAddressId").val());
            var userMessage = $.trim($("#input_userMessage_1").val());
            var orderItem_product_id = parseInt('${requestScope.orderItemList[0].product.productId}');
            var orderItem_number = parseInt('${requestScope.orderItemList[0].productOrderItem.productOrderItemNumber}');
            $.ajax({
                url: "/mall/order/one",
                type: "POST",
                data: {
                   "userAddressId":userAddressId,
                    "userMessage": userMessage,
                    "orderItemProductId": orderItem_product_id,
                    "orderItemNumber": orderItem_number
                },
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        location.href = "/mall" + data.url;
                    } else {
                        alert(data.message);
                        location.reload(true);
                    }
                },
                error: function () {
                    alert("订单提交出现问题，请重新提交！");
                    location.reload(true);
                }
            });
        }
        // 有多个订单项
        function payList() {
            var userAddressId = $.trim($("#userAddressId").val());
            var orderItemMap = {};
            var tr = $(".tr_userMessage");
            tr.each(function () {
                var orderItem_id = $(this).find(".input_orderItem_id").val();
                if (isNaN(orderItem_id) || orderItem_id === "") {
                    location.reload(true);
                    return false;
                }
                orderItemMap[orderItem_id] = $(this).find(".input_userMessage").val();
            });
            $.ajax({
                url: "/mall/order/list",
                type: "POST",
                data: {
                   "userAddressId":userAddressId,
                    "orderItemJSON": JSON.stringify(orderItemMap)
                },
                traditional: true,
                success: function (data) {
                    if (data.success) {
                        location.href = "/mall" + data.url;
                        return true;
                    } else {
                        alert(data.message);
                        location.reload(true);
                    }
                },
                beforeSend: function () {

                },
                error: function () {
                    alert("订单创建失败，请稍后再试！");
                    location.reload(true);
                }
            });
        }
    </script>
    <div class="order_info_last">
        <c:choose>
            <c:when test="${requestScope.orderItemList[0].productOrderItem.productOrderItemId != null}">
                <a href="javascript:void(0)" title="提交订单" class="go-btn" onclick="payList()">提交订单</a>
            </c:when>
            <c:otherwise>
                <a href="javascript:void(0)" title="提交订单" class="go-btn" onclick="payOne()">提交订单</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
<div class="loader"></div>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
