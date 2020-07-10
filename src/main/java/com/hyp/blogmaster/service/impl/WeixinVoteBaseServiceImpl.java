package com.hyp.blogmaster.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.mapper.WeixinMusicMapper;
import com.hyp.blogmaster.mapper.WeixinVoteBaseMapper;
import com.hyp.blogmaster.mapper.WeixinVoteConfMapper;
import com.hyp.blogmaster.mapper.WeixinVoteOrganisersMapper;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinMusic;
import com.hyp.blogmaster.pojo.modal.WeixinVoteBase;
import com.hyp.blogmaster.pojo.modal.WeixinVoteConf;
import com.hyp.blogmaster.pojo.modal.WeixinVoteOrganisers;
import com.hyp.blogmaster.pojo.vo.page.weixin.IndexWorksVO;
import com.hyp.blogmaster.pojo.vo.page.weixin.VoteDetailByWorkIdVO;
import com.hyp.blogmaster.service.WeixinVoteBaseService;
import com.hyp.blogmaster.service.WeixinVoteWorkService;
import com.hyp.blogmaster.utils.MyEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/7 20:11
 * @Description: TODO
 */
@Service
@Slf4j
public class WeixinVoteBaseServiceImpl implements WeixinVoteBaseService {

    @Autowired
    private WeixinVoteBaseMapper weixinVoteBaseMapper;
    @Autowired
    private WeixinVoteWorkService weixinVoteWorkService;

    /**
     * 按照查询条件进行查询
     *
     * @param example 查询条件
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public List<WeixinVoteBase> getWeixinVoteBaseListByExample(Example example) throws MyDefinitionException {
        List<WeixinVoteBase> weixinVoteBaseList = null;
        try {
            weixinVoteBaseList = weixinVoteBaseMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("按照查询条件，作品信息分页查询错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("按照查询条件，作品信息分页查询错误");
        }

        return weixinVoteBaseList;
    }

    /**
     * 查询近一年的活动增量
     *
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getVoteDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> voteDashboardDataAnalysis = null;
        try {
            voteDashboardDataAnalysis = weixinVoteBaseMapper.getVoteDashboardDataAnalysis();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询近一年的活动增量错误，错误原因：{}", e.toString());
        }
        return voteDashboardDataAnalysis;
    }

    /**
     * 根据日期范围查询统计数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Override
    public Integer getTotalActiveNumByTime(Date startTime, Date endTime) {
        Example example = new Example(WeixinVoteBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("createTime", startTime, endTime);
        int i = 0;
        try {
            i = weixinVoteBaseMapper.selectCountByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 获取总数
     *
     * @return
     */
    @Override
    public Integer getTotalActiveNum() {
        int i = 0;
        try {
            i = weixinVoteBaseMapper.selectCount(null);
        } catch (Exception e) {
            log.error("查询活动总数错误，错误理由：{}", e.toString());
            throw new MyDefinitionException("查询活动总数错误");
        }
        return i;
    }

    @Autowired
    private WeixinVoteConfMapper weixinVoteConfMapper;
    @Autowired
    private WeixinMusicMapper weixinMusicMapper;
    @Autowired
    private WeixinVoteOrganisersMapper weixinVoteOrganisersMapper;

    /**
     * 分页查询投票活动列表
     *
     * @param weixinVoteBase
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo getVoteWorkByPage(WeixinVoteBase weixinVoteBase, PageInfo pageInfo) {

        /*条件查询*/
        Example example = new Example(WeixinVoteBase.class);
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("activeShowOrder").desc();
        /*example.orderBy("createTime").desc();
        example.orderBy("viewCountNum").desc();*/

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        //TODO　weixinVoteBase用于条件查询
        List<WeixinVoteBase> weixinVoteBases = weixinVoteBaseMapper.selectByExample(example);
        // 如果这里需要返回VO，那么这里一定先把查询值放进去，让分页信息存储成功。然后再setList加入VO信息
        pageInfo = new PageInfo(weixinVoteBases);
        /*组装VO进行数据返回*/
        List<IndexWorksVO> indexWorksVOS = new ArrayList<>(5);
        for (WeixinVoteBase weixinVoteBase2 : weixinVoteBases) {
            Integer countWorkByVoteBaseId = weixinVoteWorkService.getCountWorkByVoteBaseId(weixinVoteBase2.getId());
            /*Integer countVoteByVoteBaseId = weixinVoteWorkService.getCountVoteByVoteBaseId(weixinVoteBase2.getId());
            if (countVoteByVoteBaseId == null) {
                countVoteByVoteBaseId = 0;
            }*/
            //IndexWorksVO indexWorksVO = new IndexWorksVO();
            //BeanUtils.copyProperties(weixinVoteBase2, indexWorksVO);
            // 使用实体转换类进行数据转换处理
            IndexWorksVO indexWorksVO = MyEntityUtil.entity2VM(weixinVoteBase2, IndexWorksVO.class);
            indexWorksVO.setVoteWorkVoteCount(weixinVoteBase2.getVoteCountNum());
            indexWorksVO.setVoteWorkJoinCount(countWorkByVoteBaseId);
            indexWorksVOS.add(indexWorksVO);
        }
        pageInfo.setList(indexWorksVOS);


        return pageInfo;
    }

    /**
     * 通过活动的ID查询活动的详情
     *
     * @param workId 活动ID
     * @return
     */
    @Override
    public VoteDetailByWorkIdVO getVoteWorkByWorkId(Integer workId) {

        VoteDetailByWorkIdVO voteDetailByWorkIdVO = null;
        WeixinVoteBase weixinVoteBase = null;
        IndexWorksVO indexWorksVO = null;
        try {
            weixinVoteBase = weixinVoteBaseMapper.selectByPrimaryKey(workId);
        } catch (Exception e) {
            log.error("通过活动ID查询活动错误，查询的活动ID为{},错误理由{}", workId, e.toString());
            throw new MyDefinitionException("通过活动ID查询活动错误，查询的活动ID为" + workId);
        }
        if (weixinVoteBase == null) {
            return null;
        } else {
            Integer countWorkByVoteBaseId = weixinVoteWorkService.getCountWorkByVoteBaseId(weixinVoteBase.getId());
            // Integer countVoteByVoteBaseId = weixinVoteWorkService.getCountVoteByVoteBaseId(weixinVoteBase.getId());
           /* if (countVoteByVoteBaseId == null) {
                countVoteByVoteBaseId = 0;
            }*/


            Example example = new Example(WeixinVoteConf.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("activeVoteBaseId", weixinVoteBase.getId());
            List<WeixinVoteConf> weixinVoteConfS = null;
            try {
                weixinVoteConfS = weixinVoteConfMapper.selectByExample(example);
            } catch (Exception e) {
                log.error("通过投票活动ID查询配置内容错误，查询的投票ID为{},错误理由{}", workId, e.toString());
                throw new MyDefinitionException("通过投票活动ID查询配置内容错误，查询的投票ID为" + workId);

            }
            WeixinVoteConf weixinVoteConf = null;
            if (weixinVoteConfS != null && weixinVoteConfS.size() > 0) {
                weixinVoteConf = weixinVoteConfS.get(0);
            }

            String activeMusic = null;
            if (weixinVoteConf != null) {
                int musicId = weixinVoteConf.getActiveConfMusicId();
                WeixinMusic weixinMusic = null;
                try {
                    weixinMusic = weixinMusicMapper.selectByPrimaryKey(musicId);
                } catch (Exception e) {
                    log.error("通过音乐ID查询音乐内容错误，查询的音乐ID为{},错误理由{}", workId, e.toString());
                    throw new MyDefinitionException("通过音乐ID查询音乐内容错误，查询的音乐ID为" + workId);

                }


                if (weixinMusic != null) {
                    activeMusic = weixinMusic.getMusicUrl();
                }
            }


            Example example1 = new Example(WeixinVoteOrganisers.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("voteBaseId", weixinVoteBase.getId());
            WeixinVoteOrganisers weixinVoteOrganisers = new WeixinVoteOrganisers();
            List<WeixinVoteOrganisers> weixinVoteOrganisersList = null;
            try {
                weixinVoteOrganisersList = weixinVoteOrganisersMapper.selectByExample(example1);
            } catch (Exception e) {
                log.error("通过活动ID查询创建单位内容错误，查询的活动ID为{},错误理由{}", workId, e.toString());
                throw new MyDefinitionException("通过活动ID查询创建单位内容错误，查询的活动ID为" + workId);

            }
            if (weixinVoteOrganisersList != null && weixinVoteOrganisersList.size() > 0) {
                weixinVoteOrganisers = weixinVoteOrganisersList.get(0);
            }


            voteDetailByWorkIdVO = new VoteDetailByWorkIdVO();
            voteDetailByWorkIdVO.setActiveBgImg(weixinVoteBase.getActiveDescImg());
            voteDetailByWorkIdVO.setActiveImg(weixinVoteBase.getActiveImg());
            voteDetailByWorkIdVO.setActiveEndTime(weixinVoteBase.getActiveEndTime());
            voteDetailByWorkIdVO.setActiveStartTime(weixinVoteBase.getActiveStartTime());
            voteDetailByWorkIdVO.setActiveName(weixinVoteBase.getActiveName());
            voteDetailByWorkIdVO.setActiveMusic(activeMusic);
            voteDetailByWorkIdVO.setActiveJoinCount(countWorkByVoteBaseId);
            voteDetailByWorkIdVO.setActiveVoteCount(weixinVoteBase.getVoteCountNum());
            voteDetailByWorkIdVO.setActiveViewCount(weixinVoteBase.getViewCountNum());
            voteDetailByWorkIdVO.setOrganisersName(weixinVoteOrganisers.getName());
            voteDetailByWorkIdVO.setOrganisersLogoImg(weixinVoteOrganisers.getLogoImg());
            voteDetailByWorkIdVO.setOrganisersWeixinQrCode(weixinVoteOrganisers.getWeixinQrCode());
            voteDetailByWorkIdVO.setOrganisersPhone(weixinVoteOrganisers.getPhone());
        }

        return voteDetailByWorkIdVO;
    }

    /**
     * 通过活动的ID更新被浏览次数
     *
     * @param workId 活动ID
     * @return
     */
    @Override
    public int updateVoteBaseViewNum(Integer workId) {

        WeixinVoteBase weixinVoteBase = null;
        try {
            weixinVoteBase = weixinVoteBaseMapper.selectByPrimaryKey(workId);
        } catch (Exception e) {
            log.error("通过活动ID查询活动错误，查询的活动ID为{},错误理由{}", workId, e.toString());
            throw new MyDefinitionException("通过活动ID查询活动错误，查询的活动ID为" + workId);
        }
        if (weixinVoteBase == null) {
            return 0;
        } else {
            Integer viewCountNum = weixinVoteBase.getViewCountNum();
            weixinVoteBase.setViewCountNum(viewCountNum + 1);
        }
        int i = 0;
        try {
            i = weixinVoteBaseMapper.updateByPrimaryKey(weixinVoteBase);
        } catch (Exception e) {
            log.error("通过活动ID更新浏览次数（数据加1）错误，更新的活动ID为{},错误理由{}", workId, e.toString());
            throw new MyDefinitionException("通过活动ID更新浏览次数（数据加1）错误，查询的活动ID为" + workId);
        }

        return i;
    }

    /**
     * 通过workId记录点赞数
     *
     * @param workId 活动ID
     * @return
     */
    @Override
    public int updateVoteBaseVoteNum(Integer workId) {
        WeixinVoteBase weixinVoteBase = weixinVoteBaseMapper.selectByPrimaryKey(workId);
        if (weixinVoteBase == null) {
            return 0;
        } else {
            Integer voteCountNum = weixinVoteBase.getVoteCountNum();
            weixinVoteBase.setVoteCountNum(voteCountNum + 1);
        }
        int i = 0;
        try {
            i = weixinVoteBaseMapper.updateByPrimaryKey(weixinVoteBase);
        } catch (Exception e) {
            log.error("通过活动ID更新投票次数（数据加1）错误，更新的活动ID为{},错误理由{}", workId, e.toString());
            throw new MyDefinitionException("通过活动ID更新投票次数（数据加1）错误，查询的活动ID为" + workId);
        }
        return i;
    }
}
