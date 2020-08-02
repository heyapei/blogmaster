package com.hyp.blogmaster.pojo.blog.query;

import lombok.Data;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 15:20
 * @Description: TODO
 */

@Data
public class BlogListQuery {

    public static BlogListQuery init() {
        BlogListQuery blogListQuery = new BlogListQuery();
        blogListQuery.setJournalType(0);
        blogListQuery.setStatus(0);
        blogListQuery.setPageNum(1);
        blogListQuery.setPageSize(15);
        return blogListQuery;
    }

    /**
     * 日志类型 0 何亚培 1去投票
     */
    private Integer journalType = 0;

    /**
     * 0 上线 1下线
     */
    private Integer status = 0;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页显示大小
     */
    private Integer pageSize = 5;

}
