package com.wsn.cp_management.mapper;

import com.wsn.cp_management.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where username=#{username}")
    Admin findByAdminName(String username);

    //小区数量
    @Select("select count(*) from community")
    Integer getCommunityNumData();

    //楼栋数量
    @Select("SELECT SUM(total_buildings) FROM community;")
    Integer getBuildingNumData();

    //房屋数量
    @Select("SELECT SUM(total_units) FROM community;")
    Integer getHouseNumData();

    //入住户数
    @Select("select count(*) from house")
    Integer getLiveNumData();

    //租客数量
    @Select("select count(*) from user where role='租客'")
    Integer getRetentNumData();

    //业主人数
    @Select("select count(*) from user where role='业主'")
    Integer getHostNumData();

    //缴费超时数
    @Select("select count(*) from property_fee where payment_status='缴费超时'")
    Integer getOverTime();

    //已缴费数
    @Select("select count(*) from property_fee where payment_status='已缴费'")
    Integer getCompTime();

    //未缴费数
    @Select("select count(*) from property_fee where payment_status='待缴费'")
    Integer getNotTime();

    //用户人数趋势
    @Select("select count(*) from user where MONTH(create_time) =#{month}")
    Integer getUserNumData(int month);
}
