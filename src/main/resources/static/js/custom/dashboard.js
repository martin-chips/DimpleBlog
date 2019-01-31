$(function () {
    //初始化爬虫统计饼图
    initEchartsPie();
    //初始化系统信息仪表盘
    initEchartsGauge();
    //初始化访客统计
    initEchartsLine();
    //填充数据到饼图
    fixDataPie();
    //填充数据到仪表盘
    fixDataGauge();
    // setInterval(fixDataGauge, 15000);
    //填充数据到折线图
    fixDataLine();

    window.onresize = function () {
        echarts_pie_spider_type.resize();
        echarts_gauge_percent.resize();
        echarts_line_visitor.resize();
    };
});

let echarts_pie_spider_type;
let echarts_gauge_percent;
let echarts_line_visitor;

function fixDataLine() {
    $.ajax({
        url: "/api/visitorCount",
        type: "get",
        success: function (result) {
            echarts_line_visitor.hideLoading();
            if (result.code == web_status.SUCCESS) {
                console.log(result.data);
                var xAxiss = new Array();
                $.each(result.data, function (index, obj) {
                    xAxiss[index] = obj.name;
                });
                echarts_line_visitor.setOption({
                    xAxis: {
                        data: xAxiss
                    },
                    series: [
                        {
                            data: result.data
                        }
                    ]
                });
            }
        }
    });
}

function initEchartsLine() {
    echarts_line_visitor = echarts.init(document.getElementById('echarts_line_visitor'));
    let option = {
        grid: {
            x: 45,
            y: 45,
            x2: 45,
            y2: 45,
        },
        title: {
            text: '近七日访问量',
            // left: 'center',
            subtext: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['近七日访问量']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {
                    readOnly: false
                },
                magicType: {
                    type: ['line', 'bar']
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
        },
        yAxis: {
            type: 'value',
        },
        series: [{
            name: '近七日访问量',
            type: 'line',
            data: [],
            markPoint: {
                data: [{
                    type: 'max',
                    name: '最大值'
                },
                    {
                        type: 'min',
                        name: '最小值'
                    }
                ]
            },
            markLine: {
                data: [{
                    type: 'average',
                    name: '平均值'
                }]
            }
        }]
    };
    echarts_line_visitor.showLoading();
    echarts_line_visitor.setOption(option);
}

function fixDataPie() {
    $.ajax({
        url: "/api/spiderPieData",
        type: "get",
        success: function (result) {
            echarts_pie_spider_type.hideLoading();
            if (result.code == web_status.SUCCESS) {
                echarts_pie_spider_type.setOption({
                    legend: {
                        data: result.data
                    },
                    series: [
                        {
                            data: result.data
                        }
                    ]
                });
            }
        }
    })
}

function fixDataGauge() {
    $.ajax({
        url: "/api/memJvmCpuData",
        type: "get",
        success: function (result) {
            echarts_gauge_percent.hideLoading();
            if (result.code == web_status.SUCCESS) {
                var option = echarts_gauge_percent.getOption();
                //mem
                option.series[0].data[0].value = result.data[0];
                //cpu
                option.series[1].data[0].value = result.data[1];
                //jvm
                option.series[2].data[0].value = result.data[2];
            }
            echarts_gauge_percent.setOption(option, true);
        }
    })

}


function initEchartsPie() {
    echarts_pie_spider_type = echarts.init(document.getElementById('echarts_pie_spider_type'));
    let option = {
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        title: {
            text: '爬虫访问统计',
            subtext: '仅列举前五',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            //左上角数据
            data: []
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '70%',
                center: ['50%', '60%'],
                data: [],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]

    };
    echarts_pie_spider_type.showLoading();
    echarts_pie_spider_type.setOption(option);


}


function initEchartsGauge() {
    echarts_gauge_percent = echarts.init(document.getElementById('echarts_gauge_percent'));

    option = {

        backgroundColor: 'rgba(255,255,255,0)',
        tooltip: {
            formatter: "{a} <br/>{c} {b}"
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        grid: {

            top: "1px",

            left: "1px",

            right: "1px",

            bottom: "1px",

            backgroundColor: '#fff',

            width: "auto", //图例宽度

            height: "100%", //图例高度

        },
        series: [
            {
                name: '内存使用率',
                type: 'gauge',
                z: 3,
                min: 0,
                max: 100,
                splitNumber: 10,
                radius: '100%',
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 15,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                title: {
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        fontSize: 20,
                        fontStyle: 'italic'
                    }
                },
                detail: {
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder'
                    }
                },
                data: [{value: 40, name: '内存使用率（%）'}]
            },
            {
                name: 'CPU使用率',
                type: 'gauge',
                center: ['22%', '55%'],    // 默认全局居中
                radius: '85%',
                min: 0,
                max: 100,
                endAngle: 45,
                splitNumber: 10,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 8
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width: 5
                },
                title: {
                    offsetCenter: [0, '-30%'],       // x, y，单位px
                },
                detail: {
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder'
                    }
                },
                data: [{value: 50, name: 'CPU使用率（%）'}]
            },
            {
                name: 'JVM使用率',
                type: 'gauge',
                center: ['79%', '55%'],    // 默认全局居中
                radius: '85%',
                min: 0,
                max: 100,
                startAngle: 140,
                endAngle: -45,
                splitNumber: 10,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 8
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width: 5
                },
                title: {
                    offsetCenter: [0, '-30%'],       // x, y，单位px
                },
                detail: {
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder'
                    }
                },
                data: [{value: 50, name: 'JVM使用率（%）'}]
            }
        ]
    };
    echarts_gauge_percent.showLoading();
    echarts_gauge_percent.setOption(option);

}

