<template>
  <div class="panel-catalog">
    <el-card>
      <div class="panel__item-title">
        <i class="el-icon-folder-opened"></i>
        <span>目录</span>
      </div>
      <div class="panel__item-body">
        <tree-folder :catalogs="catalogs"></tree-folder>
      </div>
    </el-card>
  </div>
</template>
<script>
import {mapState} from 'vuex'
import treeFolder from './tree-folder.vue'

export default {
  name: 'panelCatalog',
  props: {},
  components: {
    treeFolder
  },
  data() {
    return {}
  },
  mounted() {
  },
  computed: {
    ...mapState(['catalogs'])
  },
  methods: {},
  destroyed() {
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
@import '~@/views/panel/style/mixins';

.panel-catalog {
  @include panel-frame;
  @include respond-to(xs) {
    display: none;
  }

  .panel__item-body {
    > ol {
      padding: 0;
    }
  }
}
</style>
