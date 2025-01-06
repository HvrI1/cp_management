package com.wsn.cp_management.service;

import com.wsn.cp_management.pojo.RepairOrder;

import java.util.List;
import java.util.Map;

public interface WorkOrderService {
    //当前用户提交工单
    void provideOrder(String projectContent,String addressContent,String descriptionContent);

    //显示当前用户工单进度
    List<RepairOrder> getOrderInfo();

    //当前用户工单历史记录
    List<RepairOrder> getOrderHistory(Integer num);

    //删除当前用户工单历史记录
    void deleteOrderHistory(Integer id);

    //修改当前状态为已完成
    void updateStatus(Integer id);

    //显示所有的工单记录
    List<RepairOrder> getAllOrderInfo(Integer num);

    //根据月份查询记录
    Map getOrderByMonth();

    List<RepairOrder> getUserLimit(Integer num);

    Integer getTotal();

    Integer getUserTotal();

    List<RepairOrder> getAdminOrderInfo(Integer num);

    Integer getAdminTotal();
}
