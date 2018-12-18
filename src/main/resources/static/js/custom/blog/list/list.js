/**
 * 博客列表
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/api/blog",
        deleteUrl: "/api/blog/{id}",
        updateUrl: "/page/blogList/{id}.html",
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
            title: '博客标识',
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
            field: 'updateTime',
            title: '最后操作时间',
            align: 'center',
            formatter: function (value, row, index) {
                return $.common.dateFormat(value);
            }
        }, {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 1) {
                    return '<span class="badge badge-info">发布</span>';
                } else if (value == 2) {
                    return '<span class="badge badge-default">草稿箱</span>';
                } else if (value) {
                    return '<span class="badge badge-default">垃圾箱</span>';
                }
            }
        }, {
            field: 'support',
            title: '是否推荐',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == true) {
                    return '<span class="badge badge-info">是</span>';
                } else if (value == false) {
                    return '<span class="badge badge-default">否</span>';
                }
            }
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
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.updateFull(\'' + row.blogId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.blogId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                actions.push('<a class="btn btn-warning btn-xs ' + '" href="#" onclick="$.operate.detail(\'' + row.blogId + '\')"><i class="fa fa-search"></i>详细</a>');
                return actions.join('');
            }
        }
        ],
    };
    $.table.init(option);
}



