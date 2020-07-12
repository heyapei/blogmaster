package com.hyp.blogmaster.shiro.service.impl;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.shiro.mapper.WeixinManagerEmailReplyMapper;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmailReply;
import com.hyp.blogmaster.shiro.service.WeixinManagerEmailReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/12 22:57
 * @Description: TODO
 */
@Slf4j
@Service
public class WeixinManagerEmailReplyServiceImpl implements WeixinManagerEmailReplyService {

    @Autowired
    private WeixinManagerEmailReplyMapper weixinManagerEmailReplyMapper;

    /**
     * 保存回复邮件内容
     *
     * @param weixinManagerEmailReply
     * @return 返回主键
     * @throws MyDefinitionException
     */
    @Override
    public Integer saveWeixinManagerEmailReplyReturnPK(WeixinManagerEmailReply weixinManagerEmailReply) throws MyDefinitionException {

        if (weixinManagerEmailReply == null) {
            throw new MyDefinitionException("保存回复邮件内容参数不可以为空");
        }
        Integer key = null;
        try {
            int i = weixinManagerEmailReplyMapper.insertUseGeneratedKeys(weixinManagerEmailReply);
            if (i > 0) {
                key = weixinManagerEmailReply.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存回复邮件内容操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("保存回复邮件内容操作过程错误");
        }
        return key;
    }
}
