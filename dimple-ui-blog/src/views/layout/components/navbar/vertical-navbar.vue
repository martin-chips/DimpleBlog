<template>
  <div class="vertical-navbar">
    <div class="vertical-navbar__intro">
      <site-introduction></site-introduction>
    </div>
    <split-line></split-line>
    <el-menu :default-active="activeIndex" class="el-menu-demo" @select="handleSelect">
      <el-menu-item index="home">
        <i class="el-icon-s-home"></i>
        <span>首页</span>
      </el-menu-item>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-discover"></i>
          <span>导航</span>
        </template>
        <el-menu-item index="archives">
          <i class="el-icon-files"></i>
          <span>归档</span>
        </el-menu-item>
        <el-menu-item index="tags">
          <i class="el-icon-collection-tag"></i>
          <span>标签</span>
        </el-menu-item>
        <el-menu-item index="category">
          <i class="el-icon-folder-opened"></i>
          <span>分类</span>
        </el-menu-item>
      </el-submenu>
      <el-menu-item index="messageBoard">
        <i class="el-icon-chat-dot-round"></i>
        <span>留言板</span>
      </el-menu-item>
      <el-menu-item index="links">
        <i class="el-icon-ship"></i>
        <span>友链</span>
      </el-menu-item>
      <el-menu-item index="about">
        <i class="el-icon-user"></i>
        <span>关于我</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>
<script>
import siteIntroduction from "@/views/components/site-introduction";
import splitLine from "@/components/splitLine/";

export default {
  data() {
    return {}
  },
  components: {
    splitLine,
    siteIntroduction
  },
  computed: {
    activeIndex() {
      return this.$route.name
    }
  },
  methods: {
    handleSelect(val) {
      this.$emit('goTo', val)
    },
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.vertical-navbar {
  .el-menu-demo > :nth-child(1) {
    -webkit-animation: sidebarItem .2s;
    -moz-animation: sidebarItem .2s;
    -o-animation: sidebarItem .2s;
    -ms-animation: sidebarItem .2s;
    animation: sidebarItem .2s
  }

  .el-menu-demo > :nth-child(2) {
    -webkit-animation: sidebarItem .4s;
    -moz-animation: sidebarItem .4s;
    -o-animation: sidebarItem .4s;
    -ms-animation: sidebarItem .4s;
    animation: sidebarItem .4s
  }

  .el-menu-demo > :nth-child(3) {
    -webkit-animation: sidebarItem .6s;
    -moz-animation: sidebarItem .6s;
    -o-animation: sidebarItem .6s;
    -ms-animation: sidebarItem .6s;
    animation: sidebarItem .6s
  }

  .el-menu-demo > :nth-child(4) {
    -webkit-animation: sidebarItem .8s;
    -moz-animation: sidebarItem .8s;
    -o-animation: sidebarItem .8s;
    -ms-animation: sidebarItem .8s;
    animation: sidebarItem .8s
  }

  .el-menu-demo > :nth-child(5) {
    -webkit-animation: sidebarItem 1s;
    -moz-animation: sidebarItem 1s;
    -o-animation: sidebarItem 1s;
    -ms-animation: sidebarItem 1s;
    animation: sidebarItem 1s
  }

  .el-menu-item, .el-submenu__title {
    @include themify() {
      color: themed('font-color');
    }
  }

  .el-menu-item:hover, .el-submenu__title:hover {
    @include themify() {
      background: themed('text-bg-hover');
    }
  }

  .el-submenu__title {

  }

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
      @include themify() {
        color: themed('nav-header');
      }

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

  .vertical-navbar__menu:hover.vertical-navbar__menu:after {
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
  .vertical-navbar__menu {
    @include themify() {
      color: themed('font-color');
    }
  }
}

.navbar-transparent {
  .vertical-navbar__menu {
    @include themify() {
      color: themed('nav-header');
    }
  }
}
</style>
