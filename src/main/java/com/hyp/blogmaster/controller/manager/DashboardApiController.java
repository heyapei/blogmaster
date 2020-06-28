package com.hyp.blogmaster.controller.manager;

import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDTO;
import com.hyp.blogmaster.pojo.vo.page.dashboard.TotalQuantityVO;
import com.hyp.blogmaster.pojo.vo.result.Result;
import com.hyp.blogmaster.service.DashboardService;
import com.hyp.blogmaster.utils.MyHttpClientUtil;
import com.hyp.blogmaster.utils.dateutil.DateStyle;
import com.hyp.blogmaster.utils.dateutil.MyDateUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/24 23:33
 * @Description: TODO
 */
@RestController
@RequestMapping("/admin/dashboard")
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
     * 该接口已无法使用
     *
     * @return
     */
    @Deprecated
    @GetMapping(value = "get/dailyWord")
    public Result getDailyWord() {
        String dailyWordReturn = myHttpClientUtil.getParameter("http://api.hanximeng.com/hitokoto/api.php", null, null, 2000, 2000, 2000);
        return Result.buildResult(Result.Status.OK, dailyWordReturn);
    }

    /**
     * 获取总数据统计
     *
     * @return
     */
    @GetMapping(value = "get/totalQuantity")
    public Result getTotalQuantity() {
        TotalQuantityVO totalQuantityVO = dashboardService.getTotalQuantityVO();
        return Result.buildResult(Result.Status.OK, totalQuantityVO);
    }


    /**
     * 获取当天数据统计
     *
     * @return
     */
    @GetMapping(value = "get/nowDayQuantity")
    public Result getNowDayQuantity() {
        /*获取当前时间*/
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        String timeString = MyDateUtil.DateToString(time, DateStyle.YYYY_MM_DD);
        String startTimeString = timeString + " 00:00:00";
        String endTimeString = timeString + " 23:59:59";
        Date startTime = MyDateUtil.StringToDate(startTimeString, DateStyle.YYYY_MM_DD_HH_MM_SS);
        Date endTime = MyDateUtil.StringToDate(endTimeString, DateStyle.YYYY_MM_DD_HH_MM_SS);

        TotalQuantityVO totalQuantityVO = dashboardService.getTotalQuantityVOByTime(startTime, endTime);
        return Result.buildResult(Result.Status.OK, totalQuantityVO);
    }

    /**
     * 按照操作类型 查询近一年的数据按天统计的用户进入小程序数据
     *
     * @return
     */
    @GetMapping(value = "get/userViewDataAnalysis")
    public Result getDashboardDataAnalysisUserView() {
        List<DashboardDataAnalysisDTO> dashboardDataAnalysisByOptionType = dashboardService.getDashboardDataAnalysisByOptionType(0);
        if (dashboardDataAnalysisByOptionType == null) {
            return Result.buildResult(Result.Status.RESULE_DATA_NONE);
        }
        return Result.buildResult(Result.Status.OK, dashboardDataAnalysisByOptionType);
    }


    /**
     * 查询近一年的作品按天统计的数据
     *
     * @return
     */
    @GetMapping(value = "get/userWorkDataAnalysis")
    public Result getUserWorkDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> userWorkDashboardDataAnalysis = dashboardService.getUserWorkDashboardDataAnalysis();
        if (userWorkDashboardDataAnalysis == null) {
            return Result.buildResult(Result.Status.RESULE_DATA_NONE);
        }
        return Result.buildResult(Result.Status.OK, userWorkDashboardDataAnalysis);
    }

    /**
     * 查询近一年的用户按天统计的数据
     *
     * @return
     */
    @GetMapping(value = "get/userDashboardDataAnalysis")
    public Result getUserDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> userDashboardDataAnalysis = dashboardService.getUserDashboardDataAnalysis();
        if (userDashboardDataAnalysis == null) {
            return Result.buildResult(Result.Status.RESULE_DATA_NONE);
        }
        return Result.buildResult(Result.Status.OK, userDashboardDataAnalysis);
    }


    /**
     * 查询近一年的用户投票增量
     *
     * @return
     */
    @GetMapping(value = "get/workVoteDashboardDataAnalysis")
    public Result getWorkVoteDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> userDashboardDataAnalysis = dashboardService.getWorkVoteDashboardDataAnalysis();
        if (userDashboardDataAnalysis == null) {
            return Result.buildResult(Result.Status.RESULE_DATA_NONE);
        }
        return Result.buildResult(Result.Status.OK, userDashboardDataAnalysis);
    }

}
