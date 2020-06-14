package com.example.xyd.scheduler;

import com.example.xyd.entity.Rule;
import com.example.xyd.service.ExecuteService;
import com.example.xyd.service.RuleService;
import com.example.xyd.service.impl.ExecuteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 定时器
 *
 * @author 言曌
 * @date 2020/6/14 11:57 上午
 */
@Component
public class RuleScheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ExecuteService executeService;

    @Autowired
    private RuleService ruleService;

    private static Logger log = LoggerFactory.getLogger(ExecuteServiceImpl.class);


    // 每一分钟执行 0 */1 * * * ?
    // 每五分钟执行 */5 * * * *
    // 每小时执行     0 * * * *
    // 每天执行       0 0 * * *
    // 每周执行       0 0 * * 0
    // 每月执行       0 0 1 * *
    // 每年执行       0 0 1 1 *
    // 每天12:06执行  0 06 12 ? * *

    @Scheduled(cron = "0 */1 * * * ?")
    public void testTasks() {
        log.info("定时任务开始执行，当前时间：" + dateFormat.format(new Date()));

        // 遍历所有规则
        List<Rule> ruleList = ruleService.findAll();
        for (Rule rule : ruleList) {
            executeService.execute(rule);
        }

    }

}
