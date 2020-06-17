package com.example.xyd.entity;

import java.io.Serializable;

/**
 * (Method)实体类
 *
 * @author xyd
 * @since 2020-06-13 22:01:25
 */
public class Method implements Serializable {
    private static final long serialVersionUID = 794226678167065564L;
    /**
    * 取数方法ID
    */
    private Long id;
    /**
    * 取数方法名称
    */
    private String name;
    /**
    * 取数接口URL
    */
    private String url;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}