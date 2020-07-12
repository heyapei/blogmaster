package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmailReply;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/12 22:45
 * @Description: TODO
 */
public interface WeixinManagerEmailReplyService {


    /*通用*/

    /**
     * 保存回复邮件内容
     *
     * @param weixinManagerEmailReply
     * @return 返回主键
     * @throws MyDefinitionException
     */
    public Integer saveWeixinManagerEmailReplyReturnPK(WeixinManagerEmailReply weixinManagerEmailReply) throws MyDefinitionException;

}
