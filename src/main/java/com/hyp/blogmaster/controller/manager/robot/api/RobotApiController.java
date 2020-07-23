package com.hyp.blogmaster.controller.manager.robot.api;

import com.hyp.blogmaster.utils.MyHttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/23 20:18
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/robot")
@Slf4j
@Api(value = "机器人使用的api接口")
public class RobotApiController {

    @Autowired
    private MyHttpClientUtil httpClientUtil;

    @ApiOperation("进入机器人的页面")
    @RequestMapping(value = "talk/qingyunke/{talkText}", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
    public String talkWithQingyunke(@PathVariable String talkText) {
        Map<String, Object> parameterMap = new HashMap<>(3);
        parameterMap.put("key", "free");
        parameterMap.put("appid", "0");
        parameterMap.put("appid", "0");
        parameterMap.put("msg", talkText);
        String talkWord = httpClientUtil.getParameter("http://api.qingyunke.com/api.php", parameterMap, null, 10000, 10000, 10000);
        log.info("聊天内容：" + talkText + "返回内容：" + talkWord);
        return talkWord;
    }


}
