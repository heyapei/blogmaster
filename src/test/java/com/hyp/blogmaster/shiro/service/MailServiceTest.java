package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.pojo.dto.mail.MailDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 17:35
 * @Description: TODO
 */
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendTemplateMail() {

        MailDTO mailDTO = new MailDTO();


        mailService.sendTemplateMail(null);

    }
}