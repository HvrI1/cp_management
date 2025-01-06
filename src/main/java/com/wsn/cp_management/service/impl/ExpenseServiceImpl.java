package com.wsn.cp_management.service.impl;

import com.wsn.cp_management.mapper.ExpenseMapper;
import com.wsn.cp_management.pojo.PropertyFee;
import com.wsn.cp_management.service.ExpenseService;
import com.wsn.cp_management.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseMapper expenseMapper;

    @Override
    public void deleteExpense(Integer id) {
        expenseMapper.deleteExpense(id);
    }

    @Override
    public void updateStatus(Integer id) {
        String status = "已缴费";
        expenseMapper.updateStatus(id,status);
    }

    @Override
    public List<PropertyFee> getUserExpense(Integer num) {
        Integer pageNum =(num-1)*10;

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<PropertyFee> list=expenseMapper.getUserExpense(id,pageNum);
        // System.out.println("list="+list);
        return list;
    }

    @Override
    public void updateExpense(PropertyFee propertyFee) {
        expenseMapper.updateExpense(propertyFee);
    }

    @Override
    public List<PropertyFee> getAllExpense(Integer num) {
        Integer pageNum =(num-1)*15;
        List<PropertyFee> list=expenseMapper.getAllExpense(pageNum);
        return list;
    }

    @Override
    public void insertExpense(PropertyFee propertyFee) {
        expenseMapper.insertExpense(propertyFee);
    }

    @Override
    public Integer getTotal() {

        Integer num=expenseMapper.getTotal();
        // System.out.println("num="+num);
        return num;
    }

    @Override
    public Integer getCPTotal() {
        Integer num=expenseMapper.getCPTotal();
        return num;
    }
}
