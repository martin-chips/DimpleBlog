<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu :default-active="$route.path" class="menu" mode="horizontal" :router="true">
          <el-menu-item index="/">首页</el-menu-item>
          <el-submenu index="/category">
            <template slot="title">分类</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-submenu>
          <el-menu-item index="/archive">归档</el-menu-item>
          <el-menu-item index="/leaveComment">留言</el-menu-item>
          <el-menu-item index="/about">关于</el-menu-item>
        </el-menu>
        <div class="line"></div>
      </el-header>
      <el-container>
        <router-view/>
      </el-container>
      <el-footer class="footer">Footer</el-footer>
    </el-container>
    <el-tooltip placement="top" content="回到顶部">
      <el-backtop :visibility-height="200" :back-position="0" transition-name="fade"/>
    </el-tooltip>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {}
    }
  }

</script>

<style scoped>

  .footer {
    height: 60px;
    position: fixed;
    bottom: 0;
    left: 0px;
    width: 100%
  }

  .menu {
    margin: 0 auto;
    width: 50%
  }

  .el-menu-item {
    margin: 0px 3%;
  }
</style>
