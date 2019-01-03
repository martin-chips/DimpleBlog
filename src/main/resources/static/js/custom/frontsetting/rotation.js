/**
 * 轮播图设置
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/api/rotation",
        deleteUrl: "/api/user/{id}",
        updateUrl: "/page/user/{id}",
        addUrl: "/page/userAdd.html",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "用户记录",
        search: false,
        uniqueId: "userId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '序号',
            align: 'center',
        }, {
            field: 'img',
            title: '图片地址',
            align: 'center',
        }, {
            field: 'title',
            title: '图片描述',
            align: 'center',
        }, {
            field: 'alt',
            title: '提示',
            align: 'center',
        }, {
            field: 'display',
            title: '显示',
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
                if (row.locked == false) {
                    actions.push('<a class="btn btn-info btn-xs " href="#" onclick="changeLocked(' + row.userId + "," + row.locked + ')"><i class="fa fa-key"></i>显示</a>')
                } else {
                    actions.push('<a class="btn btn-default btn-xs " href="#" onclick="changeLocked(' + row.userId + "," + row.locked + ')"><i class="fa fa-key"></i>隐藏</a>')
                }
                return actions.join('');
            }
        }],
    };
    $.table.init(option);
}

function resetPassword(id) {
    var url = "/page/user/password/" + id;
    $.modal.open("重置密码", url, '800', '300');
}

function changeLocked(id, locked) {
    var url = "/api/user/" + id + "/" + locked;
    $.operate.submit(url, "put", "json", "");
}


