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
});






/*总投票数据查询*/
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
            console.log(responseData);
            let locationName = responseData.data.cityInfo.city;
            $("#location_name").html(locationName);

            /*添加天气内容*/
            let weatherContent = "";
            weatherContent += "<b style='text-align: left;color: red' title='空气质量等级："+
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
            }
            console.log(weatherContent)
            $("#weather_content").html(weatherContent)
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
            console.log(responseData);
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
    let D = date.getDate() + ' ';
    let h = date.getHours() + ':';
    let m = date.getMinutes() + ':';
    let s = date.getSeconds();
    return Y + M + D ;
}
