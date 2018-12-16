$(function () {
    //加载分类数据
    loadCategory();
    initSummernote();
});


$("#publishBlog").click(function () {
    var data = getData();
    $.ajax({
        url: "/blog",
        type: "post",
        success: function () {

        }
    });

});

//获取表单数据
function getData() {
    var headerUrl = $("#header")[0].src;
    var title = $("input[name='title']").val();
    var summary = $("input[name='summary']").val();
    var tags = $("input[name='tags']").val();
    var categoryId = $("input[name='categoryId']").val();
    var content = $("#summernote").summernote("code");
    var data = {
        headerUrl: headerUrl,
        title: title,
        summary: summary,
        tags: tags,
        categoryId: categoryId,
        content: content
    }
    return data;
}

function uploadImage() {
    var title = "上传图片";
    var url = "/page/imageUploadPage";
    var width = $(window).width() / 1.5;
    var height = $(window).height() / 1.5;
    //如果是移动端，就使用自适应大小弹窗
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
        width = 'auto';
        height = 'auto';
    }
    if ($.common.isEmpty(title)) {
        title = false;
    }
    if ($.common.isEmpty(url)) {
        url = "/404.html";
    }
    if ($.common.isEmpty(width)) {
        width = 800;
    }
    if ($.common.isEmpty(height)) {
        height = ($(window).height() - 50);
    }
    layer.open({
        type: 2,
        area: [width + 'px', height + 'px'],
        fix: false,
        //不固定
        maxmin: true,
        shade: 0.3,
        title: title,
        content: url,
        btn: ['<i class="fa fa-check"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
        // 弹层外区域关闭
        shadeClose: true,
        yes: function (index, layero) {
            var iframeWin = layero.find('iframe')[0];
            var data = iframeWin.contentWindow.submitHandler();
            $("#header").attr("src", data);
            iframeWin.contentWindow.closeLocal();
        },
        cancel: function (index) {
            return true;
        }
    });
}

function loadCategory() {
    $.ajax({
        type: "get",
        url: "/api/categoryNoCondition",
        dataType: "json",
        success: function (data) {
            if (data.code == web_status.SUCCESS && data.data != null) {
                var classArray = ["text-navy", "text-danger", "text-primary", "text-muted", "text-info", "text-warning"];
                for (var j = 0; j < data.data.length; j++) {
                    var li = $("<li></li>")
                    var a = $("<a></a>");
                    var i = $("<i></i>");
                    var text = $("<span></span>");
                    //i标签添加class
                    var itemClass = classArray[(j + 1) % classArray.length];
                    i.addClass("fa").addClass("fa-circle").addClass(itemClass);
                    text.html(data.data[j].title);
                    a.append(i);
                    a.append(text);
                    li.append(a);
                    $(".category-list").append(li);
                    var option = $("<option></option>");
                    option.attr("value", data.data[j].categoryId);
                    option.html(data.data[j].title);
                    $("#categorySelect").append(option);
                }

            }
        }
    });
}

var $summernote;

//调用富文本编辑
function initSummernote() {
    $summernote = $('#summernote').summernote({
        height: 300,
        minHeight: null,
        lang: 'zh-CN',
        maxHeight: null,
        focus: true,
        //调用图片上传
        callbacks:
            {
                onImageUpload: function (files) {
                    sendFile($summernote, files[0]);
                },
                onMediaDelete: function (target) {
                    var imgSrc = target.context.currentSrc;
                    console.log(imgSrc);
                    $.ajax({
                        data: {
                            fileUrl: imgSrc
                        },
                        type: "DELETE",
                        url: "/api/summernote/image",
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                        }
                    });
                }
            }
    });

}

//ajax上传图片
function sendFile($summernote, file) {
    var formData = new FormData();
    formData.append("file", file);
    $.ajax({
        url: "/api/summernote/image",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',
        success: function (data) {
            console.log(data);
            $summernote.summernote('insertImage', data, function ($image) {
                $image.attr('src', data);
            });
        }
    });
}