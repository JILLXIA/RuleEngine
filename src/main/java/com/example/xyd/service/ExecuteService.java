package com.example.xyd.service;

import com.example.xyd.entity.Rule;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 执行规则服务
 * @author xyd
 * @date 2020/6/14 12:11 下午
 */
public interface ExecuteService {

    /**
     * 执行任务
     * @param rule
     */
    void execute(Rule rule) throws MQClientException, RemotingException, InterruptedException, MQBrokerException;

}
