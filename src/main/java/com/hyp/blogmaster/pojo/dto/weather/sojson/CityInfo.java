/**
  * Copyright 2020 bejson.com 
  */
package com.hyp.blogmaster.pojo.dto.weather.sojson;

/**
 * Auto-generated: 2020-06-23 22:29:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@lombok.Data
public class CityInfo {

    private String city;
    private String citykey;
    private String parent;
    private String updateTime;
    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setCitykey(String citykey) {
         this.citykey = citykey;
     }
     public String getCitykey() {
         return citykey;
     }

    public void setParent(String parent) {
         this.parent = parent;
     }
     public String getParent() {
         return parent;
     }

    public void setUpdateTime(String updateTime) {
         this.updateTime = updateTime;
     }
     public String getUpdateTime() {
         return updateTime;
     }

}