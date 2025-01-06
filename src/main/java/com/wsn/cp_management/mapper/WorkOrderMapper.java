package com.wsn.cp_management.mapper;

import com.wsn.cp_management.pojo.RepairOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkOrderMapper {

    // //修改工单状态
    // @Update("update repair_order set status=#{status} where user_id=#{userId}")
    // void updateState(String status,Integer userId);


    //当前用户提交工单
    @Insert("insert into repair_order (user_id,house_id,project,address,user_phone,description,status,create_time,update_time)" +
            "values(#{userId},#{houseId},#{projectContent},#{addressContent},#{userPhone},#{descriptionContent},#{status},now(),now())")
    void provideOrder(@Param("projectContent") String projectContent
            ,@Param("addressContent") String addressContent
            ,@Param("descriptionContent") String descriptionContent
            ,@Param("userId") Integer userId
            ,@Param("houseId") Integer houseId
            ,@Param("userPhone") String userPhone
            ,@Param("status")String status);


    //显示当前用户工单进度
    @Select("select id,project,address,user_phone,description,status,create_time,update_time " +
            "from repair_order " +
            "where user_id=#{userId}")
    List<RepairOrder> getOrderInfo(Integer userId);


    //删除当前用户工单历史记录
    @Delete("delete from repair_order where id=#{id}")
    void deleteOrderHistory(Integer id);

    //修改当前状态为已完成
    @Update("update repair_order set status=#{status},update_time=now() where id=#{id}")
    void updateStatus(Integer id,String status);

    //显示所有的工单记录
    @Select("select * from repair_order limit #{pageNum},10")
    List<RepairOrder> getAllOrderInfo(Integer pageNum);

    //根据月份查询记录
    @Select("SELECT COUNT(*) FROM repair_order WHERE MONTH(create_time) =#{month}")
    Integer getOrderByMonth(Integer month);

    @Select("select * from repair_order where user_id=#{userId} and status IN ('待处理', '进行中') limit #{pageNum} ,15")
    List<RepairOrder> getUserLimit(Integer pageNum,Integer userId);

    @Select("select count(*) from repair_order")
    Integer getTotal();

    @Select("select id,project,address,user_phone,description,status,create_time,update_time " +
            "from repair_order " +
            "where user_id=#{userId} and status IN ('已完成')  " +
            "limit #{pageNum} ,10")
    List<RepairOrder> getOrderInfoHistory(Integer userId, Integer pageNum);

    @Select("select count(*) from repair_order where user_id=#{userId} and status IN ('待处理', '进行中')")
    Integer getUserTotal();

    @Select("select id,project,address,user_phone,description,status,create_time,update_time " +
            "from repair_order " +
            "where status IN ('待处理')" +
            "limit #{pageNum},10")
    List<RepairOrder> getAdminOrderInfo(Integer pageNum);

    @Select("select count(*) " +
            "from repair_order " +
            "where status IN ('待处理')")
    Integer getAdminTotal();
}
