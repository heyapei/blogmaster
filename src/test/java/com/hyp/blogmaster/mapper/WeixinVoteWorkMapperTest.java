package com.hyp.blogmaster.mapper;

import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/28 20:40
 * @Description: TODO
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeixinVoteWorkMapperTest {

    @Autowired
    private WeixinVoteWorkMapper weixinVoteWorkMapper;

    @Test
    public void getDashboardDataAnalysis() {
        List<DashboardDataAnalysisDTO> dashboardDataAnalysis = weixinVoteWorkMapper.getDashboardDataAnalysis();
        log.info("查询数据{}", dashboardDataAnalysis.toString());
    }
}