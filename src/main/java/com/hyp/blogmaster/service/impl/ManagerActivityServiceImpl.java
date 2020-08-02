package com.hyp.blogmaster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.dto.manager.ActivityManagerDTO;
import com.hyp.blogmaster.pojo.modal.WeixinVoteBase;
import com.hyp.blogmaster.pojo.modal.WeixinVoteConf;
import com.hyp.blogmaster.pojo.modal.WeixinVoteOrganisers;
import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;
import com.hyp.blogmaster.pojo.query.ManageActivityQuery;
import com.hyp.blogmaster.pojo.vo.page.active.ActiveDetailVO;
import com.hyp.blogmaster.pojo.vo.page.active.ActiveDetailWithConfOrgVO;
import com.hyp.blogmaster.service.*;
import com.hyp.blogmaster.utils.MyEntityUtil;
import com.hyp.blogmaster.utils.MyErrorList;
import com.hyp.blogmaster.utils.dateutil.MyDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 20:40
 * @Description: TODO
 */
@Slf4j
@Service
public class ManagerActivityServiceImpl implements ManagerActivityService {


    @Autowired
    private WeixinVoteUserService weixinVoteUserService;

    @Autowired
    private WeixinVoteOrganisersService weixinVoteOrganisersService;
    @Autowired
    private WeixinVoteConfService weixinVoteConfService;

    @Autowired
    private MyErrorList myErrorList;
    @Autowired
    private WeixinVoteBaseService weixinVoteBaseService;

    @Autowired
    private WeixinVoteWorkService weixinVoteWorkService;


    private String SEPARATOR = ";";


    /**
     * 通过活动ID查询所有能够编辑的数据
     *
     * @param activeId 活动的ID
     * @return 能编辑的数据
     * @throws MyDefinitionException
     */
    @Override
    public ActiveDetailWithConfOrgVO getActiveDetailWithConfOrgVOByActiveId(Integer activeId) throws MyDefinitionException {
        ActiveDetailWithConfOrgVO activeDetailWithConfOrgVO = null;
        if (activeId == null) {
            throw new MyDefinitionException("通过活动ID查询所有能够编辑的数据的参数不能为空");
        }

        WeixinVoteBase weixinVoteBase = weixinVoteBaseService.getWeixinVoteBaseByPK(activeId);
        if (weixinVoteBase == null) {
            throw new MyDefinitionException("未能查询到相应的活动数据");
        }
        activeDetailWithConfOrgVO = new ActiveDetailWithConfOrgVO();
        activeDetailWithConfOrgVO.setActiveName(weixinVoteBase.getActiveName());
        activeDetailWithConfOrgVO.setActiveId(activeId);
        activeDetailWithConfOrgVO.setActiveUserId(weixinVoteBase.getCreateSysUserId());
        activeDetailWithConfOrgVO.setActiveDesc(weixinVoteBase.getActiveDesc());
        activeDetailWithConfOrgVO.setActiveDescImgS(weixinVoteBase.getActiveDescImg());
        activeDetailWithConfOrgVO.setActiveReward(weixinVoteBase.getActiveReward());
        activeDetailWithConfOrgVO.setActiveRewardImgS(weixinVoteBase.getActiveRewardImg());
        activeDetailWithConfOrgVO.setActiveStartTime(weixinVoteBase.getActiveStartTime());
        activeDetailWithConfOrgVO.setActiveEndTime(weixinVoteBase.getActiveEndTime());
        activeDetailWithConfOrgVO.setActivePublic(weixinVoteBase.getActivePublic());
        activeDetailWithConfOrgVO.setActiveStatus(weixinVoteBase.getStatus());
        activeDetailWithConfOrgVO.setViewCount(weixinVoteBase.getViewCountNum());
        activeDetailWithConfOrgVO.setVoteCount(weixinVoteBase.getVoteCountNum());
        activeDetailWithConfOrgVO.setShowOrder(weixinVoteBase.getActiveShowOrder());


        WeixinVoteConf weixinVoteConf = weixinVoteConfService.getWeixinVoteConfByVoteWorkId(activeId);
        if (weixinVoteConf != null) {
            activeDetailWithConfOrgVO.setActiveConfId(weixinVoteConf.getId());
            activeDetailWithConfOrgVO.setActiveConfShareImg(weixinVoteConf.getActiveConfShareImg());
            activeDetailWithConfOrgVO.setActiveConfRepeatVote(weixinVoteConf.getActiveConfRepeatVote());
            activeDetailWithConfOrgVO.setActiveConfVoteType(weixinVoteConf.getActiveConfVoteType());
            activeDetailWithConfOrgVO.setActiveConfSignUp(weixinVoteConf.getActiveConfSignUp());
            activeDetailWithConfOrgVO.setAllowUploadStartTime(MyDateUtil.dateToLocalDateTime(weixinVoteConf.getActiveUploadEndTime()));
            activeDetailWithConfOrgVO.setAllowUploadEndTime(MyDateUtil.dateToLocalDateTime(weixinVoteConf.getActiveUploadEndTime()));
            activeDetailWithConfOrgVO.setActiveConfNeedPhone(weixinVoteConf.getActiveConfNeedPhone());
            activeDetailWithConfOrgVO.setActiveConfNeedWeixin(weixinVoteConf.getActiveConfNeedWeixin());
            activeDetailWithConfOrgVO.setActiveRule(weixinVoteConf.getActiveConfVoteType());
        }
        WeixinVoteOrganisers weixinVoteOrganisers = weixinVoteOrganisersService.getWeixinVoteConfByVoteWorkId(activeId);
        if (weixinVoteOrganisers != null) {
            activeDetailWithConfOrgVO.setActiveOrgId(weixinVoteOrganisers.getId());
            activeDetailWithConfOrgVO.setOrgLogo(weixinVoteOrganisers.getLogoImg());
            activeDetailWithConfOrgVO.setOrgName(weixinVoteOrganisers.getName());
            activeDetailWithConfOrgVO.setOrgPhone(weixinVoteOrganisers.getPhone());
            activeDetailWithConfOrgVO.setOrgWeixinQrCode(weixinVoteOrganisers.getWeixinQrCode());
        }

        return activeDetailWithConfOrgVO;
    }

    /**
     * 根据活动ID查询活动配置等相关的信息
     *
     * @param activeId 活动ID
     * @return 活动配置等相关的视图信息
     * @throws MyDefinitionException
     */
    @Override
    public ActiveDetailVO getActiveDetailVOByActiveId(Integer activeId) throws MyDefinitionException {
        if (activeId == null) {
            throw new MyDefinitionException("根据活动ID查询活动配置等相关的信息参数不能为空");
        }

        WeixinVoteBase weixinVoteBaseByPK = weixinVoteBaseService.getWeixinVoteBaseByPK(activeId);
        if (weixinVoteBaseByPK == null) {
            throw new MyDefinitionException("没有找到该活动，活动ID：" + activeId);
        }

        ActiveDetailVO activeDetailVO = new ActiveDetailVO();
        activeDetailVO.setActiveDesc(weixinVoteBaseByPK.getActiveDesc());
        String activeDescImg = weixinVoteBaseByPK.getActiveDescImg();
        if (StringUtils.isNotBlank(activeDescImg) && activeDescImg.contains(SEPARATOR)) {
            activeDetailVO.setActiveDescImgS(activeDescImg.split(SEPARATOR));
        }
        activeDetailVO.setActiveName(weixinVoteBaseByPK.getActiveName());
        activeDetailVO.setActiveReward(weixinVoteBaseByPK.getActiveReward());
        String rewardImg = weixinVoteBaseByPK.getActiveRewardImg();
        if (StringUtils.isNotBlank(rewardImg) && rewardImg.contains(SEPARATOR)) {
            activeDetailVO.setActiveRewardImgS(rewardImg.split(SEPARATOR));
        }


        String weixinVoteRuleByPK = weixinVoteBaseService.getWeixinVoteRuleByPK(weixinVoteBaseByPK.getId());
        activeDetailVO.setActiveRule(weixinVoteRuleByPK);
        WeixinVoteConf weixinVoteConfByVoteWorkId = weixinVoteConfService.getWeixinVoteConfByVoteWorkId(activeId);
        if (weixinVoteConfByVoteWorkId != null) {
            Integer activeConfSignUp = weixinVoteConfByVoteWorkId.getActiveConfSignUp();
            if (activeConfSignUp.equals(WeixinVoteConf.ActiveConfSignUpEnum.CAN_SIGN_UP.getCode())) {
                activeDetailVO.setAllowUserUpload(true);
            } else if (activeConfSignUp.equals(WeixinVoteConf.ActiveConfSignUpEnum.CANT_SIGN_UP.getCode())) {
                activeDetailVO.setAllowUserUpload(false);
                activeDetailVO.setAllowUploadStartTime(weixinVoteConfByVoteWorkId.getActiveUploadStartTime());
                activeDetailVO.setAllowUploadEndTime(weixinVoteConfByVoteWorkId.getActiveUploadEndTime());
            }
            if (StringUtils.isNotBlank((weixinVoteConfByVoteWorkId.getActiveConfShareImg()))) {
                activeDetailVO.setActiveConfShareImg(weixinVoteConfByVoteWorkId.getActiveConfShareImg().replaceAll(SEPARATOR, ""));
            }
            activeDetailVO.setActiveConfNeedWeixin(weixinVoteConfByVoteWorkId.getActiveConfNeedWeixin());
            activeDetailVO.setActiveConfNeedPhone(weixinVoteConfByVoteWorkId.getActiveConfNeedPhone());
        }
        return activeDetailVO;
    }

    /**
     * 根据查询条件进行数据分页查询
     *
     * @param manageActivityQuery 查询用实体类
     * @return 分页信息
     * @throws MyDefinitionException
     */
    @Override
    public PageInfo<ActivityManagerDTO> getActivityManagerDTOByExample(ManageActivityQuery manageActivityQuery) throws MyDefinitionException {


        Example example = new Example(WeixinVoteBase.class);
        Example.Criteria criteria = example.createCriteria();

        /*如果是按照openId查询*/
        WeixinVoteUser userByOpenId = null;
        String openid = manageActivityQuery.getOpenId();
        if (StringUtils.isNotBlank(openid)) {
            userByOpenId = weixinVoteUserService.getUserByOpenId(openid);
        }
        if (userByOpenId != null) {
            criteria.andEqualTo("createSysUserId", userByOpenId.getId());
        }

        /*如果是按照公司名称模糊查询*/
        String organisersName = manageActivityQuery.getOrganisersName();
        if (StringUtils.isNotBlank(organisersName)) {
            List<WeixinVoteOrganisers> weixinVoteOrganisersByLikeName = weixinVoteOrganisersService.getWeixinVoteOrganisersByLikeName(organisersName);
            //log.info("公司结果：{}",weixinVoteOrganisersByLikeName.toString());
            if (weixinVoteOrganisersByLikeName != null && weixinVoteOrganisersByLikeName.size() > 0) {
                List<Integer> nameList = new ArrayList<>(16);
                for (WeixinVoteOrganisers weixinVoteOrganisers : weixinVoteOrganisersByLikeName) {
                    nameList.add(weixinVoteOrganisers.getVoteBaseId());
                }
                criteria.andIn("id", nameList);
            }
        }

        /*如果是按照活动名称模糊查询*/
        String activeName = manageActivityQuery.getActiveName();
        if (StringUtils.isNotBlank(activeName)) {
            criteria.andLike("activeName", "%" + activeName + "%");
        }

        /*按照是否在首页进行公开*/
        Integer activePublic = manageActivityQuery.getActivePublic();
        if (activePublic != null) {
            criteria.andEqualTo("activePublic", activePublic);
        }
        /*按照活动状态*/
        Integer status = manageActivityQuery.getStatus();
        if (status != null) {
            criteria.andEqualTo("status", status);
        }

        /*排序查询*/
        if (manageActivityQuery.getOrderColumn() != null && StringUtils.isNotBlank(manageActivityQuery.getOrderColumn())) {
            if (manageActivityQuery.getOrderBy() != null
                    && manageActivityQuery.getOrderBy().equalsIgnoreCase("asc")) {
                example.orderBy(manageActivityQuery.getOrderColumn()).asc();
            } else {
                example.orderBy(manageActivityQuery.getOrderColumn()).desc();
            }
        }


        PageHelper.startPage(manageActivityQuery.getPageNum(), manageActivityQuery.getPageSize());
        List<WeixinVoteBase> weixinVoteBaseListByExample = null;
        try {
            weixinVoteBaseListByExample = weixinVoteBaseService.getWeixinVoteBaseListByExample(example);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            throw new MyDefinitionException(e.getMessage());
        }
        // 如果这里需要返回VO，那么这里一定先把查询值放进去，让分页信息存储成功。然后再setList加入VO信息
        PageInfo pageInfo = null;
        try {
            pageInfo = new PageInfo(weixinVoteBaseListByExample);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户信息分页查询结果处理错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("用户信息分页查询结果处理错误");
        }

        /*封装需要返回的数据*/
        List<ActivityManagerDTO> activityManagerDTOS = new ArrayList<>();
        /*对查询出来的数据做分析*/
        /*PageInfo<ActivityManagerDTO> activityManagerDTOPageInfo = new PageInfo<>();*/
        List<WeixinVoteBase> WeixinVoteBaseList = pageInfo.getList();
        if (WeixinVoteBaseList != null) {
            for (WeixinVoteBase weixinVoteBase : WeixinVoteBaseList) {
                /*活动信息*/
                ActivityManagerDTO activityManagerDTO = MyEntityUtil.entity2VM(weixinVoteBase, ActivityManagerDTO.class);
                activityManagerDTO.setActiveId(weixinVoteBase.getId());
                activityManagerDTO.setVoteWorkCountNum(weixinVoteWorkService.getCountWorkByVoteBaseId(activityManagerDTO.getActiveId()));
                /*公司信息*/
                WeixinVoteOrganisers weixinVoteConfByVoteWorkId = weixinVoteOrganisersService.getWeixinVoteConfByVoteWorkId(weixinVoteBase.getId());
                if (weixinVoteConfByVoteWorkId != null) {
                    activityManagerDTO.setOrganisersId(weixinVoteConfByVoteWorkId.getId());
                    activityManagerDTO.setOrganisersName(weixinVoteConfByVoteWorkId.getName());
                }

                /*用户信息*/
                WeixinVoteUser userById = weixinVoteUserService.getUserById(weixinVoteBase.getCreateSysUserId());
                if (userById != null) {
                    activityManagerDTO.setVoteUserId(userById.getId());
                    activityManagerDTO.setVoteUserNickName(userById.getNickName());
                }
                activityManagerDTOS.add(activityManagerDTO);
            }
            pageInfo.setList(activityManagerDTOS);
        }
        return pageInfo;
    }
}
