$(function () {
    //加载编辑器
    initSummernote();
    loadCategory();
    $("#summernote").summernote("code", $("#blogContentInit").val());
});
/**
 * 编辑或者预览
 */
var previewFlag = true;

var editOrPreview = function (target) {
    if (previewFlag) {
        var markup = $('.summernote').summernote('code');
        $('.summernote').summernote('destroy');
        $(target).html("<i class=\"fa fa-pencil\"></i>" + "编辑");
        previewFlag = false;
    } else {
        // $('.summernote').summernote({focus: true});
        initSummernote();
        $(target).html("<i class=\"fa fa-pencil\"></i>" + "预览");
        previewFlag = true;
    }

};

/**
 * 加载分类
 */
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
                var checked = $('#blogCategoryId').val();
                $("#categorySelect").val(checked);

            }
        }
    });
}

/**
 * 上传图片
 */
function uploadImage() {
    var title = "上传图片";
    var url = "/page/imageUploadPage";
    var width = $(window).width() / 1.5;
    var height = $(window).height() / 1.2;
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

var $summernote;

//调用富文本编辑
function initSummernote() {
    var height = $(window).height() / 1.5;
    $summernote = $('#summernote').summernote({
        height: height,
        minHeight: null,
        lang: 'zh-CN',
        maxHeight: null,
        focus: true,
        addclass: {
            debug: false,
            classTags: [{
                title: "Button",
                "value": "btn btn-success"
            }, "jumbotron", "lead", "img-rounded", "img-circle",
                "img-responsive", "btn", "btn btn-success", "btn btn-danger",
                "text-muted", "text-primary", "text-warning", "text-danger",
                "text-success", "table-bordered", "table-responsive", "alert",
                "alert alert-success", "alert alert-info", "alert alert-warning",
                "alert alert-danger", "visible-sm", "hidden-xs", "hidden-md",
                "hidden-lg", "hidden-print"]
        },
        toolbar: [
            ['style', ['style', 'addclass', 'clear']],
            ['font', ['bold', 'underline', 'clear']],
            ['highlight', ['highlight']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ],
        //调用图片上传

        callbacks:
            {
                onImageUpload: function (files) {
                    sendFile($summernote, files[0]);
                },
                onMediaDelete: function (target) {
                    var imgSrc = target.context.currentSrc;
                    $.ajax({

                        data: {
                            fileUrl: imgSrc
                        },
                        type: "DELETE",
                        url: "/api/summernote/image",
                        dataType: "json",
                        success: function (data) {
                            // console.log(data);
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
        success: function (result) {
            if (result.code == web_status.SUCCESS) {
                $summernote.summernote('insertImage', result.data, function ($image) {
                    $image.attr('src', result.data);
                });
            } else {
                $.modal.alertError(result.msg);
            }
        }
    });
}