package com.hyp.blogmaster.service;


import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteWork;
import com.hyp.blogmaster.pojo.vo.page.weixin.VoteDetailCompleteVO;
import com.hyp.blogmaster.pojo.vo.page.weixin.WeixinVoteUserWorkDiffVO;

import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/8 17:30
 * @Description: TODO
 */
public interface WeixinVoteWorkService {

    /**
     * 查询近一年的作品按天统计的数据
     *
     * @return
     */
    List<DashboardDataAnalysisDTO> getDashboardDataAnalysis();



    /**
     * 按照时间范围查询
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Integer getTotalUserWorkNumByTime(Date startTime, Date endTime);

    /**
     * 获取总数
     *
     * @return
     */
    Integer getTotalUserWorkNum();

    /**
     * 获取作品点赞比当前作品多的作品
     *
     * @param activeId
     * @param workId
     * @return
     */
    List<WeixinVoteWork> getThanWorkWeixinVoteWork(Integer activeId, Integer workId);


    /**
     * 获取作品点赞比当前作品少的作品
     *
     * @param activeId
     * @param workId
     * @return
     */
    List<WeixinVoteWork> getLessWorkWeixinVoteWork(Integer activeId, Integer workId);


    /**
     * 获取作品在活动中的排名
     *
     * @param activeId
     * @param workId
     * @return
     */
    Integer getRankNumByUserWorkId(Integer activeId, Integer workId);

    /**
     * 查询当前作品的差距
     *
     * @param workId
     * @return
     */
    WeixinVoteUserWorkDiffVO getUserWorkDiff(Integer workId);


    /**
     * 通过voteBaseId获取当前这个活动有多少人参加
     *
     * @param voteBaseId
     * @return
     */
    Integer getCountWorkByVoteBaseId(Integer voteBaseId);


    /**
     * 通过voteBaseId获取当前这个活动有多少人投票
     *
     * @param voteBaseId
     * @return
     */
    Integer getCountVoteByVoteBaseId(Integer voteBaseId);


    /**
     * 分页查询活动的所有的作品
     *
     * @param weixinVoteWork
     * @param pageInfo
     * @return
     */
    PageInfo getVoteWorkAllWorkByPage(WeixinVoteWork weixinVoteWork, PageInfo pageInfo);

    /**
     * 分页查询活动的人气的作品
     *
     * @param weixinVoteWork
     * @param pageInfo
     * @return
     */
    PageInfo getVoteWorkHotWorkByPage(WeixinVoteWork weixinVoteWork, PageInfo pageInfo);


    /**
     * 通过userWorkId查询当前的详细信息
     *
     * @param userWorkId
     * @return
     */
    VoteDetailCompleteVO getWeixinVoteWorkByUserWorkId(Integer userWorkId);


    /**
     * 通过作品的ID更新被浏览次数
     *
     * @param userWorkId 用户作品ID
     * @return
     */
    int updateVoteWorkViewNum(Integer userWorkId);


    /**
     * 通过作品的ID查询作品
     *
     * @param userWorkId 用户作品ID
     * @return
     */
    WeixinVoteWork getVoteWorkByUserWorkId(Integer userWorkId);



    /**
     * 通过作品的ID更新被投票次数
     *
     * @param userWorkId 用户作品ID
     * @return
     */
    int updateVoteWorkVoteNum(Integer userWorkId);





}
