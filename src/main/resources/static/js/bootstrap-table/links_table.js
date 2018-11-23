/**
 * http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
 */
$('#links_table').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
    url: "/links/linksList",//要请求数据的文件路径
    dataField: "data",//这是返回的json数组的key.默认好像是"rows".这里只有前后端约定好就行
    pageNumber: 1, //初始化加载第一页，默认第一页
    pagination: true,//是否分页
    queryParams: queryParams,//请求服务器时所传的参数
    sidePagination: 'server',//指定服务器端分页
    pageSize: 10,//单页记录数
    striped: true,//隔行变色
    search: true,//显示搜索框
    showRefresh: true,//显示刷新按钮
    showToggle: true,//显示切换按钮
    showColumns: true,//显示列表选择按钮
    height: "600",
    undefinedText: '未知',//当数据为undefined显示的字符
    pageList: [10, 20, 30, 40],//分页步进值
    responseHandler: responseHandler,//请求数据成功后，渲染表格前的方法
    clickToSelect: true,//点击自动勾选复选框
    columns: [{
        checkbox: true
    }, {
        field: 'title',
        title: '名称',
        align: 'center',
    }, {
        field: 'description',
        title: '描述',
        align: 'center',
    }, {
        field: 'url',
        title: '链接地址',
        align: 'center',

    }, {
        field: 'display',
        title: '状态',
        align: 'center',
        formatter: function (value, row, index) {
            if (value == true) {
                return '<span class="badge badge-primary">显示</span>';
            } else if (value == false) {
                return '<span class="badge badge-danger">隐藏</span>';
            }
        }
    }, {
        field: 'createTime',
        title: '创建时间',
        align: 'center',
        formatter: function (value, row, index) {
            return formatDate(value);
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
                return '<span class="badge badge-primary">否</span>';
            } else if (value == true) {
                return '<span class="badge badge-danger">是</span>';
            }
        }
    }, {
        field: 'no',
        title: '操作  ',
        align: 'center',
        formatter: function (value, row, index) {
            var actions = [];
            actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="editLink(\'' + row.linkId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
            actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="deleteLink(\'' + row.linkId + '\')"><i class="fa fa-remove"></i>删除</a> ');
            if (row.display == false) {
                actions.push('<a class="btn btn-info btn-xs " href="#" onclick="updateLink(' + row.linkId + "," + row.display + ')"><i class="fa fa-key"></i>显示</a>')
            } else {
                actions.push('<a class="btn btn-default btn-xs " href="#" onclick="updateLink(' + row.linkId + "," + row.display + ')"><i class="fa fa-key"></i>隐藏</a>')
            }
            return actions.join('');
        }
    }]
})

//请求服务数据时所传参数
function queryParams(params) {
    return {
        pageSize: params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
        pageNum: params.offset / params.limit + 1, //当前页面,默认是上面设置的1(pageNumber)
        param: "",//这里是其他的参数，根据自己的需求定义，可以是多个
        search: params.search,
    }
}

//请求成功方法
function responseHandler(result) {
    console.log(result);
    // var errcode = result.errcode;//在此做了错误代码的判断
    // if (errcode != 0) {
    //     alert("错误代码" + errcode);
    //     return;
    // }
    //如果没有错误则返回数据，渲染表格
    return {
        total: result.data.total, //总页数,前面的key必须为"total"
        data: result.data.list //行数据，前面的key要与之前设置的dataField的值一致.
    };
};

//刷新表格数据,点击按钮调用这个方法就可以刷新
function refresh() {
    //todo 修改数据后不会停留在当前页面
    // var pageNum = $('#links_table').bootstrapTable('getOptions').pageNumber;
    // console.log(pageNum);
    // console.log($('#links_table').bootstrapTable('getOptions'))
    $('#links_table').bootstrapTable('refresh');
}

/**
 * 编辑Link
 * @param linkId
 */
function editLink(linkId) {
    layer.open({
        type: 2,
        title: '修改Link信息',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['600px', '500px'],
        content: '/links/modify?linkId=' + linkId
    });


}

/**
 * 删除Link
 * @param linkId
 */
function deleteLink(linkId) {
    parent.layer.confirm('确定要删除该友链吗？', {
        btn: ['确定', '取消'], //按钮
        shade: false //不显示遮罩
    }, function () {
        $.ajax({
            type: "POST",
            url: "/links/linkDelete",
            data: {"linkId": linkId},
            success: function (data) {
                if (data.code == 200) {
                    parent.layer.msg('删除成功', {icon: 1});
                    refresh();
                } else {
                    parent.layer.msg('删除失败', {shift: 6});
                    // parent.layer.msg('切换失败', {icon: 2});
                }
            },
        });

    });

    // parent.layer.msg('玩命删除中' + linkId);
}

/**
 * 更新Link的status
 * @param linkId
 */
function updateLink(linkId, status) {
    console.log(status);
    $.ajax({
        type: "POST",
        url: "/links/linkSwitch",
        data: {"linkId": linkId, "status": status},
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
    console.log(date);
    var newDate = new Date(date).toJSON();
    var dateFormat = new Date(+new Date(newDate) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    return dateFormat;
}

//Switchery按钮初始化
var elem = document.querySelector('.js-switch');
var init = new Switchery(elem, {color: '#7c8bc7', jackColor: '#9decff'});
elem.onchange = function () {
    var isChecked = elem.checked;
};

//link 提交按钮点击事件
$("#linkSubmit").click(function () {
    // var formData = new FormData($("#form-add")[0]);
    $.ajax({
        type:"POST",
        data: $("#linkAddForm").serializeArray(),
        contentType: false,
        processData: false,
        url: "links/addLink",
        success:function (data) {
            if (data != null && data.code == 200) {
                parent.layer.msg('添加成功', {icon: 1});
            }
        }
    })

});
