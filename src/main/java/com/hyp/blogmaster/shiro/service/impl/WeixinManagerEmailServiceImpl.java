package com.hyp.blogmaster.shiro.service.impl;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.shiro.mapper.WeixinManagerEmailMapper;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmail;
import com.hyp.blogmaster.shiro.service.WeixinManagerEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 13:54
 * @Description: TODO
 */
@Slf4j
@Service
public class WeixinManagerEmailServiceImpl implements WeixinManagerEmailService {

    @Autowired
    private WeixinManagerEmailMapper weixinManagerEmailMapper;








    /**
     * 保存需要发送的邮件 返回当前主键
     *
     * @param weixinManagerEmail
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public Integer saveWeixinManagerEmailGetPK(WeixinManagerEmail weixinManagerEmail) throws MyDefinitionException {
        if (weixinManagerEmail == null) {
            log.error("需要存储的邮件实体为空，不可以执行该操作");
            throw new MyDefinitionException("需要存储的邮件实体不正确");
        }
        Integer key = null;
        try {
            int i = weixinManagerEmailMapper.insertUseGeneratedKeys(weixinManagerEmail);
            if (i > 0) {
                key = weixinManagerEmail.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存邮件实体的操作中错误，错误理由：{}", e.toString());
            throw new MyDefinitionException("保存邮件实体的操作中错误");
        }
        return key;
    }
}
