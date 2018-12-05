/**
 * 友链列表JS
 */
$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: "/links/links.json",
        addUrl: "/links/add.html",
        updateUrl: "/links/{id}.html",
        deleteUrl: "/links/{id}",
        sortName: "createTime",
        sortOrder: "desc",
        modalName: "友链",
        search: false,
        uniqueId: "linkId",//唯一标识
        showExport: false,
        columns: [{
            checkbox: true
        }, {
            field: 'title',
            title: '名称',
            align: 'center',
            formatter: function (value, row, index) {
                //设置点击title就可以进入
                var url = "http://" + row.url;
                return "<a title='" + row.url + "' target='_blank' href='" + url + "'>" + value + "</a>"
            }
        }, {
            field: 'description',
            title: '描述',
            align: 'center',
            width: 200,
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
            field: 'weight',
            title: '权重',
            align: 'center',
        }, {
            field: 'click',
            title: '点击次数',
            align: 'center',
        }, {
            field: 'available',
            title: '是否死链',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == false) {
                    return '<span class="badge badge-danger">是</span>';
                } else if (value == true) {
                    return '<span class="badge badge-primary">否</span>';
                }
            }
        }, {
            field: 'no',
            title: '操作  ',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                if (row.status == true) {
                    actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.linkId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                    actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.linkId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                    if (row.display == false) {
                        actions.push('<a class="btn btn-info btn-xs " href="#" onclick="updateLink(' + row.linkId + "," + row.display + ')"><i class="fa fa-key"></i>显示</a>')
                    } else {
                        actions.push('<a class="btn btn-default btn-xs " href="#" onclick="updateLink(' + row.linkId + "," + row.display + ')"><i class="fa fa-key"></i>隐藏</a>')
                    }
                } else if (row.status == false) {
                    actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.update(\'' + row.linkId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                    actions.push('<a class="btn btn-info btn-xs " href="#" onclick="updateLinkStatus(' + row.linkId + ')"><i class="fa fa-key"></i>通过</a>');
                    actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.linkId + '\')"><i class="fa fa-remove"></i>拒绝</a> ');
                } else {
                    actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.delete(\'' + row.linkId + '\')"><i class="fa fa-remove"></i>删除</a> ');
                }
                return actions.join('');
            }
        }]
    };
    $.table.init(option);
}

/**
 * 更新Link的status
 * @param linkId
 */
function updateLink(linkId, status) {
    $.ajax({
        type: "PUT",
        url: "/links/" + linkId + "/" + status,
        success: function (data) {
            if (data.code == 200) {
                $.modal.msgSuccess("切换成功！")
                $.table.refresh();
            } else {
                $.modal.msgError("切换失败！");
            }
        },
    });
}

/**
 * 更新友链的状态，主要表示友链有没有通过
 * @param linkId
 */
function updateLinkStatus(linkId) {
    if (linkId == null) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    var url = "/links/unhandled/" + linkId;
    var type = "PUT";
    $.operate.submit(url, "put", "json", "");
}


function searchCustom(code) {
    var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : "link-form";
    var formId = "link-form";
    var params = $("#bootstrap-table").bootstrapTable('getOptions');
    params.queryParams = function (params) {
        var search = {};
        if (code == null) {
            $.each($("#" + currentId).serializeArray(), function (i, field) {
                search[field.name] = field.value;
            });
        } else if (code != null) {
            search["searchCode"] = code;
        }
        search.pageSize = params.limit;
        search.pageNum = params.offset / params.limit + 1;
        return search;
    }
    $("#bootstrap-table").bootstrapTable('refresh', params);
}
