package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "admin_resources")
@Data
public class AdminResources implements Serializable {
    @Id
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源url
     */
    @Column(name = "res_url")
    private String resUrl;

    /**
     * 资源类型   1:菜单    2：按钮
     */
    private Integer type;

    /**
     * 父资源
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源url
     *
     * @return res_url - 资源url
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 设置资源url
     *
     * @param resUrl 资源url
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /**
     * 获取资源类型   1:菜单    2：按钮
     *
     * @return type - 资源类型   1:菜单    2：按钮
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置资源类型   1:菜单    2：按钮
     *
     * @param type 资源类型   1:菜单    2：按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取父资源
     *
     * @return parent_id - 父资源
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父资源
     *
     * @param parentId 父资源
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}