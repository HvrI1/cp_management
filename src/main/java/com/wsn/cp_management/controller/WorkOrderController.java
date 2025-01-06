package com.wsn.cp_management.controller;

import com.wsn.cp_management.pojo.RepairOrder;
import com.wsn.cp_management.pojo.Result;
import com.wsn.cp_management.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/workorder")
@Validated
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;

    //当前用户提交工单
    @PostMapping("/provideOrder")
    public Result provideOrder(String project, String address,String description){
        String projectContent =project;
        String addressContent =address;
        String descriptionContent =description;
        //校验参数
        if(projectContent==null||addressContent==null||descriptionContent==null){
            return Result.error("参数不能为空");
        }
        workOrderService.provideOrder(projectContent,addressContent,descriptionContent);
        return Result.success();
    }

    //显示当前用户工单进度(用户)
    @GetMapping("/getOrderInfo")
    public Result<List<RepairOrder>> getOrderInfo(){
        //声明一个新的集合filteredList，用来存储符合条件的RepairOrder
        List<RepairOrder> filteredList = new ArrayList<>();
        //调用workOrderService.getOrderInfo()获取全部的工单
        List<RepairOrder> repairOrderList=workOrderService.getOrderInfo();
        //遍历repairOrderList获取每个status
        for (RepairOrder repairOrder : repairOrderList) {
            String status = repairOrder.getStatus();
            // 判断 status 是否为 "待处理" 或 "进行中"
            if ("待处理".equals(status) || "进行中".equals(status)) {
                // 如果是 "待处理" 或 "进行中"，将该 RepairOrder 对象添加到 filteredList
                filteredList.add(repairOrder);
            }
        }
        //校验新的集合是否为空
        if (filteredList.isEmpty()){
            return Result.error("当前用户暂无待处理或进行中的工单");
        }
        return Result.success(filteredList);
    }

    //显示当前用户工单进度（管理员）
    @GetMapping("/dealOrder")
    public Result<List<RepairOrder>> dealOrder(Integer num){
        // //声明一个新的集合filteredList，用来存储符合条件的RepairOrder
        // List<RepairOrder> filteredList = new ArrayList<>();
        //调用workOrderService.getOrderInfo()获取全部的工单
        List<RepairOrder> repairOrderList=workOrderService.getAdminOrderInfo(num);
        // //遍历repairOrderList获取每个status
        // for (RepairOrder repairOrder : repairOrderList) {
        //     String status = repairOrder.getStatus();
        //     // 判断 status 是否为 "待处理"
        //     if ("待处理".equals(status)) {
        //         // 如果是 "待处理"将该 RepairOrder 对象添加到 filteredList
        //         filteredList.add(repairOrder);
        //     }
        // }
        // //校验新的集合是否为空
        // if (filteredList.isEmpty()){
        //     return Result.error("当前用户暂无待处理的工单");
        // }
        return Result.success(repairOrderList);
    }

    //修改当前的工单状态为已完成
    @PutMapping("/updateStatus")
    public Result updateStatus(Integer id){
        workOrderService.updateStatus(id);
        return Result.success();
    }

    //显示所有的工单记录
    @GetMapping("/getAllOrderInfo")
    public Result<List<RepairOrder>> getAllOrderInfo(Integer num){
        List<RepairOrder> list=workOrderService.getAllOrderInfo(num);
        return Result.success(list);
    }


    //当前用户工单历史记录
    @GetMapping("/getOrderHistory")
    public Result<List<RepairOrder>> getOrderHistory(Integer num){

        // //声明一个新的集合filteredList，用来存储符合条件的RepairOrder
        // List<RepairOrder> filteredList = new ArrayList<>();
        //调用workOrderService.getOrderInfo()获取全部的工单
        List<RepairOrder> orderList=workOrderService.getOrderHistory(num);
        // //遍历repairOrderList获取每个status
        // for (RepairOrder repairOrder : orderList) {
        //     String status = repairOrder.getStatus();
        //     // 判断 status 是否为 "待处理" 或 "进行中"
        //     if ("已完成".equals(status)) {
        //         // 如果是 "待处理" 或 "进行中"，将该 RepairOrder 对象添加到 filteredList
        //         filteredList.add(repairOrder);
        //     }
        // }
        // //校验新的集合是否为空
        // if (filteredList.isEmpty()){
        //     return Result.error("当前用户暂无已完成的历史记录");
        // }
        return Result.success(orderList);
    }

    //删除当前用户工单历史记录
    @DeleteMapping("/deleteOrderHistory")
    public Result deleteOrderHistory(Integer id){
        workOrderService.deleteOrderHistory(id);
        return Result.success();
    }

    //根据月份查询记录
    @GetMapping("/getOrderByMonth")
    public Result<Map> getOrderByMonth(){
        Map map =workOrderService.getOrderByMonth();
        return Result.success(map);
    }

    //分页
    @GetMapping("/getUserLimit")
    public Result<List<RepairOrder>> getLimit(Integer num){
        List<RepairOrder> list=workOrderService.getUserLimit(num);
        return Result.success(list);
    }

    //总条数
    @GetMapping("/getTotal")
    public Result<Integer> getTotal(){
        Integer num=workOrderService.getTotal();
        return Result.success(num);
    }

    //总条数
    @GetMapping("/getUserTotal")
    public Result<Integer> getUserTotal(){
        Integer num=workOrderService.getUserTotal();
        return Result.success(num);
    }

    //总条数
    @GetMapping("/getAdminTotal")
    public Result<Integer> getAdminTotal(){
        Integer num=workOrderService.getAdminTotal();
        return Result.success(num);
    }

}
