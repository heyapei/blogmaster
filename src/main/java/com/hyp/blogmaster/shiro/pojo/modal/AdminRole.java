package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "admin_role")
public class AdminRole implements Serializable {
    @Id
    private Integer id;

    @Column(name = "role_desc")
    private String roleDesc;

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
     * @return role_desc
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}