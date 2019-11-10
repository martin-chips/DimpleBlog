import {list, del, add, update, get} from "@/api/initDataApi";

export default {
  data() {
    return {
      modalName: '分类',
      // 表格遮罩层
      loading: true,
      //删除遮罩层
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
      time: 50
    }
  },
  watch: {
    open: function (val, oldVla) {
      this.$refs["form"].resetFields();
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
    /** 单条删除 */
    handleSubDelete(id) {
      this.delLoading = true;
      return new Promise((resolve, reject) => {
        del(this.base, id).then(response => {
          if (response.code == 200) {
            this.init();
            this.msgSuccess("删除成功");
          } else {
            this.msgError("删除失败");
          }
          setTimeout(() => {
            this.delLoading = false
          }, this.time)
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
  }
}
