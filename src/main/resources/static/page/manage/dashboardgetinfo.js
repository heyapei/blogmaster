/**
 * 面板数据的查询
 */


/*页面加载完成*/
$(function () {
    /*加载天气数据*/
    getWeather();
    /*加载每日一言*/
    getDailyWord();
    /*总投票数据查询*/
    getTotalQuantityVo();
    /*今日新增数据查询*/
    getNowQuantityVo();
    /*用户浏览量分析*/
    dayUserViewDataAnalysis();
    /*作品增量分析*/
    dayUserUserWorkDataAnalysis();
    /*用户增量和活动增量分析*/
    dayUserDataAnalysis();
    /*投票增量分析*/
    dayUserVoteWorkDataAnalysis();
});

/*投票增量分析*/
function dayUserVoteWorkDataAnalysis() {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('day_vote_work_data_analysis'));
    myChart.showLoading();
    $.ajax({
        url: "/admin/dashboard/get/workVoteDashboardDataAnalysis",
        type: "GET",
        cache: true,
        dataType: "JSON",
        success: function (responseData) {

            //console.log(responseData);
            let dateTime = [];
            let countNum = [];
            jQuery.each(responseData.data, function (i, item) {
                dateTime.push(item.dateTime);
                countNum.push(item.countNum)
            });
            //console.log(dateTime)
            //console.log(countNum)

            let option = {
                title: {
                    text: '投票增量',
                },
                tooltip: {
                    trigger: 'axis'
                }, dataZoom: [{
                    type: 'inside',
                    start: 50,
                    end: 100,
                }, {
                    start: 0,
                    end: 100,
                    handleSize: '80%',
                    handleStyle: {
                        color: '#fff',
                        shadowBlur: 3,
                        shadowColor: 'rgba(0, 0, 0, 0.7)',
                        shadowOffsetX: 2,
                        shadowOffsetY: 2
                    }
                }],
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: dateTime
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '当日量',
                        type: 'line',
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                color: '#0b87d9',
                                lineStyle: {
                                    color: '#0b87d9'
                                }
                            }
                        },
                        data: countNum
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart.hideLoading();
            window.addEventListener("resize", function () {
                myChart.resize();
            });
        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });
}


/*用户增量分析*/
function dayUserDataAnalysis() {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('day_user_data_analysis'));
    myChart.showLoading();
    $.ajax({
        url: "/admin/dashboard/get/userDashboardDataAnalysis",
        type: "GET",
        cache: true,
        dataType: "JSON",
        success: function (responseData) {

            //console.log(responseData);
            let dateTime = [];
            let countNum = [];
            jQuery.each(responseData.data, function (i, item) {
                dateTime.push(item.dateTime);
                countNum.push(item.countNum)
            });
            //console.log(dateTime)
            //console.log(countNum)

            let option = {
                title: {
                    text: '用户增量',
                },
                tooltip: {
                    trigger: 'axis'
                }, dataZoom: [{
                    type: 'inside',
                    start: 50,
                    end: 100,
                }, {
                    start: 0,
                    end: 100,
                    handleSize: '80%',
                    handleStyle: {
                        color: '#fff',
                        shadowBlur: 3,
                        shadowColor: 'rgba(0, 0, 0, 0.7)',
                        shadowOffsetX: 2,
                        shadowOffsetY: 2
                    }
                }],
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: dateTime
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '当日量',
                        type: 'line',
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                color: '#2b2b2b',
                                lineStyle: {
                                    color: '#2b2b2b'
                                }
                            }
                        },
                        data: countNum
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart.hideLoading();
            window.addEventListener("resize", function () {
                myChart.resize();
            });
        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });
}


/*作品增量分析*/
function dayUserUserWorkDataAnalysis() {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('day_user_work_data_analysis'));
    myChart.showLoading();
    $.ajax({
        url: "/admin/dashboard/get/userWorkDataAnalysis",
        type: "GET",
        cache: true,
        dataType: "JSON",
        success: function (responseData) {

            //console.log(responseData);
            let dateTime = [];
            let countNum = [];
            jQuery.each(responseData.data, function (i, item) {
                dateTime.push(item.dateTime);
                countNum.push(item.countNum)
            });
            //console.log(dateTime)
            //console.log(countNum)

            let option = {
                title: {
                    text: '作品增量',
                    //link:'http://www.baidu.com',//主标题超链接
                    //target:'blank',//主标题超链接打开方式
                    textStyle: { //设置主标题风格 妈的好像不行的样子
                        Color: '#00FF00',//设置主标题字体颜色
                        fontStyle: '',//主标题文字风格
                    },
                    //subtext:'副标题',
                    //sublink:'http://www.baidu.com',//副标题超链接
                    //subtarget:'blank',//副标题超链接打开方式
                    //padding:[5,10,5,5],//设置标题内边距,上，右，下，左
                    //itemGap:10,//主副标题之间的间距

                    /*left:'left',//组件的位置,center,left,right
                    top:'top',//组件离上边的距离middle,top,bottom*/ //此二者的优先级高于x吗?答案：是
                    //x:'center',
                    // backgroundColor:'white',//标题背景色
                    // borderColor:'gray',//标题边框颜色
                    //borderWidth:5,//标签线框
                    //borderRadius:5,//边框切圆角
                    // shadowBlur:10,//图形阴影模糊大小,该属性配合 shadowColor,shadowOffsetX(阴影水平方向上的偏移距离), shadowOffsetY(阴影垂直方向上的偏移距离。) 一起设置图形的阴影效果。
                    // shadowColor:'rgba(0,0,0,0.5)'//阴影颜色
                },
                tooltip: {
                    trigger: 'axis'
                }, dataZoom: [{
                    type: 'inside',
                    start: 50,
                    end: 100,
                }, {
                    start: 0,
                    end: 100,
                    handleSize: '80%',
                    handleStyle: {
                        color: '#fff',
                        shadowBlur: 3,
                        shadowColor: 'rgba(0, 0, 0, 0.7)',
                        shadowOffsetX: 2,
                        shadowOffsetY: 2
                    }
                }],
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: dateTime
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '当日量',
                        type: 'line',
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                color: '#00FF00',
                                lineStyle: {
                                    color: '#00FF00'
                                }
                            }
                        },
                        data: countNum
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart.hideLoading();
            window.addEventListener("resize", function () {
                myChart.resize();
            });
        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });

}

function dayUserViewDataAnalysis() {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('day_user_view_data_analysis'));
    myChart.showLoading();

    $.ajax({
        url: "/admin/dashboard/get/userViewDataAnalysis",
        type: "GET",
        cache: true,
        dataType: "JSON",
        success: function (responseData) {

            //console.log(responseData);
            let dateTime = [];
            let countNum = [];
            jQuery.each(responseData.data, function (i, item) {
                dateTime.push(item.dateTime);
                countNum.push(item.countNum)
            });
            //console.log(dateTime)
            //console.log(countNum)

            let option = {
                title: {
                    text: '用户浏览增量数据'
                },
                tooltip: {
                    trigger: 'axis'
                }, dataZoom: [{
                    type: 'inside',
                    start: 50,
                    end: 100,
                }, {
                    start: 0,
                    end: 100,
                    handleSize: '80%',
                    handleStyle: {
                        color: '#fff',
                        shadowBlur: 3,
                        shadowColor: 'rgba(0, 0, 0, 0.6)',
                        shadowOffsetX: 2,
                        shadowOffsetY: 2
                    }
                }],
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: dateTime
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '当日量',
                        type: 'line',
                        stack: '总量',
                        data: countNum
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart.hideLoading();
            window.addEventListener("resize", function () {
                myChart.resize();
            });
        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });

}


/*今日新增数据查询*/
function getNowQuantityVo() {
    $.ajax({
        url: "/admin/dashboard/get/nowDayQuantity",
        type: "GET",
        cache: true,
        dataType: "JSON",
        success: function (responseData) {
            $("#today_user_num").html(responseData.data.totalUserNum);
            $("#today_view_num").html(responseData.data.totalViewNum);
            $("#today_active_num").html(responseData.data.totalActiveNum);
            $("#today_user_work_num").html(responseData.data.totalUserWorkNum);
            $("#today_vote_num").html(responseData.data.totalVoteNum);
        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });
}


/*总数据查询*/
function getTotalQuantityVo() {
    $.ajax({
        url: "/admin/dashboard/get/totalQuantity",
        type: "GET",
        cache: true,
        dataType: "JSON",
        success: function (responseData) {
            $("#total_user_num").html(responseData.data.totalUserNum);
            $("#total_view_num").html(responseData.data.totalViewNum);
            $("#total_active_num").html(responseData.data.totalActiveNum);
            $("#total_user_work_num").html(responseData.data.totalUserWorkNum);
            $("#total_vote_num").html(responseData.data.totalVoteNum);
        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });
}


/*天气预报*/
function getWeather() {
    $.ajax({
        url: "/admin/dashboard/get/weather",
        type: "GET",
        cache: true,
        dataType: "JSON",
        success: function (responseData) {
            let locationName = responseData.data.cityInfo.city;
            $("#location_name").html(locationName);

            /*添加天气内容*/
            let weatherContent = "";
            weatherContent += "<b>今日天气：";
            weatherContent += responseData.data.data.forecast[0].type.replace(/\s+/g, "") + " ";
            weatherContent += responseData.data.data.forecast[0].low.replace(/\s+/g, "") + " ";
            weatherContent += responseData.data.data.forecast[0].high.replace(/\s+/g, "") + " ";
            weatherContent += "空气:" + responseData.data.data.quality;
            weatherContent += "</b>";
            weatherContent += " <a id=\"modal-19586\" href=\"#modal-container-weather\" role=\"button\" class=\"btn\" data-toggle=\"modal\">更多</a>";
            $("#weather_content").html(weatherContent)

            let moreWeatherInfo = "";
            moreWeatherInfo += "<table class=\"table table-hover table-condensed\"> " +
                "<thead> " +
                "<tr> " +
                "<th> 日期 </th> " +
                "<th> 天气 </th> " +
                "<th> 温度 </th> " +
                "<th> 空气指数 </th> " +
                "<th> 风向/风力 </th> " +
                "<th> 日出/日落 </th> " +
                "<th> 提示 </th> " +
                "</tr> " +
                "</thead> " +
                "<tbody>";

            for (let i in responseData.data.data.forecast) {

                moreWeatherInfo += "<tr>" +
                    "<td>" + timeStampToDate(responseData.data.data.forecast[i].ymd) + "" + responseData.data.data.forecast[i].week + "</td>" +
                    "<td>" + responseData.data.data.forecast[i].type + "</td>" +
                    "<td>" + responseData.data.data.forecast[i].low.replace(/\s+/g, "") + " " +
                    responseData.data.data.forecast[i].high.replace(/\s+/g, "") + "</td>" +
                    "<td>" + responseData.data.data.forecast[i].aqi + "</td>" +
                    "<td>" + responseData.data.data.forecast[i].fx + "/" + responseData.data.data.forecast[i].fl + "</td>" +
                    "<td>" + responseData.data.data.forecast[i].sunrise + "/" + responseData.data.data.forecast[i].sunset + "</td>" +
                    "<td>" + responseData.data.data.forecast[i].notice + "</td>"
                    + "</tr>";
            }
            moreWeatherInfo += "</tbody></table>";
            //console.log(moreWeatherInfo);

            $("#modal-weather-body").html(moreWeatherInfo);


            /*weatherContent += "<b style='text-align: left;color: red' title='空气质量等级："+
                responseData.data.data.quality+"，建议：" +
                responseData.data.data.ganmao+"'>" +
                "PM2.5:" + responseData.data.data.pm25+"</b>";
            weatherContent += "<lable>近日天气：</lable>";

            for (let i in responseData.data.data.forecast){

                weatherContent += "<b style='cursor:pointer;' title='" +
                    responseData.data.data.forecast[i].week +" "+
                    responseData.data.data.forecast[i].low.replace(/\s+/g,"")+ " " +
                    responseData.data.data.forecast[i].high.replace(/\s+/g,"") + " " +
                    responseData.data.data.forecast[i].type + " " +
                    responseData.data.data.forecast[i].notice + " " +
                    responseData.data.data.forecast[i].sunrise + " " +
                    responseData.data.data.forecast[i].sunset
                     + "'>" +
                    timeStampToDate(responseData.data.data.forecast[i].ymd)+"</b>";
                if (i >= 5) {
                    break;
                }
            }*/

            //$("#weather_content").innerHTML = weatherContent;
            //$("#weather_content").val(weatherContent);
            //$("#weather_content").text(weatherContent);

        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });
}

/*每日一言*/
function getDailyWord() {
    $.ajax({
        url: "https://api.vvhan.com/api/ian",
        type: "GET",
        cache: true,
        dataType: "TEXT",
        success: function (responseData) {
            $("#daily_word").html(responseData);
        },
        error: function (data, type, err) {  // 以下依次是返回过来的数据，错误类型，错误码
            console.log("ajax错误类型：" + type);
            console.log(err);
        }
    });
}


function timeStampToDate(timestamp) {
    // 简单的一句代码
    let date = new Date(timestamp); //获取一个时间对象
    /**
     1. 下面是获取时间日期的方法，需要什么样的格式自己拼接起来就好了
     2. 更多好用的方法可以在这查到 -> http://www.w3school.com.cn/jsref/jsref_obj_date.asp
     */
    /*date.getFullYear();  // 获取完整的年份(4位,1970)
    date.getMonth();  // 获取月份(0-11,0代表1月,用的时候记得加上1)
    date.getDate();  // 获取日(1-31)
    date.getTime();  // 获取时间(从1970.1.1开始的毫秒数)
    date.getHours();  // 获取小时数(0-23)
    date.getMinutes();  // 获取分钟数(0-59)
    date.getSeconds();  // 获取秒数(0-59)*/
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + " ";
    let h = date.getHours() + ':';
    let m = date.getMinutes() + ':';
    let s = date.getSeconds();
    return Y + M + D;
}
