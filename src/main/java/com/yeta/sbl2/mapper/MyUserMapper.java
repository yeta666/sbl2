package com.yeta.sbl2.mapper;

import com.yeta.sbl2.pojo.User;

import java.util.List;

public interface MyUserMapper {

    User findUserById(Integer id);

    List<User> findUsers();

    User findUserByCode(String code);

    User findUserByUsername(String username);
}