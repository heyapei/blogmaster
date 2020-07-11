package com.hyp.blogmaster.shiro.service.impl;

import com.hyp.blogmaster.shiro.mapper.WeixinManagerResourceConfigMapper;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerResourceConfig;
import com.hyp.blogmaster.shiro.service.WeixinManagerResourceConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/27 23:51
 * @Description: TODO
 */
@Service
@Slf4j
public class WeixinManagerResourceConfigServiceImpl implements WeixinManagerResourceConfigService {

    @Autowired
    private WeixinManagerResourceConfigMapper weixinManagerResourceConfigMapper;


    /**
     * 通过资源Id进行查找内容
     *
     * @param id
     * @return
     */
    @Override
    public WeixinManagerResourceConfig getWeixinResourceConfigById(Integer id) {

        WeixinManagerResourceConfig weixinResourceConfig = null;
        try {
            weixinResourceConfig = weixinManagerResourceConfigMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("通过资源Id进行查找内容错误，错误原因：{}", e.toString());
        }
        return weixinResourceConfig;
    }
}
