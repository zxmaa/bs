<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <title>产品详情</title>
    <style rel="stylesheet">
        .P_details {
            width: 100%;
            display: block;
        }

        .P_details > .P_details_list {
            margin-bottom: 10px;
            border: 1px solid #e6e6e6;
            border-top: none;
        }

        .P_details_list > .P_details_list_header {
            display: block;
            padding: 8px 20px 10px;
            border-top: 1px solid #e6e6e6;
            height: 40px;
            line-height: 40px;
            color: #666;
            margin: 0;
            font-family: "Microsoft YaHei UI", serif;
            font-size: 10px;
        }

        .P_details_list_header > span {
            color: #333333;
            font-family: "Microsoft YaHei UI", serif;
        }

        .P_details_list > .P_details_list_title {
            margin: 0;
            padding: 5px 20px;
            line-height: 22px;
            color: #999;
            font-weight: 700;
            font-family: "Microsoft YaHei UI", serif;
            font-size: 10px;
        }

        .P_details_list > .P_details_list_body {
            padding: 0 20px 18px;
            border-top: 1px solid #ffffff;
            margin: 0;
            zoom: 1;
        }

        .P_details_list_body > li {
            display: inline;
            float: left;
            width: 220px;
            height: 18px;
            overflow: hidden;
            margin: 10px 15px 0 0;
            line-height: 18px;
            vertical-align: top;
            white-space: nowrap;
            text-overflow: ellipsis;
            color: #666;
            font-family: "Microsoft YaHei UI", serif;
            font-size: 10px;
        }

        .P_details_list_body:after {
            display: block;
            content: "\0020";
            clear: both;
            visibility: hidden;
        }
    </style>
</head>
<body>
<div class="P_details">
    <div class="P_details_list">
        <p class="P_details_list_header">产品名称：<span>${requestScope.product.product_name}</span></p>
        <p class="P_details_list_title">产品参数：</p>
        <ul class="P_details_list_body">
            <c:forEach items="${requestScope.propertyList}" var="property">
                <c:if test="${property.propertyValueList[0].propertyValue_value != null}">
                    <li title="${property.propertyValueList[0].propertyValue_value}">${property.property_name}：${property.propertyValueList[0].propertyValue_value}</li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</div>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
