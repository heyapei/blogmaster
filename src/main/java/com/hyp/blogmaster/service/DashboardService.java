package com.hyp.blogmaster.service;

import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDTO;
import com.hyp.blogmaster.pojo.vo.page.dashboard.TotalQuantityVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/25 0:03
 * @Description: TODO
 */
public interface DashboardService {


    /**
     * 查询近一年的用户投票增量
     * @return
     */
    List<DashboardDataAnalysisDTO> getWorkVoteDashboardDataAnalysis();

    /**
     * 查询近一年的用户按天统计的数据

     * @return
     */
    List<DashboardDataAnalysisDTO> getUserDashboardDataAnalysis();



    /**
     * 查询近一年的作品按天统计的数据
     *
     * @return
     */
    List<DashboardDataAnalysisDTO> getUserWorkDashboardDataAnalysis();


    /**
     * 按照操作类型 查询近一年的数据按天统计的数据
     *
     * @param optionType
     * @return
     */
    List<DashboardDataAnalysisDTO> getDashboardDataAnalysisByOptionType(Integer optionType);


    /**
     * 根据请求ip获取天气信息
     * 逻辑应该是：
     * 1. 获取ip地址
     * 2. 根据ip地址通过高德的api获取IP的物理地址
     * 3. 通过物理地址找到对应的areaCode
     * 4. 判断redis中数据是否存在该地区的天气
     * 5. 如果有就是用redis中的数据 如果没有就使用天气查询接口查询数据并保存到redis中 缓存过期时间8小时
     *
     * @param httpServletRequest
     * @return
     */
    WeatherDTO getWeatherByIp(HttpServletRequest httpServletRequest);


    /**
     * 获取整个的投票程序的数据
     *
     * @return
     */
    TotalQuantityVO getTotalQuantityVO();


    /**
     * 根据日期范围查询统计数据
     *
     * @param startTime
     * @param endTime
     * @return
     */
    TotalQuantityVO getTotalQuantityVOByTime(Date startTime, Date endTime);


}
