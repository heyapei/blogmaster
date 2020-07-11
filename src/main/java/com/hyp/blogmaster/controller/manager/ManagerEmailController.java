package com.hyp.blogmaster.controller.manager;

import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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


    @RequestMapping
    public String toMangerUserIndex(Model model, HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("userSession");
        model.addAttribute("adminUserId", adminUser.getId());
        return "manage/sendEmail";
    }



}
