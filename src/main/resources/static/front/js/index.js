// 登陆
function login() {
    $('#b-modal-login').modal('show');
    setCookie('this_url', window.location.href);
}

// 退出
function logout() {
    $.post(logoutUrl);
    setTimeout(function () {
        location.replace(location)
    }, 500);
}

/**
 * 设置cookie
 * @param {string} name  键名
 * @param {string} value 键值
 * @param {integer} days cookie周期
 */
function setCookie(name, value, minute) {
    if (minute) {
        var date = new Date();
        date.setTime(date.getTime() + (minute * 1000 * 60));
        var expires = "; expires=" + date.toGMTString();
    } else {
        var expires = "";
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

// 获取cookie
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

// 删除cookie
function deleteCookie(name) {
    setCookie(name, "", -1);
}

/**
 * 记录当前选中的分类或者标签
 * @param  {string} category index：首页 cid：分类  tid：标签
 * @param  {integer} id      id
 * @return {boolean}         true 接着跳转
 */
function recordId(category, id) {
    // 设置默认值为0
    setCookie('cid', 0);
    setCookie('tid', 0);
    setCookie('search_word', 0);
    // 如果不是首页 则记录当前选中的分类或者标签
    if (category != 'index' && category != '/') {
        setCookie(category, id);
    }
    return true;
}

// /**
//  * 阻止F12操作
//  */
// document.onkeydown = function () {
//     if (event.keyCode == 123) {
//         event.keyCode = 0;
//         event.returnValue = false;
//     }
// }
// /**
//  * 禁止右键
//  */
// document.oncontextmenu = function () {
//     event.returnValue = false;
// }
// /**
//  * 禁止预先调试
//  * @type {number}
//  */
// var threshold = 160; // 打开控制台的宽或高阈值
// // 每秒检查一次
// setInternet(function () {
//     if (window.outerWidth - window.innerWidth > threshold ||
//         window.outerHeight - window.innerHeight > threshold) {
//         // 如果打开控制台，则刷新页面
//         window.location.reload();
//     }
// }, 1e3);

function showLogin() {
    $(".hide_box2").fadeToggle();
    $(".shang_box2").fadeToggle();
};

function dashangToggle2() {
    $(".hide_box2").fadeToggle();
    $(".shang_box2").fadeToggle();
}