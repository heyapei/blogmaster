package com.hyp.blogmaster.controller.login;

import com.hyp.blogmaster.pojo.vo.result.MyError;
import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/20 17:55
 * @Description: TODO
 */
@Controller
@RequestMapping("/login")
@Slf4j
@Api(value = "登录程序")
public class LoginController {

    @RequestMapping(value = "/logout")
    public String  logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
        return "redirect:login";
    }


    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, AdminUser user, Model model) {
        log.info("用户登录程序：{}", user.toString());
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassWord())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "redirect:login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        MyError myError = new MyError();
        try {
            subject.login(token);
            return "redirect:/login/admin";
        } catch (LockedAccountException lae) {
            token.clear();
            myError.setErrorCode(400);
            myError.setCodeMsg("用户已经被锁定不能登录，请与管理员联系！");
            model.addAttribute("myError", myError);
            return "error";
        } catch (AuthenticationException e) {
            token.clear();
            myError.setErrorCode(400);
            myError.setCodeMsg("用户或密码不正确！");
            model.addAttribute("myError", myError);
            return "error";
        }
    }


    @RequestMapping(value = {"/admin", ""})
    public String usersPage(Model model, HttpServletRequest request) {

        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("userSession");
        log.info("当前用户：{}", adminUser.toString());
        model.addAttribute("userName", adminUser.getUserName());
        return "manage/dashboard";
    }


}
