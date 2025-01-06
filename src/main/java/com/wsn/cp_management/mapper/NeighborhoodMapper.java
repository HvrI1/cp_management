package com.wsn.cp_management.mapper;

import com.wsn.cp_management.pojo.Building;
import com.wsn.cp_management.pojo.Community;
import com.wsn.cp_management.pojo.House;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NeighborhoodMapper {
    //获取当前用户小区详细信息
    @Select("select * from community limit #{pageNum},15;")
    List<Community> getCommunity(Integer pageNum);


    //获取当前用户楼栋详情
    @Select("select * from building limit #{pageNum},15;")
    List<Building> getBuilding(Integer pageNum);

    //获取当前用户房屋详情
    @Select("select * from house limit #{pageNum},15;")
    List<House> getHouse(Integer pageNum);

    //根据id删除小区信息
    @Delete("delete from community where id=#{id}")
    void deleteCommunity(Integer id);

    //更新小区信息
    @Update("update community set " +
            "name=#{name},address=#{address},area=#{area},total_buildings=#{totalBuildings}" +
            ",total_units=#{totalUnits},developer=#{developer},update_time=now() " +
            "where id=#{id}")
    void updateCommunity(Community community);

    //插入小区信息
    @Insert("insert into community " +
            "(name,address,area,total_buildings,total_units,developer,create_time,update_time) " +
            "values(#{name},#{address},#{area},#{totalBuildings},#{totalUnits},#{developer},now(),now())")
    void insertCommunity(Community community);

    //更新楼栋信息
    @Update("update building set " +
            "community_name=#{communityName},building_number=#{buildingNumber},total_floors=#{totalFloors}" +
            ",total_households=#{totalHouseholds},update_time=now() " +
            "where id=#{id}")
    void updateBuilding(Building building);

    //插入楼栋信息
    @Insert("insert into building " +
            "(community_id, community_name, building_number, total_floors, total_households, create_time, update_time) " +
            "values(#{communityId}, #{communityName}, #{buildingNumber}, #{totalFloors}, #{totalHouseholds}, now(), now())")
    void insertBuilding(Building building);


    //删除楼栋信息
    @Delete("delete from building where id=#{id}")
    void deleteBuilding(Integer id);

    //插入房屋信息
    @Insert("insert into house " +
            "(user_name,unit_number,floor_number,room_number,live_time,create_time,update_time) " +
            "values(#{userName},#{unitNumber},#{floorNumber},#{roomNumber},#{liveTime},now(),now())")
    void insertHouse(House house);

    //更新房屋信息
    @Update("update house set " +
            "user_name=#{userName},unit_number=#{unitNumber},floor_number=#{floorNumber}" +
            ",room_number=#{roomNumber},live_time=#{liveTime},update_time=now() " +
            "where id=#{id}")
    void updateHouse(House house);

    //删除房屋信息
    @Delete("delete from house where id=#{id}")
    void deleteHouse(Integer id);

    @Select("select count(*) from community")
    Integer getCommunityTotal();

    @Select("select count(*) from building")
    Integer getBuildingTotal();

    @Select("select count(*) from house")
    Integer getHouseTotal();

    @Select("select * from community")
    List<Community> getCommunityNameAndId();

    @Select("select name from community where id=#{communityId}")
    String getCommunityName(Integer communityId);

    @Select("select * from community where id=#{communityId}")
    Community getCommunityByInfo(Integer communityId);

    @Select("select id from building where community_name=#{name} AND building_number=#{buildingNum}")
    Integer getBuildingId(String name, Integer buildingNum);

    @Select("select id from house where building_id=#{buildingId} AND user_name=#{name}")
    Integer getHouseId(Integer buildingId, String name);

    @Insert("insert into house " +
            "(user_name,building_id,unit_number,floor_number,room_number,live_time,create_time,update_time) " +
            "values(#{userName},#{buildingId},#{unitNumber},#{floorNumber},#{roomNumber},#{liveTime},now(),now())")
    void infoInsertHouse(House house);

    @Update("update house set " +
            "user_id=#{userId} " +
            "where id=#{houseId}")
    void updateHouseByUserId(Integer userId,Integer houseId);
}
