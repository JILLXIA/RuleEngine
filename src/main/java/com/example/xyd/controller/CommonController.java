package com.example.xyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xyd
 * @date 2020/6/13 11:21 下午
 */
@Controller
public class CommonController {

    /**
     * 首页跳转到规则页面
     *
     * @return
     */
    @GetMapping
    public String index() {
        return "redirect:/rule";
    }

    /**
     * 首页跳转到规则页面
     *
     * @return
     */
    @GetMapping("/error")
    public String index(@RequestParam(value = "message", required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "redirect:/error";
    }


}
