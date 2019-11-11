import {list, del, add, update, get} from "@/api/initDataApi";

export default {
  data() {
    return {
      //类别
      modalName: '',
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      //选中行数据
      row: undefined,
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
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        this.loading = true
        list(this.base, this.queryParams).then(response => {
          this.total = response.total
          this.list = response.rows
          setTimeout(() => {
            this.loading = false
          }, this.time)
          resolve(response)
        }).catch(err => {
          this.loading = false
          reject(err)
        })
      })
    },
    /** 搜索查询 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.init()
    },
    /**重置当前搜索框*/
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.row = selection[0];
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 单条删除 */
    handleSubDelete(id) {
      this.delLoading = true;
      return new Promise((resolve, reject) => {
        del(this.base, id).then(response => {
          this.delLoading = false
          if (response.code == 200) {
            this.$refs[id].doClose();
            this.init();
            this.msgSuccess("删除成功");
          } else {
            this.msgError("删除失败");
          }
          resolve(response)
        }).catch(err => {
          this.delLoading = false
          reject(err)
        })
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加" + this.modelName;
    },
    /** 提交按钮 */
    submitForm: function (refName) {
      if (this.$refs[refName] == undefined) {
        refName = 'form';
      }
      this.$refs[refName].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            update(this.base, this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.init();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            add(this.base, this.form).then(response => {
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
      this.form = {
        id: undefined,
        title: undefined,
        description: undefined,
        support: 1,
      };
      this.resetForm("form");
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const data = row || this.row;
      this.reset();
      this.form = data;
      this.open = true;
      this.title = "修改" + this.modelName;
    },
    handleExport() {
    },
    /** 删除按钮操作 */
    handleDelete() {
      this.delLoading = true;
      let $this = this
      this.$confirm('是否确认删除主键为"' + $this.ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        del($this.base, $this.ids).then(response => {
          $this.delLoading = false
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
  }
}
