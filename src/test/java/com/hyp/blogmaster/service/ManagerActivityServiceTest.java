package com.hyp.blogmaster.service;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.pojo.dto.manager.ActivityManagerDTO;
import com.hyp.blogmaster.pojo.query.ManageActivityQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 21:56
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ManagerActivityServiceTest {

    @Autowired
    private ManagerActivityService managerActivityService;

    @Test
    public void getActivityManagerDTOByExample() {
        ManageActivityQuery manageActivityQuery = new ManageActivityQuery();
        //manageActivityQuery.setOpenId("oF9lL5K5F_q_SLf8XEsmTJ101Tv4");
       // manageActivityQuery.setOrganisersName("给你");
        //manageActivityQuery.setActiveName("名称");
        //manageActivityQuery.setStatus(0);
        //manageActivityQuery.setActivePublic(0);
        manageActivityQuery.setPageNum(1);
        manageActivityQuery.setPageSize(10);
        manageActivityQuery.setOrderColumn("activeStartTime");
        manageActivityQuery.setOrderBy("");

        PageInfo<ActivityManagerDTO> activityManagerDTOByExample = managerActivityService.getActivityManagerDTOByExample(manageActivityQuery);
        log.info("查询结果：{}", activityManagerDTOByExample);
    }
}