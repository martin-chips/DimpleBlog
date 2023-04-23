<template>
  <div class="split-line">
    <hr />
  </div>
</template>
<script>
export default {
  name: 'splitLine',
  data() {
    return {}
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.split-line {
  hr {
    position: relative;
    margin: 40px 0;
    @include respond-to(xs) {
      margin: 20px 0;
    }
    @include themify() {
      border: 2px dashed themed('hr-border');
    }
    overflow: visible;
  }
  hr:hover:before {
    left: calc(100% - 40px) !important;
  }
  hr:before {
    font-family: element-icons;
    font-size: 26px;
    font-weight: 700;
    content: '\E771';
    position: absolute;
    top: -26px;
    transition: all ease-in-out 1s;
    transform: rotate(90deg);
    @include themify() {
      color: themed('hr-border');
    }
  }
}
</style>
