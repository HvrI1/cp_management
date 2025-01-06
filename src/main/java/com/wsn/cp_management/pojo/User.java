package com.wsn.cp_management.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; //id
    @Pattern(regexp = "^\\S{5,16}$")
    private String username;//用户名
    @Pattern(regexp = "^\\S{5,16}$")
    @JsonIgnore//使springMVC把当前对象转为JSON字符串时，忽略password
    private String password;//密码
    private String role;//角色
    private Integer communityId;//所属小区
    private Integer buildingId;//所属楼栋
    private Integer houseId;//所属房产
    private String phone;//联系方式
    private String sex;//性别
    private String name;//姓名
    private String remark;//备注
    @URL
    private String picture;//图片
    @Email
    private String email;//邮箱
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    //补充属性
    private Integer buildingNum;//所属楼栋号
    private Integer houseNum;//所属房产号
}
