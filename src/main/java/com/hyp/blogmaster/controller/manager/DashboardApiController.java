package com.hyp.blogmaster.controller.manager;

import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDTO;
import com.hyp.blogmaster.pojo.vo.result.Result;
import com.hyp.blogmaster.service.DashboardService;
import com.hyp.blogmaster.utils.MyHttpClientUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/24 23:33
 * @Description: TODO
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@Api(value = "数据面板查询API，仅供查询数据，需要用户登录")
public class DashboardApiController {


    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private MyHttpClientUtil myHttpClientUtil;

    /**
     * 查询用户天气信息
     *
     * @return
     */
    @GetMapping(value = "get/weather")
    public Result getWeather() {
        WeatherDTO weatherByIp = dashboardService.getWeatherByIp(httpServletRequest);
        log.info("查询数据{}", weatherByIp.toString());
        return Result.buildResult(Result.Status.OK, weatherByIp);
    }

    /**
     * 获取每日一言
     *
     * @return
     */
    @GetMapping(value = "get/dailyWord")
    public Result getDailyWord() {
        String dailyWordReturn = myHttpClientUtil.getParameter("http://api.hanximeng.com/hitokoto/api.php", null, null, 2000, 2000, 2000);
        return Result.buildResult(Result.Status.OK, dailyWordReturn);
    }


}
