package com.example.xyd.service;

import com.example.xyd.entity.Rule;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * (Rule)表服务接口
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
public interface RuleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Rule queryById(Long id);

    /**
     * 新增数据
     *
     * @param rule 实例对象
     * @return 实例对象
     */
    Rule insert(Rule rule);

    /**
     * 修改数据
     *
     * @param rule 实例对象
     * @return 实例对象
     */
    Rule update(Rule rule);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Rule> findAll(Integer pageNo, Integer pageSize);



}