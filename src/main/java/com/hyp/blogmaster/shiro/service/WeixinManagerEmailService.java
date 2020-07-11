package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmail;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 13:54
 * @Description: TODO
 */
public interface WeixinManagerEmailService {

    /*自定义执行sql*/


    /*通用执行sql*/

    /**
     * 保存需要发送的邮件 返回当前主键
     *
     * @param weixinManagerEmail
     * @return
     * @throws MyDefinitionException
     */
    Integer saveWeixinManagerEmailGetPK(WeixinManagerEmail weixinManagerEmail) throws MyDefinitionException;


}
