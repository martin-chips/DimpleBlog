<template>
  <div class="login-qrcode">
    <h2 class="title">Dimple后台管理系统</h2>
    <vueQr text="https://bianxf.com"></vueQr>
  </div>
</template>


<script>
import vueQr from 'vue-qr/src/packages/vue-qr.vue'

export default {
  components: {
    vueQr
  }
}
</script>

<style lang="scss" scoped>
@import "../index";
</style>
