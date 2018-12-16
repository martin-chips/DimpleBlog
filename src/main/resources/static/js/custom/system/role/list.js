/**
 * 角色管理
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/api/role",
        deleteUrl: "/api/role/{id}",
        updateUrl: "/page/role/{id}.html",
        addUrl: "/page/roleAdd.html",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "用户记录",
        search: false,
        uniqueId: "roleId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'roleId',
            title: '角色ID',
            align: 'center',
        }, {
            field: 'roleName',
            title: '角色名称',
            align: 'center',
        }, {
            field: 'description',
            title: '角色描述',
            align: 'center',
        }, {
            field: 'locked',
            title: '状态',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == false) {
                    return '<span class="badge badge-info">正常</span>';
                } else if (value == true) {
                    return '<span class="badge badge-danger">锁定</span>';
                }
            }
        }, {
            field: 'createTime',
            title: '创建时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'operate',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.roleId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.roleId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                if (row.locked == false) {
                    actions.push('<a class="btn btn-info btn-xs " href="#" onclick="changeLocked(' + row.roleId + "," + row.locked + ')"><i class="fa fa-key"></i>锁定</a>')
                } else {
                    actions.push('<a class="btn btn-info btn-xs " href="#" onclick="changeLocked(' + row.roleId + "," + row.locked + ')"><i class="fa fa-key"></i>解锁</a>')
                }
                return actions.join('');
            }
        }],
    };
    $.table.init(option);
}

function initTree() {
    var option = {
        url: "/api/permission/permissionTree",
        id: "permissionTree"
    }
    $.tree.init(option);
}


function changeLocked(id, locked) {
    if (locked == null) {
        locked == false;
    }
    var url = "/api/role/" + id + "/" + locked;
    //todo 此处点击后会刷新整个页面
    $.operate.save(url, "");
}


