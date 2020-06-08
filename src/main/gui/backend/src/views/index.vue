<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData"/>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-col :xs="24" :sm="24" :lg="24">
        <line-chart :data-type="dataType"/>
      </el-col>
    </el-row>
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="24">
        <div class="chart-wrapper">
          <access></access>
        </div>
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
          <pie-chart/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import PanelGroup from './dashboard/PanelGroup'
  import LineChart from './dashboard/LineChart'
  import PieChart from './dashboard/PieChart'
  import Access from './dashboard/Access'
  import Log from "@/views/dashboard/Log"

  export default {
    name: 'Index',
    components: {
      PanelGroup,
      Log,
      LineChart,
      PieChart,
      Access
    },
    data() {
      return {
        dataType: "visitor"
      }
    },
    methods: {
      handleSetLineChartData(type) {
        this.dataType = type;
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
