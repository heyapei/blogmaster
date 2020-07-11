package com.hyp.blogmaster.pojo.query;

import lombok.Data;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/9 19:07
 * @Description: TODO 用户管理查询用实体
 */
@Data
public class ManagerUserQuery {


    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户状态
     */
    private Integer enable;

    /**
     * 微信唯一标识
     */
    private String openid;
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
    private String orderColumn = "updateTime";
    /**
     * 排序方式
     */
    private String orderBy = "desc";


}
