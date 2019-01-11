/**
 *
 * copyright 2016 creativeprogramming.it di Stefano Gargiulo
 * email: info@creativeprogramming.it
 * accepting tips at https://www.paypal.me/creativedotit
 * license: MIT
 *
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
        // Browser globals
        factory(window.jQuery);
    }
}(function ($) {

    // Extends plugins for adding hello.
    //  - plugin is external module for customizing.
    $.extend($.summernote.plugins, {
        /**
         * @param {Object} context - context object has status of editor.
         */
        'addclass': function (context) {
            var self = this;
            if (typeof context.options.addclass === 'undefined') {
                context.options.addclass = {};
            }
            if (typeof context.options.addclass.classTags === 'undefined') {
                context.options.addclass.classTags = ["jumbotron", "lead", "img-rounded", "img-circle", "img-responsive", "btn", "btn btn-success", "btn btn-danger", "text-muted", "text-primary", "text-warning", "text-danger", "text-success", "table-bordered", "table-responsive", "alert", "alert alert-success", "alert alert-info", "alert alert-warning", "alert alert-danger", "visible-sm", "hidden-xs", "hidden-md", "hidden-lg", "hidden-print"];
                //  console.log("Please define your summernote.options.addclass.classTags array");
            }
            // ui has renders to build ui elements.
            //  - you can create a button with `ui.button`
            var ui = $.summernote.ui;

            addStyleString(".scrollable-menu {height: auto; max-height: 200px; max-width:300px; overflow-x: hidden;}");

            context.memo('button.addclass', function () {
                return ui.buttonGroup([
                    ui.button({
                        className: 'dropdown-toggle',
                        contents: '<i class="fa fa-css3"\/>' + ' ' + ui.icon(context.options.icons.caret, 'span'),
                        //ui.icon(context.options.icons.magic) + ' ' + ui.icon(context.options.icons.caret, 'span'),
                        tooltip: '更换样式', //lang.style.style,
                        data: {
                            toggle: 'dropdown'
                        }
                    }),
                    ui.dropdown({
                        className: 'dropdown-style scrollable-menu',
                        items: context.options.addclass.classTags,
                        template: function (item) {

                            if (typeof item === 'string') {
                                item = {tag: "div", title: item, value: item};
                            }

                            var tag = item.tag;
                            var title = item.title;
                            var style = item.style ? ' style="' + item.style + '" ' : '';
                            var cssclass = item.value ? ' class="' + item.value + '" ' : '';


                            return '<' + tag + ' ' + style + cssclass + '>' + title + '</' + tag + '>';
                        },
                        click: function (event, namespace, value) {

                            event.preventDefault();
                            value = value || $(event.target).closest('[data-value]').data('value');


                            var $node = $(context.invoke("restoreTarget"))
                            if ($node.length == 0) {
                                $node = $(document.getSelection().focusNode.parentElement, ".note-editable");
                            }

                            if (typeof context.options.addclass !== 'undefined' && typeof context.options.addclass.debug !== 'undefined' && context.options.addclass.debug) {
                                console.debug(context.invoke("restoreTarget"), $node, "toggling class: " + value, window.getSelection());
                            }


                            $node.toggleClass(value)


                        }
                    })
                ]).render();
                return $optionList;
            });

            function addStyleString(str) {
                var node = document.createElement('style');
                node.innerHTML = str;
                document.body.appendChild(node);
            }

            // This events will be attached when editor is initialized.
            this.events = {
                // This will be called after modules are initialized.
                'summernote.init': function (we, e) {
                    //console.log('summernote initialized', we, e);
                },
                // This will be called when user releases a key on editable.
                'summernote.keyup': function (we, e) {
                    //  console.log('summernote keyup', we, e);
                }
            };

            // This method will be called when editor is initialized by $('..').summernote();
            // You can create elements for plugin
            this.initialize = function () {

            };

            // This methods will be called when editor is destroyed by $('..').summernote('destroy');
            // You should remove elements on `initialize`.
            this.destroy = function () {
                /*  this.$panel.remove();
                 this.$panel = null; */
            };
        }
    });
}));