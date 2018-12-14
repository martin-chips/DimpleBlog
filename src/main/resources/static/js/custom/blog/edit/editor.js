//调用富文本编辑
$(document).ready(function () {
    var $summernote = $('#summernote').summernote({
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
                        url: "/image",
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                        }
                    });
                }
            }
    });

//ajax上传图片
    function sendFile($summernote, file) {
        var formData = new FormData();
        formData.append("file", file);
        $.ajax({
            url: "/image",
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
})
;