package com.hyp.blogmaster.controller;

import com.hyp.blogmaster.exception.MyDefinitionException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/5/31 10:48
 * @Description: TODO
 */
@Controller
@Slf4j
@Api(value = "首页地址")
public class IndexController {
    @RequestMapping("/index")
    @ApiOperation(value = "默认用户进入首页")
    public String index(Model model) {
        model.addAttribute("name", "heyapei");
        return "index";
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



    @RequestMapping("/")
    @ApiOperation(value = "默认进入管理员登录页面")
    public String adminLogin() {
        return "login/adminLogin";
    }

}
