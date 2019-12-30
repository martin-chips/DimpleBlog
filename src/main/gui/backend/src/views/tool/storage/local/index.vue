<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="文件名">
        <el-input v-model="queryParams.name" placeholder="请输入文件名" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="文件类型">
        <el-select v-model="queryParams.type" placeholder="文件类型" clearable size="small">
          <el-option
            v-for="dict in fileTypeOptions"
            :key="dict.value"
            :label="dict.key"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索
        </el-button>
        <el-button class="filter-item" size="mini" type="primary" icon="el-icon-upload" @click="handleAdd">文件上传
        </el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="localStorageList">
      <el-table-column prop="name" width="150px" label="文件名">
        <template slot-scope="scope">
          <el-popover :content="'file/' + scope.row.type + '/' + scope.row.realName"
                      placement="top-start" title="路径" width="200"
                      trigger="hover">
            <a slot="reference"
               :href="baseApi + '/file/' + scope.row.type + '/' + scope.row.realName"
               class="el-link--primary"
               style="word-break:keep-all;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;color: #1890ff;font-size: 13px;"
               target="_blank">
              {{ scope.row.name }}
            </a>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="path" label="预览图">
        <template slot-scope="{row}">
          <el-image :src=" baseApi + '/file/' + row.type + '/' + row.realName"
                    :preview-src-list="[baseApi + '/file/' + row.type + '/' + row.realName]" fit="contain" lazy
                    class="el-avatar">
            <div slot="error">
              <i class="el-icon-document"/>
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="suffix" label="文件类型"/>
      <el-table-column prop="type" label="类别"/>
      <el-table-column prop="size" label="大小"/>
      <el-table-column prop="createBy" label="操作人"/>
      <el-table-column prop="createTime" label="创建日期">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150px"
                       align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-popover :ref="scope.row.id" placement="top" width="180">
            <p>确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
              <el-button :loading="loading" type="primary" size="mini" @click="handleSubDelete(scope.row.id)">确定
              </el-button>
            </div>
            <el-button slot="reference" type="text" icon="el-icon-delete" size="mini">删除
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>

    <LocalStorageUpload ref="localStorageUpload" :is-add="isAdd"/>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>
  </div>
</template>

<script>
  import {listLocalStorage, delLocalStorage} from "@/api/tool/local";
  import LocalStorageUpload from "./LocalStorageUpload";

  export default {
    components: {
      LocalStorageUpload
    },
    data() {
      return {
        baseApi: process.env.VUE_APP_BASE_API,
        isAdd: false,
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 表格数据
        localStorageList: [],
        // 类型数据字典
        fileTypeOptions: [],
        // 日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: undefined,
          type: undefined,
          suffix: undefined
        },
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 单条删除 */
      handleSubDelete(id) {
        this.loading = true;
        delLocalStorage(id).then((response) => {
          this.$refs[id].doClose();
          this.loading = false;
          if (response.code == 200) {
            this.msgSuccess("删除成功");
          } else {
            this.msgError("删除失败");
          }
          this.getList();
        }).catch(err => {
          this.msgError("删除失败");
          this.$refs[id].doClose();
          this.loading = false;
        });
      },
      /** 查询参数列表 */
      getList() {
        this.loading = true;
        listLocalStorage(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.localStorageList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.isAdd = true;
        this.$refs.localStorageUpload.open = true
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.isAdd = false;
        const _this = this.$refs.localStorageUpload;
        _this.form = {
          id: row.id,
          name: row.name
        };
        _this.open = true
      },
    }
  }
</script>
