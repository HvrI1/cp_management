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
public class PropertyFee {
    private Integer id;//费用ID
    private Integer userId;//用户ID
    private Integer houseId;//房屋ID
    private String project;//缴费项目
    private String amount;//费用金额
    private String paymentStatus;//缴费状态
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date dueDate;//应缴费日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
