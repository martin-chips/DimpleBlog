<template>
  <div class="app-container">

    <el-tabs type="card">
      <el-tab-pane label="Redis 管理">
        todo
      </el-tab-pane>
      <el-tab-pane label="Redis 监控">
        <el-table max-height="800" :data="redisInfoList">
          <el-table-column prop="key" align="center" label="Key">
          </el-table-column>
          <el-table-column prop="description" align="center" label="描述">
          </el-table-column>
          <el-table-column prop="value" align="center" label="值">
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {listRedisInfoList} from "@/api/monitor/redis";

  export default {
    name: "index",
    data() {
      return {
        activeNames: ['1'],
        redisInfoList: []
      }
    },
    created() {
      this.getRedisInfoList();
    },
    methods: {
      getRedisInfoList() {
        listRedisInfoList().then(response => {
          this.redisInfoList = response.data;
        });
      }
    }
  }
</script>

<style scoped>

</style>
