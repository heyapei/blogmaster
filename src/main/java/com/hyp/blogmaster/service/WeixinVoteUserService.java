package com.hyp.blogmaster.service;


import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;

import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/5 19:28
 * @Description: TODO
 */
public interface WeixinVoteUserService {


    /**
     * 获取总用户数据量根据日期范围
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Integer getTotalUserByTime(Date startTime, Date endTime);


    /**
     * 获取总用户数据量
     * @return
     */
    Integer getTotalUser();

    /**
     * 测试事务
     *
     * @param weixinVoteUser 微信用户信息
     * @return 主键
     */
    int testTransactional(WeixinVoteUser weixinVoteUser);


    /**
     * 添加用户信息
     *
     * @param weixinVoteUser 微信用户信息
     * @return 主键
     */
    int addWechatInfo(WeixinVoteUser weixinVoteUser);

    /**
     * 通过表主键获取用户信息
     *
     * @param id 主键voteUser
     * @return 信息详情
     */
    WeixinVoteUser getUserById(Integer id);

    /**
     * 通过openId获取用户信息
     * 每个openId对应一个用户，且不可以重复
     *
     * @param openId
     * @return 信息详情
     */
    WeixinVoteUser getUserByOpenId(String openId);


    /**
     * 根据用户openId更新用户信息
     *
     * @param weixinVoteUser
     * @return
     */
    Integer updateWeixinUserByOpenId(WeixinVoteUser weixinVoteUser);


}
