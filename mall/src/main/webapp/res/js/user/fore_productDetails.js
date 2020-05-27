$(function () {
    var ul = $(".context_ul_goodsList").children("ul");
    $(".P_GoodsDetails").addClass("tab-selected");
    $(".context_img_li").eq(0).addClass("context_img_li_hover");
    //搜索框验证
    $('form').submit(function () {
        if ($(this).find("input[name='product_name']").val() === "") {
            alert("请输入关键字！");
            return false;
        }
    });
    //移入预览图片列表时
    $(".context_img_li").mouseenter(function () {
        var img = $(this).children("img");
        $(".context_img_main").attr("src", img.attr("src"));
        $(".context_img_ks").children("img").attr("src", img.attr("src"));
        $(".context_img_li").removeClass("context_img_li_hover");
        $(this).addClass("context_img_li_hover");
    });
    //产品数量框验证
    $(".amount_value_up").click(function () {
        var number = parseInt($(".context_buymember").val());
        number++;
        $(".context_buymember").val(number);
    });
    $(".amount_value-down").click(function () {
        var number = parseInt($(".context_buymember").val());
        if (number > 1) {
            number--;
            $(".context_buymember").val(number);
        }
    });
    $(".context_buymember").on("input", function () {
        if ($(this).val() === "") {
            $(this).val(1);
        }
        if (parseInt($("#stock").val()) < parseInt($(this).val())) {
            $(".context_buyNow").addClass("context_notBuy").attr("disabled", "disabled");
            $(".context_addShopCart").addClass("context_notCar").attr("disabled", "disabled");
        } else {
            $(".context_buyNow").removeClass("context_notBuy").attr("disabled", null);
            $(".context_addShopCart").removeClass("context_notCar").attr("disabled", null);
        }
    });
    //放大镜逻辑
    $(".context_img_main").mouseenter(function () {
        $(".context_img_winSelector").show();
        $(".context_img_ks").show().children("img").attr("src", $(this).attr("src"));
    });
    $(".context_img_winSelector").mouseleave(function () {
        $(".context_img_winSelector").hide();
        $(".context_img_ks").hide();
    });
    $(".context_img_main,.context_img_winSelector").mousemove(function (e) {
        SelectorMousemove(e);
    });
    //模态窗口登录
    $(".loginForm").unbind("submit").submit(function () {
        var yn = true;
        $(this).find(":text,:password").each(function () {
            if ($.trim($(this).val()) === "") {
                styleUtil.errorShow($("#error_message_p"), "请输入用户名和密码！");
                yn = false;
                return yn;
            }
        });
        if (yn) {
            var obj={};
            obj["userName"]=$.trim($("#name").val());
            obj["userPassword"]=$.trim($("#password").val());
            $.ajax({
                type: "POST",
                url: "/mall/login/doLogin",
                contentType:"application/json",
               // data: {"userName": $.trim($("#name").val()), "userPassword": $.trim($("#password").val())},
                data: JSON.stringify(obj),
                dataType: "json",
                success: function (data) {
                    $(".loginButton").val("登 录");
                    if (data.success) {
                        location.reload();
                    } else {
                        styleUtil.errorShow($("#error_message_p"), "用户名和密码错误！");
                    }
                },
                error: function (data) {
                    $(".loginButton").val("登 录");
                    styleUtil.errorShow($("#error_message_p"), "服务器异常，请刷新页面再试！");
                },
                beforeSend: function () {
                    $(".loginButton").val("正在登录...");
                }
            });
        }
        return false;
    });
    //关闭模态窗口
    $(".closeLoginDiv").click(function () {
        $(".loginModel").hide();
        $(".loginDiv").hide();
    });
    //点击购买按钮时
    $(".context_buy_form").submit(function () {
        if ('${sessionScope.userName}' === "") {//未登录不能购买
            $(".loginModel").show();
            $(".loginDiv").show();
            return false;
        }
        var number = isNaN($.trim($(".context_buymember").val()));
        if (number) {
            location.reload();
        } else {
            location.href = "${pageContext.request.contextPath}/order/create/${requestScope.product.product.productId}?productNumber=" + $.trim($(".context_buymember").val());
        }
        return false;
    });
    //点击加入购物车按钮时
    $(".context_buyCar_form").submit(function () {
        if ('${sessionScope.userName}' === "") {//未登录不能加入购物车
            $(".loginModel").show();
            $(".loginDiv").show();
            return false;
        }
        var number = isNaN($.trim($(".context_buymember").val()));//NAN：非数字值的特殊值
        if (number) {
            location.reload();
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/orderItem/create/${requestScope.product.product.productId}?productNumber=" + $.trim($(".context_buymember").val()),
                type: "POST",
                data: {"productNumber": number},
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $(".msg").stop(true, true).animate({//显示已加入购物车
                            opacity: 1 //透明度
                        }, 550, function () {
                            $(".msg").animate({
                                opacity: 0
                            }, 1500);
                        });
                    } else {
                        if (data.url != null) {
                            location.href = "/mall" + data.url;
                        } else {
                            alert("加入购物车失败，请稍后再试！");
                        }
                    }
                },
                beforeSend: function () {

                },
                error: function () {
                    alert("加入购物车失败，请稍后再试！");
                }
            });
            return false;
        }
    });
});

function getDetailsPage(obj, className) {
    $(".P_TabBarBox").find("li").removeClass("tab-selected");
    $(obj).parent("li").addClass("tab-selected");
    $(".P_choose").children("div").hide();
    $("." + className).show();
}

function SelectorMousemove(e) {
    var $img = $(".context_img_main");
    var $selector = $(".context_img_winSelector");
    var $imgWidth = $img.width();
    var $imgHeight = $img.height();
    var $selectorWidth = $selector.width();
    var $selectorHeight = $selector.height();
    /*扫描器的定位*/
    //获取光标正中位置
    var x = e.pageX - $img.offset().left - $selectorWidth / 2;
    var y = e.pageY - $img.offset().top - $selectorHeight / 2;
    x = x < 0 ? 0 : x;
    y = y < 0 ? 0 : y;
    x = x > $imgWidth - $selectorWidth ? $imgWidth - $selectorWidth : x;
    y = y > $imgHeight - $selectorHeight ? $imgHeight - $selectorHeight : y;
    $selector.css({left: x, top: y});
    //1.917为转换系数
    $('.context_img_ks>img').css({
        left: -x * 1.917,
        top: -y * 1.917
    });
}
//猜你喜欢功能
function getGuessLoveProducts() {
    $.ajax({
        type: "GET",
        url: "/mall/guess/" + $("#tid").val(),
        data: {"guessNumber": $("#guessNumber").val()},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                for (var i = 0; i < data.guessLikeList.length; i++) {
                    var src = data.guessLikeList[i].productImageSrc;
                    var product_id = data.guessLikeList[i].productId;
                    var product_sale_price = data.guessLikeList[i].productSalePrice;
                    $(".context_ul_goodsList").children("ul").append("<li class='context_ul_main'><div class='context_ul_img'>" +
                        "<a href='/mall/product/" + product_id + "'><img src='/mall/res/img/item/productSinglePicture/" + src + "'/></a><p>¥" + product_sale_price + ".00</p></div></li>"
                    );
                }
            }
        }
    });
}