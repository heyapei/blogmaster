package com.hyp.blogmaster.controller.resource;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 23:30
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/resource")
@Slf4j
@Api(value = "资源管理接口")
public class ResourceRestfulController {

    @RequestMapping("file/upload")
    public Map<String, String> fileUpload(@RequestParam("file") MultipartFile file, String type) {
        Map<String, String> map = new HashMap<>(1);
        map.put("url", "231");
        return map;
    }
}
