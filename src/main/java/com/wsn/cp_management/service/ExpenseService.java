package com.wsn.cp_management.service;

import com.wsn.cp_management.pojo.PropertyFee;

import java.util.List;

public interface ExpenseService {

    //删除当前用户缴费记录
    void deleteExpense(Integer id);

    //修改当前的工单状态为已完成
    void updateStatus(Integer id);

    //显示用户费用记录
    List<PropertyFee> getUserExpense(Integer num);

    //更新缴费信息
    void updateExpense(PropertyFee propertyFee);

    //查询所有缴费信息
    List<PropertyFee> getAllExpense(Integer num);

    //插入新的更新信息
    void insertExpense(PropertyFee propertyFee);

    Integer getTotal();

    Integer getCPTotal();
}
