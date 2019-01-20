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
        url: "/api/visitor/blacklist",
        deleteUrl: "/api/visitor/blacklist/{id}",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "黑名单记录",
        search: false,
        uniqueId: "id",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '标识',
            align: 'center',
        }, {
            field: 'ip',
            title: '主机',
            align: 'center',
        }, {
            field: 'createTime',
            title: '创建时间',
            align: 'center',
            formatter:
                function (value, row, index) {
                    return $.common.dateFormat(value);
                }
        }, {
            field: 'count',
            title: '拦截次数',
            align: 'center',
        }, {
            title: '操作',
            align: 'center',
            formatter:
                function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn btn-warning btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.id + '\')"><i class="fa fa-search"></i>移除黑名单</a>');
                    return actions.join('');
                }
        }],
    };
    $.table.init(option);
}