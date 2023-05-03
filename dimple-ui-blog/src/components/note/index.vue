<template>
  <div class="note">
    <div class="note__content">
      <i class="el-icon-s-flag"></i>
      <slot></slot>
    </div>
  </div>
</template>
<script>
export default {
  name: 'note',
  data() {
    return {}
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.note {
  margin: 0 0 20px;
  border-left: 5px solid #5ccdc1;
  border-radius: 5px;
  background: rgba(92, 205, 193, 0.2);
  padding: 16px 16px 16px 10px;

  &__content {
    display: flex;
    align-items: center;

    .el-icon-s-flag {
      font-size: 20px;
      color: #5ccdc1;
      margin-right: 12px;
    }
  }
}
</style>
