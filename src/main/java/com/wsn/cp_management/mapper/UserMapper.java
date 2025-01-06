package com.wsn.cp_management.mapper;

import com.wsn.cp_management.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //注册
    @Insert("insert into user(username,password,role,create_time,update_time)" +
            " values(#{username},#{password},#{role},now(),now())")
    void register(String username, String password,String role);

    //更新用户信息
    @Update("update user set username=#{username},role=#{role},phone=#{phone},sex=#{sex},name=#{name}" +
            ",email=#{email},remark=#{remark},picture=#{picture},update_time=#{updateTime}" +
            "where id=#{id}")
    void update(User user);

    //更改用户密码
    @Update("update user set password=#{newPwd} where id= #{id}")
    void updatePwd(String newPwd,Integer id);

    //获取全部用户信息
    @Select("select * from user limit #{pageNum},15;")
    List<User> getAllUserInfo(Integer pageNum);

    //修改用户信息
    @Update("update user set username=#{username},role=#{role},community_id=#{communityId} " +
            ",building_id=#{buildingId},house_id=#{houseId},phone=#{phone},sex=#{sex} " +
            ",name=#{name},email=#{email},remark=#{remark},update_time=now() " +
            "where id=#{id}")
    void updateUser(User user);

    //删除用户信息
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    //更新用户头像
    @Update("update user set picture=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    //用户注册后填写信息
    @Update("update user set community_id=#{communityId},building_id=#{buildingId} " +
            ",house_id=#{houseId},phone=#{phone} ,sex=#{sex},name=#{name},update_time= now() " +
            "where id=#{id}")
    void updateUserInfo(User user);

    @Select("select count(*) from user")
    Integer getTotal();

    @Select("select id from user where name=#{name}")
    Integer getUserById(String name);
}
