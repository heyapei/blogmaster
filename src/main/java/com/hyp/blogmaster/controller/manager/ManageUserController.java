package com.hyp.blogmaster.controller.manager;

import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDTO;
import com.hyp.blogmaster.pojo.vo.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String toMangerUserIndex() {
        return "manage/manageUser";
    }


}

