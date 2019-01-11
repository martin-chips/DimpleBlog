$(function () {
    //初始化summernote编辑器
    initSummernote("#summernoteBlog");
    initSummernote("#summernoteResource");

    //设置值
    $("#summernoteBlog").summernote("code", $("#blogSignatureInit").val());
    $("#summernoteResource").summernote("code", $("#resourceSignatureInit").val());
});

//清空
$("#giveUpResourceSignature").click(function () {
    cleanForm("#summernoteResource");
});
$("#giveUpBlogSignature").click(function () {
    cleanForm("#summernoteBlog");
});

$("#saveBlogSignature").click(function () {
    var data = {
        id: $("#blogSignatureId").val(),
        signatureKey: "blogSignature",
        signatureValue: $("#summernoteBlog").summernote("code")
    };
    saveSignature("/api/setting/signature", data);
});

$("#saveResourceSignature").click(function () {
    var data = {
        id: $("#blogSignatureId").val(),
        signatureKey: "resourceSignature",
        signatureValue: $("#summernoteResource").summernote("code")
    };
    console.log(data);
    saveSignature("/api/setting/signature", data);
});

function saveSignature(url, data) {
    $.operate.submit(url, "put", "json", data);
}

//清空编辑器数据
function cleanForm(target) {
    var content = $(target).summernote("code", "");
}


//调用富文本编辑
function initSummernote(target) {
    var height = 400;
    $summernote = $(target).summernote({
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
        callbacks: {
            onImageUpload: function (files) {
                sendFile($summernote, files[0]);
            },
            onMediaDelete: function (target) {
                var imgSrc = target.context.currentSrc;
                $.ajax({
                    data: {fileUrl: imgSrc},
                    type: "DELETE",
                    url: "/api/summernote/image",
                    dataType: "json",
                    success: function (data) {
                    }
                });
            }
        }
    });
}

var previewFlag = true;
/**
 * 编辑或者预览
 */
var editOrPreview = function (target, summernote) {
    if (previewFlag) {
        console.log(target);
        var markup = $(summernote).summernote('code');
        $(summernote).summernote('destroy');
        $(target).html("<i class=\"fa fa-pencil\"></i>" + "编辑");
        previewFlag = false;
    } else {
        initSummernote(summernote);
        $(target).html("<i class=\"fa fa-pencil\"></i>" + "预览");
        previewFlag = true;
    }

};

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