package com.hyp.blogmaster.controller;

import com.hyp.blogmaster.exception.MyDefinitionException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/5/31 10:48
 * @Description: TODO
 */
@Controller
@Slf4j
@Api(value = "首页默认程序")
public class IndexController {

    /**
     * 进入首页
     *
     * @return
     */
    @GetMapping("/admin/index")
    public String goIndex() {
        return "forward:/login/admin";
    }


    @RequestMapping("/")
    @ApiOperation(value = "根据用户编号获取用户姓名")
    public String redirect() {
        log.info("用户请求默认首页被跳转到www.yapei.cool");
        return "redirect:http://www.yapei.cool";
    }

    /**
     * shiro默认登录地址
     *
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    @ApiOperation(value = "默认进入登录页面")
    public String loginPage() {
        return "login/adminLogin";
    }


    /**
     * shiro登录后默认首页
     *
     * @return
     */
    @RequestMapping(value = {"/index", ""})
    public String userPage() {
        return "user";
    }


    /**
     * shiro没有权限页面
     *
     * @return
     */
    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }


    @RequestMapping("/testError")
    public String testError() {
        log.info("请求错误示例页面");
        try {
            Integer.parseInt("nihao");
        } catch (NumberFormatException e) {
            throw new MyDefinitionException("该地址请求失败");
        }
        return "redirect:http://www.yapei.cool";
    }


}
