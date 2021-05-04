<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>足疗店项目销售额统计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="Deleteport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${Path}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${Path}/static/css/public.css" media="all" />
    <script type="text/javascript" src="${Path}/static/echarts/js/echarts.min.js"></script>
</head>
<body style="height: 100%;margin:10px">


<blockquote class="layui-elem-quote">
    足疗店总销售额：<span id="totalAmount"></span>元
</blockquote>


<div id="container" style="height: 80%"></div>

<script src="${Path}/static/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">

    layui.use(['jquery','laydate'],function(){
        var  $      = layui.jquery;
        var laydate = layui.laydate;

        //默认加载
        getColumnCharData();
        getSaleTotalAmount();

        function getColumnCharData(){
            //get请求获取扇形图数据
            $.get("${Path}/stats/loadSaleChartData",function (orderData) {
                var dom = document.getElementById("container");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
                    color: ['#3398DB'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: orderData.name,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '销售额（人民币）',
                            type: 'bar',
                            barWidth: '40%',
                            data: orderData.value
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            });
        }

        function getSaleTotalAmount() {
            $.get("${Path}/stats/loadSaleTotalAmount",function (totalAmount) {
               console.log(totalAmount);
               $("#totalAmount").html(totalAmount);
            });
        }
    });

</script>
</body>
</html>

