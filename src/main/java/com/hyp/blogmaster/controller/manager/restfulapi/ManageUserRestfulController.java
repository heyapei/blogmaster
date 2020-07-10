package com.hyp.blogmaster.controller.manager.restfulapi;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.service.WeixinVoteUserService;
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
 * @Date 2020/6/28 21:07
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/admin/manager/user")
@Slf4j
@Api(value = "用户管理操作接口，该接口采用restful风格")
public class ManageUserRestfulController {

    @Autowired
    private WeixinVoteUserService weixinVoteUserService;


    /**
     * 根据用户ID查询用户的数据
     *
     * @param userId
     * @return
     */
    @PostMapping("getUserInfoById")
    public MyResultVO getUserInfoById(@NotNull Integer userId) {
        WeixinVoteUser weixinVoteUser = null;
        try {
            weixinVoteUser = weixinVoteUserService.getUserById(userId);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            return MyResultVO.genFailResult(e.getMessage());
        }
        if (weixinVoteUser == null) {
            return MyResultVO.genFailResult("没有查询到用户的信息，请重试");
        }
        return MyResultVO.genSuccessResult(weixinVoteUser);
    }


    /**
     * 根据userId来更改用户的状态值
     *
     * @param userId
     * @return
     */
    @PostMapping("changerUserStatus")
    public MyResultVO toMangerUserIndex(@NotNull Integer userId) {
        Integer changeUserEnable;
        try {
            changeUserEnable = weixinVoteUserService.changeUserEnable(userId);
        } catch (MyDefinitionException e) {
            e.printStackTrace();
            return MyResultVO.genFailResult(e.getMessage());
        }
        if (changeUserEnable == null || changeUserEnable <= 0) {
            return MyResultVO.genFailResult("更新用户状态值未能成功");
        }
        return MyResultVO.genSuccessResult("更新用户状态值成功");
    }


}

