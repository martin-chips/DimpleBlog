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
        url: "/api/visitor/count",
        deleteUrl: "/api/visitor/count/{id}",
        sortName: "visitTime",
        sortOrder: "desc",
        modalName: "访客记录",
        search: false,
        uniqueId: "ip",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'ip',
            title: 'IP地址',
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
            title: '地区',
            align: 'center',
        }, {
            field: 'count',
            title: '访问次数',
            align: 'center',
        }, {
            title: '操作',
            align: 'center',
            formatter:
                function (value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="moveBlacklist()"><i class="fa fa-search"></i>移入黑名单</a>');
                    return actions.join('');
                }
        }],
    };
    $.table.init(option);
}

function moveBlacklist() {

    var blacklistUrl = "/api/visitor/blacklist/{ip}/";
    var rows = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
    if (rows.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    $.modal.confirm("确定要将这" + rows.length + "条IP移入到黑名单吗?", function () {
        var data = {"id": rows.join()};
        var url = blacklistUrl.replace("{ip}", data.id);
        $.operate.submit(url, "post", "json", data);
    });
}