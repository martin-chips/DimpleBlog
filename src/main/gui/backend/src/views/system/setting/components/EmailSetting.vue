<template>
  <el-form ref="form" :model="setting" :rules="rules" label-width="300px">
    <el-form-item label="邮件服务器SMTP地址" prop="host">
      <el-input v-model="setting.host" style="width: 60%;" placeholder="请输入邮件服务器SMTP地址"/>
    </el-form-item>
    <el-form-item label="邮件服务器 SMTP 端口" prop="port">
      <el-input v-model="setting.port" style="width: 60%;" placeholder="请输入邮件服务器 SMTP 端口"/>
    </el-form-item>
    <el-form-item label="发件者用户名" prop="user">
      <el-input v-model="setting.user" style="width: 60%;" placeholder="请输入发件者用户名"/>
    </el-form-item>
    <el-form-item label="密码或者授权码" prop="password">
      <el-input v-model="setting.password" style="width: 60%;" placeholder="请输入密码或者授权码"/>
    </el-form-item>
    <el-form-item label="发件人邮箱" prop="fromEmail">
      <el-input v-model="setting.fromEmail" style="width: 60%;" placeholder="请输入发件人邮箱"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
      <el-button type="danger" size="mini" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {updateEmailSetting, getEmailSetting} from "@/api/system/setting";

  export default {
    name: "SiteSetting",
    data() {
      return {
        setting: {},
        // 表单校验
        rules: {
          host: [
            {required: true, message: "邮件服务器SMTP地址不能为空", trigger: "blur"}
          ],
          port: [
            {required: true, message: "邮件服务器 SMTP 端口不能为空", trigger: "blur"}
          ],
          user: [
            {required: true, message: "发件者用户名不能为空", trigger: "blur"}
          ],
          password: [
            {required: true, message: "密码或者授权码不能为空", trigger: "blur"}
          ],
          fromEmail: [
            {required: true, message: "发件人邮箱不能为空", trigger: "blur"}
          ]
        }
      }
    },
    created() {
      getEmailSetting().then(response => {
        this.setting = response.data;
      })
    },
    methods: {
      submit() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            updateEmailSetting(JSON.parse(JSON.stringify(this.setting))).then(
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
