package com.hyp.blogmaster.mapper;


import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteBase;
import com.hyp.blogmaster.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinVoteBaseMapper extends MyMapper<WeixinVoteBase> {

    /**
     * 近一年内活动增量
     * @return
     */
    List<DashboardDataAnalysisDTO> getVoteDashboardDataAnalysis();
}