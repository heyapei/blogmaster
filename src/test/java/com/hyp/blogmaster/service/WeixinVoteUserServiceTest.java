package com.hyp.blogmaster.service;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.pojo.query.ManagerUserQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/9 19:47
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeixinVoteUserServiceTest {

    @Autowired
    private WeixinVoteUserService weixinVoteUserService;


    @Test
    public void getVoteUserByPage() {
        ManagerUserQuery managerUserQuery = new ManagerUserQuery();
        managerUserQuery.setPageNum(1);
        managerUserQuery.setPageSize(1);
        managerUserQuery.setOrderColumn("");
        managerUserQuery.setOrderBy("");

        PageInfo voteUserByPage = weixinVoteUserService.getVoteUserByPage(managerUserQuery);
        int[] navigatepageNums = voteUserByPage.getNavigatepageNums();
        //voteUserByPage.isHasNextPage()
        log.info("查询结果：{}", voteUserByPage);

    }
}