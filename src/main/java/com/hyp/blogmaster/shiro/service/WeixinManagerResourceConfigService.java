package com.hyp.blogmaster.shiro.service;

import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerResourceConfig;


/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/27 23:50
 * @Description: TODO
 */
public interface WeixinManagerResourceConfigService {

    /**
     * 通过资源Id进行查找内容
     *
     * @param id
     * @return
     */
    WeixinManagerResourceConfig getWeixinResourceConfigById(Integer id);

}
