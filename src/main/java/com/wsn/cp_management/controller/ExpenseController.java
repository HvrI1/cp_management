package com.wsn.cp_management.controller;


import com.wsn.cp_management.pojo.PropertyFee;
import com.wsn.cp_management.pojo.RepairOrder;
import com.wsn.cp_management.pojo.Result;
import com.wsn.cp_management.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expense")
@Validated
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;


    //删除当前缴费记录
    @DeleteMapping("/deleteExpense")
    public Result deleteExpense(Integer id){
        expenseService.deleteExpense(id);
        return Result.success();
    }


    //修改当前的状态为已缴费
    @PutMapping("/updateStatus")
    public Result updateStatus(Integer id){
        expenseService.updateStatus(id);
        return Result.success();
    }

    //显示用户费用记录
    @GetMapping("/getUserExpense")
    public Result<List<PropertyFee>> getUserExpense(Integer num){
        // //声明一个新的集合filteredList，用来存储符合条件的RepairOrder
        // List<PropertyFee> filteredList = new ArrayList<>();
        //调用workOrderService.getOrderInfo()获取全部的工单
        List<PropertyFee> PropertyFeeList=expenseService.getUserExpense(num);
        // //遍历repairOrderList获取每个status
        // for (PropertyFee propertyFee : PropertyFeeList) {
        //     String status = propertyFee.getPaymentStatus();
        //     // 判断 status 是否为 "待处理"
        //     if ("待缴费".equals(status)) {
        //         // 如果是 "待处理"将该 RepairOrder 对象添加到 filteredList
        //         filteredList.add(propertyFee);
        //     }
        // }
        // //校验新的集合是否为空
        // if (filteredList.isEmpty()){
        //     return Result.error("当前用户暂无待处理的工单");
        // }
        return Result.success(PropertyFeeList);
    }
    //用户端总条数
    @GetMapping("/getTotal")
    public Result<Integer> getTotal(){
        Integer num=expenseService.getTotal();
        return Result.success(num);
    }

    //更新缴费信息
    @PutMapping("/updateExpense")
    public Result<PropertyFee> updateExpense(@RequestBody PropertyFee propertyFee){
        System.out.println(propertyFee);
        expenseService.updateExpense(propertyFee);
        return Result.success();
    }

    //查询所有缴费信息
    @GetMapping("/getAllExpense")
    public Result<List<PropertyFee>> getAllExpense(Integer num){
        List<PropertyFee> list=expenseService.getAllExpense(num);
        return Result.success(list);
    }

    //插入新的更新信息
    @PostMapping("/insertExpense")
    public Result<PropertyFee> insertExpense(@RequestBody PropertyFee propertyFee){
        expenseService.insertExpense(propertyFee);
        return Result.success();
    }

    //管理员端总条数
    @GetMapping("/getCPTotal")
    public Result<Integer> getCPTotal(){
        Integer num=expenseService.getCPTotal();
        return Result.success(num);
    }
}
