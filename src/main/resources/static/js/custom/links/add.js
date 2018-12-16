$(function () {
    //Switchery按钮初始化
    var elem = document.querySelector('.js-switch');
    var init = new Switchery(elem, {color: '#7c8bc7', jackColor: '#9decff'});
    elem.onchange = function () {
        var isChecked = elem.checked;
        $("#display").val(isChecked);
    };
});

function submitHandler() {
    addLinks();
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
