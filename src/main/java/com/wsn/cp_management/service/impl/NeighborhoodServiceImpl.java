package com.wsn.cp_management.service.impl;

import com.wsn.cp_management.mapper.NeighborhoodMapper;
import com.wsn.cp_management.mapper.UserMapper;
import com.wsn.cp_management.pojo.Building;
import com.wsn.cp_management.pojo.Community;
import com.wsn.cp_management.pojo.House;
import com.wsn.cp_management.pojo.User;
import com.wsn.cp_management.service.NeighborhoodService;
import com.wsn.cp_management.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {
    @Autowired
    private NeighborhoodMapper neighborhoodMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Community>  getCommunity(Integer num) {
        Integer pageNum=(num-1)*15;
        List<Community> community=neighborhoodMapper.getCommunity(pageNum);
        return community;
    }

    @Override
    public List<Building> getBuilding(Integer num) {
        Integer pageNum=(num-1)*15;
        List<Building> building=neighborhoodMapper.getBuilding(pageNum);
        return building;
    }

    @Override
    public List<House> getHouse(Integer num) {
        Integer pageNum=(num-1)*15;
        List<House> house=neighborhoodMapper.getHouse(pageNum);
        return house;
    }

    @Override
    public void deleteCommunity(Integer id) {
        neighborhoodMapper.deleteCommunity(id);
    }

    @Override
    public void updateCommunity(Community community) {
        neighborhoodMapper.updateCommunity(community);
    }

    @Override
    public void insertCommunity(Community community) {
        neighborhoodMapper.insertCommunity(community);
    }

    @Override
    public void updateBuilding(Building building) { neighborhoodMapper.updateBuilding(building); }

    @Override
    public void insertBuilding(Building building) {
        System.out.println(building);
        Integer communityId = building.getCommunityId();
        String communityName=neighborhoodMapper.getCommunityName(communityId);
        building.setCommunityName(communityName);
        System.out.println(building);
        neighborhoodMapper.insertBuilding(building);
    }

    @Override
    public void deleteBuilding(Integer id) { neighborhoodMapper.deleteBuilding(id); }

    @Override
    public void insertHouse(House house) {
        // System.out.println(house);
        neighborhoodMapper.insertHouse(house);
    }

    @Override
    public void updateHouse(House house) { neighborhoodMapper.updateHouse(house); }

    @Override
    public void deleteHouse(Integer id) { neighborhoodMapper.deleteHouse(id); }

    @Override
    public Integer getCommunityTotal() {
        return neighborhoodMapper.getCommunityTotal();
    }

    @Override
    public Integer getBuildingTotal() {
        return neighborhoodMapper.getBuildingTotal();
    }

    @Override
    public Integer getHouseTotal() {
        return neighborhoodMapper.getHouseTotal();
    }

    @Override
    public List<Community> getCommunityNameAndId() {
        List<Community> community=neighborhoodMapper.getCommunityNameAndId();
        return community;
    }
}
