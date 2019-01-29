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

    var options = {
        url: "/api/job",
        detailUrl: "/page/job/{id}.html",
        addUrl: "/page/job/add.html",
        updateUrl: "/page/job/update/{id}.html",
        deleteUrl: "/api/job/{id}",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "任务",
        search: false,
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'jobId',
            title: '任务编号'
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
            field: 'cronExpression',
            title: '执行表达式'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value, row, index) {
                if (value == 0) {
                    return '<span class="badge badge-info">启用</span>';
                } else if (value == 1) {
                    return '<span class="badge badge-danger">停用</span>';
                }
            }
        }, {
            field: 'createTime',
            title: '创建时间',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-primary btn-xs ' + '" href="#" onclick="run(\'' + row.jobId + '\')"><i class="fa fa-play-circle-o"></i> 立即执行</a> ');
                if (row.status == 1) {
                    actions.push('<a class="btn btn-primary btn-xs ' + '" href="#" onclick="start(\'' + row.jobId + '\')"><i class="fa fa-play-circle-o"></i> 启用</a> ');
                } else {
                    actions.push('<a class="btn btn-primary btn-xs ' + '" href="#" onclick="stop(\'' + row.jobId + '\')"><i class="fa fa-play-circle-o"></i> 停用</a> ');
                }
                actions.push('<a class="btn btn-warning btn-xs ' + '" href="#" onclick="$.operate.detail(\'' + row.jobId + '\')"><i class="fa fa-search"></i>详细</a> ');
                return actions.join('');
            }
        }]
    };
    $.table.init(options);
}

/* 调度任务状态显示 */
function statusTools(row) {
    if (row.status == 1) {
        return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="start(\'' + row.jobId + '\')"></i> ';
    } else {
        return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="stop(\'' + row.jobId + '\')"></i> ';
    }
}

/* 立即执行一次 */
function run(jobId) {
    $.modal.confirm("确认要立即执行任务吗？", function () {
        $.operate.post("/api/job/start/" + jobId);
    })
}

/* 调度任务-停用 */
function stop(jobId) {
    $.modal.confirm("确认要停用任务吗？", function () {
        $.operate.post("/api/job/" + jobId + "/" + 1);
    })
}

/* 调度任务-启用 */
function start(jobId) {
    $.modal.confirm("确认要启用任务吗？", function () {
        $.operate.post("/api/job/" + jobId + "/" + 0);
    });
}
