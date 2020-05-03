$(function () {
    $(".nav_col > a").attr("href", "/mall/product/" + $("#banner1").attr("name"));
    //单击商品分类链接时
    $(".banner_div a").click(function () {
        $(".nav_col").unbind("click");
    });
    //悬浮到分类导航时
    $(".banner_nav>li").hover(function () {
        $(this).find(">a").css("color", "#6347ED");
        var div = $(this).find(">.banner_div").css({"display":"block","position":"absolute","float":"left","background-color":"white"});
        if ($(this).attr("data-status") === "ajaxShow") {
            return;
        }
        $(this).attr("data-status", "ajaxShow");
        $.ajax({
            type: "GET",
            url: "product/nav/" + $(this).attr("data-toggle"),
            data: null,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    var list = data.complexProductList;
                    for (var i = 0; i < list.length; i++) {
                        if (list[i].length === 0) {
                            continue;
                        }
                        div.append("<div class='hot_word'></div>");
                        var hot_word_div = div.children(".hot_word").last();
                        for (var j = 0; j < list[i].length; j++) {
                            var product_title = list[i][j].productTitle;
                            var index = product_title.indexOf(' ');
                            if (index !== -1) {
                                product_title = product_title.substring(0, index);
                            }
                            hot_word_div.append("<a href='product/" + list[i][j].productId + "'>" + product_title + "</a>");
                        }
                    }
                    //热词样式
                    div.find("a").each(function () {
                        var random = parseInt(Math.random() * 10);
                        if (random > 7) {
                            $(this).css("color", "#6347ED");
                        }
                    });
                }
            },
            error: function (data) {

            },
            beforeSend: function () {

            }
        });
    }, function () {
        $(this).find(">a").css("color", "#FFFFFF");
        $(this).find(".banner_div").css("display", "none");
    });
    //搜索框验证
    $('form').submit(function () {
        if ($(this).find("input[name='product_name']").val() === "") {
            alert("请输入关键字！");
            return false;
        }
    });
});

//滑动间隔时间
$(".carousel").carousel({
    interval:3000
})

// $(".collapse").collapse();
// $(".collapse.in").collapse('hide');
// $(function () {
// //修复collapse不能正常折叠的问题
//     $(".collapsed").click(function () {
//         var itemHref = $(this).attr("href");
//         var itemClass = $(itemHref).attr("class");
//         if (itemClass === "panel-collapse collapse") {
//             $(itemHref).attr("class", "panel-collapse collapse in").css("height", "auto");
//         } else {
//             $(itemHref).attr("class", "panel-collapse collapse").css("height", "0px");
//         }
//         return false;//停止运行bootstrap自带的函数
//     });
// })