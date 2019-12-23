<template>
  <el-tabs style="height: 300px">
    <el-tab-pane label="访问日志" name="first">
      <p v-for="">
        <el-icon class="el-icon-link"/>
        登录IP：{{ item.ip }}
      </p>
    </el-tab-pane>
    <el-tab-pane label="登录日志" name="second">登录日志</el-tab-pane>
    <el-tab-pane label="操作日志" name="third">操作日志</el-tab-pane>
    <el-tab-pane label="任务日志" name="fourth">任务日志</el-tab-pane>
  </el-tabs>
</template>

<script>
  import {listVisitLog} from "@/api/dashboard";

  export default {
    name: "Log",
    created() {

    },
    methods: {
      getVisitLog() {
        listVisitLog().then(e => {

        });
      }
    }
  }
</script>

<style scoped>

</style>
