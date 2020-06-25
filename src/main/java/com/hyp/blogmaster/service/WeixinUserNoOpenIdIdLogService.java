package com.hyp.blogmaster.service;


import com.hyp.blogmaster.pojo.modal.WeixinUserOptionConfig;
import com.hyp.blogmaster.pojo.modal.WeixinUserOptionLog;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/7 11:20
 * @Description: TODO
 */
public interface WeixinUserNoOpenIdIdLogService {

    /**
     * 添加微信用户操作日志
     *
     * @param weixinUserOptionLog
     * @param httpServletRequest
     * @return 主键ID
     */
    Integer addUserOperationLog(WeixinUserOptionLog weixinUserOptionLog, HttpServletRequest httpServletRequest);

    /**
     * 通过操作类型获取数据 这里只获取总数 不做其他的操作
     * @param weixinUserOptionConfig
     * @return
     */
    Integer getCountNumByOptionConfig(WeixinUserOptionConfig weixinUserOptionConfig);


}
