/**
 * Copyright 2020 bejson.com
 */
package com.hyp.blogmaster.pojo.dto.weather.sojson;

import java.util.List;

/**
 * Auto-generated: 2020-06-23 22:29:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@lombok.Data
public class WeatherDetail {

    private String shidu;
    private int pm25;
    private int pm10;
    private String quality;
    private String wendu;
    private String ganmao;
    private List<Forecast> forecast;
    private Yesterday yesterday;

}