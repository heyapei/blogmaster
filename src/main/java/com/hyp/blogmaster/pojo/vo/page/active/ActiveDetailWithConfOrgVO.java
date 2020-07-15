package com.hyp.blogmaster.pojo.vo.page.active;

import lombok.Data;

import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/15 21:01
 * @Description: TODO 活动的总的详细信息
 */
@Data
public class ActiveDetailWithConfOrgVO {


    /**
     * 活动id
     */
    private Integer activeId;

    /**
     * 活动所属用户Id
     */
    private Integer activeUserId;

    /**
     * 活动配置Id
     */
    private Integer activeConfId;
    /**
     * 活动主办方Id
     */
    private Integer activeOrgId;

    /**
     * 活动名称
     */
    private String activeName;
    /**
     * 活动描述
     */
    private String activeDesc;

    /**
     * 活动图片 使用;符合进行拼接
     */
    private String activeDescImgS;
    /**
     * 活动奖励
     */
    private String activeReward;
    /**
     * 活动奖励图片 使用;符合进行拼接
     */
    private String activeRewardImgS;
    /**
     * 活动规则
     */
    private String activeRule;

    /**
     * 是否允许用户上传作品 0允许 1不允许
     */
    private Integer activeConfSignUp;

    /**
     * 允许用户上传作品开始时间
     */
    private Date allowUploadStartTime;

    /**
     * 允许用户上传作品结束时间
     */
    private Date allowUploadEndTime;

    /**
     * 活动开始时间
     */
    private Date activeStartTime;

    /**
     * 活动结束时间
     */
    private Date activeEndTime;

    /**
     * 是否公布到首页
     */
    private Integer activePublic;
    /**
     * 当前活动状态
     */
    private Integer activeStatus;
    /**
     * 是否允许重复投票
     */
    private Integer activeConfRepeatVote;

    /**
     * 投票的规则
     */
    private String activeConfVoteType;

    /**
     * 活动分享图
     */
    private String activeConfShareImg;


    /**
     * 是否需要手机号
     */
    private Integer activeConfNeedPhone;

    /**
     * 是否需要手机号
     */
    private Integer activeConfNeedWeixin;

    /**
     * 以下是公司信息
     */
    private String orgName;
    private String orgPhone;
    private String orgLogo;
    private String orgWeixinQrCode;

    /**
     * 排序以及活动的浏览量和投票总量
     */
    private Integer showOrder;
    private Integer viewCount;
    private Integer voteCount;


}
