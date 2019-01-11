/*!
 * summernote highlight plugin
 * http://www.hyl.pw/
 *
 * Released under the MIT license
 */
(function (factory) {
    /* global define */
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else if (typeof module === 'object' && module.exports) {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals: jQuery
        factory(window.jQuery);
    }
}(function ($) {

    // Extends plugins for adding highlight.
    //  - plugin is external module for customizing.
    $.extend($.summernote.plugins, {

        /**
         * @param {Object} context - context object has status of editor.
         */
        'highlight': function (context) {

            var self = this;

            var ui = $.summernote.ui;
            var $editor = context.layoutInfo.editor;
            var options = context.options;
            var lang = options.langInfo;

            // add button

            context.memo('button.highlight', function () {
                // create button
                var button = ui.button({
                    contents: '<i class="fa fa-file-code-o"></i>',
                    tooltip: '插入高亮代码',
                    click: function () {
                        self.show()
                    }
                });

                // create jQuery object from button instance.
                var $highlight = button.render();
                return $highlight;
            });

            this.createDialog = function () {

                var $box = $('<div />');
                var $selectGroup = $('<div class="form-group" />');
                var $textGroup = $('<div class="form-group" />');
                var $select = $('<select class="form-control ext-highlight-select" />');

                var languages = [
                    'java', 'js', 'html', 'cs', 'cc', 'cpp', 'htm', 'php', 'py', 'sh', 'c', 'bsh',
                ];

                for (var i = 0; i < languages.length; i++) {
                    $select.append('<option value="' + languages[i] + '">' + languages[i] + '</option>');
                }

                var $label = $('<label />');
                $label.html('选择语言');
                $box.append($selectGroup.append($label));
                $box.append($selectGroup.append($select));

                var $label = $('<label />');
                $label.html('代码内容');
                var $textarea = $('<textarea class="ext-highlight-code form-control" rows="10" style="height: auto" />');

                $box.append($textGroup.append($label));
                $box.append($textGroup.append($textarea));

                return $box.html();
            };

            this.createCodeNode = function (code, select) {
                var $code = $('<code>');
                $code.html(code);
                $code.addClass('language-' + select);

                var $pre = $('<pre>');
                $pre.html($code)
                $pre.addClass('prettyprint').addClass('linenums');

                return $pre[0];
            };

            this.showHighlightDialog = function (codeInfo) {
                return $.Deferred(function (deferred) {
                    var $extHighlightCode = self.$dialog.find('.ext-highlight-code'), $code;
                    var $extHighlightBtn = self.$dialog.find('.ext-highlight-btn');
                    var $extHighlightSelect = self.$dialog.find('.ext-highlight-select');

                    ui.onDialogShown(self.$dialog, function () {

                        $extHighlightCode.val(codeInfo);

                        $extHighlightCode.on('input', function () {
                            ui.toggleBtn($extHighlightBtn, $extHighlightCode.val() != '');
                            codeInfo = $extHighlightCode.val();
                        });

                        $extHighlightBtn.one('click', function (event) {
                            event.preventDefault();
                            console.log();
                            console.log($extHighlightSelect.val());
                            //转义后的html
                            $code = $extHighlightCode.val().replace(/[<>&"]/g, function (c) {
                                return {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;'}[c];
                            });
                            // deferred.resolve(self.createCodeNode($extHighlightCode.val(), $extHighlightSelect.val()));
                            deferred.resolve(self.createCodeNode($code, $extHighlightSelect.val()));
                        });
                    });

                    ui.onDialogHidden(self.$dialog, function () {
                        $extHighlightBtn.off('click');
                        if (deferred.state() === 'pending') {
                            deferred.reject();
                        }
                    });

                    ui.showDialog(self.$dialog);
                });
            };

            this.getCodeInfo = function () {
                var text = context.invoke('editor.getSelectedText');
                return '';
            };

            this.show = function () {
                var codeInfo = self.getCodeInfo();
                context.invoke('editor.saveRange');
                this.showHighlightDialog(codeInfo).then(function (codeInfo) {
                    self.$dialog.modal('hide');
                    context.invoke('editor.restoreRange');

                    if (codeInfo) {
                        context.invoke('editor.insertNode', codeInfo);
                    }
                });
            };

            //// This events will be attached when editor is initialized.
            //this.event = {
            //    // This will be called after modules are initialized.
            //    'summernote.init': function (we, e) {
            //        console.log('summernote initialized', we, e);
            //    },
            //    // This will be called when user releases a key on editable.
            //    'summernote.keyup': function (we, e) {
            //        console.log('summernote keyup', we, e);
            //    }
            //};
            //
            //// This method will be called when editor is initialized by $('..').summernote();
            //// You can create elements for plugin
            this.initialize = function () {
                var $container = options.dialogsInBody ? $(document.body) : $editor;

                var body = [
                    '<button href="#" class="btn btn-primary ext-highlight-btn disabled" disabled>',
                    '插入代码',
                    '</button>'
                ].join('');

                this.$dialog = ui.dialog({
                    className: 'ext-highlight',
                    title: '插入代码',
                    body: this.createDialog(),
                    footer: body,
                    //callback: function ($node) {
                    //    $node.find('.modal-body').css({
                    //        'max-height': 300,
                    //        'overflow': 'scroll'
                    //    });
                    //}
                }).render().appendTo($container);
            };

            // This methods will be called when editor is destroyed by $('..').summernote('destroy');
            // You should remove elements on `initialize`.
            this.destroy = function () {
                ui.hideDialog(this.$dialog);
                this.$dialog.remove();
            };
        }
    });
}));
