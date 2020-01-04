<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="Ip">
        <el-input v-model="queryParams.ip" placeholder="请输入Ip" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="封禁原因">
        <el-input v-model="queryParams.description" placeholder="请输入封禁原因" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini"
                   @click="handleQuery">搜索
        </el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="handleAdd">新增
        </el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="handleAdd">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single"
                   @click="handleUpdate(null)">修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :loading="delLoading" :disabled="multiple"
                   @click="handleDelete">删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini"
                   @click="handleExport">导出
        </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center"/>
      <el-table-column label="IP" prop="ip" :show-overflow-tooltip="true"/>
      <el-table-column label="封禁原因" prop="description" :show-overflow-tooltip="true"/>
      <el-table-column label="上次拦截URL" prop="lastAccessUrl"/>
      <el-table-column label="上次拦截时间" align="center">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastAccessTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="拦截次数" prop="interceptCount"/>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-popover :ref="scope.row.id" placement="top" width="180">
            <p>确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消
              </el-button>
              <el-button :loading="loading" type="primary" size="mini" @click="handleSubDelete(scope.row.id)">确定
              </el-button>
            </div>
            <el-button slot="reference" type="text" icon="el-icon-delete" size="mini">删除
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="init"/>

    <!-- 添加或修改黑名单对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="Ip" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入IP地址"/>
        </el-form-item>
        <el-form-item label="封禁原因" prop="description">
          <el-input v-model="form.description" placeholder="请输入封禁原因"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {listBlacklist, getBlacklist, addBlacklist, updateBlacklist, delBlacklist} from "@/api/monitor/blacklist";
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        // 查询参数
        queryParams: {
          ip: undefined,
          description: undefined,
        },
        // 表单校验
        rules: {
          ip: [
            {required: true, message: "IP不能为空", trigger: "blur"}
          ],
          description: [
            {required: true, message: "权限字符不能为空", trigger: "blur"},
            {min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'change'}
          ]
        }
      };
    },
    created() {
      this.$nextTick(() => {
        this.init()
      })
    },
    methods: {
      beforeInit() {
        this.base = '/monitor/blacklist';
        this.modelName = '黑名单';
        return true
      },
    }
  };
</script>
