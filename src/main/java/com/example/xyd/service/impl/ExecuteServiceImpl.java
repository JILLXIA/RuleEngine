package com.example.xyd.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.xyd.entity.Method;
import com.example.xyd.entity.Rule;
import com.example.xyd.entity.User;
import com.example.xyd.service.ExecuteService;
import com.example.xyd.service.MethodService;
import com.example.xyd.service.RuleService;
import com.example.xyd.service.UserService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * 执行规则服务实现
 *
 * @author xyd
 * @date 2020/6/14 12:14 下午
 */
@Service
public class ExecuteServiceImpl implements ExecuteService {

    private static Logger log = LoggerFactory.getLogger(ExecuteServiceImpl.class);

    public static final String NAMESRV_ADDR = "127.0.0.1:9876";

    @Autowired
    private RuleService ruleService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private UserService userService;

    /**
     * 实例化RestTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(Rule rule) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        Long methodId = rule.getMethodId();
        // 获得取数方法
        Method method = methodService.queryById(methodId);
        if (method == null) {
            log.error("取数方法不存在，method={}", methodId);
            return;
        }

        // 获得取数的值
        List<User> userList = userService.findAll();
        for (User user : userList) {
            Integer result;
            try {
                String url = method.getUrl() + "?userId=" + user.getId();
                result = restTemplate.getForObject(url, Integer.class);
            } catch (Exception e) {
                log.error("取数方法URL调用失败，方法ID={}", methodId);
                e.printStackTrace();
                continue;
            }

            String operator = rule.getOperator();
            Integer minThreshold = rule.getMinThreshold();
            Integer maxThreshold = rule.getMaxThreshold();

            if(result >= minThreshold && result <= maxThreshold){
                // Rocketmq
                DefaultMQProducer producer = new DefaultMQProducer("PID_RULE_PRODUCER");
                producer.setNamesrvAddr(NAMESRV_ADDR);
                producer.start();
                String content = JSON.toJSONString(user);
                Message message = new Message("T_RULE_TOPIC",//主题
                    "RULE", //标签
                    content.getBytes()); //消息内容实体 (byte[])
                SendResult sendResult = producer.send(message);
                System.out.println("消息成功发出，结果：" + sendResult);


            }else{
                // 起一个新线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+"正在做...");

                    }
                }).start();
            }
            /*
            if (operator.equals(">")) {
                if (result > threshold) {
                    this.trigger(rule.getId(), user.getId());
                }
            } else if (operator.equals("<")) {
                if (result < threshold) {
                    this.trigger(rule.getId(), user.getId());
                }
            } else if (operator.equals(">=")) {
                if (result >= threshold) {
                    this.trigger(rule.getId(), user.getId());
                }
            } else if (operator.equals("<=")) {
                if (result <= threshold) {
                    this.trigger(rule.getId(), user.getId());
                }
            } else if (operator.equals("=")) {
                if (Objects.equals(result, threshold)) {
                    this.trigger(rule.getId(), user.getId());
                }
            }

             */
        }
    }

    /**
     * 触发调用方法
     */
    private void trigger(Long ruleId, Long userId) {
        log.info("规则成功触发成功，规则ID={},用户ID={}", ruleId, userId);
        // TODO
    }

}
