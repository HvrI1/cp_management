package com.wsn.cp_management.service.impl;

import com.wsn.cp_management.mapper.NeighborhoodMapper;
import com.wsn.cp_management.mapper.UserMapper;
import com.wsn.cp_management.pojo.Building;
import com.wsn.cp_management.pojo.Community;
import com.wsn.cp_management.pojo.House;
import com.wsn.cp_management.pojo.User;
import com.wsn.cp_management.service.UserService;
import com.wsn.cp_management.utils.Md5Util;
import com.wsn.cp_management.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NeighborhoodMapper neighborhoodMapper;

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());

        userMapper.update(user);
    }

    @Override
    public User findByUserName(String username) {
        User user= userMapper.findByUserName(username);
        return user;
    }

    @Override
    public User  register(String username, String password,String role) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //注册
        userMapper.register(username,md5String,role);

        User byUserName = userMapper.findByUserName(username);
        return byUserName;

    }

    @Override
    public void updatePwd(String  newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String newPwdMd5 = Md5Util.getMD5String(newPwd);

        userMapper.updatePwd(newPwdMd5,id);
    }

    @Override
    public List<User> getAllUserInfo(Integer num) {
        Integer pageNum=(num-1)*15;
        List<User> list =userMapper.getAllUserInfo(pageNum);
        return list;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updateUserInfo(User user) {

        //当用户选择完小区id，楼栋id，房屋id应该进行的相应操作
        //1.小区表不需要有操作
        Integer communityId = user.getCommunityId();
        Community community = neighborhoodMapper.getCommunityByInfo(communityId);
        //2.楼栋表需要新增一条数据
        Building building = new Building();
        building.setCommunityName(community.getName());
        building.setCommunityId(communityId);
        building.setBuildingNumber(user.getBuildingNum());
        Integer totalFloors=community.getTotalUnits()/community.getTotalBuildings()/4;
        building.setTotalFloors(totalFloors);
        Integer totalHouseholds=community.getTotalUnits()/community.getTotalBuildings();
        building.setTotalHouseholds(totalHouseholds);
        neighborhoodMapper.insertBuilding(building);
        String comName = community.getName();
        Integer buildingNum = user.getBuildingNum();
        Integer buildingId=neighborhoodMapper.getBuildingId(comName,buildingNum);
        //3.房屋表需要新增一条数据
        House house = new House();
        house.setUserId(user.getId());//1111111111111111111111111
        house.setUserName(user.getName());
        house.setBuildingId(buildingId);
        house.setUnitNumber(user.getBuildingNum());
        Integer floorNumber=user.getHouseNum()/4+1;
        house.setFloorNumber(floorNumber);
        house.setRoomNumber(user.getHouseNum());
        house.setLiveTime(Date.from(Instant.now()));
        neighborhoodMapper.infoInsertHouse(house);
        String userName = user.getName();
        Integer houseId= neighborhoodMapper.getHouseId(buildingId,userName);
        //4.查询新增加字段id，并赋给user表
        user.setBuildingId(buildingId);
        user.setHouseId(houseId);
        // System.out.println(user);
        userMapper.updateUserInfo(user);
        Integer userId=userMapper.getUserById(user.getName());
        neighborhoodMapper.updateHouseByUserId(userId,houseId);
    }

    @Override
    public Integer getTotal() {
        return userMapper.getTotal();
    }
}
