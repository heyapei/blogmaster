package com.hyp.blogmaster.service;

import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDTO;
import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDetail;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/25 0:03
 * @Description: TODO
 */
public interface DashboardService {

    /**
     * 根据请求ip获取天气信息
     * 逻辑应该是：
     * 1. 获取ip地址
     * 2. 根据ip地址通过高德的api获取IP的物理地址
     * 3. 通过物理地址找到对应的areaCode
     * 4. 判断redis中数据是否存在该地区的天气
     * 5. 如果有就是用redis中的数据 如果没有就使用天气查询接口查询数据并保存到redis中 缓存过期时间8小时
     * @param httpServletRequest
     * @return
     */
    WeatherDTO getWeatherByIp(HttpServletRequest httpServletRequest);

}
