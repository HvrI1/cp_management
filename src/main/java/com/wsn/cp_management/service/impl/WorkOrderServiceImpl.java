package com.wsn.cp_management.service.impl;

import com.wsn.cp_management.mapper.UserMapper;
import com.wsn.cp_management.mapper.WorkOrderMapper;
import com.wsn.cp_management.pojo.RepairOrder;
import com.wsn.cp_management.pojo.User;
import com.wsn.cp_management.service.WorkOrderService;
import com.wsn.cp_management.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
    @Autowired
    private WorkOrderMapper workOrderMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public void provideOrder(String projectContent,String addressContent,String descriptionContent) {

        //补充属性值
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userMapper.findByUserName(username);
        Integer userId=user.getId();
        Integer houseId=user.getHouseId();
        String userPhone=user.getPhone();
        String status ="待处理";
        workOrderMapper.provideOrder(projectContent
                ,addressContent
                ,descriptionContent
                ,userId
                ,houseId
                ,userPhone
                ,status);
    }

    @Override
    public List<RepairOrder> getOrderInfo() {

        //补充属性值
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<RepairOrder> orderInfo = workOrderMapper.getOrderInfo(userId);
        return orderInfo;
    }

    @Override
    public List<RepairOrder> getOrderHistory(Integer num) {
        Integer pageNum=(num-1)*10;

        //补充属性值
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<RepairOrder> orderList=workOrderMapper.getOrderInfoHistory(userId,pageNum);
        return orderList;
    }

    @Override
    public void deleteOrderHistory(Integer id) {
        workOrderMapper.deleteOrderHistory(id);
    }

    @Override
    public void updateStatus(Integer id) {
        String status = "已完成";
        workOrderMapper.updateStatus(id,status);
    }

    @Override
    public List<RepairOrder> getAllOrderInfo(Integer num) {
        Integer pageNum = (num-1)*10;
        return workOrderMapper.getAllOrderInfo(pageNum);
    }

    @Override
    public Map getOrderByMonth() {
        Map map=new LinkedHashMap();;
        for (int i=1;i<=12;i++){
            Integer value =workOrderMapper.getOrderByMonth(i);
            map.put(i+"月",value.toString());
        }
        return map;
    }

    @Override
    public List<RepairOrder> getUserLimit(Integer num) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Integer pageNum=(num-1)*15;
        List<RepairOrder> list=workOrderMapper.getUserLimit(pageNum,userId);
        return list;
    }

    @Override
    public Integer getTotal() {
        Integer num=workOrderMapper.getTotal();
        return num;
    }

    @Override
    public Integer getUserTotal() {
        Integer num =workOrderMapper.getUserTotal();
        return num;
    }

    @Override
    public List<RepairOrder> getAdminOrderInfo(Integer num) {
        Integer pageNum=(num-1)*10;
        List<RepairOrder> orderInfo = workOrderMapper.getAdminOrderInfo(pageNum);
        return orderInfo;
    }

    @Override
    public Integer getAdminTotal() {
        Integer num=workOrderMapper.getAdminTotal();
        return num;
    }


}
