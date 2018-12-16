/**
 * 登陆日志JS
 */
$(function () {
    initTable();
});

$(".osType").click(function () {
    $("#browserType").val("");
    console.log($(this).attr("osType"));
    var osType = $(this).attr("osType");
    $("#osType").val(osType);
    $.table.search();
});

$(".browserType").click(function () {
    $("#osType").val("");
    console.log($(this).attr("browserType"));
    var browserType = $(this).attr("browserType");
    $("#browserType").val(browserType);
    $.table.search();
});

function initTable() {
    var option = {
        url: "/api/loginLog",
        deleteUrl: "/page/loginLog/{id}",
        cleanUrl: "/api/loginLog",
        sortName: "createTime",
        detailUrl: "/page/loginLog/{id}",
        sortOrder: "desc",
        modalName: "登录日志",
        search: false,
        uniqueId: "logId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'loginName',
            title: '用户名称',
            align: 'center',
        }, {
            field: 'ipAddress',
            title: 'IP地址',
            align: 'center',
        }, {
            field: 'loginLocation',
            title: '登录地点',
            align: 'center',
        }, {
            field: 'browser',
            title: '浏览器',
            align: 'center',
        }, {
            field: 'os',
            title: '操作系统',
            align: 'center',
        }, {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == true) {
                    return '<span class="badge badge-info">成功</span>';
                } else if (value == false) {
                    return '<span class="badge badge-danger">失败</span>';
                }
            }
        }, {
            field: 'msg',
            title: '信息',
            align: 'center',
        }, {
            field: 'loginTime',
            title: '登录时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }],
    };
    $.table.init(option);
}


