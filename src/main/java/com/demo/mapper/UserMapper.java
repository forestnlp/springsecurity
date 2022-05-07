package com.demo.mapper;

import com.demo.entity.User;

import java.util.List;

public interface UserMapper {
    User selectByUserName(String userName);

    List<String> selectAllRoleByUserId(Integer userId);

    List<String> selectPermissionsByUserId(Integer userId);

}
