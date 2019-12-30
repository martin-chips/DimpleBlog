<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData"/>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-col :xs="24" :sm="24" :lg="24">
        <line-chart v-if="lineChartData.expectedData!=undefined" :chart-data="lineChartData"/>
      </el-col>
    </el-row>
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <Log/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="16">
        <div class="chart-wrapper">
          <pie-chart :spider-data="spiderData" v-if="spiderData.length!=0"/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import PanelGroup from './dashboard/PanelGroup'
  import LineChart from './dashboard/LineChart'
  import PieChart from './dashboard/PieChart'
  import {listLineChartData, listSpiderData} from "@/api/dashboard";
  import Log from "@/views/dashboard/Log"


  var lineChartDataAll = {
    note: {
      expectedData: [],
      actualData: [],
      axisData: []
    },
    book: {
      expectedData: [],
      actualData: [],
      axisData: []
    },
    visitor: {
      expectedData: [],
      actualData: [],
      axisData: []
    },
    blog: {
      expectedData: [],
      actualData: [],
      axisData: []
    }
  };

  export default {
    name: 'Index',
    components: {
      PanelGroup,
      Log,
      LineChart,
      PieChart,
    },
    data() {
      return {
        lineChartData: {},
        spiderData: []
      }
    },
    created() {
      this.handleSetLineChartData("visitor");
      this.getSpiderData();
    },
    methods: {
      getSpiderData() {
        listSpiderData().then(response => {
          this.spiderData = response.data;
        });
      },
      handleSetLineChartData(type) {
        if (lineChartDataAll[type].axisData.length == 0) {
          listLineChartData(type).then(response => {
            this.lineChartData = response.data;
            lineChartDataAll[type] = response.data;
          })
        } else {
          this.lineChartData = lineChartDataAll[type];
        }
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
