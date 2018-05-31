package com.yeta.sbl2.service;

import com.yeta.sbl2.pojo.User;
import com.yeta.sbl2.domain.MyResponse;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户逻辑层
 * Created by YETA666 on 2018/4/20 0020.
 */
public interface UserService {

    MyResponse saveUser(User user);

    MyResponse updateUser(User user);

    MyResponse deleteUser(int id);

    MyResponse queryUserById(int id);

    MyResponse findUserById(int id);

    MyResponse queryUsers(User user);

    MyResponse queryUsersPaged(Integer page, Integer pageSize, User user);

    MyResponse register(User user) throws MessagingException;

    MyResponse active(String code);

    MyResponse login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    MyResponse logout(HttpServletRequest request, HttpServletResponse response);

    MyResponse getMenu(HttpServletRequest request, HttpServletResponse response);

    MyResponse onlines(HttpServletRequest request, HttpServletResponse response);
}
