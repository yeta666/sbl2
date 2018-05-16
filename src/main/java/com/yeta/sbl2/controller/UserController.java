package com.yeta.sbl2.controller;

import com.yeta.sbl2.pojo.User;
import com.yeta.sbl2.service.UserService;
import com.yeta.sbl2.utils.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @PostMapping(value = "/register")
    public MyResponse register(User user) throws MessagingException {
        return userService.register(user);
    }

    @GetMapping(value = "/active")
    public MyResponse active(@RequestParam(value = "code", required = true) String code) {
        return userService.active(code);
    }

    @PostMapping(value = "/login")
    public MyResponse login(@RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        return userService.login(username, password, request, response);
    }

    @GetMapping(value = "/logout")
    public MyResponse logout(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        return userService.logout(request, response);
    }

    @GetMapping(value = "/getMenu")
    public MyResponse getMenu(HttpServletRequest request, HttpServletResponse response) {
        return userService.getMenu(request, response);
    }
}
