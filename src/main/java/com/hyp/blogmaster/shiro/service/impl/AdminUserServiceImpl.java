package com.hyp.blogmaster.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.shiro.mapper.AdminUserMapper;
import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;
import com.hyp.blogmaster.shiro.service.AdminUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public PageInfo<AdminUser> selectByPage(AdminUser user, int start, int length) {
        int page = start / length + 1;
        Example example = new Example(AdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (user.getEnable() != null) {
            criteria.andEqualTo("enable", user.getEnable());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<AdminUser> adminUsers = adminUserMapper.selectByExample(example);
        return new PageInfo(adminUsers);
    }

    @Override
    public AdminUser selectByUsername(String username) {
        Example example = new Example(AdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", username);
        List<AdminUser> userList = adminUserMapper.selectByExample(example);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    /*@Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delUser(Integer userid) {
        //删除用户表
        adminUserMapper.deleteByPrimaryKey(userid);
        //删除用户角色表
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userid);
        userRoleMapper.deleteByExample(example);
    }*/
}
