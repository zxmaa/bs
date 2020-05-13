<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <title>分页显示</title>
    <script>
        $(function () {
            $(".disabled>a,.pageThis>a").attr("onclick", null);
        })
    </script>
</head>
<body>
<div id="pageDiv">
    <ul>
        <li data-name="firstPage" <c:if test="${requestScope.orderShow.pageNum<=1}">class="disabled" </c:if>>
            <a href="javascript:void(0)" onclick="getPage(1)" aria-label="首页"><span
                    aria-hidden="true">&laquo;</span></a>
        </li>
        <li data-name="prevPage" <c:if test="${requestScope.orderShow.pageNum<=1}">class="disabled" </c:if>>
            <a href="javascript:void(0)" onclick="getPage(${requestScope.orderShow.pageNum-1})" aria-label="上一页"><span
                    aria-hidden="true">&lsaquo;</span></a>
        </li>

        <c:forEach begin="1" end="${requestScope.orderShow.pages}" varStatus="status">
            <c:if test="${status.count-requestScope.orderShow.pageNum>=-5 && status.count-requestScope.orderShow.pageNum<=5}">
                <li <c:if test="${status.count==requestScope.orderShow.pageNum}"> class="pageThis" </c:if>>
                    <a href="javascript:void(0)" onclick="getPage(${status.count})">${status.count}</a>
                </li>
            </c:if>
        </c:forEach>

        <li data-name="nextPage" <c:if test="${!requestScope.orderShow.hasNextPage}">class="disabled" </c:if>>
            <a href="javascript:void(0)" onclick="getPage(${requestScope.orderShow.pageNum+1})" aria-label="下一页"><span
                    aria-hidden="true">&rsaquo;</span></a>
        </li>
        <li data-name="lastPage" <c:if test="${!requestScope.orderShow.hasNextPage}">class="disabled" </c:if>>
            <a href="javascript:void(0)" onclick="getPage(${requestScope.orderShow.pages})" aria-label="尾页">&raquo;</a>
        </li>
    </ul>
</div>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
