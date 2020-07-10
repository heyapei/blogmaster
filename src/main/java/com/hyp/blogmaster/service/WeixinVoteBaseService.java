package com.hyp.blogmaster.service;


import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteBase;
import com.hyp.blogmaster.pojo.vo.page.weixin.VoteDetailByWorkIdVO;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/7 20:09
 * @Description: TODO
 */
public interface WeixinVoteBaseService {






    /**
     * 按照查询条件进行查询
     * @param example 查询条件
     * @return
     * @throws MyDefinitionException
     */
    List<WeixinVoteBase> getWeixinVoteBaseListByExample(Example example) throws MyDefinitionException;


    /**
     * 查询近一年的活动增量
     *
     * @return
     */
    List<DashboardDataAnalysisDTO> getVoteDashboardDataAnalysis();


    /**
     * 根据日期范围查询统计数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Integer getTotalActiveNumByTime(Date startTime,Date endTime);


    /**
     * 获取总数
     *
     * @return
     */
    Integer getTotalActiveNum();



    /**
     * 分页查询投票活动列表
     *
     * @param weixinVoteBase
     * @param pageInfo
     * @return
     */
    PageInfo getVoteWorkByPage(WeixinVoteBase weixinVoteBase, PageInfo pageInfo);


    /**
     * 通过活动的ID查询活动的详情
     *
     * @param workId 活动ID
     * @return
     */
    VoteDetailByWorkIdVO getVoteWorkByWorkId(Integer workId);


    /**
     * 通过活动的ID更新被浏览次数
     *
     * @param workId 活动ID
     * @return
     */
    int updateVoteBaseViewNum(Integer workId);


    /**
     * 通过workId记录点赞数
     *
     * @param workId 活动ID
     * @return
     */
    int updateVoteBaseVoteNum(Integer workId);


}
