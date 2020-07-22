package com.hyp.blogmaster.mapper;

import com.hyp.blogmaster.pojo.dto.manager.weixinuser.UserRegionAnalysisDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/22 22:06
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeixinVoteUserMapperTest {

    @Autowired
    private WeixinVoteUserMapper weixinVoteUserMapper;

    @Test
    public void getUserDashboardDataAnalysis() {
    }

    @Test
    public void getWeixinUserRegionAnalysisList() {
        List<UserRegionAnalysisDTO> weixinUserRegionAnalysisList = weixinVoteUserMapper.getWeixinUserRegionAnalysisList();
        log.info("查询结果：" + weixinUserRegionAnalysisList);
    }
}