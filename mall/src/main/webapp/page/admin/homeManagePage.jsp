<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/19
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--将标签库描述符文件倒入到jsp页面，prefix标签前缀名，可以随意起--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script>
        var myChart;
        $(function () {
            $.getJSON('/tmall/res/js/basicTheme.json', function (themeJSON) {
                echarts.registerTheme('basicTheme', themeJSON);
                // 基于准备好的dom，初始化eCharts实例
                myChart = echarts.init($("#chartDiv")[0], "basicTheme");
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '商城订单交易额'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        }
                    },
                    legend: {
                        data: ['总交易额', '交易完成', '等待买家确认', '等待卖家发货', '等待买家付款']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: []
                    },
                    yAxis: {
                        type: "value",
                        splitLine:{show: false}
                    },
                    series: [
                        {
                            name: '总交易额',
                            type: 'line',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    fontSize : 14,
                                    color : '#f89e9e'
                                }
                            },
                            data: [],
                            areaStyle: {},
                            itemStyle: {
                                normal: { //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                            offset: 0, color: '#f89e9e' // 0% 处的颜色
                                        }, {
                                            offset: 0.4, color: '#fccfcf' // 40% 处的颜色
                                        }, {
                                            offset: 1, color: '#fff' // 100% 处的颜色
                                        }]
                                    ), //背景渐变色
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'solid',
                                        color: "#f89e9e" //折线的颜色
                                    }
                                },
                                emphasis: {
                                    color: '#f89e9e',
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'dotted',
                                        color: "f89e9e"
                                    }
                                }
                            }
                        },
                        {
                            name: '交易完成',
                            type: 'line',
                            data: [],
                            areaStyle: {},
                            itemStyle: {
                                normal: { //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                            offset: 0, color: '#9ea7f5' // 0% 处的颜色
                                        }, {
                                            offset: 0.4, color: '#aecaf9' // 40% 处的颜色
                                        }, {
                                            offset: 1, color: '#fff' // 100% 处的颜色
                                        }]
                                    ), //背景渐变色
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'solid',
                                        color: "#9ea7f5" //折线的颜色
                                    }
                                },
                                emphasis: {
                                    color: '#9ea7f5',
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'dotted',
                                        color: "9ea7f5"
                                    }
                                }
                            }
                        },
                        {
                            name: '等待买家确认',
                            type: 'line',
                            data: [],
                            areaStyle: {},
                            itemStyle: {
                                normal: { //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                            offset: 0, color: '#ffdea4' // 0% 处的颜色
                                        }, {
                                            offset: 0.4, color: '#ffffc6' // 40% 处的颜色
                                        }, {
                                            offset: 1, color: '#fff' // 100% 处的颜色
                                        }]
                                    ), //背景渐变色
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'solid',
                                        color: "#ffdea4" //折线的颜色
                                    }
                                },
                                emphasis: {
                                    color: '#ffdea4',
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'dotted',
                                        color: "ffdea4"
                                    }
                                }
                            }
                        },
                        {
                            name: '等待卖家发货',
                            type: 'line',
                            data: [],
                            areaStyle: {},
                            itemStyle: {
                                normal: { //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                            offset: 0, color: '#b8ffb7' // 0% 处的颜色
                                        }, {
                                            offset: 0.4, color: '#daffd9' // 40% 处的颜色
                                        }, {
                                            offset: 1, color: '#fff' // 100% 处的颜色
                                        }]
                                    ), //背景渐变色
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'solid',
                                        color: "#b8ffb7" //折线的颜色
                                    }
                                },
                                emphasis: {
                                    color: '#b8ffb7',
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'dotted',
                                        color: "b8ffb7"
                                    }
                                }
                            }
                        },
                        {
                            name: '等待买家付款',
                            type: 'line',
                            data: [],
                            areaStyle: {},
                            itemStyle: {
                                normal: { //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                            offset: 0, color: '#dfbdd6' // 0% 处的颜色
                                        }, {
                                            offset: 0.4, color: '#efdee8' // 40% 处的颜色
                                        }, {
                                            offset: 1, color: '#fff' // 100% 处的颜色
                                        }]
                                    ), //背景渐变色
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'solid',
                                        color: "#dfbdd6" //折线的颜色
                                    }
                                },
                                emphasis: {
                                    color: '#dfbdd6',
                                    lineStyle: {        // 系列级个性化折线样式
                                        width: 2,
                                        type: 'dotted',
                                        color: "dfbdd6"
                                    }
                                }
                            }
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
                //异步加载数据
                getChartData(null, null, JSON.parse('${requestScope.jsonObject}'));
            });
            //设置日期控件约束
            var date = new Date();
            date.setDate(date.getDate());
            var formatDate = FormatDate(date);
            $("#input_endDate").attr("max", formatDate).attr("min", "2018-01-01").val(formatDate);
            date.setDate(date.getDate() - 6);
            formatDate = FormatDate(date);
            $("#input_beginDate").val(formatDate).attr("min", formatDate).attr("max", formatDate);
            /******
             * event
             ******/
            //点击查询按钮时
            $("#btn_chart_search").click(function () {
                getChartData($("#input_beginDate").val(), $("#input_endDate").val(), null);
            });
            //更改日期时
            $(".chartDateInput").change(function () {
                var date = new Date($("#input_endDate").val());
                date.setDate(date.getDate() - 6);
                var formatDate = FormatDate(date);
                $("#input_beginDate").val(formatDate).attr("min", formatDate).attr("max", formatDate);
            });
            //点击查询近一周数据按钮时
            $("span.chartDateBtn").click(function () {
                if ($(this).hasClass("select")) {
                    return;
                }
                //异步加载数据
                getChartData(null, null, JSON.parse('${requestScope.jsonObject}'));
                $(this).addClass("select");
            });
            //点击统计数据时
            $("#productTotal").click(function () {
                $(".menu_li[data-toggle=product]").click();
            });
            $("#userTotal").click(function () {
                $(".menu_li[data-toggle=user]").click();
            });
            $("#orderTotal").click(function () {
                $(".menu_li[data-toggle=order]").click();
            });
        });

        //获取图表数据
        function getChartData(beginDate, endDate, jsonObject) {
            if (jsonObject == null) {
                $.ajax({
                    url: "/tmall/admin/home/charts",
                    type: "get",
                    data: {"beginDate": beginDate, "endDate": endDate},
                    dataType: "json",
                    success: function (data) {
                        $(".loader").css("display", "none");
                        $("#btn_chart_search").attr("disabled", false);
                        //异步加载数据
                        myChart.setOption({
                            xAxis: {
                                data: data.dateStr
                            },
                            series: [{
                                name: "总交易额",
                                data: data.orderTotalArray
                            }, {
                                name: "交易完成",
                                data: data.orderSuccessArray
                            }, {
                                name: "等待买家确认",
                                data: data.orderUnconfirmedArray
                            }, {
                                name: "等待卖家发货",
                                data: data.orderNotShippedArray
                            }, {
                                name: "等待买家付款",
                                data: data.orderUnpaidArray
                            }]
                        });
                    }, beforeSend: function () {
                        $(".loader").css("display", "block");
                        $("#btn_chart_search").attr("disabled", true);
                        $("span.select").removeClass("select");
                    }
                });
            } else {
                //异步加载数据
                myChart.setOption({
                    xAxis: {
                        data: jsonObject.dateStr
                    },
                    series: [{
                        name: "总交易额",
                        data: jsonObject.orderTotalArray
                    }, {
                        name: "交易完成",
                        data: jsonObject.orderSuccessArray
                    }, {
                        name: "等待买家确认",
                        data: jsonObject.orderUnconfirmedArray
                    }, {
                        name: "等待卖家发货",
                        data: jsonObject.orderNotShippedArray
                    }, {
                        name: "等待买家付款",
                        data: jsonObject.orderUnpaidArray
                    }]
                });
            }
        }

        /**
         * @return {string}
         * */
        //格式化日期
        function FormatDate(strTime) {
            var date = new Date(strTime);
            var formatedMonth = ("0" + (date.getMonth() + 1)).slice(-2);
            var formatedDate = ("0" + (date.getDate())).slice(-2);
            return date.getFullYear() + "-" + formatedMonth + "-" + formatedDate;
        }
    </script>
    <style rel="stylesheet">
        #chartByDate {
            padding: 0;
            margin-bottom: 40px;
        }

        #chartByDate > li {
            display: inline-block;
            list-style: none;
        }

        #chartByDate > li > .frm_input {
            color: #999;
            height: 29px;
            cursor: pointer;
        }

        #chartTotal {
            width: 100%;
            margin-bottom: 30px;
            padding: 0 20px;
        }

        #chartTotal > li {
            width: 35%;
            display: inline-block;
            list-style: none;
            padding: 0 40px;
            cursor: pointer;
        }

        #chartTotal > li + li {
            border-left: 2px solid #eee;
        }

        #chartTotal > li:first-child {
            width: 31.5%;
            padding-left: 0;
        }

        #chartTotal > li:last-child {
            width: 31.5%;
            padding-right: 0;
        }

        #chartTotal .chartTotalTitle {
            height: 20px;
            text-indent: 3px;
        }

        .chartTotalTitle .chartTitleText {
            float: left;
            font-weight: bold;
            font-size: 14px;
            color: #666;
        }

        .chartTotalTitle .chartTitleUnit {
            float: right;
            font-weight: bold;
            font-size: 14px;
            color: #666;
        }

        #chartTotal .chartTotalValue {
            font-size: 28px;
            font-weight: bold;
            color: #666;
        }

        #chartTotal .chartTotalStyle {
            width: 100%;
            height: 6px;
            border-radius: 10px;
            margin-top: 10px;
        }

        .chartDateBtn {
            display: inline-block;
            padding: 5px 10px;
            color: #999;
            margin: 0 20px 0 0;
            border-radius: 3px;
            border: 1px solid #e9ebef;
            font-size: 12px;
            cursor: pointer;
        }

        span.chartDateBtn.select {
            background: #70BBF4;
            border: 1px solid #70BBF4;
            color: white;
        }

        .chartDateInput {
            color: #999;
            outline: none;
            border: 0;
        }

        input[type=date]::-webkit-inner-spin-button {
            display: none
        }

        input[type=date]::-webkit-clear-button {
            display: none
        }

        #btn_chart_search {
            position: relative;
            top: 1px;
        }

        .split {
            color: #999;
            padding-right: 10px;
        }

        #chartDiv {
            border: 1px solid #eee;
            padding: 20px;
        }

    </style>
</head>
<body>
<ul id="chartByDate">
    <li><span class="chartDateBtn text_info select">最近一周</span></li>
    <li class="chartDateBtn"><input class="chartDateInput" id="input_beginDate" type="date" title="开始日期"/><span
            class="split">—</span> <input class="chartDateInput details_unit" id="input_endDate" type="date"
                                          title="结束日期"/></li>
    <li><input class="frm_btn" id="btn_chart_search" type="button" value="查询"/></li>
</ul>
<ul id="chartTotal">
    <li id="productTotal">
        <p class="chartTotalTitle"><span class="chartTitleText">上架产品数量</span><span class="chartTitleUnit">(单位：件)</span>
        </p>
        <span class="chartTotalValue">${requestScope.productTotal}</span>
        <div class="chartTotalStyle" style="background-color: #f89e9e"></div>
    </li>
    <li id="userTotal">
        <p class="chartTotalTitle"><span class="chartTitleText">注册用户数量</span><span class="chartTitleUnit">(单位：人)</span>
        </p>
        <span class="chartTotalValue">${requestScope.userTotal}</span>
        <div class="chartTotalStyle" style="background-color: #9ea7f5"></div>
    </li>
    <li id="orderTotal">
        <p class="chartTotalTitle"><span class="chartTitleText">成交订单数量</span><span class="chartTitleUnit">(单位：件)</span>
        </p>
        <span class="chartTotalValue">${requestScope.orderTotal}</span>
        <div class="chartTotalStyle" style="background-color: #ffdea4"></div>
    </li>
</ul>
<div id="chartDiv" style="width: 100%;height: 500px"></div>
<div class="loader"></div>

</body>
</html>
