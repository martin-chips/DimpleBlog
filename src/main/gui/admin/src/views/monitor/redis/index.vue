<template>
  <div class="app-container">

    <el-tabs v-model="activeName" style="padding-left: 8px;" @tab-click="tabClick">
      <el-tab-pane label="Redis 管理" name="first">
        todo
      </el-tab-pane>
      <el-tab-pane label="Redis 监控" name="second">
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
        activeName: 'first',
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
      },
      tabClick(name) {
        if (this.activeName === 'first') {
          this.$refs.local.init()
        } else {
          this.$refs.qiNiu.init()
        }
      }
    }
  }
</script>

<style scoped>

</style>
