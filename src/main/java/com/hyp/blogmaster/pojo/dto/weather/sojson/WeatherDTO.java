/**
  * Copyright 2020 bejson.com 
  */
package com.hyp.blogmaster.pojo.dto.weather.sojson;
import java.util.Date;

/**
 * Auto-generated: 2020-06-23 22:29:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@lombok.Data
public class WeatherDTO {

    private String message;
    private int status;
    private String date;
    private Date time;
    private CityInfo cityInfo;
    private WeatherDetail data;
}