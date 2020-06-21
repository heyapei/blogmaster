package com.hyp.blogmaster.shiro.service;

import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.shiro.pojo.modal.AdminResources;

import java.util.List;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/21 16:13
 * @Description: TODO
 */
public interface AdminResourcesService {


    PageInfo<AdminResources> selectByPage(AdminResources resources, int start, int length);

    public List<AdminResources> queryAll();

    public List<AdminResources> loadUserResources(Map<String, Object> map);

    public List<AdminResources> queryResourcesListWithSelected(Integer rid);

}
