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
            field: 'url',
            title: '链接地址',
            align: 'center',
            formatter: function (value, row, index) {
                return "<a target='_blank' href='" + value + "'>" + value + "</a>"
            }
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
            field: 'place',
            title: '显示位置',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == '1') {
                    return '<span class="badge badge-info">轮播</span>';
                } else if (value == '2') {
                    return '<span class="badge badge-default">时间线</span>';
                } else {
                    return '<span class="badge badge-default">未指定</span>';
                }
            }
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


function changePlace(place) {
    var url = "/api/rotation/place/{id}/" + place;
    var rows = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
    if (rows.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    var msg = "";
    if (place == 1) {
        msg = "确定要将这" + rows.length + "条数据设置为轮播吗？";
    } else if (place == 2) {
        msg = "确定要将这" + rows.length + "条数据设置为时间线吗？";
    } else if (place == 0) {
        msg = "确定要将这" + rows.length + "条数据设置为暂不指定吗？";
    }
    $.modal.confirm(msg, function () {
        var data = {"id": rows.join()};
        url = url.replace("{id}", data.id);
        $.operate.submit(url, "put", "json", data);
    });
}
