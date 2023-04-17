<template>
  <div class="panel-introduction">
    <el-card>
      <site-introduction></site-introduction>
    </el-card>
  </div>
</template>
<script>
import siteIntroduction from '@/views/components/site-introduction'

export default {
  name: 'panelIntroduction',
  components: {
    siteIntroduction
  },
  props: {},
  data() {
    return {}
  },
  methods: {}
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
@import '~@/views/panel/style/mixins';

.panel-introduction {
  transition: all ease 0.38s;
  margin-bottom: 16px;
  @include panel-frame;
  @include respond-to(xs) {
    display: none;
  }
}
</style>
