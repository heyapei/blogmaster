package com.hyp.blogmaster.service;


import com.hyp.blogmaster.pojo.modal.WeixinUserOptionConfig;
import com.hyp.blogmaster.pojo.modal.WeixinUserOptionLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
     *
     * @param weixinUserOptionConfig
     * @return
     */
    Integer getCountNumByOptionConfig(WeixinUserOptionConfig weixinUserOptionConfig);

    /**
     * 根据日期范围查询统计数据
     *
     * @param weixinUserOptionConfig 日志实体类
     * @param startTime              开始时间
     * @param endTime                结束时间
     * @return
     */
    Integer getCountNumByOptionConfigAndTime(WeixinUserOptionConfig weixinUserOptionConfig, Date startTime, Date endTime);


}
