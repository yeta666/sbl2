package com.yeta.sbl2.mapper;

import com.yeta.sbl2.pojo.UserRole;

import java.util.List;

public interface MyUserRoleMapper {

    List<UserRole> findUserRoleByUserId(Integer userId);
}