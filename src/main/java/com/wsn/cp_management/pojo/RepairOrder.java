package com.wsn.cp_management.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairOrder {
    private Integer id;//工单ID
    private Integer userId;//用户ID
    private Integer houseId;//房屋ID
    private String project;//维修名称
    private String address;//维修地址
    private String userPhone;//用户联系方式
    private String description;//问题描述
    private String status;//工单状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
