$(function () {
    //刷新下拉框
    $('#select_user_address_province').selectpicker('refresh');
    $('#select_user_address_city').selectpicker('refresh');
    $('#select_user_address_district').selectpicker('refresh');


    //用户名input获取光标
    $("#user_name").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入用户名").css("display", "inline-block").css("color", "#00A0E9");
    });
    //密码input获取光标
    $("#user_password").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //再次输入密码input获取光标
    $("#user_password_one").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请再次输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //昵称input获取光标
    $("#userTel").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入电话号码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //出生日期input获取光标
    $("#user_birthday").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入出生日期").css("display", "inline-block").css("color", "#00A0E9");
    });

    //input离开光标
    $(".form-text").blur(function () {
        $(this).css("border-color", "#cccccc")
            .next().css("display", "none");
    });

    //非空验证
    $("#register_sub").click(function () {
        //用户名
        var user_name = $.trim($("input[name=user_name]").val());
        //密码
        var user_password = $.trim($("input[name=user_password]").val());
        //确认密码
        var user_password_one = $.trim($("input[name=user_password_one]").val());
        //联系电话
        var userTel = $.trim($("input[name=userTel]").val());
        //出生日期
        var user_birthday = $.trim($("input[name=user_birthday]").val());

        //验证密码的格式 包含数字和英文字母
        var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
        var tel=new RegExp(/^1[3456789]\d{9}$/);
        if (user_name == null || user_name === "") {
            $("#user_name").css("border", "1px solid red")
                .next().text("请输入用户名").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_password == null || user_password === "") {
            $("#user_password").css("border", "1px solid red")
                .next().text("请输入密码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_password_one == null || user_password_one === "") {
            $("#user_password_one").css("border", "1px solid red")
                .next().text("请重复输入密码").css("display", "inline-block").css("color", "red");
            return false;
        }else if(!reg.test(user_password)){
            $("#user_password").css("border", "1px solid red")
                .next().text("密码格式必须包含数字和字母").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_password !== user_password_one) {
            $("#user_password_one").css("border", "1px solid red")
                .next().text("两次输入密码不相同").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (userTel == null || userTel === "") {
            $("#userTel").css("border", "1px solid red")
                .next().text("请输入联系电话").css("display", "inline-block").css("color", "red");
            return false;
        }else if (!tel.test(userTel) ) {
            $("#userTel").css("border", "1px solid red")
                .next().text("请输入正确的电话号码").css("display", "inline-block").css("color", "red");
            return false;
        }else if (user_birthday == null || user_birthday === "") {
            $("#user_birthday").css("border", "1px solid red")
                .next().text("请选择出生日期").css("display", "inline-block").css("color", "red");
            return false;
        }

        var obj = {};
        obj['userName'] = user_name;
        obj['userPassword'] = user_password;
        obj['userTel'] = userTel;
        obj['userBirthday'] = user_birthday;
        obj['userGender'] = $("input[name=user_gender]:checked").val();
        $.ajax({
            type: "POST",
            url: "/mall/register/doRegister",
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(obj),

            success: function (data) {
                if (data.success) {
                    $(".msg").stop(true, true).animate({
                        opacity: 1
                    }, 550, function () {
                        $(".msg").animate({
                            opacity: 0
                        }, 1500, function () {
                            location.href = "/mall/login";
                        });
                    });
                } else {
                    alert(data.message);
                }
            },
          error: function (data) {
                location.reload(true);
            },
            beforeSend: function () {
            }
        });
    });
});

