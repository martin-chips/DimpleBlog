$(function () {
    //Switchery按钮初始化
    var elem = document.querySelector('.js-switch');
    var init = new Switchery(elem, {color: '#7c8bc7', jackColor: '#9decff'});
    elem.onchange = function () {
        var isChecked = elem.checked;
        $("#display").val(isChecked);
    };
    $("#addLinksForm").formValidate({
        rules: {
            title: {
                required: true,
                rangelength: [3, 15]
            },
            description: {
                required: true,
                rangelength: [3, 30]
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
        addLinks();
    }
}

//link 提交按钮点击事件
function addLinks() {
    $.ajax({
        type: "POST",
        async: false,
        dataType: "json",
        data: $("#addLinksForm").serializeArray(),
        url: "/api/link",
        success: function (data) {
            if (data != null && data.code == 200) {
                parent.layer.msg('添加成功！', {icon: 1});
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);//关闭当前页
                window.parent.location.reload();
            } else {
                layer.alert('添加失败！', {
                    icon: 5,
                    skin: 'layer-ext-moon'
                });
            }
        }
    });
    return false;
};
