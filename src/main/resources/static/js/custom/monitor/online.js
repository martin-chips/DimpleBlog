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
        url: "/api/online",
        deleteUrl: "/api/online/{id}",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "操作日志",
        search: false,
        uniqueId: "operateId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '标识',
            align: 'center',
        }, {
            field: 'userId',
            title: '账号',
            align: 'center',
        }, {
            field: 'username',
            title: '姓名',
            align: 'center',
        }, {
            field: 'ip',
            title: '主机',
            align: 'center',
        }, {
            field: 'location',
            title: '地址',
            align: 'center',
        }, {
            field: 'operateStatus',
            title: '在线状态',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == '01') {
                    return '<span class="badge badge-info">在线</span>';
                } else {
                    return '<span class="badge badge-danger">离线</span>';
                }
            }
        }, {
            field: 'startTime',
            title: '访问开始时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'lastAccessTime',
            title: '最后访问时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'loginTime',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.id + '\')"><i class="fa fa-search"></i>踢出</a>');
                return actions.join('');
            }
        }],
    };
    $.table.init(option);
}