import {list, del, add, update, clean} from "@/api/initDataApi";

export default {
  data() {
    return {
      //弹框的标题
      title: '',
      //类别
      modalName: '',
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      //选中行数据
      row: {},
      // 表格遮罩层
      loading: true,
      //删除按钮遮罩层
      delLoading: false,
      // 总条数
      total: 0,
      // 表格数据
      list: [],
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      //用来重置表单的form,有些属性清空不了
      formReset: {},
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderByColumn: "createTime",
        isAsc: "desc"
        //此处和具体的组件合并后可以设置查询参数
      },
      //操作延时时间
      time: 50,
      //URL前缀
      base: ''
    }
  },
  methods: {
    /** 初始化 */
    async init() {
      if (!await this.beforeInit()) {
        return
      }
      return new Promise((resolve, reject) => {
        this.loading = true;
        list(this.base, this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.total = response.total;
          this.list = response.rows;
          setTimeout(() => {
            this.loading = false
          }, this.time);
          resolve(response)
        }).catch(err => {
          this.loading = false;
          reject(err)
        })
      })
    },
    /** 搜索查询 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.init()
    },
    /**重置当前搜索框*/
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.queryParams={};
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.row = selection[0];
      this.ids = selection.map(item => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length
    },
    /** 单条删除 */
    handleSubDelete(id) {
      this.delLoading = true;
      return new Promise((resolve, reject) => {
        del(this.base, id).then(response => {
          this.delLoading = false;
          if (response.code == 200) {
            this.$refs[id].doClose();
            this.init();
            this.msgSuccess("删除成功");
          } else {
            this.msgError("删除失败");
          }
          resolve(response)
        }).catch(err => {
          this.delLoading = false;
          reject(err)
        })
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.title = "添加" + this.modelName;
      this.form = this.formReset;
      this.open = true;
    },
    /** 提交按钮 */
    submitForm: function (refName) {
      if (this.$refs[refName] == undefined) {
        refName = 'form';
      }
      let obj = JSON.parse(JSON.stringify(this.form));
      this.$refs[refName].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            update(this.base, obj).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.init();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            add(this.base, obj).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.init();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.$nextTick(() => {
        if (this.$refs["form"] !== undefined) {
          this.$refs["form"].resetFields();
        }
      });
      this.form = {};
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const data = row || this.row;
      this.form = data;
      this.open = true;
      this.title = "修改" + this.modelName;
    },
    handleExport() {
    },
    /** 删除按钮操作 */
    handleDelete() {
      this.delLoading = true;
      let $this = this;
      this.$confirm('是否确认删除主键为"' + $this.ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        del($this.base, $this.ids).then(response => {
          $this.delLoading = false;
          if (response.code == 200) {
            $this.init();
            $this.msgSuccess("删除成功");
          } else {
            $this.msgError("删除失败");
          }
        }).catch(err => {
          $this.delLoading = false
        })
      }).catch(err => {
        $this.delLoading = false
      })
    },
    /**清空数据*/
    handleClean() {
      this.delLoading = true;
      let $this = this;
      this.$confirm('是否确认清空所有数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        clean($this.base).then(response => {
          $this.delLoading = false;
          if (response.code == 200) {
            $this.init();
            $this.msgSuccess("清空成功");
          } else {
            $this.msgError("清空失败:" + response.msg);
          }
        }).catch(err => {
          $this.delLoading = false
        })
      }).catch(err => {
        $this.delLoading = false
      })
    }
  }
}
