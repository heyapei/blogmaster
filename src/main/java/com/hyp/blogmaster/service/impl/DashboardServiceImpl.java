package com.hyp.blogmaster.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyp.blogmaster.mapper.WeixinUserOptionLogMapper;
import com.hyp.blogmaster.pojo.dto.amap.AmapIpToAddressDTO;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.dto.weather.sojson.AreaCodeInfo;
import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDTO;
import com.hyp.blogmaster.pojo.modal.WeixinUserOptionConfig;
import com.hyp.blogmaster.pojo.vo.page.dashboard.TotalQuantityVO;
import com.hyp.blogmaster.service.*;
import com.hyp.blogmaster.utils.MyHttpClientUtil;
import com.hyp.blogmaster.utils.MyIpMacUtil;
import com.hyp.blogmaster.utils.amaputil.AmapApiUtil;
import com.hyp.blogmaster.utils.redis.MyRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/25 0:03
 * @Description: TODO
 */
@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {


    @Autowired
    private MyHttpClientUtil myHttpClientUtil;
    @Autowired
    private MyRedisUtil redisUtil;
    @Autowired
    private AmapApiUtil amapApiUtil;
    @Autowired
    private MyIpMacUtil myIpMacUtil;
    @Autowired
    private WeixinUserNoOpenIdIdLogService weixinUserNoOpenIdIdLogService;

    @Autowired
    private WeixinVoteUserService weixinVoteUserService;
    @Autowired
    private WeixinVoteBaseService weixinVoteBaseService;

    @Autowired
    private WeixinVoteWorkService weixinVoteWorkService;

    @Autowired
    private WeixinUserOptionLogMapper weixinUserOptionLogMapper;
    @Autowired
    private WeixinUserOptionLogService weixinUserOptionLogService;


    /**
     * 查询近一年的活动增量
     *
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getVoteDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> voteDashboardDataAnalysis = weixinVoteBaseService.getVoteDashboardDataAnalysis();
        return voteDashboardDataAnalysis;
    }

    /**
     * 查询近一年的用户投票增量
     *
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getWorkVoteDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> userDashboardDataAnalysis = weixinUserOptionLogService.getDashboardDataAnalysisByOptionType(3);
        return userDashboardDataAnalysis;
    }

    /**
     * 查询近一年的用户按天统计的数据
     *
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getUserDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> userDashboardDataAnalysis = weixinVoteUserService.getUserDashboardDataAnalysis();
        return userDashboardDataAnalysis;
    }

    /**
     * 查询近一年的作品按天统计的数据
     *
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getUserWorkDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> dashboardDataAnalysis = weixinVoteWorkService.getDashboardDataAnalysis();
        if (dashboardDataAnalysis == null) {
            return null;
        }
        return dashboardDataAnalysis;
    }

    /**
     * 按照操作类型 查询近一年的数据按天统计的数据
     *
     * @param optionType
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getDashboardDataAnalysisByOptionType(Integer optionType) {
        List<DashboardDataAnalysisDTO> dashboardDataAnalysisByOptionType = null;
        try {
            dashboardDataAnalysisByOptionType = weixinUserOptionLogMapper.getDashboardDataAnalysisByOptionType(optionType);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询近一年的数据按天统计的用户进入小程序数据错误，错误原因：{}", e.toString());
            return null;
        }
        return dashboardDataAnalysisByOptionType;
    }


    @Value("classpath:file/areacode/city.json")
    private Resource areaCodeCity;

    private final static String WEATHER_REDIS_KEY = "weixinmanager_weather_";

    @Override
    public WeatherDTO getWeatherByIp(HttpServletRequest httpServletRequest) {

        /*如果是本地数据就查询青浦的数据*/
        String realIP = myIpMacUtil.getRealIP(httpServletRequest);
        if (realIP.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            realIP = "222.69.133.116";
        }
        // 获取物理地址
        AmapIpToAddressDTO ipPosition = amapApiUtil.getIpPositionNoAsync(realIP);
        String city = null;
        if (ipPosition != null) {
            city = ipPosition.getCity();
        }
        // 获取areaCode
        String areaData = null;
        try {
            areaData = IOUtils.toString(areaCodeCity.getInputStream(), String.valueOf(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取city.json错误，错误原因：{}", e.toString());
        }
        List<AreaCodeInfo> areaCodes = null;
        try {
            areaCodes = JSONArray.parseArray(areaData, AreaCodeInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("city.json数据转换错误，错误原因：{}", e.toString());
        }
        Long areaCode = null;
        for (AreaCodeInfo areaCodeInfo : areaCodes) {
            if (areaCodeInfo.getCountyname().contains(city)) {
                areaCode = areaCodeInfo.getAreaid();
                break;
            } else if (city.contains(areaCodeInfo.getCountyname())) {
                areaCode = areaCodeInfo.getAreaid();
                break;
            }
        }
        // 检查redis中是否已经存储该天气信息了
        WeatherDTO weatherDTO = (WeatherDTO) redisUtil.get(WEATHER_REDIS_KEY + areaCode);
        log.info("weatherDTO：{}", weatherDTO);
        if (weatherDTO != null) {
            return weatherDTO;
        }
        // 查询天气情况
        String weatherReturn = myHttpClientUtil.getParameter(
                "http://t.weather.itboy.net/api/weather/city/" + areaCode,
                null, null, 2000, 2000, 2000);
        WeatherDTO jsonRootBean = null;
        try {
            jsonRootBean = JSONObject.parseObject(weatherReturn, WeatherDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("天气信息获取错误，错误原因：{}", e.toString());
        }
        redisUtil.set(WEATHER_REDIS_KEY + areaCode, jsonRootBean, 60 * 60 * 5);
        return jsonRootBean;
    }

    /**
     * 根据日期范围查询统计数据
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public TotalQuantityVO getTotalQuantityVOByTime(Date startTime, Date endTime) {


        WeixinUserOptionConfig weixinUserOptionConfig = new WeixinUserOptionConfig();
        /**
         * 进入小程序
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.INTO_WEiXIN_VOTE.getType());
        Integer intoWeixinVoteNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfigAndTime(weixinUserOptionConfig, startTime, endTime);
        /**
         * 浏览投票活动
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.VIEW_WEiXIN_VOTE_WORK.getType());
        Integer viewWeixinVoteWorkNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfigAndTime(weixinUserOptionConfig, startTime, endTime);
        /**
         * 浏览具体的用户作品
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.INTO_WEiXIN_VOTE_USER_WORK.getType());
        Integer intoWeixinVoteUserWorkNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfigAndTime(weixinUserOptionConfig, startTime, endTime);
        /**
         * 对具体用户作品投票
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.VOTE_WEiXIN_VOTE_WORK.getType());
        Integer voteWeixinVoteWorkNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfigAndTime(weixinUserOptionConfig, startTime, endTime);
        log.info("查询结果2：" + voteWeixinVoteWorkNum);

        TotalQuantityVO totalQuantityVO = new TotalQuantityVO();
        // 浏览总数
        totalQuantityVO.setTotalViewNum(intoWeixinVoteNum + viewWeixinVoteWorkNum + intoWeixinVoteUserWorkNum);
        // 投票总数
        totalQuantityVO.setTotalVoteNum(voteWeixinVoteWorkNum);
        // 用户总数
        totalQuantityVO.setTotalUserNum(weixinVoteUserService.getTotalUserByTime(startTime, endTime));
        // 活动总数
        totalQuantityVO.setTotalActiveNum(weixinVoteBaseService.getTotalActiveNumByTime(startTime, endTime));
        // 作品总数
        totalQuantityVO.setTotalUserWorkNum(weixinVoteWorkService.getTotalUserWorkNumByTime(startTime, endTime));

        return totalQuantityVO;
    }

    /**
     * 获取整个的投票程序的数据
     *
     * @return
     */
    @Override
    public TotalQuantityVO getTotalQuantityVO() {

        WeixinUserOptionConfig weixinUserOptionConfig = new WeixinUserOptionConfig();
        /**
         * 进入小程序
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.INTO_WEiXIN_VOTE.getType());
        Integer intoWeixinVoteNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfig(weixinUserOptionConfig);
        /**
         * 浏览投票活动
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.VIEW_WEiXIN_VOTE_WORK.getType());
        Integer viewWeixinVoteWorkNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfig(weixinUserOptionConfig);
        /**
         * 浏览具体的用户作品
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.INTO_WEiXIN_VOTE_USER_WORK.getType());
        Integer intoWeixinVoteUserWorkNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfig(weixinUserOptionConfig);

        /**
         * 对具体用户作品投票
         */
        weixinUserOptionConfig.setType(WeixinUserOptionConfig.typeEnum.VOTE_WEiXIN_VOTE_WORK.getType());
        Integer voteWeixinVoteWorkNum = weixinUserNoOpenIdIdLogService.getCountNumByOptionConfig(weixinUserOptionConfig);


        TotalQuantityVO totalQuantityVO = new TotalQuantityVO();
        // 浏览总数
        totalQuantityVO.setTotalViewNum(intoWeixinVoteNum + viewWeixinVoteWorkNum + intoWeixinVoteUserWorkNum);
        // 投票总数
        totalQuantityVO.setTotalVoteNum(voteWeixinVoteWorkNum);
        // 用户总数
        totalQuantityVO.setTotalUserNum(weixinVoteUserService.getTotalUser());
        // 活动总数
        totalQuantityVO.setTotalActiveNum(weixinVoteBaseService.getTotalActiveNum());
        // 作品总数
        totalQuantityVO.setTotalUserWorkNum(weixinVoteWorkService.getTotalUserWorkNum());

        return totalQuantityVO;
    }
}
