/**
 * 用户管理
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/system/user.json",
        deleteUrl: "/system/user/{id}",
        updateUrl: "/system/user/{id}",
        addUrl: "/system/user/add.html",
        sortName: "createTime",
        detailUrl: "/log/loginLog/{id}",
        sortOrder: "desc",
        modalName: "用户记录",
        search: false,
        uniqueId: "userId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'userId',
            title: '用户ID',
            align: 'center',
        }, {
            field: 'userLoginId',
            title: '登录账号',
            align: 'center',
        }, {
            field: 'userName',
            title: '姓名',
            align: 'center',
        }, {
            field: 'sex',
            title: '性别',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == false) {
                    return "女";
                } else if (value == true) {
                    return "男";
                } else {
                    return "不详";
                }
            }
        }, {
            field: 'phone',
            title: '电话号码',
            align: 'center',
        }, {
            field: 'email',
            title: '邮箱',
            align: 'center',
        }, {
            field: 'locked',
            title: '是否锁定',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == false) {
                    return '<span class="badge badge-info">否</span>';
                } else if (value == true) {
                    return '<span class="badge badge-danger">是</span>';
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
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.userId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.userId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                actions.push('<a class="btn btn-info btn-xs ' + '" href="#" onclick="resetPassword(\'' + row.userId + '\')"><i class="fa fa-key"></i>重置</a>');
                if (row.locked == false) {
                    actions.push('<a class="btn btn-info btn-xs " href="#" onclick="changeLocked(' + row.userId + "," + row.locked + ')"><i class="fa fa-key"></i>锁定</a>')
                } else {
                    actions.push('<a class="btn btn-info btn-xs " href="#" onclick="changeLocked(' + row.userId + "," + row.locked + ')"><i class="fa fa-key"></i>解锁</a>')
                }
                return actions.join('');
            }
        }],
    };
    $.table.init(option);
}

function resetPassword(id) {
    var url = "/system/user/password/" + id;
    $.modal.open("重置密码", url, '800', '300');
}

function changeLocked(id, locked) {
    var url = "/system/user/" + id + "/" + locked;
    $.operate.submit(url, "put", "json", "");
}


