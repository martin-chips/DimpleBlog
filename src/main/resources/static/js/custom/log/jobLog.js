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
        url: "/api/jobLog",
        deleteUrl: "/api/jobLog/{id}",
        cleanUrl: "/api/jobLog",
        detailUrl: "/page/jobLog/{id}.html",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "任务调度日志",
        search: false,
        uniqueId: "jobLogId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'jobLogId',
            title: '日志编号'
        }, {
            field: 'jobName',
            title: '任务名称',
            sortable: true
        }, {
            field: 'jobGroup',
            title: '任务组名',
            sortable: true
        }, {
            field: 'methodName',
            title: '方法名称'
        }, {
            field: 'methodParams',
            title: '方法参数'
        }, {
            field: 'jobMessage',
            title: '日志信息'
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
            field: 'createTime',
            title: '创建时间',
            sortable: true,
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-warning btn-xs ' + '" href="#" onclick="$.operate.detail(\'' + row.jobLogId + '\')"><i class="fa fa-search"></i>详细</a>');
                return actions.join('');
            }
        }]
    };
    $.table.init(option);
}