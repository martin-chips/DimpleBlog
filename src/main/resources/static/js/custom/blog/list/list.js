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
                    return '<span class="badge badge-danger">垃圾箱</span>';
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

function updateBlog(id) {
    var url = "/404.html";
    if ($.common.isNotEmpty(id)) {
        url = $.table._options.updateUrl.replace("{id}", id);
    } else {
        var id = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
        if (id.length == 0) {
            $.modal.alertWarning("请至少选择一条记录");
            return;
        }
        url = $.table._options.updateUrl.replace("{id}", id);
    }
    //如果是移动端，就使用自适应大小弹窗
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
        width = 'auto';
        height = 'auto';
    }
    if ($.common.isEmpty(title)) {
        title = false;
    }
    if ($.common.isEmpty(url)) {
        url = "/404.html";
    }
    if ($.common.isEmpty(width)) {
        width = 500;
    }
    if ($.common.isEmpty(height)) {
        height = ($(window).height() - 50);
    }
    layer.open({
        type: 2,
        area: [width + 'px', height + 'px'],
        fix: false,
        //不固定
        maxmin: true,
        shade: 0.3,
        title: title,
        content: url,
        btn: ['确定', '关闭'],
        // 弹层外区域关闭
        shadeClose: true,
        yes: function (index, layero) {
            var iframeWin = layero.find('iframe')[0];
            iframeWin.contentWindow.submitHandler();
            $.table.refresh();
        },
        cancel: function (index) {
            return true;
        }
    });
}

/**
 * 是否推荐
 */
function support(status) {
    var supportUrl = "/api/blog/support/{id}/" + status;
    var rows = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
    if (rows.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    var msg = "确定要" + (status == true ? "取消推荐" : "推荐") + "这";
    $.modal.confirm(msg + rows.length + "条数据吗?", function () {
        var data = {"id": rows.join()};
        var url = supportUrl.replace("{id}", data.id);
        $.operate.submit(url, "put", "json", data);
    });
}

/**
 * 改变状态：1表示发布、2表示草稿箱、3表示垃圾箱
 */
function changeStatus(status) {
    console.log(status);
    var changeStatusUrl = "/api/blog/status/{id}/" + status;
    var rows = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
    if (rows.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    var msg = "";
    if (status == 1) {
        msg = "确定要发布这" + rows.length + "条数据吗？";
    } else if (status == 2) {
        msg = "确定要将这" + rows.length + "条数据移入草稿箱吗？";
    } else if (status == 3) {
        msg = "确定要将这" + rows.length + "条数据移入垃圾箱吗？";
    }
    $.modal.confirm(msg, function () {
        var data = {"id": rows.join()};
        var url = changeStatusUrl.replace("{id}", data.id);
        console.log(url)
        $.operate.submit(url, "put", "json", data);
    });
}


