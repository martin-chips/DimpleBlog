
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
    if ($.validateCustom.form("updateLinksFrom")) {
        updateLinks();
    }
}

function updateLinks() {
    $.ajax({
        type: "PUT",
        dataType: "json",
        data: $("#updateLinksFrom").serializeArray(),
        url: "/links",
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