package com.example.xyd.controller;

import com.example.xyd.entity.User;
import com.example.xyd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author saysky
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

}