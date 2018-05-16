package com.yeta.sbl2.mapper;

import com.yeta.sbl2.pojo.RoleFunction;

import java.util.List;

public interface MyRoleFunctionMapper {

    List<RoleFunction> findRoleFunctionByRoleId(Integer roleId);
}