package com.hyp.blogmaster.shiro.pojo.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Table(name = "admin_user")
public class AdminUser implements Serializable {
    @Id
    private Integer id;

    @Column(name = "user_name")
    @NotNull(message = "用户名称不可为空")
    private String userName;

    @Column(name = "pass_word")
    @NotNull(message = "用户密码不可为空")
    private String passWord;

    /**
     * 是否启用  0启用1禁用
     */
    private Integer enable = 0;

    /**
     * 创建时间
     */
   /* @Column(name = "create_time")
    private Date createTime = new Date();*/

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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return pass_word
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取是否启用
     *
     * @return enable - 是否启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     *
     * @param enable 是否启用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

}