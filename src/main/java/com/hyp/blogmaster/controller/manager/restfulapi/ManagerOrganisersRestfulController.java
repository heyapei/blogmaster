package com.hyp.blogmaster.controller.manager.restfulapi;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.modal.WeixinVoteOrganisers;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.service.WeixinVoteOrganisersService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 0:03
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/admin/manager/organisers")
@Slf4j
@Api(value = "公司管理操作接口")
public class ManagerOrganisersRestfulController {


    @Autowired
    private WeixinVoteOrganisersService weixinVoteOrganisersService;


    /**
     * 根据公司ID查询公司的数据
     *
     * @param organisersId
     * @return
     */
    @PostMapping("getOrganisersInfoById")
    public MyResultVO getOrganisersInfoById(@NotNull Integer organisersId) {
        WeixinVoteOrganisers weixinVoteOrganisers = null;
        try {
            weixinVoteOrganisers = weixinVoteOrganisersService.getWeixinVoteOrganisersByID(organisersId);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            return MyResultVO.genFailResult(e.getMessage());
        }
        if (weixinVoteOrganisers == null) {
            return MyResultVO.genFailResult("没有查询到公司的信息，请重试");
        }
        return MyResultVO.genSuccessResult(weixinVoteOrganisers);
    }

}
