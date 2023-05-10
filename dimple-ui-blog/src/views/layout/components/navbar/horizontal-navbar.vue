<template>
  <div class="horizontal-navbar">
    <div class="horizontal-navbar__menu" @click="openSearch">
      <i class="el-icon-search"></i>
      <span>搜索</span>
    </div>
    <router-link to="/">
      <div class="horizontal-navbar__menu">
        <i class="el-icon-s-home"></i>
        <span>首页</span>
      </div>
    </router-link>
    <el-dropdown class="horizontal-navbar__menu">
      <span class="navbar-menu-title">
        <i class="el-icon-discover"></i>
        导航
        <i class="el-icon-arrow-down"></i>
      </span>
      <el-dropdown-menu slot="dropdown" class="menu-list">
        <router-link to="/app/archives">
          <el-dropdown-item>
            <i class="el-icon-files"></i>
            归档
          </el-dropdown-item>
        </router-link>
        <router-link to="/app/tags">
          <el-dropdown-item>
            <i class="el-icon-collection-tag"></i>
            标签
          </el-dropdown-item>
        </router-link>
        <router-link to="/app/category">
          <el-dropdown-item>
            <i class="el-icon-folder-opened"></i>
            分类
          </el-dropdown-item>
        </router-link>
      </el-dropdown-menu>
    </el-dropdown>
    <router-link to="/app/messageBoard">
      <div class="horizontal-navbar__menu">
        <i class="el-icon-chat-dot-round"></i>
        <span>留言板</span>
      </div>
    </router-link>
    <router-link to="/app/links">
      <div class="horizontal-navbar__menu">
        <i class="el-icon-ship"></i>
        <span>友链</span>
      </div>
    </router-link>
    <router-link to="/app/about">
      <div class="horizontal-navbar__menu">
        <i class="el-icon-user"></i>
        <span>关于我</span>
      </div>
    </router-link>
    <a href="/sitemap.xml">
      <div class="horizontal-navbar__menu">
        <i class="el-icon-place"></i>
        <span>站点地图</span>
      </div>
    </a>
  </div>
</template>
<script>
export default {
  methods: {
    openSearch() {
      this.$emit('openSearch')
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

    @include themify() {
      color: themed('nav-header');
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
    -webkit-transition: all .3s ease-in-out;
    -moz-transition: all .3s ease-in-out;
    -o-transition: all .3s ease-in-out;
    -ms-transition: all .3s ease-in-out;
    transition: all .3s ease-in-out;
  }
}

.navbar-rollup {
  .navbar__name {
    &:hover {
      color: #49b1f5;
    }
  }

  .horizontal-navbar__menu {
    @include themify() {
      color: themed('font-color');
    }
  }
}

.navbar-transparent {
  .navbar__menus, .navbar__name {
    @include themify() {
      color: themed('nav-header');
    }
  }
}
</style>
