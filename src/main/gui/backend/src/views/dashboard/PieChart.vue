<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons'); // echarts theme
  import {listSpiderData} from "@/api/dashboard";
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
        default: '300px'
      },
    },
    data() {
      return {
        chart: null,
        echartOptions: {
          toolbox: {
            show: true,
            feature: {
              dataZoom: {
                yAxisIndex: 'none'
              },
              dataView: {readOnly: false},
              restore: {},
              saveAsImage: {}
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            left: 'center',
            bottom: '10',
            data: []
          },
          series: [
            {
              name: '爬虫访问',
              type: 'pie',
              roseType: 'radius',
              radius: [15, 95],
              center: ['50%', '38%'],
              data: this.spiderData,
              animationEasing: 'cubicInOut',
              animationDuration: 2600
            }
          ]
        }
      }
    },
    //数据自动刷新，必然需要一个监听机制告诉Echarts重新设置数据
    watch: {
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
      listSpiderData().then(response => {
        this.echartOptions.series[0].data = response.data;
        this.echartOptions.legend.data = response.data.map(e => e.name);
      });
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
      initChart() {
        this.chart = echarts.init(this.$el, 'macarons');
        this.chart.setOption(this.echartOptions);
        this.chart.showLoading({
          text: '数据加载中...'
        });
      }
    }
  }
</script>
