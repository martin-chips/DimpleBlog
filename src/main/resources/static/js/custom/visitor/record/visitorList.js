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
        url: "/api/visitor/record",
        deleteUrl: "/api/visitor/record/{id}",
        cleanUrl: "/api/visitor/record",
        detailUrl: "/page/visitor/record/{id}",
        sortName: "visitTime",
        sortOrder: "desc",
        modalName: "访客记录",
        search: false,
        uniqueId: "id",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'sessionId',
            title: '标识',
            align: 'center',
        }, {
            field: 'ip',
            title: '主机',
            align: 'center',
        }, {
            field: 'title',
            title: '请求模块',
            align: 'center',
        }, {
            field: 'address',
            title: '操作地点',
            align: 'center',
        }, {
            field: 'os',
            title: '操作系统',
            align: 'center',
        }, {
            field: 'browser',
            title: '浏览器',
            align: 'center',
        }, {
            field: 'address',
            title: '操作地点',
            align: 'center',
        }, {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter:
                function (value, row, index) {
                    if (value == true) {
                        return '<span class="badge badge-info">成功</span>';
                    } else if (value == false) {
                        return '<span class="badge badge-danger">失败</span>';
                    }
                }
        }, {
            field: 'visitTime',
            title: '访问时间',
            align: 'center',
            formatter:
                function (value, row, index) {
                    return $.common.dateFormat(value);
                }
        }, {
            title: '操作',
            align: 'center',
            formatter:
                function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn btn-warning btn-xs ' + '" href="#" onclick="$.operate.detail(\'' + row.id + '\')"><i class="fa fa-search"></i>详细</a>');
                    return actions.join('');
                }
        }],
    };
    $.table.init(option);
}