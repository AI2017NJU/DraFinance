/**
 * Created by Hermit on 2017/7/7.
 */
(function() {
    initListener();

    handleRevenueChart([]);
})();

function initListener() {
    $('#start').datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        minView: 'month'
    });
    $('#end').datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        minView: 'month'
    });
    $('#submit').on('click', function() {
        requestData();
    });
}

function requestData() {
    var data = $('#request-form').serialize();

    $.ajax({
        type: 'post',
        url: '/backtest/data',
        data: data,
        dataType: 'json',
        success: function(data) {
            handleRevenueChart(data.data);
        },
        error: function() {

        }
    });
}

var revenue_chart;
function handleRevenueChart(data) {
    if (revenue_chart === undefined) {
        revenue_chart = echarts.init(document.getElementById('revenue-chart'));
    }

    var xAxis = [];
    var yAxis = [];

    for (var i = 0; i < data.length; i++) {
        yAxis.push(data[i].balance);
        xAxis.push(data[i].date);
    }

    var start_balance = parseFloat($('#balance').val());

    var colors = ['#5793f3', '#d14a61', '#675bba'];

    var option = {
        color: colors,
        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            top: 70,
            bottom: 50
        },
        visualMap: {
            top: 10,
            right: 10,
            pieces: [{
                gt: 0,
                lte: start_balance,
                color: colors[0]
            }, {
                gt: start_balance,
                // lte: 100,
                color: colors[1]
            }]
        },
        dataZoom: [{
            start: 60,
            end: 100
        }],
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '总金额  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: xAxis
            }
        ],
        yAxis: [
            {
                title: '总金额',
                type: 'value'
            }
        ],
        series: [
            {
                name:'总金额',
                type:'line',
                xAxisIndex: 0,
                smooth: true,
                data: yAxis,
                markLine: {
                    silent: true,
                    data: [{
                        yAxis: start_balance
                    }]
                }
            }
        ]
    };

    revenue_chart.setOption(option);

}