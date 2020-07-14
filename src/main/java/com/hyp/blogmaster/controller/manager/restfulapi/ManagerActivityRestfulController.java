package com.hyp.blogmaster.controller.manager.restfulapi;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.vo.page.active.ActiveDetailVO;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.service.ManagerActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

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


    @Autowired
    private ManagerActivityService managerActivityService;


    @ApiOperation("通过活动ID获取活动的详细信息")
    @PostMapping("getActiveConfDetail")
    public MyResultVO getActiveConfDetail(
            @ApiParam(name = "活动ID", value = "", required = true)
            @NotNull Integer activeId) {

        ActiveDetailVO activeDetailVOByActiveId = null;
        try {
            activeDetailVOByActiveId = managerActivityService.getActiveDetailVOByActiveId(activeId);
        } catch (MyDefinitionException e) {
            return MyResultVO.genFailResult(e.getMessage());
        }
        return MyResultVO.genSuccessResult(activeDetailVOByActiveId);
    }
}
