//package com.hyp.blogmaster.shiro.service.impl;
//
//import com.hyp.blogmaster.shiro.mapper.AdminUserRoleMapper;
//import com.hyp.blogmaster.shiro.pojo.modal.AdminUserRole;
//import com.hyp.blogmaster.shiro.config.MyShiroRealm;
//import com.hyp.blogmaster.shiro.service.AdminUserRoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class AdminUserRoleServiceImpl implements AdminUserRoleService {
//    @Autowired
//    private MyShiroRealm myShiroRealm;
//    @Autowired
//    private AdminUserRoleMapper adminUserRoleMapper;
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
//    public void addUserRole(AdminUserRole userRole) {
//        //删除
//        Example example = new Example(AdminUserRole.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("userId", userRole.getUserId());
//        adminUserRoleMapper.deleteByExample(example);
//        //添加
//        String[] roleids = userRole.getRoleId().split(",");
//        for (String roleId : roleids) {
//            AdminUserRole u = new AdminUserRole();
//            u.setUserId(userRole.getUserId());
//            u.setRoleId(roleId);
//            adminUserRoleMapper.insert(u);
//        }
//        //更新当前登录的用户的权限缓存
//        List<Integer> userid = new ArrayList<Integer>();
//        userid.add(userRole.getUserId());
//        myShiroRealm.clearUserAuthByUserId(userid);
//    }
//}
