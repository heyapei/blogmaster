package com.hyp.blogmaster.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.mapper.WeixinVoteUserMapper;
import com.hyp.blogmaster.pojo.dto.manager.weixinuser.UserRegionAnalysisDTO;
import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;
import com.hyp.blogmaster.pojo.query.ManagerUserQuery;
import com.hyp.blogmaster.service.WeixinVoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
     * 用户所属城市分析 前200条数据
     *
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public List<UserRegionAnalysisDTO> getWeixinUserAnalysisCityPieSimple() throws MyDefinitionException {
        List<UserRegionAnalysisDTO> weixinUserRegionAnalysisList = null;
        try {
            weixinUserRegionAnalysisList = weixinVoteUserMapper.getWeixinUserRegionAnalysisList();
        } catch (Exception e) {
            log.error("用户所属城市分析前200条数据查询操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("用户所属城市分析前200条数据查询操作过程错误");
        }


        return weixinUserRegionAnalysisList;
    }

    /**
     * 更新用户状态 如果是0 更新为1 如果是1更新为0
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public Integer changeUserEnable(Integer userId) throws MyDefinitionException {
        if (userId == null) {
            throw new MyDefinitionException("用户ID为空");
        }
        WeixinVoteUser userById = getUserById(userId);
        if (userById == null) {
            throw new MyDefinitionException("未查找到当前用户");
        }
        if (userById.getEnable() == 0) {
            userById.setEnable(1);
        } else {
            userById.setEnable(0);
        }
        Integer updateSelectiveWeixinUser = null;
        try {
            updateSelectiveWeixinUser = updateSelectiveWeixinUser(userById);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            throw new MyDefinitionException(e.getMessage());
        }
        if (updateSelectiveWeixinUser == null || updateSelectiveWeixinUser <= 0) {
            throw new MyDefinitionException("更新用户状态信息错误");
        }
        return updateSelectiveWeixinUser;
    }


    /**
     * 根据ID更新用户信息只更新用户有效信息
     *
     * @param weixinVoteUser
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public Integer updateSelectiveWeixinUser(WeixinVoteUser weixinVoteUser) throws MyDefinitionException {
        if (weixinVoteUser == null || weixinVoteUser.getId() == null) {
            throw new MyDefinitionException("根据ID更新用户信息只更新用户有效信息错误，参数不正确");
        }
        Integer i = null;
        try {
            i = weixinVoteUserMapper.updateByPrimaryKey(weixinVoteUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据ID更新用户信息只更新用户有效信息，执行操作错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("根据ID更新用户信息只更新用户有效信息，执行操作错误");
        }
        return i;
    }

    /**
     * 分页查询
     *
     * @param managerUserQuery 查询实体类
     * @return 分页信息
     */
    @Override
    public PageInfo getVoteUserByPage(ManagerUserQuery managerUserQuery) throws MyDefinitionException {


        Example example = new Example(WeixinVoteUser.class);
        Example.Criteria criteria = example.createCriteria();

        /*按照openId查询*/
        if (managerUserQuery.getOpenid() != null && StringUtils.isNotBlank(managerUserQuery.getOpenid())) {
            criteria.andLike("openId", "%" + managerUserQuery.getOpenid() + "%");
        }
        /*昵称查询*/
        if (managerUserQuery.getNickName() != null && StringUtils.isNotBlank(managerUserQuery.getNickName())) {
            criteria.andLike("nickName", "%" + managerUserQuery.getNickName() + "%");
        }

        /*是否启用 按照用户状态*/
        if (managerUserQuery.getEnable() != null) {
            criteria.andEqualTo("enable", managerUserQuery.getEnable());
        }

        /*排序查询*/
        if (managerUserQuery.getOrderColumn() != null && StringUtils.isNotBlank(managerUserQuery.getOrderColumn())) {
            if (managerUserQuery.getOrderBy() != null
                    && managerUserQuery.getOrderBy().equalsIgnoreCase("asc")) {
                example.orderBy(managerUserQuery.getOrderColumn()).asc();
            } else {
                example.orderBy(managerUserQuery.getOrderColumn()).desc();
            }
        }

        PageHelper.startPage(managerUserQuery.getPageNum(), managerUserQuery.getPageSize());
        List<WeixinVoteUser> weixinVoteUsers = null;
        try {
            weixinVoteUsers = weixinVoteUserMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户信息分页查询错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("用户信息分页查询错误");
        }

        // 如果这里需要返回VO，那么这里一定先把查询值放进去，让分页信息存储成功。然后再setList加入VO信息
        PageInfo<WeixinVoteUser> pageInfo = null;
        try {
            pageInfo = new PageInfo(weixinVoteUsers);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户信息分页查询结果处理错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("用户信息分页查询结果处理错误");
        }

        return pageInfo;
    }

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
            log.error("查询近一年的用户按天统计的数据错误，错误原因：{}", e.toString());
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
