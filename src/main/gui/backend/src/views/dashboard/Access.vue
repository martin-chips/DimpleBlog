<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons') // echarts theme
  import {debounce} from '@/utils'
  import {listAccessData} from "@/api/dashboard";

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
        default: '500px'
      }
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
              magicType: {type: ['line', 'bar']},
              restore: {},
              saveAsImage: {}
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            x: 'left',
            data: []
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              selectedMode: 'single',
              radius: [0, '30%'],
              label: {
                normal: {
                  position: 'inner'
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: []
            },
            {
              name: '访问来源',
              type: 'pie',
              radius: ['40%', '55%'],
              label: {
                normal: {
                  formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                  backgroundColor: '#eee',
                  borderColor: '#aaa',
                  borderWidth: 1,
                  borderRadius: 4,
                  shadowBlur: 3,
                  shadowOffsetX: 2,
                  shadowOffsetY: 2,
                  shadowColor: '#999',
                  padding: [0, 7],
                  rich: {
                    a: {
                      color: '#999',
                      lineHeight: 22,
                      align: 'center'
                    },
                    abg: {
                      backgroundColor: '#333',
                      width: '100%',
                      align: 'right',
                      height: 22,
                      borderRadius: [4, 4, 0, 0]
                    },
                    hr: {
                      borderColor: '#aaa',
                      width: '100%',
                      borderWidth: 0.5,
                      height: 0
                    },
                    b: {
                      fontSize: 16,
                      lineHeight: 33
                    },
                    per: {
                      color: '#eee',
                      backgroundColor: '#334455',
                      padding: [2, 4],
                      borderRadius: 2
                    }
                  }
                }
              },
              data: []
            }
          ]
        }
      }
    },
    mounted() {
      listAccessData().then(response => {
        this.echartOptions.legend.data = response.data.legend;
        this.echartOptions.series[0].data = response.data.inner;
        this.echartOptions.series[1].data = response.data.out;
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
    methods: {
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
