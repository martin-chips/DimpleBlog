$(function () {
    initTags();
});

function initTags() {
    $.ajax({
        url: "/api/tags",
        type: "get",
        dataType: "json",
        success: function (result) {
            console.log(result);
            if (result.code = web_status.SUCCESS) {
                var data = result.data;
                var usedNode = $("#used");
                var deleteNode = $("#deleted");
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    var classEle = ["warning-element", "success-element", "danger-element", "info-element"];
                    var element = $("<li></li>");
                    console.log(classEle[i % 4]);
                    element.addClass(classEle[i % 4]);
                    element.append(item.name);
                    var detail = $("<div></div>");
                    detail.append("<a href=\"#\" class=\"pull-right btn btn-xs btn-white\">标签</a>");
                    var time = $("<i></i>");
                    time.addClass("fa fa-clock-o");
                    time.append(item.createTime);
                    detail.append(time);
                    element.append(detail);
                    node.append(element);
                }

            }

        }
    });
}