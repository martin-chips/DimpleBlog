/**
 * Created by CherryDream on 2016/11/14.
 */
(function ($) {
    $.fn.formValidate = function (options) {
        return $(this).validate({
            focusInvalid: true,
            onkeyup: function (element) {
                $(element).valid();
            },
            submitHandler: function (form) {
                if (options.submit) {
                    options.submit(form);
                }
            },
            resetfunction: function (element) {
                var par = $(element).parent();
                $(element).tooltip("destroy");
                if (par.find("span").length != 0) {
                    par.children("span").remove();
                }
                if (par.hasClass("has-error")) {
                    par.removeClass("has-error");
                }
                if (par.hasClass("has-success")) {
                    par.removeClass("has-success");
                }
            },
            rules: options.rules,
            messages: options.messages,
            errorPlacement: function (error, element) {
                var par = $(element).parent();
                if (par.find("span").length != 0) {
                    par.children("span").remove();
                }
                var info = $('<span class="glyphicon glyphicon-remove-circle form-control-feedback" aria-hidden="true" style="right:20px"></span>');
                element.tooltip("destroy");
                element.tooltip({
                    title: error.text(),
                    placement: 'bottom'
                })
                if (par.find(".glyphicon").length == 0) {
                    info.appendTo(par);
                }
                par.addClass("has-error");
                $(element).tooltip("show");
            },
            success: function (str, element) {
                var par = $(element).parent();
                if ($(element).hasClass("selectpicker"))//验证selectpicker
                {
                    if (par.hasClass("bootstrap-select")) {
                        var par2 = par.parent();
                        par2.removeClass("has-error");
                        par2.addClass("has-success");
                    } else {
                        var nex = $(element).next();
                        nex.removeClass("has-error");
                        nex.addClass("has-success");
                    }
                } else {
                    if (par.find("span").length != 0) {
                        par.children("span").remove();
                    }
                    var info = $('<span class="glyphicon glyphicon-ok-circle form-control-feedback" aria-hidden="true" style="right:20px"></span>');
                    par.removeClass("has-error");
                    par.addClass("has-success");
                    if (par.find(".glyphicon").length == 0) {
                        info.appendTo(par);
                    }
                }
            }
        });
    }
})(jQuery)