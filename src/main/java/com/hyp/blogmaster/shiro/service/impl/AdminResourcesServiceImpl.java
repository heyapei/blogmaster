package com.hyp.blogmaster.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.shiro.mapper.AdminResourcesMapper;
import com.hyp.blogmaster.shiro.pojo.modal.AdminResources;
import com.hyp.blogmaster.shiro.service.AdminResourcesService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdminResourcesServiceImpl implements AdminResourcesService {
    @Resource
    private AdminResourcesMapper resourcesMapper;

    @Override
    public PageInfo<AdminResources> selectByPage(AdminResources resources, int start, int length) {
        int page = start / length + 1;
        Example example = new Example(AdminResources.class);
        //分页查询
        PageHelper.startPage(page, length);
        List<AdminResources> userList = resourcesMapper.selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public List<AdminResources> queryAll() {
        return resourcesMapper.queryAll();
    }

    @Override
    //@Cacheable(cacheNames="resources",key="#map['userid'].toString()+#map['type']")
    public List<AdminResources> loadUserResources(Map<String, Object> map) {
        return resourcesMapper.loadUserResources(map);
    }

    @Override
    public List<AdminResources> queryResourcesListWithSelected(Integer rid) {
        return resourcesMapper.queryResourcesListWithSelected(rid);
    }
}
