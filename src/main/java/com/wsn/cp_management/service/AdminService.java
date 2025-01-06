package com.wsn.cp_management.service;

import com.wsn.cp_management.pojo.Admin;

import java.util.Map;


public interface AdminService {

    Admin findByAdminName(String username);

    Map getGeneralData();

    Map getFeeData();

    Map getUserNumData();
}
