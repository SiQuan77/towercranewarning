function initcharts(chaozhong,liju,shangxianwei,qingxie,shangsuo,alert_taji_cishu,alert_sjj_cishu,sum_taji,sum_sjj,sum_driver){

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('alert_classified'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '故障比例'
        },
        tooltip: {},
        legend: {
            data:['塔机','升降机']
        },
        xAxis: {
            data: ["超重","力矩预警","上限位预警","倾斜预警","上锁预警"]
        },
        yAxis: {
            type:'value',
            axisLabel:{
                formatter:'{value}(次)'
            }
        },
        series: [{
            name: '塔机',
            type: 'bar',
            data: [chaozhong, liju, 0, 0, 0]
        },
            {
                name: '升降机',
                type: 'bar',
                data: [0, 0, shangxianwei, qingxie, shangsuo]
            }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);



    var mychart2=echarts.init(document.getElementById('alert_type_fenlei'));

    var option2 = {
        title: {
            text: '故障主体比例'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '故障主体',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    {value: alert_taji_cishu, name: '塔机'},
                    {value: alert_sjj_cishu, name: '升降机'},
                ]
            }
        ]
    };

    mychart2.setOption(option2)


    var mychart4=echarts.init(document.getElementById('equipdriver_type_fenlei'));

    var option4 = {
        title: {
            text: '施工设备、人员一览'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '主体数量',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    {value: sum_taji, name: '塔机'},
                    {value: sum_sjj, name: '升降机'},
                    {value: sum_driver,name:'操作员'}
                ]
            }
        ]
    };
    mychart4.setOption(option4)

    initProgress(getDaysBetween('2020/11/02',getnowtime()))

}

function initProgress(data){
    var myChart3               = echarts.init(document.getElementById('progress_jindu'));
    var option3                = {
        grid                : {
            left            : '5%',
            top             : '0',
            right           : '0',
            bottom          : '0',
            containLabel    : true,
            width           : '100%'
        },
        xAxis               : {
            type            : 'value',
            splitLine       : {show: false},
            axisLabel       : {show: false},
            axisTick        : {show: false},
            axisLine        : {show: false},
        },
        yAxis               : {
            type            : 'category',
            axisTick        : {show: false},
            axisLine        : {show: false},
            axisLabel       : {
                color       : 'black',
                fontSize    : 17
            },
        },
        series              : [
            {
                name        : '/'+365,
                type        : 'bar',
                barWidth    : 18,
                data        : [data],
                label       : {
                    show        : true,
                    //position    : 'middle',
                    offset      : [20,2],
                    formatter   : '{c}{a}',
                    color       : '#fff',
                    fontSize    : 15
                },
                itemStyle       : {
                    color: new echarts.graphic.LinearGradient(
                        1, 0, 0, 0,
                        [
                            {offset: 0, color: '#A4DF4E'},                   //柱图渐变色
                            // {offset: 0.5, color: '#44C0C1'},                 //柱图渐变色
                            {offset: 1, color: '#5DC69D'},                   //柱图渐变色
                        ]
                    ),
                    barBorderRadius: 9,
                },
                zlevel                    : 1
            },
            {
                name                      : '进度条背景',
                type                      : 'bar',
                barGap                    : '-100%',
                barWidth                  : 18,
                data                      : [365],
                color                     : '#DCDCDC',//柱条颜色
                itemStyle                 : {
                    normal                : {
                        barBorderRadius: 9,
                        borderColor: '#FEFEFE',
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart3.setOption(option3);
}


function getnowtime() {
    var nowtime = new Date();
    var year = nowtime.getFullYear();
    var month = padleft0(nowtime.getMonth() + 1);
    var day = padleft0(nowtime.getDate());
    var millisecond = nowtime.getMilliseconds(); millisecond = millisecond.toString().length == 1 ? "00" + millisecond : millisecond.toString().length == 2 ? "0" + millisecond : millisecond;
    return year + "/" + month + "/" + day;
}
//补齐两位数
function padleft0(obj) {
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);
}

function  getDaysBetween(dateString1,dateString2){
    var  startDate = Date.parse(dateString1);
    var  endDate = Date.parse(dateString2);
    var days=(endDate - startDate)/(1*24*60*60*1000);
    // alert(days);
    return  days;
}
