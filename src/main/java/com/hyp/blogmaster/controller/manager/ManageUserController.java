package com.hyp.blogmaster.controller.manager;

import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/28 21:07
 * @Description: TODO
 */
@Controller
@RequestMapping("/admin/manager/user")
@Slf4j
@Api(value = "用户管理页面，需要用户登录")
public class ManageUserController {

    @GetMapping
    public String toMangerUserIndex(Model model, HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("userSession");
        model.addAttribute("userName", adminUser.getUserName());
        return "manage/manageUser";
    }


}

