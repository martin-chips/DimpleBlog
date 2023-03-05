<template>
  <div class="horizontal-navbar">
    <div class="horizontal-navbar__menu" @click="openSearch">
      <i class="el-icon-search"></i>
      <span>搜索</span>
    </div>
    <div class="horizontal-navbar__menu" @click="goTo('home')">
      <i class="el-icon-s-home"></i>
      <span>首页</span>
    </div>
    <el-dropdown class="horizontal-navbar__menu">
      <span class="navbar-menu-title">
        <i class="el-icon-discover"></i>
        导航
        <i class="el-icon-arrow-down"></i>
      </span>
      <el-dropdown-menu slot="dropdown" class="menu-list">
        <el-dropdown-item @click.native="goTo('archives')">
          <i class="el-icon-files"></i>
          归档
        </el-dropdown-item>
        <el-dropdown-item @click.native="goTo('tags')">
          <i class="el-icon-collection-tag"></i>
          标签
        </el-dropdown-item>
        <el-dropdown-item @click.native="goTo('category')">
          <i class="el-icon-folder-opened"></i>
          分类
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <div class="horizontal-navbar__menu" @click="goTo('messageBoard')">
      <i class="el-icon-chat-dot-round"></i>
      <span>留言板</span>
    </div>
    <div class="horizontal-navbar__menu" @click="friendLink">
      <i class="el-icon-ship"></i>
      <span>友链</span>
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    goTo(val) {
      this.$emit('goTo', val)
    },
    openSearch() {
      this.$emit('openSearch')
    },
    friendLink() {
      this.$message({
        message: '拼命开发中😭'
      })
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.horizontal-navbar {
  &__menu {
    display: inline-block;
    padding: 0 10px;
    cursor: pointer;
    position: relative;
    [class^='el-icon-'] {
      font-weight: 900;
      margin-right: 4px;
      font-size: 15px;
    }
    @include themeify() {
      color: themed('color-navbar');
    }
    .navbar-menu-title {
      .el-icon-arrow-down {
        display: inline-block;
        transition: all ease 0.38s;
      }
    }
    .navbar-menu-title:hover {
      .el-icon-arrow-down {
        transform: rotate(180deg);
      }
    }
  }
  .horizontal-navbar__menu:hover.horizontal-navbar__menu:after {
    width: 100%;
  }
  &__menu:after {
    content: '';
    position: absolute;
    bottom: -5px;
    left: 0;
    width: 0;
    height: 3px;
    background: #80c8f8;
    display: inline-block;
    transition: all ease 0.38s;
  }
}
.navbar-rollup {
  .horizontal-navbar__menu {
    @include themeify() {
      color: themed('color-navbar-rollup-color');
    }
  }
}
.navbar-transparent {
  .horizontal-navbar__menu {
    @include themeify() {
      color: themed('color-navbar');
    }
  }
}
</style>
