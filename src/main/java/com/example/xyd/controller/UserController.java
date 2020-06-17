package com.example.xyd.controller;

import com.example.xyd.entity.User;
import com.example.xyd.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author xyd
 * @since 2020-06-13 22:01:25
 */
@Controller
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    /**
     * 通过用户ID查询用户积分
     *
     * @param userId 用户ID
     * @return 单条数据
     */
    @GetMapping("/user/score")
    @ApiOperation(value="查看用户的积分")
    @ResponseBody
    public Integer getUserScore(@RequestParam("userId") Long userId) {
        User user = userService.queryById(userId);
        if (user == null) {
            return null;
        }
        return user.getScore();
    }

    @GetMapping("/user/addScore")
    @ApiOperation(value="增加用户的积分")
    @ResponseBody
    public Integer addUserScore(@RequestParam("userId") Long userId,@RequestParam("value") int value) {
        User user = userService.addById(userId,value);
        if (user == null) {
            return null;
        }
        return user.getScore();
    }

    @GetMapping("/user/subScore")
    @ApiOperation(value="减少用户的积分")
    @ResponseBody
    public Integer subUserScore(@RequestParam("userId") Long userId,@RequestParam("value") int value) {
        User user = userService.subById(userId,value);
        if (user == null) {
            return null;
        }
        return user.getScore();
    }



    /**
     * 规则列表页面
     *
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/user")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        Model model) {
        PageInfo<User> pageInfo = userService.findAll(pageNo, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "user/user_list";
    }


    /**
     * 添加规则页面
     *
     * @param model
     * @return
     */
    @GetMapping("/user/add")
    public String userAddPage(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user/user_add";
    }


    /**
     * 编辑规则页面
     *
     * @param model
     * @return
     */
    @GetMapping("/user/edit")
    public String userAddPage(@RequestParam("id") Long id, Model model) {
        User user = userService.queryById(id);
        if (user == null) {
            return "redirect:/error?message=取数方法不存在";
        }

        model.addAttribute("user", user);
        return "user/user_edit";
    }



    /**
     * 删除规则请求
     *
     * @param id
     * @return
     */
    @GetMapping("/user/delete")
    public String userAddPage(@RequestParam("id") Long id) {
        User user = userService.queryById(id);
        if (user == null) {
            return "redirect:/error?message=取数方法不存在";
        }

        userService.deleteById(id);
        return "redirect:/user";
    }

    /**
     * 添加或修改规则，提交请求
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public String index(User user) {
        if (user.getId() == null) {
            userService.insert(user);
        } else {
            userService.update(user);
        }
        // 重定向到规则列表页面
        return "redirect:/user";
    }

}