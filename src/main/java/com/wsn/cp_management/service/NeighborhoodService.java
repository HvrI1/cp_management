package com.wsn.cp_management.service;

import com.wsn.cp_management.pojo.Building;
import com.wsn.cp_management.pojo.Community;
import com.wsn.cp_management.pojo.House;

import java.util.List;

public interface NeighborhoodService {
    //获取用户小区详细信息
    List<Community>  getCommunity(Integer num);

    //获取用户楼栋详情
    List<Building> getBuilding(Integer num);

    //获取用户房屋详情
    List<House> getHouse(Integer num);

    //根据id删除小区信息
    void deleteCommunity(Integer id);

    //更新小区信息
    void updateCommunity(Community community);

    //插入小区信息
    void insertCommunity(Community community);

    //更新楼栋信息
    void updateBuilding(Building building);

    //插入楼栋信息
    void insertBuilding(Building building);

    //删除楼栋信息
    void deleteBuilding(Integer id);

    //插入房屋信息
    void insertHouse(House house);

    //更新房屋信息
    void updateHouse(House house);

    //删除房屋信息
    void deleteHouse(Integer id);

    Integer getCommunityTotal();

    Integer getBuildingTotal();

    Integer getHouseTotal();

    List<Community> getCommunityNameAndId();
}
