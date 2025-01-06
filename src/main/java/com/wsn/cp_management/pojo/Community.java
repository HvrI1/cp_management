package com.wsn.cp_management.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Community {
    private Integer id;//小区id
    private String name;//小区名称
    private String address;//小区地址
    private Integer area;//占地面积
    private Integer totalBuildings;//总楼栋数
    private Integer totalUnits;//总户数
    private String developer;//开发商名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
