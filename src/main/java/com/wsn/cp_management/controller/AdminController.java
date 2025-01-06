package com.wsn.cp_management.controller;

import com.wsn.cp_management.pojo.Admin;
import com.wsn.cp_management.pojo.Result;
import com.wsn.cp_management.service.AdminService;
import com.wsn.cp_management.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    //管理员登录
    @PostMapping("/login")
    public Result<String> login(String username, String password){
        //根据用户名查询用户
        Admin admin = adminService.findByAdminName(username);
        //判断用户是否存在
        if(admin==null){
            return Result.error("管理员账户错误");
        }
        //判断密码是否正确
        if(password.equals(admin.getPassword())){
            //成功
            Map<String, Object>claims = new HashMap<>();
            claims.put("id", admin.getId())   ;
            claims.put("username", admin.getUsername());
            String token = JwtUtil.genToken(claims);

            // //把token存储到redis中
            // ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            // operations.set(token,token,1, TimeUnit.HOURS);

            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    //查询管理员概况里房屋，小区，人数等相关数据接口
    @GetMapping("/getGeneralData")
    public Result<Map> getGeneralData() {
        Map map = adminService.getGeneralData();
        return Result.success(map);
    }

    //查询缴费状态数据
    @GetMapping("/getFeeData")
    public Result<Map> getFeeData(){
        Map map =adminService.getFeeData();
        return Result.success(map);
    }

    //查询用户数量趋势
    @GetMapping("/getUserNumData")
    public Result<Map> getUserNumData(){
        Map map =adminService.getUserNumData();
        return Result.success(map);
    }
}
