/**
 * 轮播图设置
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/api/rotation",
        deleteUrl: "/api/rotation/{id}",
        updateUrl: "/page/rotation/{id}.html",
        addUrl: "/page/rotationAdd.html",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "轮播图设置",
        search: false,
        uniqueId: "id",//唯一标识
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
            title: '状态',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == true) {
                    return '<span class="badge badge-info">显示</span>';
                } else if (value == false) {
                    return '<span class="badge badge-default">隐藏</span>';
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
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.id + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.id + '\')"><i class="fa fa-remove"></i>删除</a> ');
                if (row.display == false) {
                    actions.push('<a class="btn btn-info btn-xs " href="#" onclick="changeDisplay(' + row.id + "," + row.display + ')"><i class="fa fa-key"></i>显示</a>')
                } else {
                    actions.push('<a class="btn btn-default btn-xs " href="#" onclick="changeDisplay(' + row.id + "," + row.display + ')"><i class="fa fa-key"></i>隐藏</a>')
                }
                return actions.join('');
            }
        }],
    };
    $.table.init(option);
}


function changeDisplay(id, display) {
    var url = "/api/rotation/" + id + "/" + display;
    $.operate.submit(url, "put", "json", "");
}



