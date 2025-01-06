package com.wsn.cp_management.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    private  Integer id; //楼栋id
    private  Integer communityId;//小区ID
    private  String communityName;//小区名称
    private  Integer buildingNumber;//楼栋号
    private  Integer totalFloors;//总楼层数
    private  Integer totalHouseholds;//总户数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

}
