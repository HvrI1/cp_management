package com.wsn.cp_management.service;


import com.wsn.cp_management.pojo.User;

import java.util.List;

public interface UserService {

    //更新用户信息
    void update(User user);

    //根据用户名查询用户
    User findByUserName(String username);

    //注册
    User register(String username, String password,String role);

    //修改用户密码
    void updatePwd(String  newPwd);

    //获取全部用户信息
    List<User> getAllUserInfo(Integer num);

    //修改用户信息
    void updateUser(User user);

    //删除用户信息
    void deleteUser(Integer id);

    //更新用户头像
    void updateAvatar(String avatarUrl);

    //用户注册后填写信息
    void updateUserInfo(User user);

    Integer getTotal();
}
