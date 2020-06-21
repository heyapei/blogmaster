package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "admin_user_role")
public class AdminUserRole implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private String roleId;


}