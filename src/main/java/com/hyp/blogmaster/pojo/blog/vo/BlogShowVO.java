package com.hyp.blogmaster.pojo.blog.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 15:33
 * @Description: TODO
 */
@Data
public class BlogShowVO {

    private Integer id;
    private Integer createUserId;
    private String journalCoverImg;
    private String title;
    private String journalContent;
    private String explainWord;
    private Integer commentNum;
    private Integer viewNum;
    private String[] journalClassify;
    private Date createTime;
    /**
     * 创建距今多久
     */
    private String dateDiff;


    /**
     * 创建人信息
     */
    private String avatarImg;
    private String name;

    /**
     * 日志类型描述
     */
    private String blogTypeMsg;


}
