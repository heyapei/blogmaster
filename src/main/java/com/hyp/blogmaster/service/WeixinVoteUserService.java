package com.hyp.blogmaster.service;


import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.dto.manager.weixinuser.UserAnalysisSimpleDTO;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;
import com.hyp.blogmaster.pojo.query.ManagerUserQuery;

import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/5 19:28
 * @Description: TODO
 */
public interface WeixinVoteUserService {


    /**
     * 用户所属城市分析 前200条数据
     * @return
     * @throws MyDefinitionException
     */
    List<UserAnalysisSimpleDTO> getWeixinUserAnalysisCityPieSimple() throws MyDefinitionException;

    /**
     * 用户性别分析
     * @return
     * @throws MyDefinitionException
     */
    List<UserAnalysisSimpleDTO> getWeixinUserAnalysisSexPieSimple() throws MyDefinitionException;


    /**
     * 更新用户状态 如果是0 更新为1 如果是1更新为0
     * @param userId
     * @return
     * @throws MyDefinitionException
     */
    Integer changeUserEnable(Integer userId) throws MyDefinitionException;


    /**
     * 根据ID更新用户信息只更新用户有效信息
     * @param weixinVoteUser
     * @return
     * @throws MyDefinitionException
     */
    Integer updateSelectiveWeixinUser(WeixinVoteUser weixinVoteUser) throws MyDefinitionException;


    /**
     * * 分页查询
     * *
     * * @param managerUserQuery 查询实体类
     * * @return 分页信息
     *
     * @param managerUserQuery
     * @return
     * @throws MyDefinitionException
     */
    PageInfo getVoteUserByPage(ManagerUserQuery managerUserQuery) throws MyDefinitionException;


    /**
     * 查询近一年的用户按天统计的数据
     *
     * @return
     */
    List<DashboardDataAnalysisDTO> getUserDashboardDataAnalysis();


    /**
     * 获取总用户数据量根据日期范围
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    Integer getTotalUserByTime(Date startTime, Date endTime);


    /**
     * 获取总用户数据量
     *
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
