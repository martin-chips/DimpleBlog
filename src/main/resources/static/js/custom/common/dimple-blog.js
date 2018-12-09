(function ($) {
    $.extend({

        table: {
            _options: {},
            _params: {},
            init: function (options) {
                $.table._options = options;
                //如果查询参数为空，就使用table自带的param，否则使用option中使用的查询参数
                $.table._params = $.common.isEmpty(options.queryParams) ? $.table.queryParams : options.queryParams;
                //设置排序标识
                _sortOrder = $.common.isEmpty(options.sortOrder) ? "asc" : options.sortOrder;
                _sortName = $.common.isEmpty(options.sortName) ? "" : options.sortName;
                //设置请求方式，默认为get
                _method = $.common.isEmpty(options.method) ? "get" : options.method;
                //设置行间隔色
                _striped = $.common.isEmpty(options.striped) ? "true" : options.striped;
                //对bootstrap-table参数进行处理
                $("#bootstrap-table").bootstrapTable({
                    url: options.url,                                    //请求后台的URL参数（*）
                    contentType: "application/x-www-form-urlencoded",   //编码类型
                    method: _method,                                    //请求方式(*)
                    undefinedText: "未知",                                //当数据为undefined显示的字符
                    cache: false,                                       //是否使用缓存
                    striped: _striped,                                  //设置行间隔色
                    sortStable: true,                                   // 设置为 true 将获得稳定的排序
                    sortName: _sortName,                                // 排序列名称
                    sortOrder: _sortOrder,                              // 排序方式  asc 或者 desc
                    pagination: $.common.visible(options.pagination),   // 是否显示分页（*）
                    pageNumber: 1,                                      // 初始化加载第一页，默认第一页
                    pageSize: 10,                                       // 每页的记录行数（*）
                    pageList: [10, 25, 50],                             // 可供选择的每页的行数（*）
                    iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
                    toolbar: '#toolbar',                                // 指定工作栏
                    sidePagination: "server",                           // 启用服务端分页
                    search: $.common.visible(options.search),           // 是否显示搜索框功能
                    showSearch: $.common.visible(options.showSearch),   // 是否显示检索信息
                    showRefresh: $.common.visible(options.showRefresh), // 是否显示刷新按钮
                    showColumns: $.common.visible(options.showColumns), // 是否显示隐藏某列下拉框
                    showToggle: $.common.visible(options.showToggle),   // 是否显示详细视图和列表视图的切换按钮
                    showExport: $.common.visible(options.showExport),   // 是否支持导出文件
                    queryParams: $.table._params,                       // 传递参数（*）
                    columns: options.columns,                           // 显示列信息（*）
                    responseHandler: $.table.responseHandler,            // 回调函数
                    clickToSelect: true,                                //点击自动勾选复选框
                });
            },
            // 查询条件
            queryParams: function (params) {
                return {
                    // 传递参数查询参数
                    pageSize: params.limit,
                    pageNum: params.offset / params.limit + 1,
                    searchValue: params.search,
                    orderByColumn: params.sort,
                    isAsc: params.order
                };
            },
            // 请求获取数据后处理回调函数
            responseHandler: function (result) {
                if (result.code == web_status.SUCCESS) {
                    //使用PageInfo包装后返回的数据名称为list
                    return {rows: result.data.list, total: result.data.total};
                } else {
                    $.modal.msgWarning(result.msg);
                    return {rows: [], total: 0};
                }
            },
            // 搜索-默认第一个form
            search: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var params = $("#bootstrap-table").bootstrapTable('getOptions');
                params.queryParams = function (params) {
                    var search = {};
                    $.each($("#" + currentId).serializeArray(), function (i, field) {
                        search[field.name] = field.value;
                    });
                    search.pageSize = params.limit;
                    search.pageNum = params.offset / params.limit + 1;
                    search.searchValue = params.search;
                    search.orderByColumn = params.sort;
                    search.isAsc = params.order;
                    return search;
                }
                $("#bootstrap-table").bootstrapTable('refresh', params);
            },
            // 下载-默认第一个form
            exportExcel: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                $.modal.loading("正在导出数据，请稍候...");
                $.post($.table._options.exportUrl, $("#" + currentId).serializeArray(), function (result) {
                    if (result.code == web_status.SUCCESS) {
                        //todo 修改
                        window.location.href = ctx + "common/download?fileName=" + result.msg + "&delete=" + true;
                    } else {
                        $.modal.alertError(result.msg);
                    }
                    $.modal.closeLoading();
                });
            },
            // 刷新表格
            refresh: function () {
                $("#bootstrap-table").bootstrapTable('refresh', {
                    silent: true
                });
            },
            //重置搜索栏，并重新刷新表格
            resetFormRefresh: function (formId) {
                //获取formId
                var currentId = $.common.isEmpty(formId) ? $("form").attr("id") : formId;
                $("#" + currentId)[0].reset();
                $.table.refresh();
            },
            // 查询表格指定列值
            selectColumns: function (column) {
                return $.map($('#bootstrap-table').bootstrapTable('getSelections'), function (row) {
                    return row[column];
                });
            },
            // 查询表格首列值
            selectFirstColumns: function () {
                return $.map($('#bootstrap-table').bootstrapTable('getSelections'), function (row) {
                    return row[$.table._options.columns[1].field];
                });
            },
            // 回显数据字典
            selectDictLabel: function (datas, value) {
                var actions = [];
                $.each(datas, function (index, dict) {
                    if (dict.dictValue == value) {
                        actions.push("<span class='badge badge-" + dict.listClass + "'>" + dict.dictLabel + "</span>");
                        return false;
                    }
                });
                return actions.join('');
            }
        },
        //表单封装处理
        form: {
            //表单重置
            reset: function (formId) {
                //获取formId
                var currentId = $.common.isEmpty(formId) ? $("form").attr("id") : formId;
                $("#" + currentId)[0].reset();
            },
            //获取选中的复选框项
            selectCheckeds: function (name) {
                var checkeds = "";
                $('input:checkbox[name="' + name + '"]:checked').each(function (i) {
                    if (0 == i) {
                        checkeds = $(this).val();
                    } else {
                        checkeds += ("," + $(this).val());
                    }
                });
                return checkeds;
            },
            // 获取选中下拉框项
            selectSelects: function (name) {
                var selects = "";
                $('#' + name + ' option:selected').each(function (i) {
                    if (0 == i) {
                        selects = $(this).val();
                    } else {
                        selects += ("," + $(this).val());
                    }
                });
                return selects;
            }
        },
        //todo 表单验证失效
        // 校验封装处理
        validateCustom: {
            // 判断返回标识是否唯一 false 不存在 true 存在
            unique: function (value) {
                if (value == "0") {
                    return true;
                }
                return false;
            },
            // 表单验证
            form: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                return $("#" + currentId).validate().form();
            }
        },
        //弹出层封装处理
        modal: {
            // 显示图标
            icon: function (type) {
                var icon = "";
                if (type == modal_status.WARNING) {
                    icon = 0;
                } else if (type == modal_status.SUCCESS) {
                    icon = 1;
                } else if (type == modal_status.FAIL) {
                    icon = 2;
                } else {
                    icon = 3;
                }
                return icon;
            },
            //消息通知
            msg: function (content, type) {
                if (type != undefined) {
                    layer.msg(content, {
                            icon: $.modal.icon(type),
                            time: 1000,
                            shift: 500
                        }
                    );
                } else {
                    layer.msg(content);
                }
            },
            //错误消息
            msgError: function (content) {
                $.modal.msg(content, modal_status.FAIL);
            },
            //成功消息
            msgSuccess: function (content) {
                $.modal.msg(content, modal_status.SUCCESS);
            },
            //警告消息
            msgWarning: function (content) {
                $.modal.msg(content, modal_status.WARNING);
            },
            //弹出提示
            msgAlert: function (content, type) {
                layer.alert(content, {
                    icon: $.modal.icon(type),
                    title: "提示",
                    btn: ["确认"],
                    btnclass: ["btn btn-primary"],
                });
            },
            //消息提示并刷新父窗体
            msgReload: function (content, type) {
                layer.msg(content, {
                        icon: $.modal.icon(type),
                        time: 1500,
                        shade: [0.1, "#8F8F8F"]
                    },
                    function () {
                        $.modal.reload();
                    }
                );
            },
            //错误消息提示
            alertError: function (content) {
                $.modal.msgAlert(content, modal_status.FAIL);
            },
            // 成功提示
            alertSuccess: function (content) {
                $.modal.msgAlert(content, modal_status.SUCCESS);
            },
            // 警告提示
            alertWarning: function (content) {
                $.modal.msgAlert(content, modal_status.WARNING);
            },
            // 关闭窗体
            close: function () {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            confirm: function (content, callBack) {
                layer.confirm(content, {
                    icon: 3,
                    title: "提示",
                    btn: ['确认', '取消'],
                    btnclass: ['btn btn-primary', 'btn btn-danger'],
                }, function (index) {
                    layer.close(index);
                    callBack(true);
                });
            },
            // 弹出层指定宽度
            open: function (title, url, width, height) {
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(title)) {
                    title = false;
                }
                ;
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                }
                ;
                if ($.common.isEmpty(width)) {
                    width = 800;
                }
                ;
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 50);
                }
                ;
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
            },
            // 弹出层指定参数选项
            openOptions: function (options) {
                var _url = $.common.isEmpty(options.url) ? "/404.html" : options.url;
                var _title = $.common.isEmpty(options.title) ? "系统窗口" : options.title;
                var _width = $.common.isEmpty(options.width) ? "800" : options.width;
                var _height = $.common.isEmpty(options.height) ? ($(window).height() - 50) : options.height;
                layer.open({
                    type: 2,
                    maxmin: true,
                    shade: 0.3,
                    title: _title,
                    fix: false,
                    area: [_width + 'px', _height + 'px'],
                    content: _url,
                    shadeClose: true,
                    btn: ['<i class="fa fa-check"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
                    yes: function (index, layero) {
                        options.callBack(index, layero)
                    }, cancel: function () {
                        return true;
                    }
                });
            },
            // 弹出层全屏
            openFull: function (title, url, width, height) {
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(title)) {
                    title = false;
                }
                ;
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                }
                ;
                if ($.common.isEmpty(width)) {
                    width = 800;
                }
                ;
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 50);
                }
                ;
                var index = layer.open({
                    type: 2,
                    area: [width + 'px', height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: title,
                    content: url,
                    // 弹层外区域关闭
                    shadeClose: true
                });
                layer.full(index);
            },
            // 弹出层指定参数选项
            openOptions: function (options) {
                var _url = $.common.isEmpty(options.url) ? "/404.html" : options.url;
                var _title = $.common.isEmpty(options.title) ? "系统窗口" : options.title;
                var _width = $.common.isEmpty(options.width) ? "800" : options.width;
                var _height = $.common.isEmpty(options.height) ? ($(window).height() - 50) : options.height;
                layer.open({
                    type: 2,
                    maxmin: true,
                    shade: 0.3,
                    title: _title,
                    fix: false,
                    area: [_width + 'px', _height + 'px'],
                    content: _url,
                    shadeClose: true,
                    btn: ['<i class="fa fa-check"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
                    yes: function (index, layero) {
                        options.callBack(index, layero)
                    }, cancel: function () {
                        return true;
                    }
                });
            },
            // 弹出层全屏
            openFull: function (title, url, width, height) {
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(title)) {
                    title = false;
                }
                ;
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                }
                ;
                if ($.common.isEmpty(width)) {
                    width = 800;
                }
                ;
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 50);
                }
                ;
                var index = layer.open({
                    type: 2,
                    area: [width + 'px', height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: title,
                    content: url,
                    // 弹层外区域关闭
                    shadeClose: true
                });
                layer.full(index);
            },
            // 打开遮罩层
            loading: function (message) {
                $.blockUI({message: '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>'});
            },
            // 关闭遮罩层
            closeLoading: function () {
                setTimeout(function () {
                    $.unblockUI();
                }, 50);
            },
            // 重新加载
            reload: function () {
                parent.location.reload();
            }
        },
        // 树插件封装处理
        tree: {
            _option: {},
            _lastValue: {},
            // 初始化树结构
            init: function (options) {
                $.tree._option = options;
                // 属性ID
                var _id = $.common.isEmpty(options.id) ? "tree" : options.id;
                // 展开等级节点
                var _expandLevel = $.common.isEmpty(options.expandLevel) ? 0 : options.expandLevel;
                // 树结构初始化加载
                var setting = {
                    check: options.check,
                    view: {selectedMulti: false, nameIsHTML: true},
                    data: {key: {title: "title"}, simpleData: {enable: true}},
                    callback: {onClick: options.onClick}
                };
                $.get(options.url, function (data) {
                    var treeName = $("#treeName").val();
                    var treeId = $("#treeId").val();
                    tree = $.fn.zTree.init($("#" + _id), setting, data.data);
                    $._tree = tree;
                    // 展开第一级节点
                    var nodes = tree.getNodesByParam("level", 0);
                    for (var i = 0; i < nodes.length; i++) {
                        if (_expandLevel > 0) {
                            tree.expandNode(nodes[i], true, false, false);
                        }
                        $.tree.selectByIdName(treeId, treeName, nodes[i]);
                    }
                    // 展开第二级节点
                    nodes = tree.getNodesByParam("level", 1);
                    for (var i = 0; i < nodes.length; i++) {
                        if (_expandLevel > 1) {
                            tree.expandNode(nodes[i], true, false, false);
                        }
                        $.tree.selectByIdName(treeId, treeName, nodes[i]);
                    }
                    // 展开第三级节点
                    nodes = tree.getNodesByParam("level", 2);
                    for (var i = 0; i < nodes.length; i++) {
                        if (_expandLevel > 2) {
                            tree.expandNode(nodes[i], true, false, false);
                        }
                        $.tree.selectByIdName(treeId, treeName, nodes[i]);
                    }
                }, null, null, "正在加载，请稍后...");
            },
            // 搜索节点
            searchNode: function () {
                // 取得输入的关键字的值
                var value = $.common.trim($("#keyword").val());
                if ($.tree._lastValue === value) {
                    return;
                }
                // 保存最后一次搜索名称
                $.tree._lastValue = value;
                var nodes = $._tree.getNodes();
                // 如果要查空字串，就退出不查了。
                if (value == "") {
                    $.tree.showAllNode(nodes);
                    return;
                }
                $.tree.hideAllNode(nodes);
                // 根据搜索值模糊匹配
                $.tree.updateNodes($._tree.getNodesByParamFuzzy("name", value));
            },
            // 根据Id和Name选中指定节点
            selectByIdName: function (treeId, treeName, node) {
                if ($.common.isNotEmpty(treeName) && $.common.isNotEmpty(treeId)) {
                    if (treeId == node.id && treeName == node.name) {
                        $._tree.selectNode(node, true);
                    }
                }
            },
            // 显示所有节点
            showAllNode: function (nodes) {
                nodes = $._tree.transformToArray(nodes);
                for (var i = nodes.length - 1; i >= 0; i--) {
                    if (nodes[i].getParentNode() != null) {
                        $._tree.expandNode(nodes[i], true, false, false, false);
                    } else {
                        $._tree.expandNode(nodes[i], true, true, false, false);
                    }
                    $._tree.showNode(nodes[i]);
                    $.tree.showAllNode(nodes[i].children);
                }
            },
            // 隐藏所有节点
            hideAllNode: function (nodes) {
                var tree = $.fn.zTree.getZTreeObj("tree");
                var nodes = $._tree.transformToArray(nodes);
                for (var i = nodes.length - 1; i >= 0; i--) {
                    $._tree.hideNode(nodes[i]);
                }
            },
            // 显示所有父节点
            showParent: function (treeNode) {
                var parentNode;
                while ((parentNode = treeNode.getParentNode()) != null) {
                    $._tree.showNode(parentNode);
                    $._tree.expandNode(parentNode, true, false, false);
                    treeNode = parentNode;
                }
            },
            // 显示所有孩子节点
            showChildren: function (treeNode) {
                if (treeNode.isParent) {
                    for (var idx in treeNode.children) {
                        var node = treeNode.children[idx];
                        $._tree.showNode(node);
                        $.tree.showChildren(node);
                    }
                }
            },
            // 更新节点状态
            updateNodes: function (nodeList) {
                $._tree.showNodes(nodeList);
                for (var i = 0, l = nodeList.length; i < l; i++) {
                    var treeNode = nodeList[i];
                    $.tree.showChildren(treeNode);
                    $.tree.showParent(treeNode)
                }
            },
            // 获取当前被勾选集合
            getCheckedNodes: function (column) {
                var _column = $.common.isEmpty(column) ? "id" : column;
                var nodes = $._tree.getCheckedNodes(true);
                return $.map(nodes, function (row) {
                    return row[_column];
                }).join();
            },
            // 不允许根父节点选择
            notAllowParents: function (_tree) {
                var nodes = _tree.getSelectedNodes();
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].level == 0) {
                        $.modal.msgError("不能选择根节点（" + nodes[i].name + "）");
                        return false;
                    }
                    if (nodes[i].isParent) {
                        $.modal.msgError("不能选择父节点（" + nodes[i].name + "）");
                        return false;
                    }
                }
                return true;
            },
            // 隐藏/显示搜索栏
            toggleSearch: function () {
                $('#search').slideToggle(200);
                $('#btnShow').toggle();
                $('#btnHide').toggle();
                $('#keyword').focus();
            },
            // 折叠
            collapse: function () {
                $._tree.expandAll(false);
            },
            // 展开
            expand: function () {
                $._tree.expandAll(true);
            }
        },
        //操作封装处理
        operate: {
            //提交数据
            submit: function (url, type, dataType, data) {
                $.modal.loading("正在处理中，请稍候...");
                var config = {
                    url: url,
                    type: type,
                    dataType: dataType,
                    data: data,
                    success: function (result) {
                        // $.operate.ajaxSuccessAutoClose(result);
                        $.operate.ajaxSuccess(result);
                    }
                };
                $.ajax(config);
            },

            post: function (url, data) {
                $.operate.submit(url, "post", "json", data);
            },
            //详细信息
            detail: function (id, width, height) {
                var _url = $.common.isEmpty(id) ? $.table._options.detailUrl : $.table._options.detailUrl.replace("{id}", id);
                var _width = $.common.isEmpty(width) ? "800" : width;
                var _height = $.common.isEmpty(height) ? ($(window).height() - 50) : height;
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    _width = 'auto';
                    _height = 'auto';
                }
                layer.open({
                    type: 2,
                    area: [_width + 'px', _height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: $.table._options.modalName + "详细",
                    content: _url,
                    btn: '关闭',
                    // 弹层外区域关闭
                    shadeClose: true,
                    success: function (layer) {
                        layer[0].childNodes[3].childNodes[0].attributes[0].value = 'layui-layer-btn1';
                    },
                    btn1: function (index) {
                        layer.close(index);
                    }
                });
            },
            delete: function (id) {
                $.modal.confirm("确定删除该条" + $.table._options.modalName + "信息吗？", function () {
                    var url = $.common.isEmpty(id) ? $.table._options.deleteUrl : $.table._options.deleteUrl.replace("{id}", id);
                    var data = {"ids": id};
                    $.operate.submit(url, "delete", "json", data);
                });
            },
            deleteAll: function () {
                var rows = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
                if (rows.length == 0) {
                    $.modal.alertWarning("请至少选择一条记录");
                    return;
                }
                $.modal.confirm("确认要删除选中的" + rows.length + "条数据吗?", function () {
                    var data = {"id": rows.join()};
                    var url = $.table._options.deleteUrl.replace("{id}", data.id);
                    $.operate.submit(url, "delete", "json", data);
                });
            },
            //清空
            clean: function () {
                $.modal.confirm("确定清空所有" + $.table._options.modalName + "吗？", function () {
                    var url = $.table._options.cleanUrl;
                    $.operate.submit(url, "delete", "json", "");
                });
            },
            // 添加信息
            add: function (id) {
                var url = $.common.isEmpty(id) ? $.table._options.addUrl : $.table._options.addUrl.replace("{id}", id);
                $.modal.open("添加" + $.table._options.modalName, url);
            },
            //修改信息
            update: function (id) {
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
                $.modal.open("修改" + $.table._options.modalName, url);
            },
            // 添加信息 全屏
            addFull: function (id) {
                var url = $.common.isEmpty(id) ? $.table._options.addUrl : $.table._options.addUrl.replace("{id}", id);
                $.modal.openFull("添加" + $.table._options.modalName, url);
            },
            // 修改信息 全屏
            updateFull: function (id) {
                var url = "/404.html";
                if ($.common.isNotEmpty(id)) {
                    url = $.table._options.updateUrl.replace("{id}", id);
                } else {
                    var row = $.common.isEmpty($.table._options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._options.uniqueId);
                    url = $.table._options.updateUrl.replace("{id}", row);
                }
                $.modal.openFull("修改" + $.table._options.modalName, url);
            },
            // 保存信息
            save: function (url, data) {
                $.modal.loading("正在处理中，请稍候...");
                var config = {
                    url: url,
                    type: "put",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        $.operate.successCallback(result);
                    }
                };
                $.ajax(config);
            },
            ajaxSuccessAutoClose: function (result) {
                if (result.code == web_status.SUCCESS) {
                    $.modal.msgReload(result.msg, modal_status.SUCCESS);
                } else {
                    $.modal.msgReload(result.msg, modal_status.FAIL);
                }
                $.modal.closeLoading();
            },
            // 保存结果弹出msg刷新table表格
            ajaxSuccess: function (result) {
                $.modal.closeLoading();
                if (result.code == web_status.SUCCESS) {
                    $.modal.msgSuccess(result.msg);
                    $.table.refresh();
                } else {
                    $.modal.alertError(result.msg);
                }
            },
            // 成功结果提示msg（父窗体全局更新）
            saveSuccess: function (result) {
                if (result.code == web_status.SUCCESS) {
                    $.modal.msgReload("保存成功,正在刷新数据请稍候……", modal_status.SUCCESS);
                } else {
                    $.modal.alertError(result.msg);
                }
                $.modal.closeLoading();
            },
            // 成功回调执行事件（父窗体静默更新）
            successCallback: function (result) {
                if (result.code == web_status.SUCCESS) {
                    if (window.parent.$("#bootstrap-table").length > 0) {
                        window.parent.$.modal.msgSuccess(result.msg);
                        window.parent.$.table.refresh();
                        $.modal.close();
                    } else {
                        $.modal.msgReload("保存成功,正在刷新数据请稍候……", modal_status.SUCCESS);
                    }
                } else {
                    $.modal.alertError(result.msg);
                }
                $.modal.closeLoading();
            }
        },
        //校验封装处理
        validate: {
            //判断返回标识是否唯一，false不存在，true存在
            unique: function (value) {
                if (value == "0") {
                    return true;
                }
                return false;
            },
            //表单验证
            form: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                return $("#" + currentId).validate().form();
            },
        },
        common: {
            //判断字符串是否为空
            isEmpty: function (value) {
                if (value == null || this.trim(value) == "") {
                    return true;
                }
                return false;
            },
            // 是否显示数据 为空默认为显示
            visible: function (value) {
                if ($.common.isEmpty(value) || value == true) {
                    return true;
                }
                return false;
            },
            // 空格截取
            trim: function (value) {
                if (value == null) {
                    return "";
                }
                return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },
            //日期处理
            dateFormat: function (date) {
                var newDate = new Date(date).toJSON();
                var dateFormat = new Date(+new Date(newDate) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
                return dateFormat;
            },
            // 判断一个字符串是否为非空串
            isNotEmpty: function (value) {
                return !$.common.isEmpty(value);
            },
        }
    });
    /**
     * 通用方法封装处理
     */

    $(function () {
        // select2复选框事件绑定
        if ($.fn.select2 !== undefined) {
            $("select.form-control:not(.noselect2)").each(function () {
                $(this).select2().on("change", function () {
                    $(this).valid();
                })
            })
        }
        // // checkbox 事件绑定
        // if ($(".check-box").length > 0) {
        //     $(".check-box").iCheck({
        //         checkboxClass: 'icheckbox-blue',
        //         radioClass: 'iradio-blue',
        //     })
        // }
        // // radio 事件绑定
        // if ($(".radio-box").length > 0) {
        //     $(".radio-box").iCheck({
        //         checkboxClass: 'icheckbox-blue',
        //         radioClass: 'iradio-blue',
        //     })
        // }
        // laydate 时间控件绑定
        if ($(".select-time").length > 0) {
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                var startDate = laydate.render({
                    elem: '#startTime',
                    max: $('#endTime').val(),
                    theme: 'molv',
                    trigger: 'click',
                    done: function (value, date) {
                        // 结束时间大于开始时间
                        if (value !== '') {
                            endDate.config.min.year = date.year;
                            endDate.config.min.month = date.month - 1;
                            endDate.config.min.date = date.date;
                        } else {
                            endDate.config.min.year = '';
                            endDate.config.min.month = '';
                            endDate.config.min.date = '';
                        }
                    }
                });
                var endDate = laydate.render({
                    elem: '#endTime',
                    min: $('#startTime').val(),
                    theme: 'molv',
                    trigger: 'click',
                    done: function (value, date) {
                        // 开始时间小于结束时间
                        if (value !== '') {
                            startDate.config.max.year = date.year;
                            startDate.config.max.month = date.month - 1;
                            startDate.config.max.date = date.date;
                        } else {
                            startDate.config.max.year = '';
                            startDate.config.max.month = '';
                            startDate.config.max.date = '';
                        }
                    }
                });
            });
        }
        // laydate time-input 时间控件绑定
        if ($(".time-input").length > 0) {
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                var times = $(".time-input");
                for (var i = 0; i < times.length; i++) {
                    var time = times[i];
                    laydate.render({
                        elem: time,
                        theme: 'molv',
                        trigger: 'click',
                        done: function (value, date) {
                        }
                    });
                }
            });
        }
        // tree 关键字搜索绑定
        if ($("#keyword").length > 0) {
            $("#keyword").bind("focus", function focusKey(e) {
                if ($("#keyword").hasClass("empty")) {
                    $("#keyword").removeClass("empty");
                }
            }).bind("blur", function blurKey(e) {
                if ($("#keyword").val() === "") {
                    $("#keyword").addClass("empty");
                }
                $.tree.searchNode(e);
            }).bind("input propertychange", $.tree.searchNode);
        }
        // 复选框后按钮样式状态变更
        $("#bootstrap-table").on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", function () {
            var ids = $("#bootstrap-table").bootstrapTable("getSelections");

            $('#toolbar .btn-del').toggleClass('disabled', !ids.length);
            $('#toolbar .btn-edit').toggleClass('disabled', ids.length != 1);
            ;
        });
        // tree表格树 展开/折叠
        var expandFlag = false;
        $("#expandAllBtn").click(function () {
            if (expandFlag) {
                $('#bootstrap-table').bootstrapTreeTable('expandAll');
            } else {
                $('#bootstrap-table').bootstrapTreeTable('collapseAll');
            }
            expandFlag = expandFlag ? false : true;
        })
    });

    /** 创建选项卡 */
    function createMenuItem(dataUrl, menuName) {
        dataIndex = $.common.random(1, 100),
            flag = true;
        if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;
        var topWindow = $(window.parent.document);
        // 选项卡菜单已存在
        $('.menuTab', topWindow).each(function () {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.menuTab').removeClass('active');
                    $('.page-tabs-content').animate({marginLeft: ""}, "fast");
                    // 显示tab对应的内容区
                    $('.mainContent .RuoYi_iframe', topWindow).each(function () {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.RuoYi_iframe').hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });
        // 选项卡菜单不存在
        if (flag) {
            var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $('.menuTab', topWindow).removeClass('active');

            // 添加选项卡对应的iframe
            var str1 = '<iframe class="RuoYi_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
            $('.mainContent', topWindow).find('iframe.RuoYi_iframe').hide().parents('.mainContent').append(str1);

            // 添加选项卡
            $('.menuTabs .page-tabs-content', topWindow).append(str);
        }
        return false;
    }

    /** 设置全局ajax处理 */
    $.ajaxSetup({
        complete: function (XMLHttpRequest, textStatus) {
            if (textStatus == 'timeout') {
                $.modal.alertWarning("服务器超时，请稍后再试！");
                $.modal.closeLoading();
            } else if (textStatus == "parsererror") {
                $.modal.alertWarning("服务器错误，请联系管理员！");
                $.modal.closeLoading();
            }
        }
    });

})(jQuery);

/** 消息状态码 */
web_status = {
    SUCCESS: 200,
    FAIL: 500
};

/** 弹窗状态码 */
modal_status = {
    SUCCESS: "success",
    FAIL: "error",
    WARNING: "warning"
};