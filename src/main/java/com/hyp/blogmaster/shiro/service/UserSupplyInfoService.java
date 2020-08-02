package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.shiro.pojo.modal.UserSupplyInfo;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 15:10
 * @Description: TODO
 */
public interface UserSupplyInfoService {

    /*通用*/

    /**
     * 通过userId查询用户补充信息
     * @param userId
     * @return
     * @throws MyDefinitionException
     */
    UserSupplyInfo selectByUserId(Integer userId) throws MyDefinitionException;
}
