<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="tabChange" style="height: 300px">
      <el-tab-pane style="height: 230px;overflow: auto" label="访问日志" name="visitLogTab" :loading="true">
        <ul class="list" v-infinite-scroll="getVisitLog">
          <li v-for="item in visitLog.data" class="list-item" v-html="item">{{ item }}</li>
        </ul>
        <p v-if="visitLog.loading">加载中...</p>
        <p v-if="visitLog.noMore">没有更多了</p>
      </el-tab-pane>
      <el-tab-pane style="height: 230px;overflow: auto" label="登录日志" name="loginLogTab">
        <ul class="list" v-infinite-scroll="getLoginLog">
          <li v-for="item in loginLog.data" class="list-item" v-html="item">{{ item }}</li>
        </ul>
        <p v-if="loginLog.loading">加载中...</p>
        <p v-if="loginLog.noMore">没有更多了</p>
      </el-tab-pane>
      <el-tab-pane style="height: 230px;overflow: auto" label="操作日志" name="operateLogTab">
        <ul class="list" v-infinite-scroll="getOperateLog">
          <li v-for="item in operateLog.data" class="list-item" v-html="item">{{ item }}</li>
        </ul>
        <p v-if="operateLog.loading">加载中...</p>
        <p v-if="operateLog.noMore">没有更多了</p>
      </el-tab-pane>
      <el-tab-pane style="height: 230px;overflow: auto" label="任务日志" name="taskLogTab">
        <ul class="list" v-infinite-scroll="getTaskLog">
          <li v-for="item in taskLog.data" class="list-item" v-html="item">{{ item }}</li>
        </ul>
        <p v-if="taskLog.loading">加载中...</p>
        <p v-if="taskLog.noMore">没有更多了</p>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
  import {listVisitLog, listOperateLog, listLoginLog, listTaskLog} from "@/api/dashboard";

  export default {
    name: "Log",
    created() {
      this.getVisitLog();
    },
    data() {
      return {
        activeName: 'visitLogTab',
        visitLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 10,
            orderByColumn: "createTime",
            isAsc: "desc"
          },
        },
        loginLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 10,
            orderByColumn: "createTime",
            isAsc: "desc"
          },
        },
        operateLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 10,
            orderByColumn: "createTime",
            isAsc: "desc"
          },
        },
        taskLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 10,
            orderByColumn: "createTime",
            isAsc: "desc"
          },
        },
      }
    },
    methods: {
      tabChange(target) {
        if (target.paneName == 'visitLogTab') {
          if (!this.visitLog.handle) {
            this.getVisitLog();
          }
        } else if (target.paneName == 'loginLogTab') {
          if (!this.loginLog.handle) {
            this.getLoginLog();
          }
        } else if (target.paneName == 'operateLogTab') {
          if (!this.operateLog.handle) {
            this.getOperateLog();
          }
        } else if (target.paneName == 'taskLogTab') {
          if (!this.taskLog.handle) {
            this.getTaskLog();
          }
        }
      },
      getVisitLog() {
        if (this.visitLog.noMore) {
          return
        }
        this.visitLog.loading = true;
        setTimeout(() => {
          this.visitLog.queryParams.pageNum++;
          listVisitLog(this.visitLog.queryParams).then(response => {
            this.visitLog.handle = true;
            this.visitLog.data.push(...response.rows);
            if (response.total == this.visitLog.data.length) {
              this.visitLog.noMore = true;
            }
            this.visitLog.loading = false;
          });
        }, 1000)
      },
      getOperateLog() {
        if (this.operateLog.noMore) {
          return
        }
        this.operateLog.loading = true;
        setTimeout(() => {
          this.operateLog.queryParams.pageNum++;
          listOperateLog(this.operateLog.queryParams).then(response => {
            this.operateLog.handle = true;
            this.operateLog.data.push(...response.rows);
            if (response.total == this.operateLog.data.length) {
              this.operateLog.noMore = true;
            }
            this.operateLog.loading = false;
          });
        }, 1000)
      },
      getLoginLog() {
        if (this.loginLog.noMore) {
          return
        }
        this.loginLog.loading = true;
        setTimeout(() => {
          this.loginLog.queryParams.pageNum++;
          listLoginLog(this.loginLog.queryParams).then(response => {
            this.loginLog.handle = true;
            this.loginLog.data.push(...response.rows);
            if (response.total == this.loginLog.data.length) {
              this.loginLog.noMore = true;
            }
            this.loginLog.loading = false;
          });
        }, 1000)
      },
      getTaskLog() {
        if (this.taskLog.noMore) {
          return
        }
        this.taskLog.loading = true;
        setTimeout(() => {
          this.taskLog.queryParams.pageNum++;
          listTaskLog(this.taskLog.queryParams).then(response => {
            this.taskLog.handle = true;
            this.taskLog.data.push(...response.rows);
            if (response.total == this.taskLog.data.length) {
              this.taskLog.noMore = true;
            }
            this.taskLog.loading = false;
          });
        }, 1000)
      }
    }
  }
</script>

<style scoped>
  .list {
    padding: 0;
    margin: 0;
    list-style: none;
  }

  .list-item {
    margin: 10px;
  }
</style>
