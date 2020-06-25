package com.hyp.blogmaster.pojo.vo.page.dashboard;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/25 11:00
 * @Description: TODO
 */
@Data
public class TotalQuantityVO {
    /**
     * 总用户量
     */
    private Integer totalUserNum;

    /**
     * 总浏览量
     */
    private Integer totalViewNum;

    /**
     * 总活动数量
     */
    private Integer totalActiveNum;
    /**
     * 总作品数量
     */
    private Integer totalUserWorkNum;
    /**
     * 总投票数量
     */
    private Integer totalVoteNum;
}
