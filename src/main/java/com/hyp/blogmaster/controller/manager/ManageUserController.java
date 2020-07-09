package com.hyp.blogmaster.controller.manager;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.pojo.modal.WeixinVoteUser;
import com.hyp.blogmaster.pojo.query.ManagerUserQuery;
import com.hyp.blogmaster.service.WeixinVoteUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private WeixinVoteUserService weixinVoteUserService;

    @RequestMapping
    public String toMangerUserIndex(Model model, ManagerUserQuery managerUserQuery) {

        PageInfo<WeixinVoteUser> voteUserByPage = weixinVoteUserService.getVoteUserByPage(managerUserQuery);
        model.addAttribute("pageInfo", voteUserByPage);
        model.addAttribute("managerUserQuery", managerUserQuery);
        return "manage/manageUser";
    }


}

