package com.example.xyd.entity;

import java.io.Serializable;

/**
 * (Rule)实体类
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
public class Rule implements Serializable {
    private static final long serialVersionUID = 845962510029786334L;
    /**
    * 规则ID
    */
    private Long id;
    /**
    * 规则名称
    */
    private String name;
    /**
    * 取数方法ID
    */
    private Long methodId;
    /**
    * 运算符：>、<、>=、<=、=
    */
    private String operator;
    /**
    * 阈值
    */
    private Integer threshold;

    /**
     * 取数方法名称：根据methodId去method表查询
     */
    private String methodName;


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

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}