package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "admin_role_resources")
public class AdminRoleResources implements Serializable {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "resources_id")
    private Integer resourcesId;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return resources_id
     */
    public Integer getResourcesId() {
        return resourcesId;
    }

    /**
     * @param resourcesId
     */
    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }
}