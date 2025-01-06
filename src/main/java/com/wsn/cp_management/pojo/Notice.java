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
public class Notice {
    private Integer id;//id
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date date;//时间
    private String title;//标题
    private String content;//内容
    private String type;//类型：公告/活动
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
