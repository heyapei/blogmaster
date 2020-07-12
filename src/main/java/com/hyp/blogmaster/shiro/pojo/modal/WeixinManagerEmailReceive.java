package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "weixin_manager_email_receive")
public class WeixinManagerEmailReceive {
    /**
     * 实例化
     *
     * @return
     */
    public static WeixinManagerEmailReceive init() {
        WeixinManagerEmailReceive weixinManagerEmailReceive = new WeixinManagerEmailReceive();
        weixinManagerEmailReceive.setReceiveEmailId("");
        weixinManagerEmailReceive.setReceiveEmailTitle("");
        weixinManagerEmailReceive.setReceiveEmailFrom("");
        weixinManagerEmailReceive.setReceiveEmailGetDate(new Date());
        weixinManagerEmailReceive.setReceiveEmailDate("");
        weixinManagerEmailReceive.setReceiveEmailHasReaded(0);
        weixinManagerEmailReceive.setReceiveEmailPriority(0);
        weixinManagerEmailReceive.setReceiveEmailNeedReturn(0);
        weixinManagerEmailReceive.setReceiveEmailSize(0L);
        weixinManagerEmailReceive.setReceiveEmailHasAttach(0);
        weixinManagerEmailReceive.setReceiveEmailAttachUrl("");
        weixinManagerEmailReceive.setReceiveEmailFromName("");
        weixinManagerEmailReceive.setReceiveEmailHasDelete(0);
        weixinManagerEmailReceive.setReceiveEmailRemark("");
        weixinManagerEmailReceive.setReceiveEmailStatus(0);
        weixinManagerEmailReceive.setReceiveEmailExt1("");
        weixinManagerEmailReceive.setReceiveEmailExt2("");
        weixinManagerEmailReceive.setReceiveEmailExt3("");
        weixinManagerEmailReceive.setReceiveEmailExt4("");
        weixinManagerEmailReceive.setReceiveEmailExt5("");
        weixinManagerEmailReceive.setReceiveEmailContent("");
        return weixinManagerEmailReceive;

    }

    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 邮件主键
     */
    @Column(name = "receive_email_id")
    private String receiveEmailId;

    /**
     * 主题
     */
    @Column(name = "receive_email_title")
    private String receiveEmailTitle;

    /**
     * 收件人
     */
    @Column(name = "receive_email_from")
    private String receiveEmailFrom;

    /**
     * 拉取回来的时间
     */
    @Column(name = "receive_email_get_date")
    private Date receiveEmailGetDate;

    /**
     * 发送时间 用户发送邮件的时间 也是第三方邮箱得到这个邮件的时间
     */
    @Column(name = "receive_email_date")
    private String receiveEmailDate;

    /**
     * 是否已读 0未读1已读 当前没用
     */
    @Column(name = "receive_email_has_readed")
    private Integer receiveEmailHasReaded;

    /**
     * 邮件优先级 0 普通 当前没有
     */
    @Column(name = "receive_email_priority")
    private Integer receiveEmailPriority;

    /**
     * 是否需要回执
     */
    @Column(name = "receive_email_need_return")
    private Integer receiveEmailNeedReturn;

    /**
     * 邮件大小
     */
    @Column(name = "receive_email_size")
    private Long receiveEmailSize;

    /**
     * 是否包含附件 0不含 1包含
     */
    @Column(name = "receive_email_has_attach")
    private Integer receiveEmailHasAttach;

    /**
     * 附件地址
     */
    @Column(name = "receive_email_attach_url")
    private String receiveEmailAttachUrl;

    /**
     * 发送邮件用户的姓名
     */
    @Column(name = "receive_email_from_name")
    private String receiveEmailFromName;

    /**
     * 是否从邮件服务器中删除0未删除1已删除
     */
    @Column(name = "receive_email_has_delete")
    private Integer receiveEmailHasDelete;

    /**
     * 邮件的备注 系统使用
     */
    @Column(name = "receive_email_remark")
    private String receiveEmailRemark;

    /**
     * 接收回来的邮件的状态 0默认状态，1已回复，
     */
    @Column(name = "receive_email_status")
    private Integer receiveEmailStatus;

    /**
     * 备用字段
     */
    @Column(name = "receive_email_ext1")
    private String receiveEmailExt1;

    /**
     * 备用字段
     */
    @Column(name = "receive_email_ext2")
    private String receiveEmailExt2;

    /**
     * 备用字段
     */
    @Column(name = "receive_email_ext3")
    private String receiveEmailExt3;

    /**
     * 备用字段
     */
    @Column(name = "receive_email_ext4")
    private String receiveEmailExt4;

    /**
     * 备用字段
     */
    @Column(name = "receive_email_ext5")
    private String receiveEmailExt5;

    /**
     * 邮件内容
     */
    @Column(name = "receive_email_content")
    private String receiveEmailContent;

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
     * 获取邮件主键
     *
     * @return receive_email_id - 邮件主键
     */
    public String getReceiveEmailId() {
        return receiveEmailId;
    }

    /**
     * 设置邮件主键
     *
     * @param receiveEmailId 邮件主键
     */
    public void setReceiveEmailId(String receiveEmailId) {
        this.receiveEmailId = receiveEmailId;
    }

    /**
     * 获取主题
     *
     * @return receive_email_title - 主题
     */
    public String getReceiveEmailTitle() {
        return receiveEmailTitle;
    }

    /**
     * 设置主题
     *
     * @param receiveEmailTitle 主题
     */
    public void setReceiveEmailTitle(String receiveEmailTitle) {
        this.receiveEmailTitle = receiveEmailTitle;
    }

    /**
     * 获取收件人
     *
     * @return receive_email_from - 收件人
     */
    public String getReceiveEmailFrom() {
        return receiveEmailFrom;
    }

    /**
     * 设置收件人
     *
     * @param receiveEmailFrom 收件人
     */
    public void setReceiveEmailFrom(String receiveEmailFrom) {
        this.receiveEmailFrom = receiveEmailFrom;
    }

    /**
     * 获取拉取回来的时间
     *
     * @return receive_email_get_date - 拉取回来的时间
     */
    public Date getReceiveEmailGetDate() {
        return receiveEmailGetDate;
    }

    /**
     * 设置拉取回来的时间
     *
     * @param receiveEmailGetDate 拉取回来的时间
     */
    public void setReceiveEmailGetDate(Date receiveEmailGetDate) {
        this.receiveEmailGetDate = receiveEmailGetDate;
    }

    /**
     * 获取发送时间 用户发送邮件的时间 也是第三方邮箱得到这个邮件的时间
     *
     * @return receive_email_date - 发送时间 用户发送邮件的时间 也是第三方邮箱得到这个邮件的时间
     */
    public String getReceiveEmailDate() {
        return receiveEmailDate;
    }

    /**
     * 设置发送时间 用户发送邮件的时间 也是第三方邮箱得到这个邮件的时间
     *
     * @param receiveEmailDate 发送时间 用户发送邮件的时间 也是第三方邮箱得到这个邮件的时间
     */
    public void setReceiveEmailDate(String receiveEmailDate) {
        this.receiveEmailDate = receiveEmailDate;
    }

    /**
     * 获取是否已读 0未读1已读 当前没用
     *
     * @return receive_email_has_readed - 是否已读 0未读1已读 当前没用
     */
    public Integer getReceiveEmailHasReaded() {
        return receiveEmailHasReaded;
    }

    /**
     * 设置是否已读 0未读1已读 当前没用
     *
     * @param receiveEmailHasReaded 是否已读 0未读1已读 当前没用
     */
    public void setReceiveEmailHasReaded(Integer receiveEmailHasReaded) {
        this.receiveEmailHasReaded = receiveEmailHasReaded;
    }

    /**
     * 获取邮件优先级 0 普通 当前没有
     *
     * @return receive_email_priority - 邮件优先级 0 普通 当前没有
     */
    public Integer getReceiveEmailPriority() {
        return receiveEmailPriority;
    }

    /**
     * 设置邮件优先级 0 普通 当前没有
     *
     * @param receiveEmailPriority 邮件优先级 0 普通 当前没有
     */
    public void setReceiveEmailPriority(Integer receiveEmailPriority) {
        this.receiveEmailPriority = receiveEmailPriority;
    }

    /**
     * 获取是否需要回执
     *
     * @return receive_email_need_return - 是否需要回执
     */
    public Integer getReceiveEmailNeedReturn() {
        return receiveEmailNeedReturn;
    }

    /**
     * 设置是否需要回执
     *
     * @param receiveEmailNeedReturn 是否需要回执
     */
    public void setReceiveEmailNeedReturn(Integer receiveEmailNeedReturn) {
        this.receiveEmailNeedReturn = receiveEmailNeedReturn;
    }

    /**
     * 获取邮件大小
     *
     * @return receive_email_size - 邮件大小
     */
    public Long getReceiveEmailSize() {
        return receiveEmailSize;
    }

    /**
     * 设置邮件大小
     *
     * @param receiveEmailSize 邮件大小
     */
    public void setReceiveEmailSize(Long receiveEmailSize) {
        this.receiveEmailSize = receiveEmailSize;
    }

    /**
     * 获取是否包含附件 0不含 1包含
     *
     * @return receive_email_has_attach - 是否包含附件 0不含 1包含
     */
    public Integer getReceiveEmailHasAttach() {
        return receiveEmailHasAttach;
    }

    /**
     * 设置是否包含附件 0不含 1包含
     *
     * @param receiveEmailHasAttach 是否包含附件 0不含 1包含
     */
    public void setReceiveEmailHasAttach(Integer receiveEmailHasAttach) {
        this.receiveEmailHasAttach = receiveEmailHasAttach;
    }

    /**
     * 获取附件地址
     *
     * @return receive_email_attach_url - 附件地址
     */
    public String getReceiveEmailAttachUrl() {
        return receiveEmailAttachUrl;
    }

    /**
     * 设置附件地址
     *
     * @param receiveEmailAttachUrl 附件地址
     */
    public void setReceiveEmailAttachUrl(String receiveEmailAttachUrl) {
        this.receiveEmailAttachUrl = receiveEmailAttachUrl;
    }

    /**
     * 获取发送邮件用户的姓名
     *
     * @return receive_email_from_name - 发送邮件用户的姓名
     */
    public String getReceiveEmailFromName() {
        return receiveEmailFromName;
    }

    /**
     * 设置发送邮件用户的姓名
     *
     * @param receiveEmailFromName 发送邮件用户的姓名
     */
    public void setReceiveEmailFromName(String receiveEmailFromName) {
        this.receiveEmailFromName = receiveEmailFromName;
    }

    /**
     * 获取是否从邮件服务器中删除0未删除1已删除
     *
     * @return receive_email_has_delete - 是否从邮件服务器中删除0未删除1已删除
     */
    public Integer getReceiveEmailHasDelete() {
        return receiveEmailHasDelete;
    }


    public enum ReceiveEmailHasDeleteEnum {

        NO_DELETE(0, "未从服务器中删除"),
        HAS_DELETE(1, "从服务器已删除");

        /**
         * 状态码
         */
        private Integer code;
        /**
         * 状态描述
         */
        private String msg;

        ReceiveEmailHasDeleteEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "ReceiveEmailHasDeleteEnum{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
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
     * 设置是否从邮件服务器中删除0未删除1已删除
     *
     * @param receiveEmailHasDelete 是否从邮件服务器中删除0未删除1已删除
     */
    public void setReceiveEmailHasDelete(Integer receiveEmailHasDelete) {
        this.receiveEmailHasDelete = receiveEmailHasDelete;
    }

    /**
     * 获取邮件的备注 系统使用
     *
     * @return receive_email_remark - 邮件的备注 系统使用
     */
    public String getReceiveEmailRemark() {
        return receiveEmailRemark;
    }

    /**
     * 设置邮件的备注 系统使用
     *
     * @param receiveEmailRemark 邮件的备注 系统使用
     */
    public void setReceiveEmailRemark(String receiveEmailRemark) {
        this.receiveEmailRemark = receiveEmailRemark;
    }

    /**
     * 获取接收回来的邮件的状态 0默认状态，1已回复，
     *
     * @return receive_email_status - 接收回来的邮件的状态 0默认状态，1已回复，
     */
    public Integer getReceiveEmailStatus() {
        return receiveEmailStatus;
    }

    /**
     * 设置接收回来的邮件的状态 0默认状态，1已回复，
     *
     * @param receiveEmailStatus 接收回来的邮件的状态 0默认状态，1已回复，
     */
    public void setReceiveEmailStatus(Integer receiveEmailStatus) {
        this.receiveEmailStatus = receiveEmailStatus;
    }

    /**
     * 获取备用字段
     *
     * @return receive_email_ext1 - 备用字段
     */
    public String getReceiveEmailExt1() {
        return receiveEmailExt1;
    }

    /**
     * 设置备用字段
     *
     * @param receiveEmailExt1 备用字段
     */
    public void setReceiveEmailExt1(String receiveEmailExt1) {
        this.receiveEmailExt1 = receiveEmailExt1;
    }

    /**
     * 获取备用字段
     *
     * @return receive_email_ext2 - 备用字段
     */
    public String getReceiveEmailExt2() {
        return receiveEmailExt2;
    }

    /**
     * 设置备用字段
     *
     * @param receiveEmailExt2 备用字段
     */
    public void setReceiveEmailExt2(String receiveEmailExt2) {
        this.receiveEmailExt2 = receiveEmailExt2;
    }

    /**
     * 获取备用字段
     *
     * @return receive_email_ext3 - 备用字段
     */
    public String getReceiveEmailExt3() {
        return receiveEmailExt3;
    }

    /**
     * 设置备用字段
     *
     * @param receiveEmailExt3 备用字段
     */
    public void setReceiveEmailExt3(String receiveEmailExt3) {
        this.receiveEmailExt3 = receiveEmailExt3;
    }

    /**
     * 获取备用字段
     *
     * @return receive_email_ext4 - 备用字段
     */
    public String getReceiveEmailExt4() {
        return receiveEmailExt4;
    }

    /**
     * 设置备用字段
     *
     * @param receiveEmailExt4 备用字段
     */
    public void setReceiveEmailExt4(String receiveEmailExt4) {
        this.receiveEmailExt4 = receiveEmailExt4;
    }

    /**
     * 获取备用字段
     *
     * @return receive_email_ext5 - 备用字段
     */
    public String getReceiveEmailExt5() {
        return receiveEmailExt5;
    }

    /**
     * 设置备用字段
     *
     * @param receiveEmailExt5 备用字段
     */
    public void setReceiveEmailExt5(String receiveEmailExt5) {
        this.receiveEmailExt5 = receiveEmailExt5;
    }

    /**
     * 获取邮件内容
     *
     * @return receive_email_content - 邮件内容
     */
    public String getReceiveEmailContent() {
        return receiveEmailContent;
    }

    /**
     * 设置邮件内容
     *
     * @param receiveEmailContent 邮件内容
     */
    public void setReceiveEmailContent(String receiveEmailContent) {
        this.receiveEmailContent = receiveEmailContent;
    }
}