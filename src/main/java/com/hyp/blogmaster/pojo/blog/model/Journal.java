package com.hyp.blogmaster.pojo.blog.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Journal {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 创建人 这里的userID对应weixin库中的信息
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 日志类型 0 何亚培 1去投票
     */
    @Column(name = "journal_type")
    private Integer journalType;

    /**
     * 封面图
     */
    @Column(name = "journal_cover_img")
    private String journalCoverImg;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    @Column(name = "explain_word")
    private String explainWord;

    /**
     * 日志分类
     */
    @Column(name = "journal_classify")
    private String journalClassify;

    /**
     * 查看人数
     */
    @Column(name = "view_num")
    private Integer viewNum;

    /**
     * 评论人数
     */
    @Column(name = "comment_num")
    private Integer commentNum;

    /**
     * 密码
     */
    @Column(name = "pass_word")
    private String passWord;

    /**
     * 可查看权限
     */
    @Column(name = "access_role")
    private String accessRole;

    /**
     * 状态值 0 默认 显示 1不显示
     */
    private Integer status;

    /**
     * 排序
     */
    @Column(name = "show_order")
    private Integer showOrder;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备用字段
     */
    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String ext5;

    /**
     * 日志内容
     */
    @Column(name = "journal_content")
    private String journalContent;


}