package com.hyp.blogmaster.shiro.service;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.query.ManageReceiveEmailQuery;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmailReceive;

import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/12 15:25
 * @Description: TODO
 */
public interface WeixinManagerEmailReceiveService {

    /*自定义*/

    /**
     * 通过查询条件进行分页数据查询
     *
     * @param manageReceiveEmailQuery
     * @return 分页查询出来的数据
     * @throws MyDefinitionException
     */
    PageInfo<WeixinManagerEmailReceive> getWeixinManagerEmailReceiveByManageReceiveEmailQueryPage(ManageReceiveEmailQuery manageReceiveEmailQuery) throws MyDefinitionException;


    /*通用*/

    /**
     * 保存从邮件服务商拉取的邮件信息
     *
     * @param weixinManagerEmailReceive
     * @return 主键
     * @throws MyDefinitionException
     */
    Integer saveWeixinManagerEmailReceiveReturnPK(WeixinManagerEmailReceive weixinManagerEmailReceive) throws MyDefinitionException;


    /**
     * 通过通用参数进行查询
     *
     * @param weixinManagerEmailReceive
     * @return
     * @throws MyDefinitionException
     */
    List<WeixinManagerEmailReceive> getWeixinManagerEmailReceiveByParameter(WeixinManagerEmailReceive weixinManagerEmailReceive) throws MyDefinitionException;


    /**
     * 按照主键对有值的数据进行更新
     *
     * @param weixinManagerEmailReceive
     * @return 影响行数
     * @throws MyDefinitionException
     */
    Integer updateSelectiveWeixinManagerEmailReceiveByPK(WeixinManagerEmailReceive weixinManagerEmailReceive) throws MyDefinitionException;


    /**
     * 按照主键进行删除
     *
     * @param pk
     * @return 影响行数
     * @throws MyDefinitionException
     */
    Integer deleteWeixinManagerEmailReceivePK(Integer pk) throws MyDefinitionException;

}
