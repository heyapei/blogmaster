package com.hyp.blogmaster.pojo.dto.page;

import lombok.Data;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/28 19:44
 * @Description: TODO 作为图表分析的数据传输层
 */
@Data
public class DashboardDataAnalysisDTO {
    /**
     * 日期
     */
    private String dateTime;
    /**
     * 数据
     */
    private Integer countNum;
}
