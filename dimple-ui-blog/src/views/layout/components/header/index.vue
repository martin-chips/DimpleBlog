<template>
  <div class="header" :style="cover ? { backgroundImage: 'url(' + cover + ')' } : ''">
    <div class="header__content">
      <slot>
        <div class="header__default-title">
          <h1 style="line-height: initial;">{{ title }}</h1>
        </div>
      </slot>
    </div>
  </div>
</template>
<script>
export default {
  name: 'layout-header',
  props: {
    cover: {
      type: String
      // default: ''
    },
    title: {
      type: String,
      default: '标题'
    }
  },
  data() {
    return {}
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.header {
  position: relative;
  height: 400px;
  background-color: #49b1f5;
  background-size: cover;
  background-position: center center;

  &__content {
    position: absolute;
    width: 100%;
    height: 100%;
  }

  &__default-title {
    width: 100%;
    height: 100%;
    font-size: 20px;
    @include themify() {
      color: themed('text-highlight-color');
    }
    @include flex-box-center;
  }
}

.header:before {
  content: '';
  background-color: rgba(0, 0, 0, .5);
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
