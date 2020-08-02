package com.hyp.blogmaster.pojo.blog.model;
import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Journal {


    public static Journal init() {
        Journal journal = new Journal();
        journal.setCreateUserId(0);
        journal.setJournalType(0);
        journal.setJournalCoverImg("");
        journal.setTitle("");
        journal.setExplainWord("");
        journal.setJournalClassify("");
        journal.setViewNum(0);
        journal.setCommentNum(0);
        journal.setPassWord("");
        journal.setAccessRole("");
        journal.setStatus(0);
        journal.setShowOrder(0);
        journal.setCreateTime(new Date());
        journal.setUpdateTime(new Date());
        journal.setExt1("");
        journal.setExt2("");
        journal.setExt3("");
        journal.setExt4("");
        journal.setExt5("");
        journal.setJournalContent("");
        return journal;
    }
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