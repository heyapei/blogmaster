package com.hyp.blogmaster.mapper;


import com.hyp.blogmaster.pojo.dto.manager.weixinuser.UserAnalysisSimpleDTO;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;
import com.hyp.blogmaster.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinVoteUserMapper extends MyMapper<WeixinVoteUser> {

    /**
     * 查询近一年的用户按天统计的数据

     * @return
     */
    List<DashboardDataAnalysisDTO> getUserDashboardDataAnalysis();


    /**
     * 获取用户所在城市占比
     * @return
     */
    List<UserAnalysisSimpleDTO> getWeixinUserRegionAnalysisList();
    /**
     * 获取用户性别占比
     * @return
     */
    List<UserAnalysisSimpleDTO> getWeixinUserAnalysisSexPieSimple();

}