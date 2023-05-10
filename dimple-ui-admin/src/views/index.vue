<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData"/>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData"/>
    </el-row>

    <el-row :gutter="32">
      <el-col :lg="8" :sm="24" :xs="24">
        <div class="chart-wrapper">
          <raddar-chart/>
        </div>
      </el-col>
      <el-col :lg="8" :sm="24" :xs="24">
        <div class="chart-wrapper">
          <pie-chart/>
        </div>
      </el-col>
      <el-col :lg="8" :sm="24" :xs="24">
        <div class="chart-wrapper">
          <bar-chart/>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'

const lineChartData = {
  visitor: {
    lastWeekData: [100, 120, 161, 134, 105, 160, 165],
    currentWeekData: [120, 82, 91, 154, 162, 140, 145]
  },
  comment: {
    lastWeekData: [200, 192, 120, 144, 160, 130, 140],
    currentWeekData: [180, 160, 151, 106, 145, 150, 130]
  },
  article: {
    lastWeekData: [80, 100, 121, 104, 105, 90, 100],
    currentWeekData: [120, 90, 100, 138, 142, 130, 130]
  },
  login: {
    lastWeekData: [130, 140, 141, 142, 145, 150, 160],
    currentWeekData: [120, 82, 91, 154, 162, 140, 130]
  }
};

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis
    }
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
