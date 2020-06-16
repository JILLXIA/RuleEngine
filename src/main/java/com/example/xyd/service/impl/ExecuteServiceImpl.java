package com.example.xyd.service.impl;

import com.example.xyd.entity.Method;
import com.example.xyd.entity.Rule;
import com.example.xyd.entity.User;
import com.example.xyd.service.ExecuteService;
import com.example.xyd.service.MethodService;
import com.example.xyd.service.RuleService;
import com.example.xyd.service.UserService;
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
 * @author 言曌
 * @date 2020/6/14 12:14 下午
 */
@Service
public class ExecuteServiceImpl implements ExecuteService {

    private static Logger log = LoggerFactory.getLogger(ExecuteServiceImpl.class);

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
    public void execute(Rule rule) {
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
