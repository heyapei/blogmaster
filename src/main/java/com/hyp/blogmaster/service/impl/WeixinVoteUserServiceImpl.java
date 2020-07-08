package com.hyp.blogmaster.service.impl;


import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.mapper.WeixinVoteUserMapper;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;
import com.hyp.blogmaster.service.WeixinVoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/5 19:28
 * @Description: TODO
 */
@Service
@Slf4j
public class WeixinVoteUserServiceImpl implements WeixinVoteUserService {


    @Autowired
    private WeixinVoteUserMapper weixinVoteUserMapper;


    /**
     * 查询近一年的用户按天统计的数据
     *
     * @return
     */
    @Override
    public List<DashboardDataAnalysisDTO> getUserDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> userDashboardDataAnalysis = null;
        try {
            userDashboardDataAnalysis = weixinVoteUserMapper.getUserDashboardDataAnalysis();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询近一年的用户按天统计的数据错误，错误原因：{}",e.toString());
        }

        return userDashboardDataAnalysis;
    }

    /**
     * 获取总用户数据量根据日期范围
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Override
    public Integer getTotalUserByTime(Date startTime, Date endTime) {
        Example example = new Example(WeixinVoteUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("createTime", startTime, endTime);
        int i = 0;
        try {
            i = weixinVoteUserMapper.selectCountByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 获取总用户数据量
     *
     * @return
     */
    @Override
    public Integer getTotalUser() {
        int i = 0;
        try {
            i = weixinVoteUserMapper.selectCount(null);
        } catch (Exception e) {
            log.error("查询用户总数错误，错误理由：{}", e.toString());
            throw new MyDefinitionException("查询用户总数错误");

        }
        return i;
    }

    /**
     * 测试结果为正确的 可以有效地进行事务上的回滚
     */
    @Override
    @Transactional
    public int testTransactional(WeixinVoteUser weixinVoteUser) {
        /*try {
            weixinVoteUserMapper.insertUseGeneratedKeys(weixinVoteUser);
        } catch (Exception e) {
            log.info(e.toString());
            throw new MyDefinitionException("保存微信用户数错误");
        }

        if (true) {
            try {
                Example example = new Example(WeixinVoteUser.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("ope3411nId", "openId");
                List<WeixinVoteUser> weixinVoteUsers = weixinVoteUserMapper.selectByExample(example);
                if (weixinVoteUsers != null && weixinVoteUsers.size() > 0) {
                    weixinVoteUser = weixinVoteUsers.get(0);
                }
            } catch (Exception e) {
                log.info(e.toString());
                throw new MyDefinitionException("通过openId查询用户数据失败");
            }
        }*/

        return 0;
    }

    /**
     * 添加用户信息
     *
     * @param weixinVoteUser 微信用户信息
     * @return
     */
    @Override
    public int addWechatInfo(WeixinVoteUser weixinVoteUser) {
        try {
            weixinVoteUserMapper.insertUseGeneratedKeys(weixinVoteUser);
        } catch (Exception e) {
            log.info(e.toString());
            throw new MyDefinitionException("保存微信用户数错误");
        }

        return weixinVoteUser.getId();
    }

    /**
     * 通过表主键获取用户信息
     *
     * @param id 主键voteUser
     * @return 信息详情
     */
    @Override
    public WeixinVoteUser getUserById(Integer id) {
        WeixinVoteUser weixinVoteUser = weixinVoteUserMapper.selectByPrimaryKey(id);
        if (weixinVoteUser != null) {
            return weixinVoteUser;
        }
        return null;
    }

    /**
     * 通过openId获取用户信息
     *
     * @param openId
     * @return
     */
    @Override
    public WeixinVoteUser getUserByOpenId(String openId) {
        WeixinVoteUser weixinVoteUser = null;
        try {
            Example example = new Example(WeixinVoteUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("openId", openId);
            List<WeixinVoteUser> weixinVoteUsers = weixinVoteUserMapper.selectByExample(example);
            if (weixinVoteUsers != null && weixinVoteUsers.size() > 0) {
                weixinVoteUser = weixinVoteUsers.get(0);
            }
        } catch (Exception e) {
            log.info(e.toString());
            throw new MyDefinitionException("通过openId查询用户数据失败");
        }
        return weixinVoteUser;
    }

    /**
     * 根据用户openId更新用户信息
     *
     * @param weixinVoteUser
     * @return
     */
    @Override
    public Integer updateWeixinUserByOpenId(WeixinVoteUser weixinVoteUser) {
        Integer i = 0;
        WeixinVoteUser userByOpenId = getUserByOpenId(weixinVoteUser.getOpenId());
        if (userByOpenId != null) {
            Example example = new Example(WeixinVoteUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("openId", weixinVoteUser.getOpenId());
            i = weixinVoteUserMapper.updateByExampleSelective(weixinVoteUser, example);
        }
        return i;
    }
}