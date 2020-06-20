package com.hyp.blogmaster.controller.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @RequestMapping("/adminlogin")
    @ApiOperation(value = "后端程序登录程序")
    public String index(Model model, String userName, String passWord) {

        log.info("用户登录程序：{}，{}", userName, passWord);

        return "index";
    }


}
