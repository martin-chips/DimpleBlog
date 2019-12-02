<template>
  <div>
    <el-container>
      <el-main width="70%">
        <el-carousel :interval="5000" arrow="always" height="300px">
          <el-carousel-item v-for="item in 4" :key="item">
            <h3>{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </el-main>
      <el-aside width="30%">Aside</el-aside>
    </el-container>

  </div>
</template>

<script>
  export default {
    name: "Home"
  }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
