package com.hyp.blogmaster.controller.bootstraptest;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/5/31 10:48
 * @Description: TODO
 */
@Controller
@RequestMapping("/bootstrap/test")
@Slf4j
@Api(value = "bootstrap测试用")
public class BootstrapTestController {

    /**
     * 进入首页
     *
     * @return
     */
    @GetMapping("test1")
    public String goIndex() {
        return "/bootstraptest/test1";
    }

    @GetMapping("test2")
    public String goTestSweetAlert2() {
        return "/bootstraptest/testSweetAlert2";
    }


}
