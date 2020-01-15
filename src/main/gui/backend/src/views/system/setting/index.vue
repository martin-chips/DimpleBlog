<template>
  <div class="app-container">
    <el-tabs>
      <el-tab-pane label="关于我" name="first">
        <AboutSetting/>
      </el-tab-pane>
      <el-tab-pane label="菜单设置" name="second">
        <FrontMenuSetting/>
      </el-tab-pane>
      <el-tab-pane label="前台网站设置" name="three">
        <SiteSetting/>
      </el-tab-pane>
      <el-tab-pane label="Email设置" name="four">
        <EmailSetting/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import AboutSetting from "./components/AboutSetting";
  import EmailSetting from "./components/EmailSetting";
  import FrontMenuSetting from "./components/FrontMenuSetting";
  import SiteSetting from "./components/SiteSetting";

  export default {
    name: "index",
    components: {SiteSetting, FrontMenuSetting, AboutSetting, EmailSetting},
    data() {
      return {}
    },
  }
</script>

<style scoped>

</style>
