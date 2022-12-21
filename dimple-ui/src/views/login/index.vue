<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-left">
        <img src="@/assets/login/side-logo.png">
      </div>
      <div class="login-form">
        <div class="info-qrcode">{{ accountLogin ? '扫码登录' : '账号登录' }}</div>
        <img class="qrcode" src="@/assets/login/qrcode-icon.png" @click="handleClick">

        <LoginForm v-if="accountLogin"/>
        <LoginQrcode v-else/>
      </div>
    </div>
  </div>
</template>

<script>
import LoginForm from "@/views/login/components/LoginForm.vue";
import LoginQrcode from "@/views/login/components/LoginQrcode.vue";

export default {
  name: "LoginIndex",
  components: {
    LoginForm, LoginQrcode
  },
  data() {
    return {
      accountLogin: true
    }
  },
  methods: {
    handleClick() {
      this.accountLogin = !this.accountLogin
    }
  }
}
</script>
<style lang="scss" scoped>
@import "index";
</style>
