$(function () {

    //自定义js

//公共配置


    $(document).ready(function () {

        // MetsiMenu
        $('#side-menu').metisMenu();

        // 打开右侧边栏
        $('.right-sidebar-toggle').click(function () {
            $('#right-sidebar').toggleClass('sidebar-open');
        });

        // 右侧边栏使用slimscroll
        $('.sidebar-container').slimScroll({
            height: '100%',
            railOpacity: 0.4,
            wheelStep: 10
        });

        // Small todo handler
        $('.check-link').click(function () {
            var button = $(this).find('i');
            var label = $(this).next('span');
            button.toggleClass('fa-check-square').toggleClass('fa-square-o');
            label.toggleClass('todo-completed');
            return false;
        });

        //固定菜单栏
        $(function () {
            $('.sidebar-collapse').slimScroll({
                height: '100%',
                railOpacity: 0.9,
                alwaysVisible: false
            });
        });


        // 菜单切换
        $('.navbar-minimalize').click(function () {
            $("body").toggleClass("mini-navbar");
            SmoothlyMenu();
        });


        // 侧边栏高度
        function fix_height() {
            var heightWithoutNavbar = $("body > #wrapper").height() - 61;
            $(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
        }

        fix_height();

        $(window).bind("load resize click scroll", function () {
            if (!$("body").hasClass('body-small')) {
                fix_height();
            }
        });

        //侧边栏滚动
        $(window).scroll(function () {
            if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
                $('#right-sidebar').addClass('sidebar-top');
            } else {
                $('#right-sidebar').removeClass('sidebar-top');
            }
        });

        $('.full-height-scroll').slimScroll({
            height: '100%'
        });

        $('#side-menu>li').click(function () {
            if ($('body').hasClass('mini-navbar')) {
                NavToggle();
            }
        });
        $('#side-menu>li li a').click(function () {
            if ($(window).width() < 769) {
                NavToggle();
            }
        });

        $('.nav-close').click(NavToggle);

        //ios浏览器兼容性处理
        if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
            $('#content-main').css('overflow-y', 'auto');
        }

    });

    $(window).bind("load resize", function () {
        if ($(this).width() < 769) {
            $('body').addClass('mini-navbar');
            $('.navbar-static-side').fadeIn();
        }
    });

    function NavToggle() {
        $('.navbar-minimalize').trigger('click');
    }

    function SmoothlyMenu() {
        if (!$('body').hasClass('mini-navbar')) {
            $('#side-menu').hide();
            setTimeout(
                function () {
                    $('#side-menu').fadeIn(500);
                }, 100);
        } else if ($('body').hasClass('fixed-sidebar')) {
            $('#side-menu').hide();
            setTimeout(
                function () {
                    $('#side-menu').fadeIn(500);
                }, 300);
        } else {
            $('#side-menu').removeAttr('style');
        }
    }

// 全屏显示
    $('#fullScreen').on('click', function () {
        $('#wrapper').fullScreen();
    });

// 刷新按钮
    $('.tabReload').on('click', refreshTab);

//刷新iframe
    function refreshTab() {
        $.modal.loading("数据加载中，请稍后...");
        var currentId = $('.page-tabs-content').find('.active').attr('data-id');
        var target = $('.J_iframe[data-id="' + currentId + '"]');
        var url = target.attr('src');
        target.attr('src', url).ready();
        $('.J_mainContent iframe:visible').load(function () {
            $.modal.closeLoading();
        });
    }

// 双击选项卡全屏显示
    $('.J_menuTabs').on('dblclick', '.J_menuTab', activeTabMax);

// 双击选项卡全屏显示
    function activeTabMax() {
        $('#content-main').toggleClass('max');
        $('#ax_close_max').show();
    }

    $(window).keydown(function (event) {
        if (event.keyCode == 27) {
            $('#content-main').removeClass('max');
            $('#ax_close_max').hide();
        }
    });
    // 关闭全屏
    $('#ax_close_max').click(function () {
        $('#content-main').toggleClass('max');
        $('#ax_close_max').hide();
    })


    $(function () {
        //计算元素集合的总宽度
        function calSumWidth(elements) {
            var width = 0;
            $(elements).each(function () {
                width += $(this).outerWidth(true);
            });
            return width;
        }

        //滚动到指定选项卡
        function scrollToTab(element) {
            var marginLeftVal = calSumWidth($(element).prevAll()), marginRightVal = calSumWidth($(element).nextAll());
            // 可视区域非tab宽度
            var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
            //可视区域tab宽度
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            //实际滚动宽度
            var scrollVal = 0;
            if ($(".page-tabs-content").outerWidth() < visibleWidth) {
                scrollVal = 0;
            } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
                if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                    scrollVal = marginLeftVal;
                    var tabElement = element;
                    while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                        scrollVal -= $(tabElement).prev().outerWidth();
                        tabElement = $(tabElement).prev();
                    }
                }
            } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
                scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
            }
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        }

        //查看左侧隐藏的选项卡
        function scrollTabLeft() {
            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
            // 可视区域非tab宽度
            var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
            //可视区域tab宽度
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            //实际滚动宽度
            var scrollVal = 0;
            if ($(".page-tabs-content").width() < visibleWidth) {
                return false;
            } else {
                var tabElement = $(".J_menuTab:first");
                var offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {//找到离当前tab最近的元素
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                offsetVal = 0;
                if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                    while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                        offsetVal += $(tabElement).outerWidth(true);
                        tabElement = $(tabElement).prev();
                    }
                    scrollVal = calSumWidth($(tabElement).prevAll());
                }
            }
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        }

        //查看右侧隐藏的选项卡
        function scrollTabRight() {
            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
            // 可视区域非tab宽度
            var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
            //可视区域tab宽度
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            //实际滚动宽度
            var scrollVal = 0;
            if ($(".page-tabs-content").width() < visibleWidth) {
                return false;
            } else {
                var tabElement = $(".J_menuTab:first");
                var offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {//找到离当前tab最近的元素
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                scrollVal = calSumWidth($(tabElement).prevAll());
                if (scrollVal > 0) {
                    $('.page-tabs-content').animate({
                        marginLeft: 0 - scrollVal + 'px'
                    }, "fast");
                }
            }
        }

        //通过遍历给菜单项加上data-index属性
        $(".J_menuItem").each(function (index) {
            if (!$(this).attr('data-index')) {
                $(this).attr('data-index', index);
            }
        });

        function menuItem() {
            // 获取标识数据
            var dataUrl = $(this).attr('href'),
                dataIndex = $(this).data('index'),
                menuName = $.trim($(this).text()),
                flag = true;
            if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;

            // 选项卡菜单已存在
            $('.J_menuTab').each(function () {
                if ($(this).data('id') == dataUrl) {
                    if (!$(this).hasClass('active')) {
                        $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                        scrollToTab(this);
                        // 显示tab对应的内容区
                        $('.J_mainContent .J_iframe').each(function () {
                            if ($(this).data('id') == dataUrl) {
                                $(this).show().siblings('.J_iframe').hide();
                                return false;
                            }
                        });
                    }
                    flag = false;
                    return false;
                }
            });

            // 选项卡菜单不存在
            if (flag) {
                var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
                $('.J_menuTab').removeClass('active');

                // 添加选项卡对应的iframe
                var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
                $('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

                $.modal.loading("数据加载中，请稍后...");

                $('.J_mainContent iframe:visible').load(function () {
                    $.modal.closeLoading();
                });

                // 添加选项卡
                $('.J_menuTabs .page-tabs-content').append(str);
                scrollToTab($('.J_menuTab.active'));
            }
            return false;
        }

        $('.J_menuItem').on('click', menuItem);

        // 关闭选项卡菜单
        function closeTab() {
            var closeTabId = $(this).parents('.J_menuTab').data('id');
            var currentWidth = $(this).parents('.J_menuTab').width();

            // 当前元素处于活动状态
            if ($(this).parents('.J_menuTab').hasClass('active')) {

                // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
                if ($(this).parents('.J_menuTab').next('.J_menuTab').size()) {

                    var activeId = $(this).parents('.J_menuTab').next('.J_menuTab:eq(0)').data('id');
                    $(this).parents('.J_menuTab').next('.J_menuTab:eq(0)').addClass('active');

                    $('.J_mainContent .J_iframe').each(function () {
                        if ($(this).data('id') == activeId) {
                            $(this).show().siblings('.J_iframe').hide();
                            return false;
                        }
                    });

                    var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
                    if (marginLeftVal < 0) {
                        $('.page-tabs-content').animate({
                            marginLeft: (marginLeftVal + currentWidth) + 'px'
                        }, "fast");
                    }

                    //  移除当前选项卡
                    $(this).parents('.J_menuTab').remove();

                    // 移除tab对应的内容区
                    $('.J_mainContent .J_iframe').each(function () {
                        if ($(this).data('id') == closeTabId) {
                            $(this).remove();
                            return false;
                        }
                    });
                }

                // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
                if ($(this).parents('.J_menuTab').prev('.J_menuTab').size()) {
                    var activeId = $(this).parents('.J_menuTab').prev('.J_menuTab:last').data('id');
                    $(this).parents('.J_menuTab').prev('.J_menuTab:last').addClass('active');
                    $('.J_mainContent .J_iframe').each(function () {
                        if ($(this).data('id') == activeId) {
                            $(this).show().siblings('.J_iframe').hide();
                            return false;
                        }
                    });

                    //  移除当前选项卡
                    $(this).parents('.J_menuTab').remove();

                    // 移除tab对应的内容区
                    $('.J_mainContent .J_iframe').each(function () {
                        if ($(this).data('id') == closeTabId) {
                            $(this).remove();
                            return false;
                        }
                    });
                }
            }
            // 当前元素不处于活动状态
            else {
                //  移除当前选项卡
                $(this).parents('.J_menuTab').remove();

                // 移除相应tab对应的内容区
                $('.J_mainContent .J_iframe').each(function () {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
                scrollToTab($('.J_menuTab.active'));
            }
            return false;
        }

        $('.J_menuTabs').on('click', '.J_menuTab i', closeTab);

        //关闭其他选项卡
        function closeOtherTabs() {
            $('.page-tabs-content').children("[data-id]").not(":first").not(".active").each(function () {
                $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove();
                $(this).remove();
            });
            $('.page-tabs-content').css("margin-left", "0");
        }

        $('.J_tabCloseOther').on('click', closeOtherTabs);

        //滚动到已激活的选项卡
        function showActiveTab() {
            scrollToTab($('.J_menuTab.active'));
        }

        $('.J_tabShowActive').on('click', showActiveTab);


        // 点击选项卡菜单
        function activeTab() {
            if (!$(this).hasClass('active')) {
                var currentId = $(this).data('id');
                // 显示tab对应的内容区
                $('.J_mainContent .J_iframe').each(function () {
                    if ($(this).data('id') == currentId) {
                        $(this).show().siblings('.J_iframe').hide();
                        return false;
                    }
                });
                $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                scrollToTab(this);
            }
        }

        $('.J_menuTabs').on('click', '.J_menuTab', activeTab);

        //刷新iframe
        function refreshTab() {
            var target = $('.J_iframe[data-id="' + $(this).data('id') + '"]');
            var url = target.attr('src');
//        //显示loading提示
//        var loading = layer.load();
//        target.attr('src', url).load(function () {
//            //关闭loading提示
//            layer.close(loading);
//        });
        }

        $('.J_menuTabs').on('dblclick', '.J_menuTab', refreshTab);

        // 左移按扭
        $('.J_tabLeft').on('click', scrollTabLeft);

        // 右移按扭
        $('.J_tabRight').on('click', scrollTabRight);

        // 关闭全部
        $('.J_tabCloseAll').on('click', function () {
            $('.page-tabs-content').children("[data-id]").not(":first").each(function () {
                $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove();
                $(this).remove();
            });
            $('.page-tabs-content').children("[data-id]:first").each(function () {
                $('.J_iframe[data-id="' + $(this).data('id') + '"]').show();
                $(this).addClass("active");
            });
            $('.page-tabs-content').css("margin-left", "0");
        });

    });

});