$(function () {
    //刷新下拉框
   /* $('#select_user_address_province').selectpicker('refresh');
    $('#select_user_address_city').selectpicker('refresh');
    $('#select_user_address_district').selectpicker('refresh');
    //改变订单信息时
    $('#select_user_address_province').change(function () {
        $.ajax({
            type: "GET",
            url: "/mall/address/" + $(this).val(),
            data: null,
            dataType: "json",
            success: function (data) {
                $(".loader").hide();
                if (data.success) {
                    $("#select_user_address_city").empty();
                    $("#select_user_address_district").empty();
                    for (var i = 0; i < data.addressList.length; i++) {
                        var address_id = data.addressList[i].address_areaId;
                        var address_name = data.addressList[i].address_name;
                        $("#select_user_address_city").append("<option value='" + address_id + "'>" + address_name + "</option>")
                    }
                    for (var j = 0; j < data.childAddressList.length; j++) {
                        var childAddress_id = data.childAddressList[j].address_areaId;
                        var childAddress_name = data.childAddressList[j].address_name;
                        $("#select_user_address_district").append("<option value='" + childAddress_id + "'>" + childAddress_name + "</option>")
                    }
                    $('#select_user_address_city').selectpicker('refresh');
                    $("#select_user_address_district").selectpicker('refresh');
                    $("span.address-province").text($("#select_user_address_province").find("option:selected").text());
                    $("span.address-city").text($("#select_user_address_city").find("option:selected").text());
                    $("span.address_district").text($("#select_user_address_district").find("option:selected").text());
                } else {
                    alert("加载地区信息失败，请刷新页面再试！")
                }
            },
            beforeSend: function () {
                $(".loader").show();
            },
            error: function () {
                alert("加载地区信息失败，请刷新页面再试！")
            }
        });
    });
    $("#select_user_address_city").change(function () {
        $.ajax({
            type: "GET",
            url: "/mall/address/" + $(this).val(),
            data: null,
            dataType: "json",
            success: function (data) {
                $(".loader").hide();
                if (data.success) {
                    $("#select_user_address_district").empty();
                    for (var i = 0; i < data.addressList.length; i++) {
                        var address_id = data.addressList[i].address_areaId;
                        var address_name = data.addressList[i].address_name;
                        $("#select_user_address_district").append("<option value='" + address_id + "'>" + address_name + "</option>")
                    }
                    $('#select_user_address_district').selectpicker('refresh');
                    $("span.address-city").text($("#select_user_address_city").find("option:selected").text());
                    $("span.address_district").text($("#select_user_address_district").find("option:selected").text());
                } else {
                    alert("加载地区信息失败，请刷新页面再试！")
                }
            },
            beforeSend: function () {
                $(".loader").show();
            },
            error: function () {
                alert("加载地区信息失败，请刷新页面再试！")
            }
        });
    });
    $("#select_user_address_district").change(function () {
        $("span.address_district").text($(this).find("option:selected").text());
    });*/


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

    //出生日期input获取光标
    $("#user_birthday").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入出生日期").css("display", "inline-block").css("color", "#00A0E9");
    });

    //电话号码input获取光标
    $("#userTel").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入电话号码").css("display", "inline-block").css("color", "#00A0E9");
    });

    //input离开光标
    $(".form-text").blur(function () {
        $(this).css("border-color", "#cccccc")
            .next().css("display", "none");
    });

    //非空验证
    $("#register_sub").click(function () {
        //图片路径#user_profile_picture_src_value
        var userProfilePictureSrc =$.trim($("input[name=user_profile_picture_src]").val());
        //联系电话
        var userTel = $.trim($("input[name=userTel]").val());
        //出生日期
        var user_birthday = $.trim($("input[name=user_birthday]").val());
        //验证电话号码格式
        var tel=new RegExp(/^1[3456789]\d{9}$/);
        if (userTel == null || userTel === "") {
            $("#userTel").css("border", "1px solid red")
                .next().text("请输入联系电话").css("display", "inline-block").css("color", "red");
            return false;
        }else if (!tel.test(userTel) ) {
            $("#userTel").css("border", "1px solid red")
                .next().text("请输入正确的电话号码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_birthday == null || user_birthday === "") {
            $("#user_birthday").css("border", "1px solid red")
                .next().text("请选择出生日期").css("display", "inline-block").css("color", "red");
            return false;
        }
        var obj = {};
        obj['userTel'] = userTel;
        obj['userBirthday'] = user_birthday;
        obj['userGender'] = $("input[name=user_gender]:checked").val();
        obj['userProfilePictureSrc'] = userProfilePictureSrc;
        $.ajax({
            type: "POST",
            url: "/mall/user/updateBasic",
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(obj),

            success: function (data) {
                if (data.success) {
                            location.href = "/mall/userDetails";
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

    $("#register_sub1").click(function () {
        //用户名
        var user_name = $.trim($("input[name=user_name1]").val());
        //密码
        var user_password = $.trim($("input[name=user_password]").val());
        //确认密码
        var user_password_one = $.trim($("input[name=user_password_one]").val());

        var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
        if (user_name == null || user_name === "") {
            $("#user_name1").css("border", "1px solid red")
                .next().text("请输入用户名").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (user_password == null || user_password === "") {
            $("#user_password").css("border", "1px solid red")
                .next().text("请输入密码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_password_one == null || user_password_one === "") {
            $("#user_password_one").css("border", "1px solid red")
                .next().text("请重复输入密码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (!reg.test(user_password)) {
            $("#user_password").css("border", "1px solid red")
                .next().text("密码格式必须包含数字和字母").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_password !== user_password_one) {
            $("#user_password_one").css("border", "1px solid red")
                .next().text("两次输入密码不相同").css("display", "inline-block").css("color", "red");
            return false;
        }

        var obj = {};
        obj['userName'] = user_name;
        obj['userPassword'] = user_password;

        $.ajax({
            type: "POST",
            url: "/mall/user/updateAccount",
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



//图片上传
function uploadImage(fileDom) {
    //获取文件
    var file = fileDom.files[0];
    //判断类型
    var imageType = /^image\//;
    if (file === undefined || !imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //判断大小
    if (file.size > 512000) {
        alert("图片大小不能超过500K！");
        return;
    }
    //清空值
    $(fileDom).val('');
    var formData = new FormData();
    formData.append("file", file);
    //上传图片
    $.ajax({
        url: "/mall/user/uploadUserHeadImage",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            if (data.success) {
                $(fileDom).prev("img").attr("src","/mall/res/img/item/userProfilePicture/"+data.fileName);
                $("#user_profile_picture_src_value").val(data.fileName);
            } else {
                alert("图片上传异常！");
            }
        },
        beforeSend: function () {
        },
        error: function () {

        }
    });
}
//页面隐藏与显示
function getDataPage(obj, className) {
    $(".J_TabBarBox").find("li").removeClass("tab-selected");
    $(obj).parent("li").addClass("tab-selected");
    $(".J_choose").children("div").hide();
    $("." + className).show();
}