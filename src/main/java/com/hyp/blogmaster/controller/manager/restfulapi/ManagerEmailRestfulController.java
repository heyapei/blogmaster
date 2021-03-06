package com.hyp.blogmaster.controller.manager.restfulapi;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.dto.mail.MailDTO;
import com.hyp.blogmaster.pojo.dto.resource.ResourceSimpleDTO;
import com.hyp.blogmaster.pojo.query.ManagerEmailSendQuery;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.service.ResourceService;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmail;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmailReceive;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmailReply;
import com.hyp.blogmaster.shiro.service.MailService;
import com.hyp.blogmaster.shiro.service.WeixinManagerEmailReceiveService;
import com.hyp.blogmaster.shiro.service.WeixinManagerEmailReplyService;
import com.hyp.blogmaster.shiro.service.WeixinManagerEmailService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 11:44
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/admin/manager/email")
@Slf4j
@Api(value = "邮件处理相关页面，需要用户登录")
public class ManagerEmailRestfulController {


    @Autowired
    private ResourceService resourceService;

    @Autowired
    private WeixinManagerEmailService weixinManagerEmailService;

    @Autowired
    private WeixinManagerEmailReplyService weixinManagerEmailReplyService;
    @Autowired
    private WeixinManagerEmailReceiveService weixinManagerEmailReceiveService;

    @Autowired
    private MailService mailService;

    @Value("${spring.mail.username}")
    private String sendFrom;


    @PostMapping("saveManagerEmailSend")
    public MyResultVO saveManagerEmailSend(@Validated ManagerEmailSendQuery managerEmailSendQuery) {
        log.info("参数值：{}", managerEmailSendQuery.toString());
        if (managerEmailSendQuery == null) {
            return MyResultVO.genFailResult("保存的邮件实体不能为空");
        }
        WeixinManagerEmailReply weixinManagerEmailReply = WeixinManagerEmailReply.init();

        if (managerEmailSendQuery.getCrontabSendTimeStart() != null) {
            if (managerEmailSendQuery.getCrontabSendTimeStart().before(new Date())) {
                return MyResultVO.genFailResult("开始时间不能小于当前时间");
            }
            weixinManagerEmailReply.setReplyEmailCrontabStartTime(managerEmailSendQuery.getCrontabSendTimeStart());
        }
        if (managerEmailSendQuery.getCrontabSendTimeEnd() != null) {
            if (!managerEmailSendQuery.getCrontabSendTimeStart().before(managerEmailSendQuery.getCrontabSendTimeEnd())) {
                return MyResultVO.genFailResult("结束时间不能小于开始时间");
            }
            weixinManagerEmailReply.setReplyEmailCrontabEndTime(managerEmailSendQuery.getCrontabSendTimeEnd());
        }
        weixinManagerEmailReply.setReplyEmailSendTo(managerEmailSendQuery.getSendTo());
        weixinManagerEmailReply.setReplyEmailContent(managerEmailSendQuery.getEmailContent());
        weixinManagerEmailReply.setReplyEmailFrom(sendFrom);

        if (managerEmailSendQuery.getSendType().equals(WeixinManagerEmailReply.ReplyEmailSendStatusEnum.IMMEDIATELY_SEND.getCode())) {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setTitle("问题反馈");
            mailDTO.setContent("");
            mailDTO.setEmail(managerEmailSendQuery.getSendTo());
            //mailDTO.setEmail("heyapei@hotmail.com");
            Map<String, Object> map = new HashMap<>(1);
            map.put("emailContent", managerEmailSendQuery.getEmailContent());
            mailDTO.setAttachment(map);
            //log.info("邮件值：" + mailDTO.toString());
            mailService.sendTemplateMailAsync(mailDTO);
            weixinManagerEmailReply.setReplyEmailStatus(WeixinManagerEmailReply.ReplyEmailStatusEnum.SENT.getCode());
            if (managerEmailSendQuery.getReceiveEmailId() != null) {
                WeixinManagerEmailReceive weixinManagerEmailReceiveByPK = weixinManagerEmailReceiveService.getWeixinManagerEmailReceiveByPK(managerEmailSendQuery.getReceiveEmailId());
                if (weixinManagerEmailReceiveByPK != null) {
                    weixinManagerEmailReceiveByPK.setReceiveReplyStatus(WeixinManagerEmailReceive.ReceiveReplyStatusEnum.HAS_REPLY.getCode());
                    weixinManagerEmailReceiveService.updateSelectiveWeixinManagerEmailReceiveByPK(weixinManagerEmailReceiveByPK);
                }
            }
        } else {
            weixinManagerEmailReply.setReplyEmailSendStatus(managerEmailSendQuery.getSendType());
        }
        //log.info("组装参数：{}", weixinManagerEmailReply.toString());

        Integer saveWeixinManagerEmailGetPK = null;
        try {
            saveWeixinManagerEmailGetPK = weixinManagerEmailReplyService.
                    saveWeixinManagerEmailReplyReturnPK(weixinManagerEmailReply);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            return MyResultVO.genFailResult(e.getMessage());
        }

        return MyResultVO.genSuccessResult(saveWeixinManagerEmailGetPK);
    }


    /**
     * 当前这个请求已无效
     * 创建新的需要发送的邮件
     *
     * @param managerEmailSendQuery
     * @return
     */
    @Deprecated
    //@PostMapping("saveManagerEmailSend")
    public MyResultVO DeprecatedsaveManagerEmailSend(@Validated ManagerEmailSendQuery managerEmailSendQuery) {
        log.info("参数值：{}", managerEmailSendQuery.toString());
        if (managerEmailSendQuery == null) {
            return MyResultVO.genFailResult("保存的邮件实体不能为空");
        }

        WeixinManagerEmail weixinManagerEmail = WeixinManagerEmail.init();
        if (managerEmailSendQuery.getCrontabSendTimeStart() != null) {

            if (managerEmailSendQuery.getCrontabSendTimeStart().before(new Date())) {
                return MyResultVO.genFailResult("开始时间不能小于当前时间");
            }

            weixinManagerEmail.setCrontabSendTimeStart(managerEmailSendQuery.getCrontabSendTimeStart());
        }
        if (managerEmailSendQuery.getCrontabSendTimeEnd() != null) {
            if (!managerEmailSendQuery.getCrontabSendTimeStart().before(managerEmailSendQuery.getCrontabSendTimeEnd())) {
                return MyResultVO.genFailResult("结束时间不能小于开始时间");
            }
            weixinManagerEmail.setCrontabSendTimeEnd(managerEmailSendQuery.getCrontabSendTimeEnd());
        }
        weixinManagerEmail.setSendTo(managerEmailSendQuery.getSendTo());
        weixinManagerEmail.setEmailContent(managerEmailSendQuery.getEmailContent());
        weixinManagerEmail.setSendType(managerEmailSendQuery.getSendType());
        weixinManagerEmail.setSendFrom(sendFrom);

        if (managerEmailSendQuery.getSendType().equals(WeixinManagerEmail.SendTypeEnum.IMMEDIATELY_SEND.getCode())) {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setTitle("问题反馈");
            mailDTO.setContent("");
            mailDTO.setEmail(managerEmailSendQuery.getSendTo());
            //mailDTO.setEmail("heyapei@hotmail.com");
            Map<String, Object> map = new HashMap<>(1);
            map.put("emailContent", managerEmailSendQuery.getEmailContent());
            mailDTO.setAttachment(map);
            //log.info("邮件值：" + mailDTO.toString());
            mailService.sendTemplateMailAsync(mailDTO);
            weixinManagerEmail.setSendStatus(WeixinManagerEmail.SendStatusEnum.SENT.getCode());
        }


        //log.info("组装参数：{}", weixinManagerEmail.toString());

        Integer saveWeixinManagerEmailGetPK = null;
        try {
            saveWeixinManagerEmailGetPK = weixinManagerEmailService.
                    saveWeixinManagerEmailGetPK(weixinManagerEmail);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            return MyResultVO.genFailResult(e.getMessage());
        }

        return MyResultVO.genSuccessResult(saveWeixinManagerEmailGetPK);
    }


    /**
     * 邮件用资源上传
     *
     * @param file
     * @return
     */
    @PostMapping("upload")
    public Map<String, String> fileUpload(@RequestParam("file") MultipartFile file) {
        MyResultVO myResultVO = null;
        try {
            myResultVO = resourceService.saveEmailRes(file);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            return null;
        }
        ResourceSimpleDTO data = (ResourceSimpleDTO) myResultVO.getData();
        Map<String, String> map = new HashMap<>(1);
        map.put("url", data.getFileUrl());
        return map;
    }


}
