
function initCommentEditor(space) {
    var editor = ' <div id="CommentEditor">\n' +
        '                <form id="myform">\n' +
        '                    <div class="select_star_wrap ">\n' +
        '                        <div class="select_star">\n' +
        '                            <span class="sp">信息获取：</span>\n' +
        '                            <span class="i_star_0">\n' +
        '                                <input name="qqNum" type="number" id="qqNum" class="text" value="" maxlength="49"\n' +
        '                                       placeholder="填入qq自动获取信息" tabindex="0">\n' +
        '                            </span>\n' +
        '                            <span id="error"></span>\n' +
        '                            <span id="ajaxloading"></span>\n' +
        '                        </div>\n' +
        '                        <div class="select_star right">\n' +
        '                            <span class="sp">您的称呼：</span>\n' +
        '                            <span class="i_star_0">\n' +
        '                                <input name="nickname" type="text" class="text" id="nickname" value="" maxlength="16"\n' +
        '                                       placeholder="您的称呼 必须">\n' +
        '                            </span>\n' +
        '                        </div>\n' +
        '                        <div class="select_star">\n' +
        '                            <span class="sp">您的邮箱：</span>\n' +
        '                            <span class="i_star_0">\n' +
        '                                <input name="email" type="text" id="email" class="text" value="" maxlength="35"\n' +
        '                                       placeholder="您的邮箱 选填">\n' +
        '                                <span class="sel_check_form" data-name="is_email"\n' +
        '                                      style="position: relative;left: 8px;top: 8px;">\n' +
        '\t\t\t\t                    <span class="display0">\n' +
        '                                        <input type="checkbox" id="notice" name="notice"\n' +
        '                                               value="1" checked="">\n' +
        '                                    </span>\n' +
        '                   <span class="gx_check current" data-val="1">邮件通知</span>\n' +
        '                </span>\n' +
        '                            </span>\n' +
        '                        </div>\n' +
        '                        <div class="select_star right">\n' +
        '                            <span class="sp">您的网站：</span>\n' +
        '                            <span class="i_star_0">\n' +
        '                                <input name="u_url" type="text" class="text" id="u_url" maxlength="35" value=""\n' +
        '                                       placeholder="您的网站 选填">\n' +
        '                            </span>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                    <div class="clear"></div>\n' +
        '                    <textarea id="markdownEditor"></textarea>\n' +
        '                    <div class="feed-sub" style="width: 100%">\n' +
        '                        <input type="button" class="btn btn-inverse" onclick="leaveComment()" value="提交留言">\n' +
        '                    </div>\n' +
        '                </form>\n' +
        '            </div>'
    $(space).append(editor);
    initSimpleMDE("markdownEditor")
}

var simplemde;

function initSimpleMDE(id) {
    simplemde = new SimpleMDE({
        element: document.getElementById(id),
        toolbar: ["bold", "italic", "|", "code", "quote", "|", "preview", "|", "link", "|", "image"],
        autoDownloadFontAwesome: false,
        // autofocus: true,
        placeholder: "说点什么吧",
        renderingConfig: {
            codeSyntaxHighlighting: true
        },
        tabSize: 4
    });
}

function reply(commentId) {
    //设置replyId
    $("#replyId").val(commentId);
    $(".cancel-reply").remove();
    $(".comment-reply").show();
    //去除页面上回复框
    $("#CommentEditor").remove();
    initCommentEditor("#comment-" + commentId);
    //设置取消回复按钮
    $("#commentDiv-" + commentId).after("<a class=\"cancel-reply\" id=\"cancel-reply\" style=\"\" href=\"javascript:void(0);\" onclick='cancelReply()' rel=\"external nofollow\"><i class=\"fa fa-share\"></i>取消回复</a>")
    //隐藏回复按钮
    $("#reply-" + commentId).hide();
}

/**
 * 取消回复
 */
function cancelReply() {
    $("#replyId").val("");
    $(".comment-reply").show();
    $("#CommentEditor").remove();
    $(".cancel-reply").remove();
    initCommentEditor("#Space");
}

function checkForm() {
    if ($("#nickname").val().trim() == "") {
        layer.msg('称呼不能为空', {icon: 2});
        return false;
    }

    if ($("#email").val().trim() == "") {
        layer.msg('邮箱不能为空喔', {icon: 2});
        return false;
    } else {
        var reg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
        var email = $("#email").val();
        if (!reg.test(email)) {
            layer.msg('邮箱格式错误', {icon: 2});
            return false;
        }
    }
    var markdownText = simplemde.value().trim();
    var htmlText = simplemde.markdown(markdownText);
    if (htmlText == "" || htmlText.length < 2) {
        layer.msg('亲啥都没写喔，或者内容太少啦！', {icon: 2});
        return false;
    }
    return true;
}

function leaveComment() {
    if (!checkForm()) {
        return;
    }
    var data = {
        pageId: $("#pageId").val(),
        nickname: $("#nickname").val(),
        qqNum: $("#qqNum").val(),
        url: window.location.href,
        replyId: $("#replyId").val(),
        email: $("#email").val(),
        avatar: $("#avatar").attr("src"),
        content: simplemde.markdown(simplemde.value().trim()),
        notice: $("#notice").val()
    }
    //留言发送
    $.ajax({
        type: "POST",
        url: "/f/comment",
        data: data,
        success: function (data) {
            if (data.code == 200) {
                //刷新当前评论框
                layer.msg("评论成功!", {icon: 1});
                $.ajax({
                    type: "get",
                    data: {pageId: $("#pageId").val()},
                    url: "/f/comment",
                    success: function (data) {
                        $("#Comment").html(data);
                        initCommentEditor("#Space");
                    }
                })
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }
    });
}


$("body").on("blur", "#qqNum", function () {
    var qqNumStr = $('#qqNum').val();
    if (qqNumStr == "") {
        return false;
    }
    var data = {qqNum: qqNumStr}
    $("#ajaxloading").html('<img src="/front/images/loading-sm.gif"><a style="font-size:12px;margin-left:5px;">正在获取QQ信息..</a>');
    $.ajax({
        type: "GET",
        url: "/f/qqInfo",
        data: data,
        success: function (data, textStatus) {
            if (data.code == 200) {
                $('#nickname').val(data.qqInfo.nickname);
                $('#email').val(data.qqInfo.email);
                $("#ajaxloading").html('<img id="avatar" style="width:24px;border-radius:100px" src="' + data.qqInfo.avatar + '">');
            } else {
                $("#ajaxloading").hide();
                $("#error").html('qq账号错误').show().fadeOut(4000);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#ajaxloading").hide();
            $("#error").html('qq账号错误').show().fadeOut(4000);
        }
    });
})
function good(event, commentId) {
    var cookie = getCookie("comment-good-" + commentId);
    console.log(cookie)
    if (cookie != null) {
        layer.msg("一分钟只能点赞一次哦", {icon: 2});
        return;
    }
    setCookie("comment-good-" + commentId, commentId, 1);
    animation(event, "<i class=\"fa fa-smile-o\"></i>+1");
    updateGoodBadCount(commentId, "good");
}

/**
 * 动画
 */
function animation(event, content) {
    var $i = $("<span></span>").html(content);
    var x = event.pageX,
        y = event.pageY;
    $i.css({
        "z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
        "top": y - 20,
        "left": x,
        "position": "absolute",
        "font-weight": "bold",
        "color": "rgb(" + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + ")"
    });
    $("body").append($i);
    $i.animate({
            "top": y - 180,
            "opacity": 0
        },
        1500,
        function () {
            $i.remove();
        });
}

function updateGoodBadCount(commentId, status) {
    $.ajax({
        url: status === 'good' ? "/f/comment/good" : "/f/comment/bad",
        data: {
            commentId: commentId,
            pageId: $("#pageId").val()
        },
        type: "put",
        success: function (data) {
            if (data.code == 200) {
                var target = status == "good" ? $("#good-count-" + commentId) : $("#bad-count-" + commentId);
                target.text(target.text() * 1 + 1);
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }
    });
}

function bad(event, commentId) {
    var cookie = getCookie("comment-bad-" + commentId);
    if (cookie != null) {
        layer.msg("一分钟只能踩一次哦", {icon: 2});
        return;
    }
    setCookie("comment-bad-" + commentId, commentId, 1);
    animation(event, "<i class=\"fa fa-meh-o\"></i>+1");
    updateGoodBadCount(commentId, "bad");
}