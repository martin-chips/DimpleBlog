$(document).ready(function () {
    //nav     
    var obj = null;
    var As = document.getElementById('starlist').getElementsByTagName('a');
    obj = As[0];
    for (i = 1; i < As.length; i++) {
        if (window.location.href.indexOf(As[i].href) >= 0) obj = As[i];
    }
    obj.id = 'selected';
    //nav
    $("#mnavh").click(function () {
        $("#starlist").toggle();
        $("#mnavh").toggleClass("open");
    });
    //search  
    $(".searchico").click(function () {
        $(".search").toggleClass("open");
    });
    //searchclose 
    $(".searchclose").click(function () {
        $(".search").removeClass("open");
    });
    //banner
    $('#banner').easyFader();
    //nav menu   
    $(".menu").click(function (event) {
        $(this).children('.sub').slideToggle();
    });
    //tab
    $('.tab_buttons li').click(function () {
        $(this).addClass('newscurrent').siblings().removeClass('newscurrent');
        $('.newstab>div:eq(' + $(this).index() + ')').show().siblings().hide();
    });

    loadSpecialEffects();
    loadCustomSetting();

    //配置图片错误使用默认图片展示
    document.addEventListener("error", function (e) {
        var elem = e.target;
        if (elem.tagName.toLowerCase() == "img") {
            elem.src = "/static/imgs/image-load-error.jpg";
        }
    }, true);

    //配置百度站长推送
    loadBaiduPush();

    //返回顶部
    loadReturnTop();

});

function loadReturnTop() {

    $('body').append($('<a onclick="gotoTop();return false;" class="totop" title="返回顶部"></a>'));

}

function loadBaiduPush() {
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https') {
        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
    } else {
        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
    }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
}

function gotoTop(acceleration, stime) {
    acceleration = acceleration || 0.1;
    stime = stime || 10;
    var x1 = 0;
    var y1 = 0;
    var x2 = 0;
    var y2 = 0;
    var x3 = 0;
    var y3 = 0;
    if (document.documentElement) {
        x1 = document.documentElement.scrollLeft || 0;
        y1 = document.documentElement.scrollTop || 0;
    }
    if (document.body) {
        x2 = document.body.scrollLeft || 0;
        y2 = document.body.scrollTop || 0;
    }
    var x3 = window.scrollX || 0;
    var y3 = window.scrollY || 0;

    // 滚动条到页面顶部的水平距离
    var x = Math.max(x1, Math.max(x2, x3));
    // 滚动条到页面顶部的垂直距离
    var y = Math.max(y1, Math.max(y2, y3));

    // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
    var speeding = 1 + acceleration;
    window.scrollTo(Math.floor(x / speeding), Math.floor(y / speeding));

    // 如果距离不为零, 继续调用函数
    if (x > 0 || y > 0) {
        var run = "gotoTop(" + acceleration + ", " + stime + ")";
        window.setTimeout(run, stime);
    }
}

function loadSpecialEffects() {
    /* 鼠标点击特效 - 7Core.CN */
    var a_idx = 0;
    jQuery(document).ready(function ($) {
        $("body").click(function (e) {
            var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正", "法治", "爱国", "敬业", "诚信", "友善");
            var $i = $("<span/>").text(a[a_idx]);
            a_idx = (a_idx + 1) % a.length;
            var x = e.pageX, y = e.pageY;
            $i.css({
                "z-index": 100000000,
                "top": y - 20,
                "left": x,
                "position": "absolute",
                "font-weight": "bold",
                "color": "#ff6651"
            });
            $("body").append($i);
            $i.animate({"top": y - 180, "opacity": 0}, 1500, function () {
                $i.remove();
            });
        });
    });
    /*7Core-CN - 网页鼠标点击特效（爱心）*/
    !function (e, t, a) {
        function r() {
            for (var e = 0; e < s.length; e++) s[e].alpha <= 0 ? (t.body.removeChild(s[e].el), s.splice(e, 1)) : (s[e].y--, s[e].scale += .004, s[e].alpha -= .013, s[e].el.style.cssText = "left:" + s[e].x + "px;top:" + s[e].y + "px;opacity:" + s[e].alpha + ";transform:scale(" + s[e].scale + "," + s[e].scale + ") rotate(45deg);background:" + s[e].color + ";z-index:99999");
            requestAnimationFrame(r)
        }

        function n() {
            var t = "function" == typeof e.onclick && e.onclick;
            e.onclick = function (e) {
                t && t(), o(e)
            }
        }

        function o(e) {
            var a = t.createElement("div");
            a.className = "heart", s.push({
                el: a,
                x: e.clientX - 5,
                y: e.clientY - 5,
                scale: 1,
                alpha: 1,
                color: c()
            }), t.body.appendChild(a)
        }

        function i(e) {
            var a = t.createElement("style");
            a.type = "text/css";
            try {
                a.appendChild(t.createTextNode(e))
            } catch (t) {
                a.styleSheet.cssText = e
            }
            t.getElementsByTagName("head")[0].appendChild(a)
        }

        function c() {
            return "rgb(" + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + ")"
        }

        var s = [];
        e.requestAnimationFrame = e.requestAnimationFrame || e.webkitRequestAnimationFrame || e.mozRequestAnimationFrame || e.oRequestAnimationFrame || e.msRequestAnimationFrame || function (e) {
            setTimeout(e, 1e3 / 60)
        }, i(".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: fixed;}.heart:after{top: -5px;}.heart:before{left: -5px;}"), n(), r()
    }(window, document);

}

/**
 * 获取最近更新的博客
 */
function loadNewestUpdateBlog() {
    $.ajax({
        url: "/public/api/newestUpdateBlog",
    }).done(function (result) {
        if (result.code == 200 && result.data != null) {
            $.each(result.data, function (index, item) {

                $("<li></li>")
                    .append($("<a></a>").attr("href", "/view/" + item.blogId).text(item.title))
                    .appendTo($("#newestUpdateBlog"));
            });
        }
    })
}

/**
 * 获取点击排行
 */
function loadClickRanking() {
    $.ajax({
        url: "/public/api/clickRankingBlog",
    }).done(function (result) {
        if (result.code == 200 && result.data != null) {
            $.each(result.data, function (index, item) {
                if (index == 0) {
                    $("<a></a>").attr("href", "/view/" + item.blogId)
                        .append($("<img>").attr("src", item.headerUrl))
                        .append($("<span></span>").text(item.title))
                        .appendTo($("#clickRankingImg"));
                }
                $("<li></li>")
                    .append($("<i></i>"))
                    .append($("<a></a>").attr("href", "/view/" + item.blogId).text(item.title))
                    .appendTo($("#clickRanking"));
            });
        }
    })
}

/**
 * 猜你喜欢的博文
 */
function loadYouMayLike() {
    $.ajax({
        url: "/public/api/youMayLike",
    }).done(function (result) {
        if (result.code == 200 && result.data != null) {
            $.each(result.data, function (index, item) {
                if (index == 0) {
                    $("<a></a>").attr("href", "/view/" + item.blogId)
                        .append($("<img>").attr("src", item.headerUrl))
                        .append($("<span></span>").text(item.title))
                        .appendTo($("#youMayLikeImg"));
                }
                $("<li></li>")
                    .append($("<a></a>").attr("href", "/view/" + item.blogId).append($("<i></i>").append($("<img>").attr("src", item.headerUrl)))
                        .append($("<p></p>").text(item.title)))
                    .appendTo($("#youMayLike"));
            });
        }
    })
}

/**
 * 加载Link
 */
function loadLink() {
    $.ajax({
        url: "/public/api/linkSide",
    }).done(function (result) {
        if (result.code == 200 && result.data != null) {
            $.each(result.data, function (index, item) {
                $("<li></li>").append($("<a></a>").attr("href", item.url).text(item.title).attr("alt", item.description)).appendTo($("#link"));
            });
        }
    })
}

function loadCustomSetting() {
    loadNewestUpdateBlog();
    loadClickRanking();
    loadYouMayLike();
    loadLink();
}
