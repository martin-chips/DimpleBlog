/**
 * 博客列表
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/blog",
        deleteUrl: "/api/blog/{id}",
        updateUrl: "/page/blog/{id}.html",
        addUrl: "",
        detailUrl: "",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "博客",
        search: false,
        uniqueId: "blogId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'blogId',
            title: '博客',
            align: 'center',
        }, {
            field: 'title',
            title: '标题',
            align: 'center',
        }, {
            field: 'tags',
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
            field: 'updateTime',
            title: '更新时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'status',
            title: '状态',
            align: 'center',
        }, {
            field: 'support',
            title: '是否推荐',
            align: 'center',
        }, {
            field: 'weight',
            title: '权重',
            align: 'center',
        }, {
            field: 'click',
            title: '点击数',
            align: 'center',
        }, {
            field: 'operate',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.blogId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.blogId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                actions.push('<a class="btn btn-warning btn-xs ' + '" href="#" onclick="$.operate.detail(\'' + row.blogId + '\')"><i class="fa fa-search"></i>详细</a>');
                return actions.join('');
            }
        }
        ],
    };
    $.table.init(option);
}



