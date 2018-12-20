/**
 * 角色管理
 */
$(function () {

    initTable();
    initDetails();
});

function initTable() {
    var option = {
        url: "/api/category",
        deleteUrl: "/api/category/{id}",
        updateUrl: "/page/category/{id}.html",
        addUrl: "/page/categoryAdd.html",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "分类",
        search: false,
        uniqueId: "categoryId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'categoryId',
            title: '类别ID',
            align: 'center',
        }, {
            field: 'title',
            title: '类别',
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
            field: 'weight',
            title: '权重',
            align: 'center',
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
            field: 'operate',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.categoryId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.categoryId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                return actions.join('');
            }
        }

        ],
    };
    $.table.init(option);
}

/**
 * 加载左侧详细
 */
var initDetails = function () {
    var nodeParent = $("#categoryDetails");
    $.ajax({
        // url:
    })


};

/**
 * 是否推荐
 */
function support(status) {
    var supportUrl = "/api/category/support/{id}/" + status;
    var rows = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
    if (rows.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    $.modal.confirm("确定要首页推荐这" + rows.length + "条数据吗?", function () {
        var data = {"id": rows.join()};
        var url = supportUrl.replace("{id}", data.id);
        $.operate.submit(url, "put", "json", data);
    });
}

