package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_supply_info")
@Data
public class UserSupplyInfo {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 昵称
     */
    private String name;

    /**
     * email地址
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信号
     */
    private String weixin;

    /**
     * 微信二维码
     */
    @Column(name = "weixin_img")
    private String weixinImg;

    /**
     * 头像
     */
    @Column(name = "avatar_img")
    private String avatarImg;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 网址
     */
    private String website;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * 备用字段
     */
    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String ext5;

    /**
     * 生日
     */
    private Long birthday;


}