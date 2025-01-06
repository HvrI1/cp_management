package com.wsn.cp_management.service.impl;

import com.wsn.cp_management.mapper.AdminMapper;
import com.wsn.cp_management.pojo.Admin;
import com.wsn.cp_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin findByAdminName(String username) {

        return adminMapper.findByAdminName(username);
    }

    @Override
    public Map getGeneralData() {
        HashMap map = new HashMap<>();
        Integer cn= adminMapper.getCommunityNumData();
        Integer bn= adminMapper.getBuildingNumData();
        Integer hn= adminMapper.getHouseNumData();
        Integer ln= adminMapper.getLiveNumData();
        Integer rn= adminMapper.getRetentNumData();
        Integer hon= adminMapper.getHostNumData();
        map.put("小区数量",cn);
        map.put("楼宇数量",bn);
        map.put("房屋数量",hn);
        map.put("入住户数",ln);
        map.put("租客人数",rn);
        map.put("业主人数",hon);
        return map;
    }

    @Override
    public Map getFeeData() {
        HashMap map = new HashMap<>();
        Integer ot =adminMapper.getOverTime();
        Integer ct =adminMapper.getCompTime();
        Integer nt =adminMapper.getNotTime();
        map.put("缴费超时",ot);
        map.put("已缴费",ct);
        map.put("未缴费",nt);
        return map;
    }

    @Override
    public Map getUserNumData() {
        Map map=new LinkedHashMap();;
        for (int i=1;i<=12;i++){
            Integer value =adminMapper.getUserNumData(i);
            map.put(i+"月",value.toString());
        }
        System.out.println(map);

        return map;
    }
}
