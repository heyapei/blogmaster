package com.hyp.blogmaster.service;

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
 * @Date 2020/7/15 20:05
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeixinVoteBaseServiceTest {

    @Autowired
    private WeixinVoteBaseService weixinVoteBaseService;

    @Test
    public void getWeixinVoteRuleByPK() {
        String weixinVoteRuleByPK = weixinVoteBaseService.getWeixinVoteRuleByPK(77);
        log.info("查询结果：{}",weixinVoteRuleByPK.toString());
    }
}