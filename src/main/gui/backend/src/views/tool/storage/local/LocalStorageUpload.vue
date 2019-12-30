<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="open"
             :title="isAdd ? '上传文件' : '编辑文件'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="文件名">
        <el-input v-model="form.name" style="width: 370px;"/>
      </el-form-item>
      <!--   上传文件   -->
      <el-form-item v-if="isAdd" label="上传">
        <el-upload
          ref="upload"
          :limit="1"
          :before-upload="beforeUpload"
          :auto-upload="false"
          :headers="headers"
          :on-success="handleSuccess"
          :on-error="handleError"
          :action="fileUploadApi + '?name=' + form.name">
          <div><i class="el-icon-upload"/> 添加文件</div>
          <div slot="tip" class="el-upload__tip">可上传任意格式文件，且不超过100M</div>
        </el-upload>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="submitForm">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {getToken} from '@/utils/auth'
  import {updateLocalStorage} from '@/api/tool/local'

  export default {
    props: {
      isAdd: {
        type: Boolean,
        required: true
      }
    },
    data() {
      return {
        loading: false,
        open: false,
        fileUploadApi: process.env.VUE_APP_BASE_API + "/tool/localStorage",
        headers: {'Authorization': 'Bearer ' + getToken()},
        form: {
          id: '',
          name: ''
        },
        rules: {}
      }
    },

    methods: {
      cancel() {
        this.resetForm()
      },
      submitForm() {
        this.loading = true;
        if (this.isAdd) {
          this.$refs.upload.submit()
        } else {
          this.handleUpdate()
        }
      },
      handleUpdate() {
        updateLocalStorage(this.form).then(res => {
          this.resetForm();
          this.msgSuccess("修改成功");
          this.loading = false;
          this.$parent.getList()
        }).catch(err => {
          this.loading = false;
          console.log(err.response.data.message)
        })
      },
      resetForm() {
        this.open = false;
        this.$refs['form'].resetFields();
        this.form = {
          id: '',
          name: ''
        }
      },
      beforeUpload(file) {
        let isLt2M = true;
        isLt2M = file.size / 1024 / 1024 < 100;
        if (!isLt2M) {
          this.msgError('上传文件大小不能超过 100MB!')
        }
        this.loading = false;
        return isLt2M
      },
      handleSuccess(response, file, fileList) {
        this.open = false;
        this.resetForm();
        this.$refs.upload.clearFiles();
        this.$parent.getList()
      },
      // 监听上传失败
      handleError(e, file, fileList) {
        const msg = JSON.parse(e.msg);
        this.msgError(msg);
        this.loading = false
      }
    }
  }
</script>

<style scoped>
</style>
