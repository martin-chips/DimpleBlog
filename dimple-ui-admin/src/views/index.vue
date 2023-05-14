<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData"/>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :xAxisData="xAxisData" :chart-data="lineChartData"/>
    </el-row>

    <el-row :gutter="32">
      <el-col :lg="8" :sm="24" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
          </div>
          <div class="chart-wrapper">
            <raddar-chart/>
          </div>
        </el-card>
      </el-col>
      <el-col :lg="8" :sm="24" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>爬虫访问统计</span>
          </div>
          <div class="chart-wrapper">
            <pie-chart chartName="爬虫访问统计" :chart-data="this.pieChartSpiderData"/>
          </div>
        </el-card>
      </el-col>
      <el-col :lg="8" :sm="24" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>爬虫访问统计</span>
          </div>
          <div class="chart-wrapper">
            <pie-chart chartName="网站跳转统计" :chartData="this.pieChartRefererData"/>
          </div>
        </el-card>
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
import {getDashboardCount, getDashboardSpider, getDashboardReferer} from "@/api/system/dashboard";


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
      lineData: {},
      lineChartData: {},
      xAxisData: [],
      pieChartSpiderData: [],
      pieChartRefererData: []
    };
  },
  created() {
    getDashboardCount().then(response => {
      this.lineData = response.data;
      this.handleSetLineChartData('visitor');
    });
    getDashboardSpider().then(response => {
      this.pieChartSpiderData = response.data;
    })

    getDashboardReferer().then(response => {
      this.pieChartRefererData = response.data;
    })
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = this.lineData[type];
      this.xAxisData = this.lineData['weeks'];
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
