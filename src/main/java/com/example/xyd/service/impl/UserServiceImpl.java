package com.example.xyd.service.impl;

import com.example.xyd.entity.User;
import com.example.xyd.dao.UserDao;
import com.example.xyd.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author xyd
 * @since 2020-06-13 22:01:25
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 根据id增加score
     *
     * @param id 主键
     * @param value 值
     * @return 实例对象
     */
    @Override
    public User addById(Long id,int value) {
        User user = this.userDao.queryById(id);
        int res = user.getScore()+value;
        user.setScore(res);
        return this.userDao.updateById(user);
    }

    /**
     * 根据id减少score
     *
     * @param id 主键
     * @param value 值
     * @return 实例对象
     */
    @Override
    public User subById(Long id,int value) {
        User user = this.userDao.queryById(id);
        int res = user.getScore()-value;
        user.setScore(res);
        return this.userDao.updateById(user);
    }



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }


    @Override
    public PageInfo<User> findAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userDao.queryAll(null);
        return new PageInfo<>(userList);
    }

    @Override
    public List<User> findAll() {
        return userDao.queryAll(null);
    }
}