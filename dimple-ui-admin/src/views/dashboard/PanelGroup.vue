<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('visitor')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon class-name="card-panel-icon" icon-class="peoples"/>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            访客
          </div>
          <count-to :duration="2600" :end-val="countItems.visitor" :start-val="0" class="card-panel-num"/>
        </div>
      </div>
    </el-col>
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('comment')">
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon class-name="card-panel-icon" icon-class="message"/>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            留言
          </div>
          <count-to :duration="3000" :end-val="countItems.comment" :start-val="0" class="card-panel-num"/>
        </div>
      </div>
    </el-col>
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('article')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon class-name="card-panel-icon" icon-class="money"/>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            文章
          </div>
          <count-to :duration="3200" :end-val="countItems.article" :start-val="0" class="card-panel-num"/>
        </div>
      </div>
    </el-col>
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('login')">
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon class-name="card-panel-icon" icon-class="shopping"/>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            登录
          </div>
          <count-to :duration="3600" :end-val="countItems.login" :start-val="0" class="card-panel-num"/>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'
import {getDashboardCountItem} from "@/api/system/dashboard";

export default {
  components: {
    CountTo
  },
  created() {
    getDashboardCountItem().then(response => {
      this.countItems = response.data;
    })
  },
  data() {
    return {
      countItems: {},
    }
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width: 550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
