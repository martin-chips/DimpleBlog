$(document).ready(function () {
    $('#summernote').summernote({
        height: 270,
        lang: 'zh-CN',
        onImageUpload: function (files, editor, $editable) {
            sendFile(files[0], 1, $editable);
        }
    });

    function sendFile(file, editor, $editable) {
        data = new FormData();
        data.append("file", file);
        $.ajax({
            data: data,
            type: "POST",
            url: "/uploadImg",
            cache: false,
            contentType: false,
            processData: false,
            //todo 未做验证，需要验证
            success: function (data) {
                $('.summernote').summernote('insertImage', data);//新增窗口的富文本编辑器
            }
        });
    }
});
