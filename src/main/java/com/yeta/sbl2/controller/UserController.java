package com.yeta.sbl2.controller;

import com.yeta.sbl2.pojo.User;
import com.yeta.sbl2.service.UserService;
import com.yeta.sbl2.utils.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关操作接口定义
 * Created by YETA666 on 2018/4/20 0020.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/saveUser")
    public MyResponse saveUser(User user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/updateUser")
    public MyResponse updateUser(User user) {
        return userService.updateUser(user);
    }

    @GetMapping(value = "/deleteUser")
    public MyResponse deleteUser(@RequestParam(value = "id", required = true) int id) {
        return userService.deleteUser(id);
    }

    @GetMapping(value = "/queryUserById")
    public MyResponse queryUserById(@RequestParam(value = "id", required = true) int id) {
        return userService.queryUserById(id);
    }

    @GetMapping(value = "/findUserById")
    public MyResponse findUserById(@RequestParam(value = "id", required = true) int id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/queryUsers")
    public MyResponse queryUsers(User user) {
        return userService.queryUsers(user);
    }

    @GetMapping(value = "/queryUsersPaged")
    public MyResponse queryUsersPaged(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                      User user) {
        return userService.queryUsersPaged(page, pageSize, user);
    }
}
