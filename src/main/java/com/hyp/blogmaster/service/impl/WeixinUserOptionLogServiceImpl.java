package com.hyp.blogmaster.service.impl;

import com.hyp.blogmaster.mapper.WeixinUserOptionLogMapper;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.service.WeixinUserOptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/28 20:57
 * @Description: TODO
 */
@Service
@Slf4j
public class WeixinUserOptionLogServiceImpl implements WeixinUserOptionLogService {

    @Autowired
    private WeixinUserOptionLogMapper weixinUserOptionLogMapper;

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
}
