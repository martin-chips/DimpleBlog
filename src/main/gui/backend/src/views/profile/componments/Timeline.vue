<template>
  <div class="block">
    <el-timeline style="height: 600px; overflow: auto" :loading="loading" v-infinite-scroll="load">
      <el-timeline-item v-for="(item,index) of timeline" :key="index" :timestamp="parseTime(item.createTime)"
                        placement="top">
        <el-card>
          <p>
            <el-icon class="el-icon-link"/>
            登录IP：{{ item.ip }}
          </p>
          <p>
            <el-icon class="el-icon-location-outline"/>
            登录地点：{{ item.location }}
          </p>
          <p>
            <el-icon class="el-icon-bangzhu"/>
            浏览器类型：{{ item.browser }}
          </p>
          <p>
            <el-icon class="el-icon-monitor"/>
            OS：{{ item.os }}
          </p>
        </el-card>
      </el-timeline-item>
      <p v-if="loading">加载中...</p>
      <p v-if="noMore">没有更多了</p>
    </el-timeline>
  </div>
</template>

<script>
  import {listCurrentUserLoginLog} from "@/api/log/loginLog";

  export default {
    props: {
      username: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        timeline: [],
        loading: false,
        noMore: false,
        // 日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 0,
          pageSize: 10,
          orderByColumn: "createTime",
          isAsc: "desc"
        },
      }
    },
    mounted() {
      this.getTimeLine()
    },
    methods: {
      load() {
        setTimeout(() => {
          this.queryParams.pageNum++;
          this.getTimeLine();
        }, 1000)
      },
      getTimeLine() {
        if (this.noMore) {
          return
        }
        this.loading = true;
        listCurrentUserLoginLog(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          if (this.timeline.length == response.total) {
            this.noMore = true;
          }
          this.timeline.push(...response.rows);
          this.loading = false;
        });
      }
    }
  }
</script>
<style lang="scss" scoped>
  .el-card.is-always-shadow {
    box-shadow: none;
  }

  .el-card {
    border: 1px solid #f1f1f1;
    border-radius: 2px;
  }
</style>
