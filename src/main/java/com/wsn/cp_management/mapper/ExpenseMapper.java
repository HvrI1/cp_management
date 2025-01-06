package com.wsn.cp_management.mapper;

import com.wsn.cp_management.pojo.PropertyFee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    //显示当前用户缴费记录
    @Select("select id,project,amount,payment_status,due_date,create_time,update_time " +
            "from property_fee " +
            "where user_id=#{id}")
    List<PropertyFee> getExpenseInfo(Integer id);

    //删除当前用户选中的费用记录
    @Delete("delete from property_fee where id=#{id}")
    void deleteExpense(Integer id);

    //修改当前的工单状态为已完成
    @Update("update property_fee set payment_status=#{status},update_time=now() " +
            "where id =#{id}")
    void updateStatus(Integer id,String status);

    //显示用户费用记录
    @Select("select * from property_fee where user_id=#{id} and payment_status in ('待缴费') limit #{pageNum},10")
    List<PropertyFee> getUserExpense(Integer id,Integer pageNum);

    //更新缴费信息
    @Update("update property_fee set " +
            "user_id=#{userId},project=#{project},amount=#{amount},payment_status=#{paymentStatus}" +
            ",due_date=#{dueDate},update_time=now() " +
            "where id=#{id}" )
    void updateExpense(PropertyFee propertyFee);

    //查询所有缴费信息
    @Select("select * from property_fee limit #{pageNum},15")
    List<PropertyFee> getAllExpense(Integer pageNum);

    //插入新的更新信息
    @Insert("insert into property_fee " +
            "(user_id,project,amount,payment_status,due_date,create_time,update_time) " +
            "values(#{userId},#{project},#{amount},#{paymentStatus},#{dueDate},now(),now())")
    void insertExpense(PropertyFee propertyFee);

    @Select("select count(*) from property_fee where payment_status in ('待缴费')")
    Integer getTotal();

    @Select("select count(*) from property_fee")
    Integer getCPTotal();
}
