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
/*总数据图表分析*/
    totalDataAnalysis();
});


function totalDataAnalysis() {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('total_data_analysis'));
    myChart.showLoading();
    var option = {
        title: {
            text: '近一年内数据统计'
        },
        tooltip: {
            trigger: 'axis'
        },dataZoom: [{
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
        legend: {
            data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
        },
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
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: '邮件营销',
                type: 'line',
                stack: '总量',
                data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
                name: '联盟广告',
                type: 'line',
                stack: '总量',
                data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
                name: '视频广告',
                type: 'line',
                stack: '总量',
                data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
                name: '直接访问',
                type: 'line',
                stack: '总量',
                data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
                name: '搜索引擎',
                type: 'line',
                stack: '总量',
                data: [820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.hideLoading();
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}


/*今日新增数据查询*/
function getNowQuantityVo() {
    $.ajax({
        url: "/admin/get/nowDayQuantity",
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
        url: "/admin/get/totalQuantity",
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
        url: "/admin/get/weather",
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
                    +"</tr>";
            }
            moreWeatherInfo += "</tbody></table>";
            console.log(moreWeatherInfo);

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
    let D = (date.getDate()  < 10 ? '0' + (date.getDate()) : date.getDate()) +" " ;
    let h = date.getHours() + ':';
    let m = date.getMinutes() + ':';
    let s = date.getSeconds();
    return Y + M + D;
}
