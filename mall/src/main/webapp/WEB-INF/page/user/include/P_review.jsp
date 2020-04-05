
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><%--以最高版本IE来渲染页面--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"><%-- 不同设备之间的自适应--%>
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <title>商品评价</title>
    <style rel="stylesheet">
        .P_reviews {
            width: 100%;
            display: none;
            margin-bottom: 10px;
        }
        .reviews_info {
            width: 100%;
            padding: 16px 7px;
            border-bottom: 1px solid #e3e3e3;
            font-family: tahoma, arial, \5FAE\8F6F\96C5\9ED1, sans-serif;
            font-size: 12px;
            min-height: 68px;
            box-sizing: border-box;
        }
        .reviews_main {
            display: inline-block;
        }
        .reviews_content {
            width: 494px;
            padding-right: 30px;
            line-height: 19px;
            overflow: hidden;
            word-wrap: break-word;
            word-break: break-all;
            color: #333333;
        }
        .reviews_date {
            clear: both;
            color: #cccccc;
        }
        .reviews_author {
            position: relative;
            right: 30px;
            float: right;
            display: inline-block;
            height: 36px;
            line-height: 36px;
            min-width: 100px;
        }
    </style>
</head>
<body>
<div class="P_reviews">
    <div class="reviews_main">
        <c:forEach items="${requestScope.product.reviewList}" var="review">
            <div class="reviews_info">
                <div class="reviews_main">
                    <div class="reviews_content">
                        <p>${review.review_content}</p>
                    </div>
                    <div class="reviews_date">
                        <span>${review.review_createDate}</span>
                    </div>
                </div>
                <div class="reviews_author">${review.review_user.user_nickname}</div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>
