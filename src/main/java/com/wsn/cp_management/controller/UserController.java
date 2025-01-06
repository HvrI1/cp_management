package com.wsn.cp_management.controller;

import com.wsn.cp_management.pojo.Result;
import com.wsn.cp_management.pojo.User;
import com.wsn.cp_management.service.UserService;
import com.wsn.cp_management.utils.JwtUtil;
import com.wsn.cp_management.utils.Md5Util;
import com.wsn.cp_management.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Delete;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //用户登录接口
    @PostMapping("/login")
    public Result<String> login(String username,String password){
        //根据用户名查询用户
        User u = userService.findByUserName(username);
        //判断用户是否存在
        if(u==null){
            return Result.error("用户名错误");
        }
        //判断密码是否正确
        if(Md5Util.getMD5String(password).equals(u.getPassword())){
            //成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);

            // //把token存储到redis中
            // ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            // operations.set(token,token,1, TimeUnit.HOURS);

            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    //用户注册接口
    @PostMapping("/register")
    public Result<User> register( String username, String password,String role){
        //查询用户
        User u=userService.findByUserName(username);

        if(u==null){
            //没有占用
            //注册
            User user =userService.register(username,password,role);
            return Result.success(user);
        }else{
            return Result.error("用户名已被占用");
        }

    }

    //更改用户信息接口
    @PutMapping("/update")
    public Result update(@Validated @RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    //修改用户密码接口
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestParam Map<String,String> params/*,@RequestHeader String token*/){

        //取出前端传入的三种密码
        String oldPwd = params.get("oldPwd");//用户输入的旧密码
        String newPwd = params.get("newPwd");//用户输入的新密码
        String rePwd = params.get("rePwd");//再次确认密码
        //校验是否为空，但凡有一个为空返回错误
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        //取出数据库的原密码比对
        Map<String,String> map = ThreadLocalUtil.get();
        String username = map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }
        //判断新密码和确认密码是否一致
        if(!rePwd.equals(newPwd)){
            return Result.error("新密码和确认密码不一致");
        }
        //调用service完成密码更新
        userService.updatePwd(newPwd);
        // //删除redis中的token
        // ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // operations.getOperations().delete(token);
        return Result.success("修改密码成功，请重新登录");
    }

    //获取用户详细信息接口
    @GetMapping("/getUserInfo")
    public Result<User> getUserInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    //获取全部的用户信息
    @GetMapping("/getAllUserInfo")
    public Result<List<User>> getAllUserInfo(Integer num){
        List<User> list=userService.getAllUserInfo(num);
        return Result.success(list);
    }

    //获取全部用户信息的条数
    @GetMapping("/getTotal")
    public Result<Integer> getTotal(){
        Integer num=userService.getTotal();
        return Result.success(num);
    }
    //修改当前这条信息
    @PutMapping("/updateUser")
    public Result<User> updateUser(@RequestBody  User user){
        userService.updateUser(user);
        return Result.success();
    }

    //删除当前这条信息
    @DeleteMapping("/deleteUser")
    public Result deleteUser(Integer id){
        userService.deleteUser(id);
        return Result.success();
    }

    //更新用户头像接口
    @PatchMapping("/updateAvatar")
    public  Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //用户注册后填写信息
    @PutMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody User user){
        // System.out.println(user);

        userService.updateUserInfo(user);
        return Result.success();
    }


}
