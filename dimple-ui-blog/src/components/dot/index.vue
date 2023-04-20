<template>
  <div class="dot">
    <slot></slot>
  </div>
</template>
<script>
export default {
  name: 'dot',
  data() {
    return {}
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.dot {
  position: relative;
  display: flex;
  align-items: center;
  padding-left: 20px;
}
.dot:before {
  content: '';
  position: absolute;
  left: -2px;
  top: 50%;
  transform: translate(0, -50%);
  width: 8px;
  height: 8px;
  border: 3px solid;
  border-radius: 50%;
  font-weight: normal;
  transition: all ease 0.2s;
  @include themify() {
    border-color: themed('btn-bg');
  }
}
</style>
