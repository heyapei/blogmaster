package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.shiro.pojo.modal.AdminRole;

import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/21 15:44
 * @Description: TODO
 */
public interface AdminRoleService {


    /**
     *
     * @param userName
     * @return
     */
    List<AdminRole> findRoleByUsername(String userName);
}
