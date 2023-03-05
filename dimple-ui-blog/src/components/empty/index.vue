<template>
  <div class="empty">
    {{ text }}
  </div>
</template>
<script>
export default {
  name: 'empty',
  props: {
    text: {
      type: String,
      default: '暂无数据'
    }
  },
  data() {
    return {}
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.empty {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  @include themeify() {
    color: themed('color-ele-holder');
  }
}
</style>
