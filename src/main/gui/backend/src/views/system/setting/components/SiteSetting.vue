<template>
  <el-form ref="form" :model="setting" :rules="rules" label-width="80px">
    <el-form-item label="网站名称" prop="title">
      <el-input v-model="setting.title" placeholder="请输入网站名称"/>
    </el-form-item>
    <el-form-item label="网站备案号" prop="icp">
      <el-input v-model="setting.icp" placeholder="请输入网站备案号"/>
    </el-form-item>
    <el-form-item label="网站描述" prop="description">
      <el-input v-model="user.description" placeholder="请输入网站描述"/>
    </el-form-item>
    <el-form-item label="版权信息" prop="copyright">
      <el-input v-model="user.copyright" placeholder="请输入版权信息"/>
    </el-form-item>
    <el-form-item label="版权信息描述" prop="copyrightDesc">
      <el-input v-model="user.copyrightDesc" placeholder="请输入版权信息描述"/>
    </el-form-item>
    <el-form-item label="版权信息英文描述" prop="copyrightDescEn">
      <el-input v-model="user.copyrightDescEn" placeholder="请输入版权信息英文描述"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
      <el-button type="danger" size="mini" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {updateSiteSetting} from "@/api/system/setting";

  export default {
    name: "SiteSetting",
    data() {
      return {
        setting: {},
        // 表单校验
        rules: {
          title: [
            {required: true, message: "网站名不能为空", trigger: "blur"}
          ]
        }
      }
    },
    methods: {
      submit() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            updateSiteSetting(JSON.parse(JSON.stringify(this.setting))).then(
              response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                } else {
                  this.msgError(response.msg);
                }
              }
            );
          }
        });
      },
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({path: "/index"});
      }
    }

  }
</script>

<style scoped>

</style>
