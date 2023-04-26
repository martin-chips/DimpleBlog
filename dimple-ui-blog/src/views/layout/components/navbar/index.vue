<template>
  <nav class="navbar" :class="{ 'navbar-rollup': rollBack&&!rollbackTop, 'navbar-transparent': rollbackTop }">
    <div>
      <a href="/">
        <span class="navbar__name">
          Dimple's Blog
        </span>
      </a>
    </div>
    <div class="navbar__menus">
      <horizontal-navbar @goTo="goTo" @openSearch="openSearch"></horizontal-navbar>
      <div class="vertical-menu-trigger">
        <div class="trigger__search" @click="openSearch">
          <i class="el-icon-search"></i>
          <span>搜索</span>
        </div>
        <i class="el-icon-menu" @click="drawer = true"></i>
      </div>
    </div>
    <el-dialog
      title="搜索"
      :visible.sync="searchVisible"
      :style="{ minWidth: '340px' }"
      width="30%"
      :append-to-body="true"
      :lock-scroll="false"
      custom-class="search-box"
    >
      <search @hasJumped="closeSearch"></search>
    </el-dialog>
    <el-drawer :append-to-body="true" custom-class="navbar-drawer" :size="300" :visible.sync="drawer" direction="rtl">
      <vertical-navbar @goTo="goTo"></vertical-navbar>
    </el-drawer>
  </nav>
</template>
<script>
import {mapMutations, mapState} from 'vuex'
import {getScrollTop} from '@/utils/getScrollTop'
import search from '@/views/components/search'
import horizontalNavbar from './horizontal-navbar'
import verticalNavbar from './vertical-navbar'
import debounce from 'lodash/debounce'

export default {
  name: 'navbar',
  props: {},
  components: {search, horizontalNavbar, verticalNavbar},
  data() {
    return {
      lastTopPos: 0,
      rollbackTop: false,
      searchVisible: false,
      drawer: false
    }
  },
  mounted() {
    if (getScrollTop() == 0) this.rollbackTop = true
    window.addEventListener('scroll', this.scrollHandler)
    window.addEventListener('resize', this.resizeHandler)
  },
  computed: {
    ...mapState(['rollBack'])
  },
  methods: {
    ...mapMutations(['setRollBack']),
    goTo(name) {
      this.$router.push({name})
    },
    // 文章关键词搜索
    openSearch() {
      this.searchVisible = true
    },
    closeSearch() {
      this.searchVisible = false
    },
    scrollHandler() {
      const nowTopPos = getScrollTop()
      // nowTopPos this.lastTopPos在移动端可为负数
      if (nowTopPos >= 0 && this.lastTopPos >= 0 && nowTopPos - this.lastTopPos > 0) {
        this.setRollBack(false)
        this.rollbackTop = false
      } else if (nowTopPos <= 0) {
        this.rollbackTop = true
      } else this.setRollBack(true)
      this.lastTopPos = nowTopPos
    },
    resizeHandler: debounce(function () {
      const width = document.documentElement.clientWidth

      // 切换至手机尺寸后，将sticky相关状态全部置空
      if (width > 768) {
        this.drawer = false
      }
    }, 200)
  },
  destroyed() {
    window.removeEventListener('scroll', this.scrollHandler)
    window.removeEventListener('resize', this.resizeHandler)
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.navbar {
  width: 100%;
  height: 60px;
  background: transparent;
  position: fixed;
  top: -60px;
  z-index: 1200;
  transition: all ease 0.38s;
  text-align: right;
  padding: 0 36px;
  @include flex-box-space;
  @include themify() {
    color: themed('font-color');
  }
  @include respond-to(xs) {
    padding: 0 16px;
  }

  &__name {
    text-shadow: 2px 2px 4px rgba(0, 0, 0, .15);
    @include themify() {
      color: themed('font-color');
    }
    font-weight: 700;
    font-size: 18px;
  }

  &__menus {
    display: flex;
    align-items: center;
  }

  .vertical-menu-trigger {
    display: none;
  }

  @include respond-to(xs) {
    .horizontal-navbar {
      display: none;
    }
    .vertical-menu-trigger {
      display: flex;
      align-items: center;

      .trigger__search {
        margin-right: 20px;
      }

      > i {
        font-size: 18px;
        margin-left: 12px;
      }

      .el-icon-search {
        margin-right: 8px;
      }
    }
  }
}

.menu-list {
  [class^='el-icon-'] {
    font-weight: 900;
    margin-right: 4px;
    font-size: 16px;
  }

  .popper__arrow {
    display: none;
  }
}

.navbar-rollup {
  position: fixed;
  transform: translateY(100%);
  box-shadow: 0 5px 6px -5px rgba(133, 133, 133, 0.6);
  @include themify() {
    background: themed('color-navbar-rollup-bg');
  }
}

.navbar-transparent {
  transform: translateY(100%);
  background: transparent;
  box-shadow: none;

  .navbar__name a {
    @include themify() {
      color: themed('light-grey');
    }
  }
}
</style>
