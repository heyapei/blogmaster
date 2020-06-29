package com.hyp.blogmaster.mapper;


import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinUserOptionLog;
import com.hyp.blogmaster.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinUserOptionLogMapper extends MyMapper<WeixinUserOptionLog> {

    /**
     * 按照操作类型 查询近一年的数据按天统计的数据
     *
     * @param optionType
     * @return
     */
    List<DashboardDataAnalysisDTO> getDashboardDataAnalysisByOptionType(Integer optionType);


    /**
     * 查询近一年的数据按天统计的数据 除了投票的数量
     *
     * @return
     */
    List<DashboardDataAnalysisDTO> getDashboardDataAnalysisWithoutVote();

}