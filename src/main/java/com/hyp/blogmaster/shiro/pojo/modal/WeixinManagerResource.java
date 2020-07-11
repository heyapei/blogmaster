package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "weixin_manager_resource")
@Data
public class WeixinManagerResource {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 资源类型
     */
    @Column(name = "resource_config_id")
    private Integer resourceConfigId;

    /**
     * 文件后缀名 如png mp4 
     */
    private String type;

    /**
     * 资源路径
     */
    private String path;

    /**
     * 名字系统分配的名称
     */
    private String name;

    /**
     * 文件真实的名称
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 状态 默认为0 开始状态 1关闭
     */
    private Integer status;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件MD5值
     */
    private String md5;

    /**
     * 文件title主要为文件未能正确加载页面显示
     */
    private String title;

    /**
     * 文件的描述
     */
    private String description;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人userId
     */
    @Column(name = "create_admin_user_id")
    private Integer createAdminUserId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源类型
     *
     * @return resource_config_id - 资源类型
     */
    public Integer getResourceConfigId() {
        return resourceConfigId;
    }

    /**
     * 设置资源类型
     *
     * @param resourceConfigId 资源类型
     */
    public void setResourceConfigId(Integer resourceConfigId) {
        this.resourceConfigId = resourceConfigId;
    }

    /**
     * 获取文件后缀名 如png mp4 
     *
     * @return type - 文件后缀名 如png mp4 
     */
    public String getType() {
        return type;
    }

    /**
     * 设置文件后缀名 如png mp4 
     *
     * @param type 文件后缀名 如png mp4 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取资源路径
     *
     * @return path - 资源路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置资源路径
     *
     * @param path 资源路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取名字系统分配的名称
     *
     * @return name - 名字系统分配的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字系统分配的名称
     *
     * @param name 名字系统分配的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取文件真实的名称
     *
     * @return real_name - 文件真实的名称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置文件真实的名称
     *
     * @param realName 文件真实的名称
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取状态 默认为0 开始状态 1关闭
     *
     * @return status - 状态 默认为0 开始状态 1关闭
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 默认为0 开始状态 1关闭
     *
     * @param status 状态 默认为0 开始状态 1关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取文件大小
     *
     * @return size - 文件大小
     */
    public Long getSize() {
        return size;
    }

    /**
     * 设置文件大小
     *
     * @param size 文件大小
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * 获取文件MD5值
     *
     * @return md5 - 文件MD5值
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置文件MD5值
     *
     * @param md5 文件MD5值
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 获取文件title主要为文件未能正确加载页面显示
     *
     * @return title - 文件title主要为文件未能正确加载页面显示
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文件title主要为文件未能正确加载页面显示
     *
     * @param title 文件title主要为文件未能正确加载页面显示
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文件的描述
     *
     * @return description - 文件的描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文件的描述
     *
     * @param description 文件的描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人userId
     *
     * @return create_admin_user_id - 创建人userId
     */
    public Integer getCreateAdminUserId() {
        return createAdminUserId;
    }

    /**
     * 设置创建人userId
     *
     * @param createAdminUserId 创建人userId
     */
    public void setCreateAdminUserId(Integer createAdminUserId) {
        this.createAdminUserId = createAdminUserId;
    }
}