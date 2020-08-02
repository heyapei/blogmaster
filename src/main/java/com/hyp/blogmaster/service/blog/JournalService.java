package com.hyp.blogmaster.service.blog;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.blog.model.Journal;
import com.hyp.blogmaster.pojo.blog.query.BlogListQuery;
import com.hyp.blogmaster.pojo.blog.vo.BlogShowVO;


/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 14:08
 * @Description: TODO
 */
public interface JournalService {

    /**
     * 浏览日志信息
     *
     * @param pkId
     * @return
     * @throws MyDefinitionException
     */
    BlogShowVO showBlogByPkId(Integer pkId) throws MyDefinitionException;


    /**
     * 根据查询条件 分页查询 日志列表
     *
     * @param blogListQuery
     * @return
     * @throws MyDefinitionException
     */
    PageInfo selectPageInfoByBlogListQuery(BlogListQuery blogListQuery) throws MyDefinitionException;


    /*通用*/

    /**
     * 获取日志详细信息
     *
     * @param pkId
     * @return
     * @throws MyDefinitionException
     */
    Journal selectJournalById(Integer pkId) throws MyDefinitionException;


    /**
     * 根据主键更新日志信息
     *
     * @param journal 日志信息（含主键）
     * @return 影响行数
     * @throws MyDefinitionException
     */
    Integer updateSelectiveByPkId(Journal journal) throws MyDefinitionException;

}
