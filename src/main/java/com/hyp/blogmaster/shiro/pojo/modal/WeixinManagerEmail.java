package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "weixin_manager_email")
public class WeixinManagerEmail {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 发送给谁
     */
    @Column(name = "send_to")
    private String sendTo;

    /**
     * 发送人
     */
    @Column(name = "send_from")
    private String sendFrom;

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
     * 邮件类型 0默认
     */
    @Column(name = "email_type")
    private Integer emailType;

    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 发送定时 开始时间
     */
    @Column(name = "crontab_send_time_start")
    private Date crontabSendTimeStart;

    /**
     * 发送定时 结束时间
     */
    @Column(name = "crontab_send_time_end")
    private Date crontabSendTimeEnd;

    /**
     * 发送类型 0即刻发送 1定时发送
     */
    @Column(name = "send_type")
    private Integer sendType;

    /**
     * 当前邮件状态 0为默认值未发送 1已发送
     */
    @Column(name = "send_status")
    private Integer sendStatus;

    /**
     * 邮件的内容
     */
    @Column(name = "email_content")
    private String emailContent;


    /**
     * 发送类型
     */
    public enum SendTypeEnum {

        IMMEDIATELY_SEND(0, "即刻发送"),
        CRONTAB_SEND(1, "定时发送");
        private Integer code;
        private String desc;

        SendTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "SendTypeEnum{" +
                    "code=" + code +
                    ", desc='" + desc + '\'' +
                    '}';
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
     * 邮件类型 0默认
     */
    public enum EmailTypeEnum {
        OWNER_SEND(0, "我方发送");

        private Integer code;
        private String desc;

        EmailTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }


        @Override
        public String toString() {
            return "EmailTypeEnum{" +
                    "code=" + code +
                    ", desc='" + desc + '\'' +
                    '}';
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
     * 当前邮件状态
     */
    public enum SendStatusEnum {

        UN_SEND(0, "未发送"),
        SENT(0, "已发送");


        private Integer code;
        private String desc;

        @Override
        public String toString() {
            return "SendStatusEnum{" +
                    "code=" + code +
                    ", desc='" + desc + '\'' +
                    '}';
        }

        SendStatusEnum(Integer code, String desc) {
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
     * 创建默认值 不包含ID主键
     *
     * @return 默认实体类
     */
    public static WeixinManagerEmail init() {
        WeixinManagerEmail weixinManagerEmail = new WeixinManagerEmail();
        weixinManagerEmail.setSendTo("");
        weixinManagerEmail.setSendFrom("");
        weixinManagerEmail.setCreateTime(new Date());
        weixinManagerEmail.setUpdateTime(new Date());
        weixinManagerEmail.setEmailType(0);
        weixinManagerEmail.setSendTime(new Date());
        weixinManagerEmail.setCrontabSendTimeStart(new Date());
        weixinManagerEmail.setCrontabSendTimeEnd(new Date());
        weixinManagerEmail.setSendType(0);
        weixinManagerEmail.setEmailContent("");
        weixinManagerEmail.setSendStatus(0);
        return weixinManagerEmail;
    }
}