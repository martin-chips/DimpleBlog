//todo 做验证
$(function () {
    $("#loginBtn").click(function () {
        $.modal.loading($("#loginBtn").data("loading"));
        var loginId = $("input[name='loginId']").val().trim();
        var password = $("input[name='password']").val().trim();
        var kaptcha = $("input[name='kaptcha']").val();
        var rememberMe = $("input[name='rememberme']").is(':checked');
        $.ajax({
            type: "post",
            url: "/auth",
            data: {
                "loginId": loginId,
                "password": password,
                "kaptcha": kaptcha,
                "rememberMe": rememberMe
            },
            success: function (data) {
                if (data.code == 200) {
                    location.href = '/index.html';
                } else {
                    $.modal.closeLoading();
                    $(".captchaImg").click();
                    $(".captcha").val("");
                    $.modal.msg(data.msg);
                }
            }
        });
        return false;
    });
});


function validateForm() {
    console.log("111");
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#loginForm").validate({
        rules: {
            loginId: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            loginId: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}