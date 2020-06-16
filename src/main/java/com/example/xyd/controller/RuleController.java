package com.example.xyd.controller;

import com.example.xyd.entity.Method;
import com.example.xyd.entity.Rule;
import com.example.xyd.service.MethodService;
import com.example.xyd.service.RuleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Rule)表控制层
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
@RestController
public class RuleController {

    /**
     * 服务对象
     */
    @Resource
    private RuleService ruleService;

    /**
     * 服务对象
     */
    @Resource
    private MethodService methodService;

    /**
     * 规则列表页面
     *
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/rule")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        Model model) {
        PageInfo<Rule> pageInfo = ruleService.findAll(pageNo, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "rule/rule_list";
    }


    /**
     * 添加规则页面
     *
     * @param model
     * @return
     */
    @GetMapping("/rule/add")
    public String ruleAddPage(Model model) {
        List<Method> methodList = methodService.findAll();
        model.addAttribute("methodList", methodList);
        return "rule/rule_add";
    }


    /**
     * 编辑规则页面
     *
     * @param model
     * @return
     */
    @GetMapping("/rule/edit")
    public String ruleAddPage(@RequestParam("id") Long id, Model model) {
        Rule rule = ruleService.queryById(id);
        if (rule == null) {
            return "redirect:/error?message=规则不存在";
        }

        model.addAttribute("rule", rule);
        List<Method> methodList = methodService.findAll();
        model.addAttribute("methodList", methodList);
        return "rule/rule_edit";
    }



    /**
     * 删除规则请求
     *
     * @param id
     * @return
     */
    @GetMapping("/rule/delete")
    public String ruleAddPage(@RequestParam("id") Long id) {
        Rule rule = ruleService.queryById(id);
        if (rule == null) {
            return "redirect:/error?message=规则不存在";
        }

        ruleService.deleteById(id);
        return "redirect:/rule";
    }

    /**
     * 添加或修改规则，提交请求
     *
     * @param rule
     * @return
     */
    @PostMapping("/rule")
    public String index(Rule rule) {
        if (rule.getId() == null) {
            ruleService.insert(rule);
        } else {
            ruleService.update(rule);
        }
        // 重定向到规则列表页面
        return "redirect:/rule";
    }


}