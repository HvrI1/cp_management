package com.wsn.cp_management.controller;

import com.wsn.cp_management.pojo.Notice;
import com.wsn.cp_management.pojo.Result;
import com.wsn.cp_management.service.NoticeService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    //获取全部的公告
    @GetMapping("/getInfo")
    public Result<List<Notice>> getInfo(){
        List<Notice> list=noticeService.getInfo();
        return Result.success(list);
    }

    //获取全部的活动
    @GetMapping("/getActivity")
    public Result<List<Notice>> getActivity(){
        List<Notice> list=noticeService.getActivity();
        return Result.success(list);
    }

    //获取全部的内容
    @GetMapping("/getCont")
    public Result<List<Notice>> getCont(Integer num){
        List<Notice> list=noticeService.getCont(num);
        return Result.success(list);
    }

    //获取全部通知总条数
    @GetMapping("/getTotal")
    public Result<Integer> getTotal(){
        Integer num =noticeService.getTotal();
        return  Result.success(num);
    }

    //增
    @PostMapping("/insertNotice")
    public Result<Notice> insertNotice(@RequestBody Notice notice){
        noticeService.insertNotice(notice);
        return Result.success();
    }
    //删
    @DeleteMapping("/deleteNotice")
    public Result deleteNotice(Integer id){
        noticeService.deleteNotice(id);
        return Result.success();
    }
    //改
    @PutMapping("/updateNotice")
    public Result<Notice> updateNotice(@RequestBody Notice notice){
        noticeService.updateNotice(notice);
        return Result.success();
    }

    //公告信息分页接口
    @GetMapping("/getLimitInfo")
    public Result<List<Notice>> getLimitInfo(Integer num){
        List<Notice> list=noticeService.getLimitInfo(num);
        return Result.success(list);
    }
    //公告信息总条数
    @GetMapping("/getInfoTotal")
    public Result<Integer> getInfoTotal(){
        Integer num =noticeService.getInfoTotal();
        return Result.success(num);
    }


    //活动信息分页接口
    @GetMapping("/getLimitActivity")
    public Result<List<Notice>> getLimitActivity(Integer num){
        List<Notice> list=noticeService.getLimitActivity(num);
        return Result.success(list);
    }

    //活动信息总条数
    @GetMapping("/getActivityTotal")
    public Result<Integer> getActivityTotal(){
        Integer num =noticeService.getActivityTotal();
        return Result.success(num);
    }





}
