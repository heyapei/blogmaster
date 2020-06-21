package com.hyp.blogmaster.shiro.service;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/21 15:42
 * @Description: TODO
 */
public interface AdminUserService {

    PageInfo<AdminUser> selectByPage(AdminUser user, int start, int length);

    AdminUser selectByUsername(String username);

}
