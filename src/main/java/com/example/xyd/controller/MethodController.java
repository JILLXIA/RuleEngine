package com.example.xyd.controller;

import com.example.xyd.entity.Method;
import com.example.xyd.service.MethodService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Method)表控制层
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
@RestController
@RequestMapping("method")
public class MethodController {
    /**
     * 服务对象
     */
    @Resource
    private MethodService methodService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Method selectOne(Long id) {
        return this.methodService.queryById(id);
    }

}