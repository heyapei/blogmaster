package com.hyp.blogmaster.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 17:57
 * @Description: TODO 活动查询
 */
@Data
public class ManageActivityQuery {


    /**
     * 微信唯一标识
     */
    private String openId;
    /**
     * 公司名称
     */
    private String organisersName;

    /**
     * 活动名称
     */
    private String activeName;

    /**
     * 0 待审核 1上线  2未开始 3已结束 4未启动
     */
    private Integer status;

    /**
     * 是否公开到首页 0默认公开 1不公开
     */
    private Integer activePublic;


    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页显示大小
     */
    private Integer pageSize = 5;
    /**
     * 排序用字段
     */
    private String orderColumn = "createTime";
    /**
     * 排序方式
     */
    private String orderBy = "desc";

}
