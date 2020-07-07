<template>
  <div class="app-container">
    oo
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog
        :close-on-click-modal="false"
        :before-close="crud.cancelCU"
        :visible.sync="crud.status.cu > 0"
        :title="crud.status.title"
        width="500px"
      >
        <el-form
          ref="form"
          :model="form"
          :rules="rules"
          size="small"
          label-width="80px"
        >
          <el-form-item
            label=" id"
          >
            <el-input v-model="form.blogId" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 分类id "
          >
            <el-input v-model="form.categoryId" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 文章标题 "
          >
            <el-input v-model="form.title" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 摘要 "
          >
            <el-input v-model="form.summary" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="headerImgType"
            prop="headerImgType"
          >
            <el-input v-model="form.headerImgType" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 封面图片地址 "
          >
            <el-input v-model="form.headerImg" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="htmlContent"
          >
            <el-input v-model="form.htmlContent" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 正文内容 "
          >
            <el-input v-model="form.content" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 文章状态,
    1表示已经发表,
    2表示草稿箱 "
          >
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 是否允许评论,
    1表示允许,
    0表示不允许 "
          >
            <el-input v-model="form.comment" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 推荐 "
          >
            <el-input v-model="form.support" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 权重 "
          >
            <el-input v-model="form.weight" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 点赞数 "
          >
            <el-input v-model="form.like" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label=" 点击数 "
          >
            <el-input v-model="form.click" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="createBy"
          >
            <el-input v-model="form.createBy" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="createTime"
          >
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="updateBy"
          >
            <el-input v-model="form.updateBy" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="updateTime"
          >
            <el-input v-model="form.updateTime" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table
        ref="table"
        v-loading="crud.loading"
        :data="crud.data"
        size="small"
        style="width: 100%;"
        @selection-change="crud.selectionChangeHandler"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column
          prop="blogId"
          label=" id"
        />
        <el-table-column
          prop="categoryId"
          label=" 分类id "
        />
        <el-table-column
          prop="title"
          label=" 文章标题 "
        />
        <el-table-column
          prop="summary"
          label=" 摘要 "
        />
        <el-table-column
          prop="headerImgType"
          label="headerImgType"
        />
        <el-table-column
          prop="headerImg"
          label=" 封面图片地址 "
        />
        <el-table-column
          prop="htmlContent"
          label="htmlContent"
        />
        <el-table-column
          prop="content"
          label=" 正文内容 "
        />
        <el-table-column
          prop="status"
          label=" 文章状态,
    1表示已经发表,
    2表示草稿箱 "
        />
        <el-table-column
          prop="comment"
          label=" 是否允许评论,
    1表示允许,
    0表示不允许 "
        />
        <el-table-column
          prop="support"
          label=" 推荐 "
        />
        <el-table-column
          prop="weight"
          label=" 权重 "
        />
        <el-table-column
          prop="like"
          label=" 点赞数 "
        />
        <el-table-column
          prop="click"
          label=" 点击数 "
        />
        <el-table-column
          prop="createBy"
          label="createBy"
        />
        <el-table-column
          prop="createTime"
          label="createTime"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="updateBy"
          label="updateBy"
        />
        <el-table-column
          prop="updateTime"
          label="updateTime"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updateTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          v-permission="['admin','bgBlog:edit','bgBlog:del']"
          label="操作"
          width="150px"
          align="center"
        >
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import CRUD, { crud, form, header, presenter } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {
  blogId:
      null,
  categoryId:
      null,
  title:
      null,
  summary:
      null,
  headerImgType:
      null,
  headerImg:
      null,
  htmlContent:
      null,
  content:
      null,
  status:
      null,
  comment:
      null,
  support:
      null,
  weight:
      null,
  like:
      null,
  click:
      null,
  createBy:
      null,
  createTime:
      null,
  updateBy:
      null,
  updateTime:
      null
}
export default {
  name: 'Article',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      url: 'api/blog',
      sort: 'blogId,desc',
      crudMethod: { ...crudBgBlog }
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'bgBlog:add'],
        edit: ['admin', 'bgBlog:edit'],
        del: ['admin', 'bgBlog:del']
      },
      rules: {
        headerImgType:
            [
              { required: true, message: '不能为空', trigger: 'blur' }
            ]
      }
    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
