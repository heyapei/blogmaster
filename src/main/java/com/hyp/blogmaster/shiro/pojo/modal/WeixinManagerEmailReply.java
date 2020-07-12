package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "weixin_manager_email_reply")
public class WeixinManagerEmailReply {

    /**
     * 实例化
     *
     * @return
     */
    public static WeixinManagerEmailReply init() {
        WeixinManagerEmailReply weixinManagerEmailReply = new WeixinManagerEmailReply();
        weixinManagerEmailReply.setReplyEmailFrom("");
        weixinManagerEmailReply.setReplyEmailAdminUserId(0);
        weixinManagerEmailReply.setReplyEmailTitle("");
        weixinManagerEmailReply.setReplyEmailCreateDate(new Date());
        weixinManagerEmailReply.setReplyEmailUpdateDate(new Date());
        weixinManagerEmailReply.setReplyEmailSendDate(new Date());
        weixinManagerEmailReply.setReplyEmailCrontabStartTime(new Date());
        weixinManagerEmailReply.setReplyEmailCrontabEndTime(new Date());
        weixinManagerEmailReply.setReplyEmailFromName("");
        weixinManagerEmailReply.setReplyEmailStatus(0);
        weixinManagerEmailReply.setReplyEmailSendStatus(0);
        weixinManagerEmailReply.setReplyEmailRemark("");
        weixinManagerEmailReply.setReplyEmailExt1("");
        weixinManagerEmailReply.setReplyEmailExt2("");
        weixinManagerEmailReply.setReplyEmailExt3("");
        weixinManagerEmailReply.setReplyEmailExt4("");
        weixinManagerEmailReply.setReplyEmailExt5("");
        weixinManagerEmailReply.setReplyEmailHasAttach(0);
        weixinManagerEmailReply.setReplyEmailAttachUrl("");
        weixinManagerEmailReply.setReplyEmailPriority(0);
        weixinManagerEmailReply.setReplyEmailContent("");
        return weixinManagerEmailReply;
    }


    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 回复邮件使用的邮箱
     */
    @Column(name = "reply_email_from")
    private String replyEmailFrom;

    /**
     * 回复邮件的管理员ID
     */
    @Column(name = "reply_email_admin_user_id")
    private Integer replyEmailAdminUserId;

    /**
     * 回复邮件的主题
     */
    @Column(name = "reply_email_title")
    private String replyEmailTitle;

    /**
     * 回复邮件的创建时间
     */
    @Column(name = "reply_email_create_date")
    private Date replyEmailCreateDate;

    /**
     * 回复邮件的更新时间
     */
    @Column(name = "reply_email_update_date")
    private Date replyEmailUpdateDate;

    /**
     * 回复邮件的发送时间
     */
    @Column(name = "reply_email_send_date")
    private Date replyEmailSendDate;

    /**
     * 定时发送开始时间
     */
    @Column(name = "reply_email_crontab_start_time")
    private Date replyEmailCrontabStartTime;

    /**
     * 定时发送的结束时间
     */
    @Column(name = "reply_email_crontab_end_time")
    private Date replyEmailCrontabEndTime;

    /**
     * 回复邮件使用的姓名
     */
    @Column(name = "reply_email_from_name")
    private String replyEmailFromName;

    /**
     * 回复邮件的状态 0默认 1取消发送
     */
    @Column(name = "reply_email_status")
    private Integer replyEmailStatus;

    /**
     * 回复邮件状态
     */
    public enum ReplyEmailStatusEnum {

        UN_SEND(0, "未发送"),
        SENT(1, "已发送");


        private Integer code;
        private String desc;

        @Override
        public String toString() {
            return "SendStatusEnum{" +
                    "code=" + code +
                    ", desc='" + desc + '\'' +
                    '}';
        }

        ReplyEmailStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 发送类型
     */
    public enum ReplyEmailSendStatusEnum {

        IMMEDIATELY_SEND(0, "即刻发送"),
        CRONTAB_SEND(1, "定时发送");
        private Integer code;
        private String msg;

        ReplyEmailSendStatusEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "SendTypeEnum{" +
                    "code=" + code +
                    ", desc='" + msg + '\'' +
                    '}';
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     * 回复邮件发送状态 0即刻发送 1定时发送
     */
    @Column(name = "reply_email_send_status")
    private Integer replyEmailSendStatus;

    /**
     * 回复邮件的备注
     */
    @Column(name = "reply_email_remark")
    private String replyEmailRemark;

    /**
     * 发送者备用字段
     */
    @Column(name = "reply_email_ext1")
    private String replyEmailExt1;

    /**
     * 发送者备用字段
     */
    @Column(name = "reply_email_ext2")
    private String replyEmailExt2;

    /**
     * 发送者备用字段
     */
    @Column(name = "reply_email_ext3")
    private String replyEmailExt3;

    /**
     * 发送者备用字段
     */
    @Column(name = "reply_email_ext4")
    private String replyEmailExt4;

    /**
     * 发送者备用字段
     */
    @Column(name = "reply_email_ext5")
    private String replyEmailExt5;

    /**
     * 是否包含附件 0不含 1包含
     */
    @Column(name = "reply_email_has_attach")
    private Integer replyEmailHasAttach;

    /**
     * 附件地址
     */
    @Column(name = "reply_email_attach_url")
    private String replyEmailAttachUrl;

    /**
     * 邮件优先级 0 普通 当前没有
     */
    @Column(name = "reply_email_priority")
    private Integer replyEmailPriority;

    /**
     * 回复邮件的内容
     */
    @Column(name = "reply_email_content")
    private String replyEmailContent;
    /**
     * 邮件接收方地址
     */
    @Column(name = "reply_email_send_to")
    private String replyEmailSendTo;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取回复邮件使用的邮箱
     *
     * @return reply_email_from - 回复邮件使用的邮箱
     */
    public String getReplyEmailFrom() {
        return replyEmailFrom;
    }

    /**
     * 设置回复邮件使用的邮箱
     *
     * @param replyEmailFrom 回复邮件使用的邮箱
     */
    public void setReplyEmailFrom(String replyEmailFrom) {
        this.replyEmailFrom = replyEmailFrom;
    }

    /**
     * 获取回复邮件的管理员ID
     *
     * @return reply_email_admin_user_id - 回复邮件的管理员ID
     */
    public Integer getReplyEmailAdminUserId() {
        return replyEmailAdminUserId;
    }

    /**
     * 设置回复邮件的管理员ID
     *
     * @param replyEmailAdminUserId 回复邮件的管理员ID
     */
    public void setReplyEmailAdminUserId(Integer replyEmailAdminUserId) {
        this.replyEmailAdminUserId = replyEmailAdminUserId;
    }

    /**
     * 获取回复邮件的主题
     *
     * @return reply_email_title - 回复邮件的主题
     */
    public String getReplyEmailTitle() {
        return replyEmailTitle;
    }

    /**
     * 设置回复邮件的主题
     *
     * @param replyEmailTitle 回复邮件的主题
     */
    public void setReplyEmailTitle(String replyEmailTitle) {
        this.replyEmailTitle = replyEmailTitle;
    }

    /**
     * 获取回复邮件的创建时间
     *
     * @return reply_email_create_date - 回复邮件的创建时间
     */
    public Date getReplyEmailCreateDate() {
        return replyEmailCreateDate;
    }

    /**
     * 设置回复邮件的创建时间
     *
     * @param replyEmailCreateDate 回复邮件的创建时间
     */
    public void setReplyEmailCreateDate(Date replyEmailCreateDate) {
        this.replyEmailCreateDate = replyEmailCreateDate;
    }

    /**
     * 获取回复邮件的更新时间
     *
     * @return reply_email_update_date - 回复邮件的更新时间
     */
    public Date getReplyEmailUpdateDate() {
        return replyEmailUpdateDate;
    }

    /**
     * 设置回复邮件的更新时间
     *
     * @param replyEmailUpdateDate 回复邮件的更新时间
     */
    public void setReplyEmailUpdateDate(Date replyEmailUpdateDate) {
        this.replyEmailUpdateDate = replyEmailUpdateDate;
    }

    /**
     * 获取回复邮件的发送时间
     *
     * @return reply_email_send_date - 回复邮件的发送时间
     */
    public Date getReplyEmailSendDate() {
        return replyEmailSendDate;
    }

    /**
     * 设置回复邮件的发送时间
     *
     * @param replyEmailSendDate 回复邮件的发送时间
     */
    public void setReplyEmailSendDate(Date replyEmailSendDate) {
        this.replyEmailSendDate = replyEmailSendDate;
    }

    /**
     * 获取定时发送开始时间
     *
     * @return reply_email_crontab_start_time - 定时发送开始时间
     */
    public Date getReplyEmailCrontabStartTime() {
        return replyEmailCrontabStartTime;
    }

    /**
     * 设置定时发送开始时间
     *
     * @param replyEmailCrontabStartTime 定时发送开始时间
     */
    public void setReplyEmailCrontabStartTime(Date replyEmailCrontabStartTime) {
        this.replyEmailCrontabStartTime = replyEmailCrontabStartTime;
    }

    /**
     * 获取定时发送的结束时间
     *
     * @return reply_email_crontab_end_time - 定时发送的结束时间
     */
    public Date getReplyEmailCrontabEndTime() {
        return replyEmailCrontabEndTime;
    }

    /**
     * 设置定时发送的结束时间
     *
     * @param replyEmailCrontabEndTime 定时发送的结束时间
     */
    public void setReplyEmailCrontabEndTime(Date replyEmailCrontabEndTime) {
        this.replyEmailCrontabEndTime = replyEmailCrontabEndTime;
    }

    /**
     * 获取回复邮件使用的姓名
     *
     * @return reply_email_from_name - 回复邮件使用的姓名
     */
    public String getReplyEmailFromName() {
        return replyEmailFromName;
    }

    /**
     * 设置回复邮件使用的姓名
     *
     * @param replyEmailFromName 回复邮件使用的姓名
     */
    public void setReplyEmailFromName(String replyEmailFromName) {
        this.replyEmailFromName = replyEmailFromName;
    }

    /**
     * 获取回复邮件的状态 0默认 1取消发送
     *
     * @return reply_email_status - 回复邮件的状态 0默认 1取消发送
     */
    public Integer getReplyEmailStatus() {
        return replyEmailStatus;
    }

    /**
     * 设置回复邮件的状态 0默认 1取消发送
     *
     * @param replyEmailStatus 回复邮件的状态 0默认 1取消发送
     */
    public void setReplyEmailStatus(Integer replyEmailStatus) {
        this.replyEmailStatus = replyEmailStatus;
    }

    /**
     * 获取回复邮件发送状态 0未发送 1已发送
     *
     * @return reply_email_send_status - 回复邮件发送状态 0未发送 1已发送
     */
    public Integer getReplyEmailSendStatus() {
        return replyEmailSendStatus;
    }

    /**
     * 设置回复邮件发送状态 0未发送 1已发送
     *
     * @param replyEmailSendStatus 回复邮件发送状态 0未发送 1已发送
     */
    public void setReplyEmailSendStatus(Integer replyEmailSendStatus) {
        this.replyEmailSendStatus = replyEmailSendStatus;
    }

    /**
     * 获取回复邮件的备注
     *
     * @return reply_email_remark - 回复邮件的备注
     */
    public String getReplyEmailRemark() {
        return replyEmailRemark;
    }

    /**
     * 设置回复邮件的备注
     *
     * @param replyEmailRemark 回复邮件的备注
     */
    public void setReplyEmailRemark(String replyEmailRemark) {
        this.replyEmailRemark = replyEmailRemark;
    }

    /**
     * 获取发送者备用字段
     *
     * @return reply_email_ext1 - 发送者备用字段
     */
    public String getReplyEmailExt1() {
        return replyEmailExt1;
    }

    /**
     * 设置发送者备用字段
     *
     * @param replyEmailExt1 发送者备用字段
     */
    public void setReplyEmailExt1(String replyEmailExt1) {
        this.replyEmailExt1 = replyEmailExt1;
    }

    /**
     * 获取发送者备用字段
     *
     * @return reply_email_ext2 - 发送者备用字段
     */
    public String getReplyEmailExt2() {
        return replyEmailExt2;
    }

    /**
     * 设置发送者备用字段
     *
     * @param replyEmailExt2 发送者备用字段
     */
    public void setReplyEmailExt2(String replyEmailExt2) {
        this.replyEmailExt2 = replyEmailExt2;
    }

    /**
     * 获取发送者备用字段
     *
     * @return reply_email_ext3 - 发送者备用字段
     */
    public String getReplyEmailExt3() {
        return replyEmailExt3;
    }

    /**
     * 设置发送者备用字段
     *
     * @param replyEmailExt3 发送者备用字段
     */
    public void setReplyEmailExt3(String replyEmailExt3) {
        this.replyEmailExt3 = replyEmailExt3;
    }

    /**
     * 获取发送者备用字段
     *
     * @return reply_email_ext4 - 发送者备用字段
     */
    public String getReplyEmailExt4() {
        return replyEmailExt4;
    }

    /**
     * 设置发送者备用字段
     *
     * @param replyEmailExt4 发送者备用字段
     */
    public void setReplyEmailExt4(String replyEmailExt4) {
        this.replyEmailExt4 = replyEmailExt4;
    }

    /**
     * 获取发送者备用字段
     *
     * @return reply_email_ext5 - 发送者备用字段
     */
    public String getReplyEmailExt5() {
        return replyEmailExt5;
    }

    /**
     * 设置发送者备用字段
     *
     * @param replyEmailExt5 发送者备用字段
     */
    public void setReplyEmailExt5(String replyEmailExt5) {
        this.replyEmailExt5 = replyEmailExt5;
    }

    /**
     * 获取是否包含附件 0不含 1包含
     *
     * @return reply_email_has_attach - 是否包含附件 0不含 1包含
     */
    public Integer getReplyEmailHasAttach() {
        return replyEmailHasAttach;
    }

    /**
     * 设置是否包含附件 0不含 1包含
     *
     * @param replyEmailHasAttach 是否包含附件 0不含 1包含
     */
    public void setReplyEmailHasAttach(Integer replyEmailHasAttach) {
        this.replyEmailHasAttach = replyEmailHasAttach;
    }

    /**
     * 获取附件地址
     *
     * @return reply_email_attach_url - 附件地址
     */
    public String getReplyEmailAttachUrl() {
        return replyEmailAttachUrl;
    }

    /**
     * 设置附件地址
     *
     * @param replyEmailAttachUrl 附件地址
     */
    public void setReplyEmailAttachUrl(String replyEmailAttachUrl) {
        this.replyEmailAttachUrl = replyEmailAttachUrl;
    }

    /**
     * 获取邮件优先级 0 普通 当前没有
     *
     * @return reply_email_priority - 邮件优先级 0 普通 当前没有
     */
    public Integer getReplyEmailPriority() {
        return replyEmailPriority;
    }

    /**
     * 设置邮件优先级 0 普通 当前没有
     *
     * @param replyEmailPriority 邮件优先级 0 普通 当前没有
     */
    public void setReplyEmailPriority(Integer replyEmailPriority) {
        this.replyEmailPriority = replyEmailPriority;
    }

    /**
     * 获取回复邮件的
     *
     * @return reply_email_content - 回复邮件的
     */
    public String getReplyEmailContent() {
        return replyEmailContent;
    }

    /**
     * 设置回复邮件的
     *
     * @param replyEmailContent 回复邮件的
     */
    public void setReplyEmailContent(String replyEmailContent) {
        this.replyEmailContent = replyEmailContent;
    }
}