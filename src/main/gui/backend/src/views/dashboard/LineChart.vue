<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons'); // echarts theme
  import {listLineChartData} from "@/api/dashboard";
  import {debounce} from '@/utils'

  export default {
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '350px'
      },
      dataType: {
        type: String,
        default: 'visitor'
      },
    },
    data() {
      return {
        chart: null,
        echartOptions: {
          xAxis: {
            data: [],
            boundaryGap: false,
            axisTick: {
              show: false
            }
          },
          grid: {
            left: 10,
            right: 10,
            bottom: 20,
            top: 30,
            containLabel: true
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross'
            },
            padding: [5, 10]
          },
          yAxis: {
            axisTick: {
              show: false
            }
          },
          legend: {
            data: ['上周同期', '本周']
          },
          series: [{
            name: '上周', itemStyle: {
              normal: {
                color: '#FF005A',
                lineStyle: {
                  color: '#FF005A',
                  width: 2
                }
              }
            },
            smooth: true,
            type: 'line',
            data: [],
            animationDuration: 2800,
            animationEasing: 'cubicInOut'
          },
            {
              name: '本周',
              smooth: true,
              type: 'line',
              itemStyle: {
                normal: {
                  color: '#3888fa',
                  lineStyle: {
                    color: '#3888fa',
                    width: 2
                  },
                  areaStyle: {
                    color: '#f3f8ff'
                  }
                }
              },
              data: [],
              animationDuration: 2800,
              animationEasing: 'quadraticOut'
            }]
        }
      }
    },
    //数据自动刷新，必然需要一个监听机制告诉Echarts重新设置数据
    watch: {
      dataType: {
        handler(newVal, oldVal) {
          if (this.chart) {
            this.chart.showLoading({
              text: '数据加载中...'
            });
            this.initData();
            this.chart.hideLoading()
          } else {
            this.initChart();
          }
        }
      },
      //观察option的变化
      echartOptions: {
        handler(newVal, oldVal) {
          if (this.chart) {
            if (newVal) {
              this.chart.setOption(newVal);
            } else {
              this.chart.setOption(oldVal);
            }
            this.chart.hideLoading()
          } else {
            this.initChart();
          }
        },
        deep: true //对象内部属性的监听，关键。
      }
    },
    mounted() {
      this.initData();
      this.initChart()

      this.__resizeHandler = debounce(() => {
        if (this.chart) {
          this.chart.resize()
        }
      }, 100)
      window.addEventListener('resize', this.__resizeHandler)
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      window.removeEventListener('resize', this.__resizeHandler)
      this.chart.dispose()
      this.chart = null
    },
    methods: {
      initData() {
        if (this.dataType == null) {
          this.dataType = "visitor"
        }
        listLineChartData(this.dataType).then(response => {
          this.echartOptions.xAxis.data = response.data.axisData;
          this.echartOptions.series[0].data = response.data.expectedData;
          this.echartOptions.series[1].data = response.data.actualData;
        });
      },
      initChart() {
        this.chart = echarts.init(this.$el, 'macarons');
        this.chart.setOption(this.echartOptions);
        this.chart.showLoading({
          text: '数据加载中...'
        });
      },
    }
  }
</script>
