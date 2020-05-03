$(function () {
    //二维码动画
    $("#qrcodeA").hover(
        function () {
            $(this).stop().animate({left: "13px"}, 450, function () {
                $("#qrcodeB").stop().animate({opacity: 1}, 300)
            });
        }
        , function () {
            $("#qrcodeB").css("opacity", "0");
            $(this).stop().animate({left: "80px"}, 450);
        });
    //登录方式切换
    $("#loginSwitch").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        if ($(".pwdLogin").css("display") === "block") {//class=pwdLogin的元素的样式是否是以块模式显示
            $(".pwdLogin").css("display", "none");//隐藏
            $(".qrcodeLogin").css("display", "block");//显示
            messageSpan.text("密码登录在这里");
            $(this).removeClass("loginSwitch").addClass("loginSwitch_two");
        } else {
            $(".pwdLogin").css("display", "block");
            $(".qrcodeLogin").css("display", "none");
            messageSpan.text("扫码登录更安全");
            $(this).removeClass("loginSwitch_two").addClass("loginSwitch");
        }
    });
    //密码登录点击事件
    $("#pwdLogin").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        $(".pwdLogin").css("display", "block");
        $(".qrcodeLogin").css("display", "none");
        messageSpan.text("扫码登录更安全");
        $("#loginSwitch").removeClass("loginSwitch_two").addClass("loginSwitch");
    });
    //登录验证
    $(".loginForm").submit(function () {
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
                data: JSON.stringify(obj),
                contentType:"application/json",
                dataType: "json",
                success: function (data) {
                    $(".loginButton").val("登 录");
                    if (data.success) {
                        location.href = "/mall";
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
    $(".loginForm :text,.loginForm :password").focus(function () {
        styleUtil.errorHide($("#error_message_p"));
    });
});