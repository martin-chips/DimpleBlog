$(function () {
    $('.captchaImg').click(function () {
        var url = "/public/api/kaptcha?time=" + Math.random();
        $(".captchaImg").attr("src", url);
    });

    $("#loginBtn").click(function () {
        $.modal.loading("正在加载中");
        let loginId = $("input[name='loginId']").val().trim();
        let password = $("input[name='password']").val().trim();
        let kaptcha = $("input[name='kaptcha']").val();
        let rememberMe = $("input[name='rememberMe']").is(':checked');
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
                    location.href = '/page/index.html';
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

/**
 * 更改验证码
 */
function changeCaptcha() {

}

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