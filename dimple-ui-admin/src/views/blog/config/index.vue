<template>
  <div class="app-container">
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 站点配置</span>
        <el-form style="width: 800px;margin: 0 auto;" :model="configForm" :rules="rules" ref="configForm" label-width="100px">
          <el-form-item label="管理员邮箱" prop="email">
            <el-input v-model="configForm.email"></el-input>
          </el-form-item>
          <el-form-item label="友链回复模板" prop="enableCommentReply">
            <editor v-model="configForm.content" :min-height="192"/>
          </el-form-item>
          <el-form-item label="灰色主题" prop="grayTheme">
            <el-switch v-model="configForm.mask"></el-switch>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('configForm')">立即创建</el-button>
            <el-button @click="resetForm('configForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>

export default {
  data() {
    return {
      configForm: {


      },
      rules: {

      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submitimple!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>
