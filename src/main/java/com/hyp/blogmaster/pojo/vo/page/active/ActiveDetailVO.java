package com.hyp.blogmaster.pojo.vo.page.active;

import lombok.Data;

import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/14 22:57
 * @Description: TODO
 */
@Data
public class ActiveDetailVO {

    /**
     * 活动名称
     */
    private String activeName;
    /**
     * 活动描述
     */
    private String activeDesc;

    /**
     * 活动图片
     */
    private String[] activeDescImgS;
    /**
     * 活动奖励
     */
    private String activeReward;
    /**
     * 活动奖励图片
     */
    private String[] activeRewardImgS;
    /**
     * 活动规则
     */
    private String activeRule;

    /**
     * 是否允许用户上传作品
     */
    private Boolean allowUserUpload;

    /**
     * 允许用户上传作品开始时间
     */
    private Date allowUploadStartTime;

    /**
     * 允许用户上传作品结束时间
     */
    private Date allowUploadEndTime;

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


}
