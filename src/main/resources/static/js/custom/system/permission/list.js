$(function () {
    var options = {
            code: "permissionId",
            parentCode: "pid",
            uniqueId: "permissionId",
            expandAll: false,
            expandFirst: true,
            url: "/api/permission",
            addUrl: "/page/permissionAdd/{id}.html",
            createUrl: "/page/permission/{id}.html",
            updateUrl: "/page/permissionUpdate/{id}.html",
            removeUrl: "/api/remove/{id}",
            modalName: "权限",
            columns: [{
                field: 'selectItem',
                radio: true
            },
                {
                    title: '权限名称',
                    field: 'title',
                    width: '20%',
                    formatter: function (value, row, index) {
                        if ($.common.isEmpty(row.icon)) {
                            return row.title;
                        } else {
                            return '<i class="' + row.icon + '"></i> <span class="nav-label">' + row.title + '</span>';
                        }
                    }
                }, {
                    field: 'requestUrl',
                    title: '请求地址',
                    width: '15%',
                    align: "left"
                }, {
                    title: '类型',
                    field: 'type',
                    width: '10%',
                    align: "left",
                    formatter: function (value, item, index) {
                        if (item.type == 1) {
                            return '<span class="label label-success">目录</span>';
                        } else if (item.type == 2) {
                            return '<span class="label label-primary">菜单</span>';
                        } else if (item.type == 3) {
                            return '<span class="label label-warning">按钮</span>';
                        }
                    }
                }, {
                    field: 'name',
                    title: '权限标识',
                    width: '15%',
                    align: "left",
                }, {
                    field: 'status',
                    title: '状态',
                    width: '15%',
                    align: "left",
                    formatter: function (value, row, index) {
                        if (value == true) {
                            return "<span class='badge badge-info'>" + "启用" + " </span>"
                        } else if (value == false) {
                            return "<span class='badge badge-danger'>" + "停用" + " </span>"
                        }
                    }
                }, {
                    title: '操作',
                    width: '20%',
                    align: "left",
                    formatter: function (value, row, index) {
                        var actions = [];
                        actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.permissionId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                        actions.push('<a class="btn btn-info btn-xs ' + '" href="#" onclick="$.operate.add(\'' + row.permissionId + '\')"><i class="fa fa-plus"></i>新增</a> ');
                        actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="deletePermission(\'' + row.permissionId + '\')"><i class="fa fa-remove"></i>删除</a>');
                        return actions.join('');
                    }
                }
            ]
        }
    ;
    $.treeTable.init(options);
});

function deletePermission(id) {
    $.modal.confirm("确定删除该条" + $.table._option.modalName + "信息吗？", function () {
        $.ajax({
            type: 'delete',
            url: "/api/permission/" + id + "/validate",
            success: function (result) {
                if (result.code == web_status.SUCCESS) {
                    if (result.data == null) {
                        $.modal.msgSuccess(result.msg);
                        $.treeTable.refresh();
                    } else {
                        var message = $.table._option.modalName + "有以下依赖角色:" + "</br>" + result.data + ";</br>" + "<font style='color: red'>确认删除将会删除该权限信息以及其他的依赖关系！</font>"
                        $.modal.confirm(message, function () {
                            $.ajax({
                                type: 'delete',
                                url: "/system/permission/" + id,
                                success: function (result) {
                                    if (result.code == web_status.SUCCESS) {
                                        $.modal.msgSuccess(result.msg);
                                        $.treeTable.refresh();
                                    } else {
                                        $.modal.msgError(result.msg);
                                    }
                                }
                            });
                        });
                    }
                } else {
                    $.modal.msgError(result.msg);
                }
            }
        });
    });
}