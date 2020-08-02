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
public class BlogListVO {

    private Integer id;
    private Integer createUserId;
    private String journalCoverImg;
    private String title;
    private String explainWord;
    private Integer commentNum;
    private Integer viewNum;
    private Date createTime;
    private Date updateTime;
    private Integer journalType;
    private String journalTypeMsg;
    private Integer showOrder;
    private String journalContent;
    private String journalClassify;

    /**创建人信息*/
    private String avatarImg;
    private String name;


}
