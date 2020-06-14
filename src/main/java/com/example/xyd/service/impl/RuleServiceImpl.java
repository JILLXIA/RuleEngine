package com.example.xyd.service.impl;

import com.example.xyd.dao.MethodDao;
import com.example.xyd.entity.Method;
import com.example.xyd.entity.Rule;
import com.example.xyd.dao.RuleDao;
import com.example.xyd.service.RuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Rule)表服务实现类
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
@Service("ruleService")
public class RuleServiceImpl implements RuleService {
    @Resource
    private RuleDao ruleDao;

    @Resource
    private MethodDao methodDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Rule queryById(Long id) {
        return this.ruleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param rule 实例对象
     * @return 实例对象
     */
    @Override
    public Rule insert(Rule rule) {
        this.ruleDao.insert(rule);
        return rule;
    }

    /**
     * 修改数据
     *
     * @param rule 实例对象
     * @return 实例对象
     */
    @Override
    public Rule update(Rule rule) {
        this.ruleDao.update(rule);
        return this.queryById(rule.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ruleDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Rule> findAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Rule> ruleList = ruleDao.queryAll(null);
        // 如果考虑性能问题，可以考虑用 LEFT JOIN 连表查询
        for (Rule rule : ruleList) {
            Method method = methodDao.queryById(rule.getMethodId());
            if (method != null) {
                rule.setMethodName(method.getName());
            }
        }
        return new PageInfo<>(ruleList);
    }

    @Override
    public List<Rule> findAll() {
        return ruleDao.queryAll(null);
    }
}