package com.hyp.blogmaster.pojo.dto.mail;

import lombok.Data;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/12 21:56
 * @Description: TODO
 */
@Data
public class ReceiveMailForReplyAppointDTO {

    private Integer receiveEmailId;

    private String receiveEmailFrom;

    private String receiveEmailFromName;

    private String receiveEmailTitle;

    private String receiveEmailContent;

}
