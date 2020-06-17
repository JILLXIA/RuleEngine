package com.example.xyd.service;

import com.example.xyd.entity.Method;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Method)表服务接口
 *
 * @author xyd
 * @since 2020-06-13 22:01:25
 */
public interface MethodService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Method queryById(Long id);

    /**
     * 新增数据
     *
     * @param method 实例对象
     * @return 实例对象
     */
    Method insert(Method method);

    /**
     * 修改数据
     *
     * @param method 实例对象
     * @return 实例对象
     */
    Method update(Method method);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    /**
     * 分页查询
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return
     */
    PageInfo<Method> findAll(Integer pageNo, Integer pageSize);

    /**
     * 查询所有
     * @return
     */
    List<Method> findAll();

}