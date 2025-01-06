package com.wsn.cp_management.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {
    private Integer id;//房屋ID
    private Integer userId;//用户id
    private String userName;//用户名称
    private Integer buildingId;//楼栋ID
    private Integer unitNumber;//单元号
    private Integer floorNumber;//楼层号
    private Integer roomNumber;//房间号
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date liveTime;//入住时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

}
