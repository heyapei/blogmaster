package com.hyp.blogmaster.service.impl;

import com.hyp.blogmaster.pojo.vo.page.dashboard.TotalQuantityVO;
import com.hyp.blogmaster.service.DashboardService;
import com.hyp.blogmaster.service.WeixinUserNoOpenIdIdLogService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/25 11:48
 * @Description: TODO
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DashboardServiceImplTest {


    @Autowired
    private DashboardService dashboardService;

    @Test
    public void getTotalQuantityVO() {
        TotalQuantityVO totalQuantityVO = dashboardService.getTotalQuantityVO();
        log.info("查询结果{}",totalQuantityVO.toString());
    }
}