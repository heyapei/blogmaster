package com.hyp.blogmaster.pojo.query;

import lombok.Data;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/12 17:58
 * @Description: TODO 收取的邮件查询实体类
 */
@Data
public class ManageReceiveEmailQuery {


    /**
     * 邮件发件人
     */
    private String receiveEmailFrom;


    /**
     * 邮件主题
     */
    private String receiveEmailTitle;

    /**
     * 是否已经回复了
     */
    private Integer receiveReplyStatus;

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
    private String orderColumn = "receiveEmailGetDate";
    /**
     * 排序方式
     */
    private String orderBy = "desc";


}
