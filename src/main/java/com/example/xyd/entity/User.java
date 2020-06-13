package com.example.xyd.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
public class User implements Serializable {
    private static final long serialVersionUID = 223255522539881567L;
    /**
    * ID
    */
    private Long id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 积分
    */
    private Integer score;
    /**
    * 用户类型：管理员admin、用户user
    */
    private String type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}