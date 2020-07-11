package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "weixin_manager_resource_config")
public class WeixinManagerResourceConfig {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 关键字 最好几个字
     */
    @Column(name = "key_word")
    private String keyWord;

    /**
     * 资源类型 默认为0 默认资源
     */
    private Integer type;

    /**
     * 资源类型描述
     */
    private String description;

    public enum ConfigType {

        HeadImg(0, "email图片");


        private Integer configType;
        private String configTypeDesc;

        ConfigType(Integer configType, String configTypeDesc) {
            this.configType = configType;
            this.configTypeDesc = configTypeDesc;
        }

        public Integer getConfigType() {
            return configType;
        }

        public String getConfigTypeDesc() {
            return configTypeDesc;
        }

        @Override
        public String toString() {
            return configType + ": " + configTypeDesc;
        }

    }
}