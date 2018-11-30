//todo 做验证
$(function () {
    $("#loginBtn").click(function () {
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
                "kaptcha": kaptcha
            },
            success: function (data) {
                console.log(data);
                if (data.code == 200) {
                    location.href = '/index.html';
                } else {

                }
            }
        });
        //todo 这里的return false ，阻断form表单的get提交
        return false;
    });
});