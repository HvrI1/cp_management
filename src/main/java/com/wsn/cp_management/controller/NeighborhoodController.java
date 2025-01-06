package com.wsn.cp_management.controller;

import com.wsn.cp_management.pojo.Building;
import com.wsn.cp_management.pojo.Community;
import com.wsn.cp_management.pojo.House;
import com.wsn.cp_management.pojo.Result;
import com.wsn.cp_management.service.NeighborhoodService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neighborhood")
@Validated
public class NeighborhoodController {
    @Autowired
    private NeighborhoodService neighborhoodService;

    //获取当前用户小区详细信息
    @GetMapping("/getCommunity")
    public Result<List<Community>> getCommunity(Integer num){
        List<Community> community=neighborhoodService.getCommunity(num);
        return Result.success(community);
    }

    @GetMapping("/getCommunityNameAndId")
    public Result<List<Community>> getCommunityNameAndId(){
        List<Community> community=neighborhoodService.getCommunityNameAndId();
        return Result.success(community);
    }


    @GetMapping("/getCommunityTotal")
    public Result<Integer> getCommunityTotal(){
        Integer num = neighborhoodService.getCommunityTotal();
        return Result.success(num);
    }

    //删除小区信息
    @DeleteMapping("/deleteCommunity")
    public Result deleteCommunity(Integer id){
        neighborhoodService.deleteCommunity(id);
        return Result.success("删除成功");
    }
    //更新小区信息
    @PutMapping("/updateCommunity")
    public Result<Community>  updateCommunity(@RequestBody Community community){
        System.out.println(community);
        neighborhoodService.updateCommunity(community);
        return Result.success();
    }

    //添加小区信息
    @PostMapping("/insertCommunity")
    public Result<Community> insertCommunity(@RequestBody Community community){
        System.out.println(community);
        neighborhoodService.insertCommunity(community);
        return Result.success();
    }


    //获取当前用户楼栋详情
    @GetMapping ("/getBuilding")
    public Result<List<Building>> getBuilding(Integer num){
        List<Building> building= neighborhoodService.getBuilding(num);
        return Result.success(building);
    }

    @GetMapping("/getBuildingTotal")
    public Result<Integer> getBuildingTotal(){
        Integer num = neighborhoodService.getBuildingTotal();
        return Result.success(num);
    }

    //更新楼栋信息
    @PutMapping("/updateBuilding")
    public Result<Building> updateBuilding(@RequestBody Building building){
        neighborhoodService.updateBuilding(building);
        return Result.success();
    }

    //插入楼栋信息
    @PostMapping("/insertBuilding")
    public Result<Building> insertBuilding(@RequestBody  Building building){

        neighborhoodService.insertBuilding(building);
        return Result.success();
    }
    //删除楼栋信息
    @DeleteMapping("/deleteBuilding")
    public Result deleteBuilding(Integer id){
        neighborhoodService.deleteBuilding(id);
        return Result.success();
    }

    //获取当前用户房屋详情
    @GetMapping ("/getHouse")
    public Result<List<House>> getHouse(Integer num){
        List<House> house= neighborhoodService.getHouse(num);
        return Result.success(house);
    }

    @GetMapping("/getHouseTotal")
    public Result<Integer> getHouseTotal(){
        Integer num = neighborhoodService.getHouseTotal();
        return Result.success(num);
    }

    //插入房屋信息
    @PostMapping("/insertHouse")
    public Result<House> insertHouse(@RequestBody House house){
        // System.out.println(house);
        neighborhoodService.insertHouse(house);
        return Result.success();
    }
    //更新房屋信息
    @PutMapping("/updateHouse")
    public Result<House> updateHouse(@RequestBody House house){
        neighborhoodService.updateHouse(house);
        return Result.success();
    }
    //删除房屋信息
    @DeleteMapping("/deleteHouse")
    public Result deleteHouse(Integer id){
        neighborhoodService.deleteHouse(id);
        return Result.success();
    }




}
