package com.hyp.blogmaster.controller.manager;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.pojo.dto.manager.ActivityManagerDTO;
import com.hyp.blogmaster.pojo.query.ManageActivityQuery;
import com.hyp.blogmaster.service.ManagerActivityService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 17:54
 * @Description: TODO
 */
@Controller
@RequestMapping("/admin/manager/activity")
@Slf4j
@Api(value = "活动管理页面，需要用户登录")
public class ManagerActivityController {


    @Autowired
    private ManagerActivityService managerActivityService;

    /**
     * 根据活动查询实体类进行数据查询
     *
     * @param model
     * @param manageActivityQuery
     * @return
     */
    @RequestMapping
    public String toMangerActivityIndex(Model model, ManageActivityQuery manageActivityQuery) {

        PageInfo<ActivityManagerDTO> activityManagerDTOByExample =
                managerActivityService.getActivityManagerDTOByExample(manageActivityQuery);
        model.addAttribute("pageInfo", activityManagerDTOByExample);
        model.addAttribute("manageActivity", manageActivityQuery);
        return "manage/manageActivity";
    }

}
