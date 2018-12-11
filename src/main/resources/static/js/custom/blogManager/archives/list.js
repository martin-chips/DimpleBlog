/**
 * 角色管理
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/blog/archives.json",
        deleteUrl: "/blog/archives/{id}",
        updateUrl: "/blog/archives/{id}.html",
        addUrl: "/blog/archives/add.html",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "归档",
        search: false,
        uniqueId: "archivesId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'archivesId',
            title: '归档ID',
            align: 'center',
        }, {
            field: 'title',
            title: '归档名称',
            align: 'center',
        }, {
            field: 'description',
            title: '描述',
            align: 'center',
        }, {
            field: 'createTime',
            title: '创建时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'updateTime',
            title: '更新时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'count',
            title: '数量',
            align: 'center',
        }, {
            field: 'operate',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.archivesId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.archivesId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                return actions.join('');
            }
        }
        ],
    };
    $.table.init(option);
}



