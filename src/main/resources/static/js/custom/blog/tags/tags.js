$(function () {
    initTags();
});

function initTags() {
    let option = {
        url: "/api/tag",
        deleteUrl: "/api/tags/{id}",
        updateUrl: "/page/blogList/{id}.html",
        addUrl: "/page/tags/{id}.html",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "博客",
        search: false,
        uniqueId: "blogId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'title',
            title: '标签',
            align: 'center',
        }, {
            field: 'createTime',
            title: '创建时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'count',
            title: '统计',
            align: 'center',
            formatter: function (value, row, index) {
                return value + "篇";
            }
        }, {
            field: 'operate',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.updateFull(\'' + row.id + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.id + '\')"><i class="fa fa-remove"></i>删除</a> ');
                return actions.join('');
            }
        }
        ],
    };
    $.table.init(option);
}