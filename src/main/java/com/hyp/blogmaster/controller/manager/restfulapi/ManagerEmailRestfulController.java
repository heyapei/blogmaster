package com.hyp.blogmaster.controller.manager.restfulapi;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.dto.resource.ResourceSimpleDTO;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.service.ResourceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 11:44
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/admin/manager/email")
@Slf4j
@Api(value = "邮件处理相关页面，需要用户登录")
public class ManagerEmailRestfulController {


    @Autowired
    private ResourceService resourceService;

    @PostMapping("upload")
    public Map<String, String> fileUpload(@RequestParam("file") MultipartFile file) {
        MyResultVO myResultVO = null;
        try {
            myResultVO = resourceService.saveEmailRes(file);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            return null;
        }
        ResourceSimpleDTO data = (ResourceSimpleDTO) myResultVO.getData();
        Map<String, String> map = new HashMap<>(1);
        map.put("url", data.getFileUrl());
        return map;
    }


}
