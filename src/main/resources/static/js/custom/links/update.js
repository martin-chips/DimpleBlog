$(function () {
    //Switchery按钮初始化
    var elem = document.querySelector('.js-switch');
    var init = new Switchery(elem, {color: '#7c8bc7', jackColor: '#9decff'});
    elem.onchange = function () {
        var isChecked = elem.checked;
        $("#display").val(isChecked);
    };
    $("#updateLinksFrom").formValidate({
        rules: {
            title: {
                required: true,
                rangelength: [3, 80]
            },
            description: {
                required: true,
                rangelength: [3, 80]
            },
            url: {
                required: true,
                url: true
            },

            weight: {
                required: true,
                number: true
            }
        },
        messages: {
            "title": {
                required: "请输入友链名称",
                rangelength: "请输入长度为 {0} 至 {1} 之间的友链名称"
            },
            "description": {
                required: "请输入友链描述",
                rangelength: "请输入长度为 {0} 至 {1} 之间的友链描述"
            },
            "url": {
                required: "请输入友链地址",
                url: "请输入有效的友链地址"
            },
            "weight": {
                required: "请输入友链权重",
                number: "请输入有效的数字"
            }
        },
    });
});

function submitHandler() {
    if ($.validate.form()) {
        updateLinks();
    }
}

function updateLinks() {
    $.ajax({
        type: "PUT",
        dataType: "json",
        data: $("#updateLinksFrom").serializeArray(),
        url: "/api/link",
        success: function (data) {
            if (data != null && data.code == web_status.SUCCESS) {
                $.modal.msgReload("修改成功！", modal_status.SUCCESS);
            } else {
                $.modal.msgReload("修改失败！", modal_status.FAIL);
            }
        }
    });
    return false;
}