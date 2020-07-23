package com.hyp.blogmaster.controller.manager.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/23 20:18
 * @Description: TODO
 */
@Controller
@RequestMapping("/robot")
@Slf4j
@Api(value = "机器人")
public class RobotController {


    @ApiOperation("进入机器人聊天页面")
    @RequestMapping(value = "goTalkIndex")
    public String goRobotTalkIndex() {
        return "manage/robot/robotTalk";
    }


}
