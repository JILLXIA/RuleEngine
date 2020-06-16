package com.example.xyd.controller;

import com.example.xyd.entity.Method;
import com.example.xyd.service.MethodService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Method)表控制层
 *
 * @author saysky
 * @since 2020-06-13 22:01:25
 */
@Controller
public class MethodController {

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
    @GetMapping("/method")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        Model model) {
        PageInfo<Method> pageInfo = methodService.findAll(pageNo, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "method/method_list";
    }


    /**
     * 添加规则页面
     *
     * @param model
     * @return
     */
    @GetMapping("/method/add")
    public String methodAddPage(Model model) {
        List<Method> methodList = methodService.findAll();
        model.addAttribute("methodList", methodList);
        return "method/method_add";
    }


    /**
     * 编辑规则页面
     *
     * @param model
     * @return
     */
    @GetMapping("/method/edit")
    public String methodAddPage(@RequestParam("id") Long id, Model model) {
        Method method = methodService.queryById(id);
        if (method == null) {
            return "redirect:/error?message=取数方法不存在";
        }

        model.addAttribute("method", method);
        return "method/method_edit";
    }



    /**
     * 删除规则请求
     *
     * @param id
     * @return
     */
    @GetMapping("/method/delete")
    public String methodAddPage(@RequestParam("id") Long id) {
        Method method = methodService.queryById(id);
        if (method == null) {
            return "redirect:/error?message=取数方法不存在";
        }

        methodService.deleteById(id);
        return "redirect:/method";
    }

    /**
     * 添加或修改规则，提交请求
     *
     * @param method
     * @return
     */
    @PostMapping("/method")
    public String index(Method method) {
        if (method.getId() == null) {
            methodService.insert(method);
        } else {
            methodService.update(method);
        }
        // 重定向到规则列表页面
        return "redirect:/method";
    }


}