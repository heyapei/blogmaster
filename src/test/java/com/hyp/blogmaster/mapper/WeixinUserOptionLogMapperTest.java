package com.hyp.blogmaster.mapper;

import com.hyp.blogmaster.pojo.dto.page.DashboardDataAnalysisDTO;
import com.hyp.blogmaster.service.DashboardService;
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
 * @Date 2020/6/28 19:49
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeixinUserOptionLogMapperTest {

    @Autowired
    private WeixinUserOptionLogMapper weixinUserOptionLogMapper;

    @Test
    public void getDashboardDataAnalysisByOptionType() {
        List<DashboardDataAnalysisDTO> dashboardDataAnalysisByOptionType = weixinUserOptionLogMapper.getDashboardDataAnalysisByOptionType(0);
        System.out.println("查询数据："+dashboardDataAnalysisByOptionType.toString());
    }
}