<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <keep-alive :include="cachedViews">
        <router-view :key="key" />
      </keep-alive>
    </transition>
    <div id="el-main-footer">
      <span>© 2019 Dimple <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache License 2.0</a></span>
      <span> ⋅ </span>
      <a href="http://www.beian.miit.gov.cn" target="_blank">蜀ICP备 19004343</a>
    </div>
  </section>
</template>

<script>
  export default {
    name: 'AppMain',
    computed: {
      cachedViews() {
        return this.$store.state.tagsView.cachedViews
      },
      key() {
        return this.$route.path
      }
    }
  }
</script>

<style lang="scss" scoped>
  .app-main {
    /* 50= navbar  50  */
    min-height: calc(100vh - 50px);
    width: 100%;
    position: relative;
    overflow: hidden;
  }

  .fixed-header+.app-main {
    padding-top: 50px;
  }

  .hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    min-height: calc(100vh - 84px);
  }

  .fixed-header+.app-main {
    padding-top: 84px;
  }
  }
  </style>

    <style lang="scss">
  // fix css style bug in open el-dialog
     .el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
  }
</style>
