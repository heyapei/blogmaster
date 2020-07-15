package com.hyp.blogmaster.pojo.dto.manager;

import lombok.Data;

import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 20:33
 * @Description: TODO
 */
@Data
public class ActivityManagerDTO {


    /*用户信息*/
    /**
     * 用户ID
     */
    private Integer voteUserId;
    /**
     * 用户昵称
     */
    private String voteUserNickName;


    /*活动内容*/

    /**
     * 活动ID
     */
    private Integer activeId;

    /**
     * 活动名称
     */
    private String activeName;

    /**
     * 封面图片
     */
    private String activeImg;

    /**
     * 活动开始时间
     */
    private Date activeStartTime;

    /**
     * 活动结束时间
     */
    private Date activeEndTime;

    /**
     * 是否公开到首页 0默认公开 1不公开
     */
    private Integer activePublic;

    /**
     * 活动创建时间
     */
    private Date createTime;

    /**
     * 最后的更新时间
     */
    private Date updateTime;


    /**
     * 0 待审核 1上线  2未开始 3已结束 4未启动
     */
    private Integer status;

    /**
     * 活动下作品总数
     */
    private Integer voteWorkCountNum;

    /**
     * 被浏览总次数
     */
    private Integer viewCountNum;
    /**
     * 被浏览总次数
     */
    private Integer voteCountNum;

    /*公司内容*/
    /**
     * 主键
     */
    private Integer organisersId;
    /**
     * 名称
     */
    private String organisersName;


}
