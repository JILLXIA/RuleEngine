package com.example.xyd.service.impl;

import com.example.xyd.entity.Method;
import com.example.xyd.dao.MethodDao;
import com.example.xyd.entity.Rule;
import com.example.xyd.service.MethodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Method)表服务实现类
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
@Service("methodService")
public class MethodServiceImpl implements MethodService {
    @Resource
    private MethodDao methodDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Method queryById(Long id) {
        return this.methodDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param method 实例对象
     * @return 实例对象
     */
    @Override
    public Method insert(Method method) {
        this.methodDao.insert(method);
        return method;
    }

    /**
     * 修改数据
     *
     * @param method 实例对象
     * @return 实例对象
     */
    @Override
    public Method update(Method method) {
        this.methodDao.update(method);
        return this.queryById(method.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.methodDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Method> findAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Method> methodList = methodDao.queryAll(null);
        return new PageInfo<>(methodList);
    }

    @Override
    public List<Method> findAll() {
        return methodDao.queryAll(null);
    }
}