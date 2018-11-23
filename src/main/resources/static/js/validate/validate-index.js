$().ready(function () {
    // validate the comment form when it is submitted
    $("#modifyLinksFrom").validate();

    // validate signup form on keyup and submit
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#modifyLinksFrom").validate({
        rules: {
            linkTitle: {
                required: true,
                minlength: 2
            },
            linkDescription: {
                required: true,
                minlength: 5
            },
            linkUrl: {
                required: true,
            },
            // topic: {
            //     required: "#newsletter:checked",
            //     minlength: 2
            // },
            // agree: "required"
        },
        messages: {
            linkTitle: {
                required: icon + "请输入您的用户名",
                minlength: icon + "用户名必须两个字符以上"
            },
            linkDescription: {
                required: icon + "请输入描述",
                minlength: icon + "描述必须5个字符以上"
            },
            linkUrl: icon + "请输入URL地址",
            // confirm_password: {
            //     required: icon + "请再次输入密码",
            //     minlength: icon + "密码必须5个字符以上",
            //     equalTo: icon + "两次输入的密码不一致"
            // },
            // email: icon + "请输入您的E-mail",
            // agree: {
            //     required: icon + "必须同意协议后才能注册",
            //     element: '#agree-error'
            // }
        }

    });
})