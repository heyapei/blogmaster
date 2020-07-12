package com.hyp.blogmaster.controller.manager;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.pojo.dto.mail.ReceiveMailForReplyAppointDTO;
import com.hyp.blogmaster.pojo.query.ManageReceiveEmailQuery;
import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmailReceive;
import com.hyp.blogmaster.shiro.service.WeixinManagerEmailReceiveService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 11:44
 * @Description: TODO
 */
@Controller
@RequestMapping("/admin/manager/email")
@Slf4j
@Api(value = "邮件处理相关页面，需要用户登录")
public class ManagerEmailController {


    @Autowired
    private WeixinManagerEmailReceiveService weixinManagerEmailReceiveService;


    @RequestMapping
    public String toMangerEmailIndex(ManageReceiveEmailQuery manageReceiveEmailQuery,
                                     Model model) {
        PageInfo<WeixinManagerEmailReceive> weixinManagerEmailReceivePageInfo =
                weixinManagerEmailReceiveService.getWeixinManagerEmailReceiveByManageReceiveEmailQueryPage(manageReceiveEmailQuery);

        model.addAttribute("pageInfo", weixinManagerEmailReceivePageInfo);
        model.addAttribute("manageReceiveEmailQuery", manageReceiveEmailQuery);
        return "manage/manageReceiveEmail";
    }


    @RequestMapping("/replyAppoint")
    public String toEmailToAppoint(Integer receiveEmailId, Model model, HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("userSession");
        model.addAttribute("adminUserId", adminUser.getId());
        ReceiveMailForReplyAppointDTO receiveMailForReplyAppointDTO = null;
        if (receiveEmailId != null) {
            WeixinManagerEmailReceive weixinManagerEmailReceiveByPK = weixinManagerEmailReceiveService.getWeixinManagerEmailReceiveByPK(receiveEmailId);
            if (weixinManagerEmailReceiveByPK != null) {
                receiveMailForReplyAppointDTO = new ReceiveMailForReplyAppointDTO();
                receiveMailForReplyAppointDTO.setReceiveEmailContent(weixinManagerEmailReceiveByPK.getReceiveEmailContent());
                receiveMailForReplyAppointDTO.setReceiveEmailFrom(weixinManagerEmailReceiveByPK.getReceiveEmailFrom());
                receiveMailForReplyAppointDTO.setReceiveEmailId(receiveEmailId);
                receiveMailForReplyAppointDTO.setReceiveEmailTitle(weixinManagerEmailReceiveByPK.getReceiveEmailTitle());
                receiveMailForReplyAppointDTO.setReceiveEmailFromName(weixinManagerEmailReceiveByPK.getReceiveEmailFromName());
            }
        }
        model.addAttribute("receiveMailForReplyAppointDTO", receiveMailForReplyAppointDTO);
        return "manage/sendEmail";
    }


}
