package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.shiro.pojo.modal.UserSupplyInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/2 11:21
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserSupplyInfoServiceTest {

    @Autowired
    private UserSupplyInfoService userSupplyInfoService;

    @Test
    public void selectByUserId() {
        UserSupplyInfo userSupplyInfo = userSupplyInfoService.selectByUserId(1);
        log.info("查询结果：{}", userSupplyInfo);

    }
}