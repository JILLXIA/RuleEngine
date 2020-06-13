package com.example.xyd.service;

import com.example.xyd.entity.User;
import com.github.pagehelper.PageInfo;


/**
 * (User)表服务接口
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

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
    PageInfo<User> findAll(Integer pageNo, Integer pageSize);

}