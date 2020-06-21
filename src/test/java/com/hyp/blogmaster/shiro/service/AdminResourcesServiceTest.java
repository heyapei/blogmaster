package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.shiro.pojo.modal.AdminResources;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/21 21:10
 * @Description: TODO
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdminResourcesServiceTest {

    @Autowired
    private AdminResourcesService adminResourcesService;

    @Test
    public void selectByPage() {
    }

    @Test
    public void queryAll() {
    }

    @Test
    public void loadUserResources() {
        Map map = new HashMap();
        map.put("userid", 1);
        List<AdminResources> list = adminResourcesService.loadUserResources(map);
        for (AdminResources adminResources : list) {
            log.info("查询结果：{}", adminResources.toString());
        }
    }

    @Test
    public void queryResourcesListWithSelected() {

    }
}