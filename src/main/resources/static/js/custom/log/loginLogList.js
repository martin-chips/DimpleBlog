/**
 * 登陆日志JS
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/log/loginLog.json",
        deleteUrl: "/log/loginLog/{id}",
        cleanUrl: "/log/loginLog",
        sortName: "createTime",
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


function searchCustom(code) {
    var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : "link-form";
    var formId = "link-form";
    var params = $("#bootstrap-table").bootstrapTable('getOptions');
    params.queryParams = function (params) {
        var search = {};
        if (code == null) {
            $.each($("#" + currentId).serializeArray(), function (i, field) {
                search[field.name] = field.value;
            });
        } else if (code != null) {
            search["searchCode"] = code;
        }
        search.pageSize = params.limit;
        search.pageNum = params.offset / params.limit + 1;
        return search;
    }
    $("#bootstrap-table").bootstrapTable('refresh', params);
}

