package com.yeta.sbl2.service;

import com.yeta.sbl2.pojo.User;
import com.yeta.sbl2.utils.MyResponse;

/**
 * 用户service
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

}
