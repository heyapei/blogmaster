package com.hyp.blogmaster.controller.mail;


import com.hyp.blogmaster.pojo.dto.mail.MailDTO;
import com.hyp.blogmaster.pojo.vo.result.MyError;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.shiro.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/5/27 22:38
 * @Description: TODO
 */

@Controller
@RequestMapping("mail")
@Slf4j
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping("/sendTextMail")
    @ResponseBody
    public MyResultVO sendTextMail(MailDTO mailDO) {
        try {
            mailService.sendTextMail(mailDO);
        } catch (Exception e) {
            log.info("发送文本邮件失败：{}，错误信息:{}", mailDO.toString(), e.toString());
            return MyResultVO.buildResult(MyResultVO.Status.UNAUTHORIZED);
        }
        log.info("发送文本邮件成功：{}", mailDO.toString());
        return MyResultVO.buildResult(MyResultVO.Status.OK);
    }

    @RequestMapping("/sendHtmlMail")
    @ResponseBody
    public MyResultVO sendHtmlMail(MailDTO mailDO) {
        Map<String, Object> map = new HashMap<>(16);
        try {
            map.put("附件名", "附件的绝对路径");
            mailDO.setAttachment(map);
            mailService.sendHtmlMail(mailDO, false);
        } catch (Exception e) {
            log.info("发送富文本邮件失败：{}，附件参数：{}，错误信息：{}", mailDO.toString(), map, e.toString());
            return MyResultVO.buildResult(MyResultVO.Status.UNAUTHORIZED);
        }
        log.info("发送富文本邮件成功：{}，附件参数：{}", mailDO.toString(), map);
        return MyResultVO.buildResult(MyResultVO.Status.OK);
    }


    @RequestMapping("/sendTemplateMail")
    public String sendTemplateMail(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String message, ModelMap map) {
        MailDTO mailDO = new MailDTO();
        mailDO.setEmail(email);
        mailDO.setTitle("趣互动反馈通知");
        mailDO.setContent(message);
        Map<String, Object> map1 = new HashMap<>(16);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map1.put("time", format.format(new Date()));
            map1.put("userName", name);
            mailDO.setAttachment(map1);
            mailService.sendTemplateMail(mailDO);
        } catch (Exception e) {
            log.info("发送网页模板邮件失败：{}，用户名：{}，用户发送的信息：{}，网页模板参数：{}，错误信息：{}", name, message, mailDO.toString(), map, e.toString());
            //return MyResultVO.buildResult(MyResultVO.Status.UNAUTHORIZED);
            MyError myError = new MyError(500, "发送失败了", "请检查您的邮箱地址是否正确，我们需要它用于及时回复您");
            map.put("myError", myError);
            return "error/error";
        }
        log.info("发送网页模板邮件成功：{}，用户名：{}，用户主题：{}，用户发送的信息：{}，网页模板参数：{}", name, subject, message, mailDO.toString(), map);
        map.put("message", true);
        return "index/index";
    }


}
