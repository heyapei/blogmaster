package com.hyp.blogmaster.shiro.mapper;

import com.hyp.blogmaster.shiro.pojo.modal.AdminResources;
import com.hyp.blogmaster.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminResourcesMapper extends MyMapper<AdminResources> {

    List<AdminResources> queryAll();

    List<AdminResources> loadUserResources(Map<String, Object> map);

    List<AdminResources> queryResourcesListWithSelected(Integer rid);

}