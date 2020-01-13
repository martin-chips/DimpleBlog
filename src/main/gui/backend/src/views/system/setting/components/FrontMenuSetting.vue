<template>
  <el-form :model="menuList" ref="form" label-width="100px">
    <el-form-item prop="category" label="分类">
      
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "FrontMenuSetting",
    data() {
      return {
        menuList: [
          {
            title: "分类",
            prop: "category",
            target: false,
            order: 1,
            url: "#"
          }
        ]
      }
    }
  }
</script>

<style scoped>

</style>
