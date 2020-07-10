package com.hyp.blogmaster.service;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.dto.manager.ActivityManagerDTO;
import com.hyp.blogmaster.pojo.query.ManageActivityQuery;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/10 20:32
 * @Description: TODO
 */
public interface ManagerActivityService {

    /**
     * 根据查询条件进行数据分页查询
     *
     * @param manageActivityQuery 查询用实体类
     * @return 分页信息
     * @throws MyDefinitionException
     */
    PageInfo<ActivityManagerDTO> getActivityManagerDTOByExample(ManageActivityQuery manageActivityQuery) throws MyDefinitionException;
}
