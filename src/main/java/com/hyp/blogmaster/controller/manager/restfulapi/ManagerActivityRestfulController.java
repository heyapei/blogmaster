package com.hyp.blogmaster.controller.manager.restfulapi;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 23:30
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/admin/manager/activity")
@Slf4j
@Api(value = "活动管理操作接口，我放弃restful风格了，对我来说限制有点多")
public class ManagerActivityRestfulController {
}
