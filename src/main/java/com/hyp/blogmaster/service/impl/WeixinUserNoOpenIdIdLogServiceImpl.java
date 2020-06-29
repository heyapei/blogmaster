package com.hyp.blogmaster.service.impl;


import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.mapper.WeixinUserOptionLogMapper;
import com.hyp.blogmaster.mapper.WeixinVoteBaseMapper;
import com.hyp.blogmaster.pojo.dto.amap.AmapIpToAddressDTO;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinUserOptionConfig;
import com.hyp.blogmaster.pojo.modal.WeixinUserOptionLog;
import com.hyp.blogmaster.pojo.modal.WeixinVoteBase;
import com.hyp.blogmaster.service.WeixinUserNoOpenIdIdLogService;
import com.hyp.blogmaster.utils.MyIpMacUtil;
import com.hyp.blogmaster.utils.amaputil.AmapApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/7 14:31
 * @Description: TODO
 */
@Service
@Slf4j
public class WeixinUserNoOpenIdIdLogServiceImpl implements WeixinUserNoOpenIdIdLogService {


    @Autowired
    private AmapApiUtil amapApiUtil;
    @Autowired
    private MyIpMacUtil myIpMacUtil;

    @Autowired
    private WeixinUserOptionLogMapper weixinUserOptionLogMapper;

    @Autowired
    private WeixinVoteBaseMapper weixinVoteBaseMapper;

    /**
     * 添加微信用户操作日志
     *
     * @param weixinUserOptionLog
     * @param httpServletRequest
     * @return 主键ID
     */
    @Async("threadPoolTaskExecutor")
    @Override
    public Integer addUserOperationLog(WeixinUserOptionLog weixinUserOptionLog,
                                       HttpServletRequest httpServletRequest) {

        String userAgent = httpServletRequest.getHeader("User-Agent");
        String realIP = myIpMacUtil.getRealIP(httpServletRequest);
        if (StringUtils.isBlank(realIP)) {
            log.error("该服务类要求必须传递IP地址");
            return 0;
        }

        if (StringUtils.isNotBlank(weixinUserOptionLog.getOptionObject())) {
            WeixinVoteBase weixinVoteBase = weixinVoteBaseMapper.selectByPrimaryKey(Integer.parseInt(weixinUserOptionLog.getOptionObject()));
            //log.info("查询活动数据：" + weixinVoteBase.toString());
            if (weixinVoteBase == null) {
                log.error("未能查询到用户浏览的活动数据，浏览的活动ID为：{}", weixinUserOptionLog.getOptionObject());
                return 0;
            }
        }


        AmapIpToAddressDTO ipPositionNoAsync = amapApiUtil.getIpPositionNoAsync(realIP);
        log.info("高德地图通过IP查询的数据：" + ipPositionNoAsync.toString());
        if (ipPositionNoAsync != null) {
            String province = ipPositionNoAsync.getProvince();
            if (province != null) {
                weixinUserOptionLog.setProvince(province);
            }
            String city = ipPositionNoAsync.getCity();
            if (city != null) {
                weixinUserOptionLog.setCity(city);
            }
        }

        String mobileName = myIpMacUtil.getMobileName(userAgent);
        if (StringUtils.isNotBlank(mobileName)) {
            weixinUserOptionLog.setDeviceName(mobileName);
        }
        String mobileType = myIpMacUtil.getMobileType(userAgent);
        if (StringUtils.isNotBlank(mobileType)) {
            weixinUserOptionLog.setDeviceType(mobileType);
        }

        if (ipPositionNoAsync != null && ipPositionNoAsync.getRectangle() != null) {
            String geocodeByIpAddressGeneral = amapApiUtil.getGeocodeByIpAddressGeneralNoAsync(ipPositionNoAsync.getRectangle());
            log.info("高德地图通过经纬度查询的数据：" + geocodeByIpAddressGeneral);
            if (StringUtils.isNotBlank(geocodeByIpAddressGeneral)) {
                weixinUserOptionLog.setGeneralAddress(geocodeByIpAddressGeneral);
            }
        }

        weixinUserOptionLog.setIp(myIpMacUtil.ipToLong(realIP));

        try {
            weixinUserOptionLogMapper.insertUseGeneratedKeys(weixinUserOptionLog);
            log.info("用户操作记录结果：" + weixinUserOptionLog.toString());
            return (Integer) weixinUserOptionLog.getId();
        } catch (Exception e) {
            log.error("添加微信用户操作日志出现失败，原因{}", e.toString());
        }
        return 0;
    }

    /**
     * 根据日期范围查询统计数据
     *
     * @param weixinUserOptionConfig 日志实体类
     * @param startTime              开始时间
     * @param endTime                结束时间
     * @return
     */
    @Override
    public Integer getCountNumByOptionConfigAndTime(WeixinUserOptionConfig weixinUserOptionConfig, Date startTime, Date endTime) {
        Example example = new Example(WeixinUserOptionLog.class);
        Example.Criteria criteria = example.createCriteria();
        if (weixinUserOptionConfig != null) {
            criteria.andEqualTo("optionType", weixinUserOptionConfig.getType());
        } else {
            return null;
        }

        if (startTime != null && endTime != null) {
            criteria.andBetween("createTime", startTime, endTime);
        }

        try {
            return weixinUserOptionLogMapper.selectCountByExample(example);
        } catch (Exception e) {
            log.error("根据开始时间结束时间操作类型统计数据错误，错误原因:{}", e.toString());
            throw new MyDefinitionException("根据操作类型统计数据错误");
        }


    }

    /**
     * 通过操作类型获取数据 这里只获取总数 不做其他的操作
     *
     * @param weixinUserOptionConfig
     * @return
     */
    @Override
    public Integer getCountNumByOptionConfig(WeixinUserOptionConfig weixinUserOptionConfig) {

        Example example = new Example(WeixinUserOptionLog.class);
        Example.Criteria criteria = example.createCriteria();
        if (weixinUserOptionConfig != null) {
            criteria.andEqualTo("optionType", weixinUserOptionConfig.getType());
        } else {
            return null;
        }
        try {
            return weixinUserOptionLogMapper.selectCountByExample(example);
        } catch (Exception e) {
            log.error("根据操作类型统计数据错误，错误原因:{}", e.toString());
            throw new MyDefinitionException("根据操作类型统计数据错误");
        }
    }

    /**
     * 查询近一年的数据按天统计的数据 除了投票的数量
     *
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getDashboardDataAnalysisWithoutVote() {
        List<DashboardDataAnalysisDTO> dashboardDataAnalysisWithoutVote = null;
        try {
            dashboardDataAnalysisWithoutVote = weixinUserOptionLogMapper.getDashboardDataAnalysisWithoutVote();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询近一年的数据按天统计的数据除了投票的数量错误，错误理由：{}", e.toString());
        }
        return dashboardDataAnalysisWithoutVote;
    }
}
