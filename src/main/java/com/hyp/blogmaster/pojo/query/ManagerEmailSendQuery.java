package com.hyp.blogmaster.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 14:19
 * @Description: TODO
 */
@Data
public class ManagerEmailSendQuery {

    /**
     * 管理员Id
     */
    @NotNull(message = "管理员ID不可以为空")
    private Integer adminUserId;

    /**
     * 邮件接收人
     */
    @NotNull(message = "邮件接收人不可以为空")
    private String sendTo;
    /**
     * 发送类型
     */
    @NotNull(message = "邮件发送类型不可以为空")
    private Integer sendType = 0;

    /**
     * 开始发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date crontabSendTimeStart = new Date();
    /**
     * 结束发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date crontabSendTimeEnd = new Date();
    /**
     * 邮件富文本内容
     */
    @NotNull(message = "邮件内容不可以为空")
    private String emailContent;


}
