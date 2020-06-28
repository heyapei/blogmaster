package com.hyp.blogmaster.service;

import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;

import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/28 20:56
 * @Description: TODO
 */
public interface WeixinUserOptionLogService {

    /**
     * 查询近一年的用户按天统计的数据
     * @param optionType 操作类型
     * @return
     */
    List<DashboardDataAnalysisDTO> getDashboardDataAnalysisByOptionType(Integer optionType);
}
