package com.example.xyd.dao;

import com.example.xyd.entity.Method;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Method)表数据库访问层
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
@Mapper
public interface MethodDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Method queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Method> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param method 实例对象
     * @return 对象列表
     */
    List<Method> queryAll(Method method);

    /**
     * 新增数据
     *
     * @param method 实例对象
     * @return 影响行数
     */
    int insert(Method method);

    /**
     * 修改数据
     *
     * @param method 实例对象
     * @return 影响行数
     */
    int update(Method method);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}