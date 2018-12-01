/**
 * http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
 */
$('#bootstrap-table').bootstrapTable({
    method: 'GET',
    contentType: "application/x-www-form-urlencoded",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
    url: "/log/loginLog.json",//要请求数据的文件路径
    dataField: "data",//这是返回的json数组的key.默认好像是"rows".这里只有前后端约定好就行
    pageNumber: 1, //初始化加载第一页，默认第一页
    pagination: true,//是否分页
    queryParams: queryParams,//请求服务器时所传的参数
    sidePagination: 'server',//指定服务器端分页
    pageSize: 10,//单页记录数
    // striped: true,//隔行变色
    search: false,//显示搜索框
    showSearch: true,
    showRefresh: true,//显示刷新按钮
    showToggle: true,//显示切换按钮
    showColumns: true,//显示列表选择按钮
    // height: "540",
    undefinedText: '未知',//当数据为undefined显示的字符
    pageList: [10, 20, 30, 40],//分页步进值
    responseHandler: responseHandler,//请求数据成功后，渲染表格前的方法
    clickToSelect: true,//点击自动勾选复选框
    toolbar: '#toolbar',
    columns: [{
        checkbox: true
    }, {
        field: 'loginName',
        title: '用户名称',
        align: 'center',
    }, {
        field: 'ipAddress',
        title: 'IP地址',
        align: 'center',
    }, {
        field: 'logId',
        title: 'no',
        visible: false//设置隐藏列
    }, {
        field: 'loginLocation',
        title: '登录地点',
        align: 'center',
    }, {
        field: 'browser',
        title: '浏览器',
        align: 'center',
    }, {
        field: 'os',
        title: '操作系统',
        align: 'center',
    }, {
        field: 'status',
        title: '状态',
        align: 'center',
        formatter: function (value, row, index) {
            if (value == true) {
                return '<span class="badge badge-info">成功</span>';
            } else if (value == false) {
                return '<span class="badge badge-danger">失败</span>';
            }
        }
    }, {
        field: 'msg',
        title: '信息',
        align: 'center',
    }, {
        field: 'loginTime',
        title: '登录时间',
        align: 'center',
        formatter: function (value, row, index) {
            return formatDate(value);
        }
    }],
})


//请求服务数据时所传参数
function queryParams(params) {
    return {
        pageSize: params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
        pageNum: params.offset / params.limit + 1, //当前页面,默认是上面设置的1(pageNumber)
        // param: "",//这里是其他的参数，根据自己的需求定义，可以是多个
        // search: params.search,

    }
}

//请求成功方法
function responseHandler(result) {
    //如果没有错误则返回数据，渲染表格
    return {
        total: result.data.total, //总页数,前面的key必须为"total"
        data: result.data.list //行数据，前面的key要与之前设置的dataField的值一致.
    };
};

//刷新表格数据,点击按钮调用这个方法就可以刷新
function refresh() {
    $('#bootstrap-table').bootstrapTable('refresh');
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
                parent.layer.msg('切换成功', {icon: 1});
                refresh();
            } else {
                parent.layer.msg('切换失败', {icon: 2});
            }
        },
    })
}

/**
 * 时间格式化
 */
function formatDate(date) {
    var newDate = new Date(date).toJSON();
    var dateFormat = new Date(+new Date(newDate) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    return dateFormat;
}


/**
 * 批量删除
 */
function deleteLog() {
    //获取所有的选中列
    var rows = $("#bootstrap-table").bootstrapTable('getSelections');
    var ids = new Array();
    for (var i = 0; i < rows.length; i++) {
        ids[i] = rows[i].logId;
    }
    if (ids.length == 0) {
        parent.layer.msg('请选择需要删除的Log记录!', {icon: 2});
        return;
    }
    parent.layer.confirm('确定要删除这' + ids.length + '条Log记录吗？', {
        btn: ['确定', '取消'], //按钮
        shade: false //不显示遮罩
    }, function () {
        $.ajax({
            type: "DELETE",
            dataType: "json",
            cache: false,
            contentType: "application/json",
            url: "/log/loginLog/" + ids,
            success: function (data) {
                console.log(data);
                if (data != null && data.code == 200) {
                    parent.layer.msg('删除成功', {icon: 1});
                    refresh();
                } else {
                    parent.layer.msg('删除失败，请稍后重试！', {icon: 2});
                }
            },
            error: function (data) {
                parent.layer.msg('系统故障，请联系管理员！', {icon: 2});
            }
        })

    });
}

/**
 * 清空form表单
 */
function resetForm() {
    $("#loginLog-form")[0].reset();
    refresh();
}


// 搜索
function searchCustom() {
    var currentId = isEmpty(formId) ? $('form').attr('id') : "loginLog-form";
    var formId = "loginLog-form";
    var params = $("#bootstrap-table").bootstrapTable('getOptions');
    params.queryParams = function (params) {
        var search = {};
        $.each($("#" + currentId).serializeArray(), function (i, field) {
            console.log(field)
            search[field.name] = field.value;
        });
        search.pageSize = params.limit;
        search.pageNum = params.offset / params.limit + 1;
        return search;
    }
    $("#bootstrap-table").bootstrapTable('refresh', params);
}

function isEmpty(value) {
    if (value == null || this.trim(value) == "") {
        return true;
    }
    return false;
}

/**
 * 清空Log
 */
function clean() {
    parent.layer.confirm('确定要清空登录日志吗？', {
        btn: ['确定', '取消'], //按钮
        shade: false //不显示遮罩
    }, function () {
        $.ajax({
            type: "DELETE",
            dataType: "json",
            cache: false,
            contentType: "application/json",
            url: "/log/loginLog/",
            success: function (data) {
                console.log(data);
                if (data != null && data.code == 200) {
                    parent.layer.msg('删除成功', {icon: 1});
                    refresh();
                } else {
                    parent.layer.msg('删除失败，请稍后重试！', {icon: 2});
                }
            },
            error: function (data) {
                parent.layer.msg('系统故障，请联系管理员！', {icon: 2});
            }
        })

    });
}