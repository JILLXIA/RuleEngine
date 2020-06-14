package com.example.xyd.service;

import com.example.xyd.entity.Rule;

/**
 * 执行规则服务
 * @author 言曌
 * @date 2020/6/14 12:11 下午
 */
public interface ExecuteService {

    /**
     * 执行任务
     * @param rule
     */
    void execute(Rule rule);

}
