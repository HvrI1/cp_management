package com.wsn.cp_management.service;

import com.wsn.cp_management.pojo.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getInfo();

    List<Notice> getActivity();

    List<Notice> getCont(Integer num);

    void insertNotice(Notice notice);

    void deleteNotice(Integer id);

    void updateNotice(Notice notice);

    List<Notice> getLimitInfo(Integer num);

    List<Notice> getLimitActivity(Integer num);

    Integer getInfoTotal();

    Integer getActivityTotal();

    Integer getTotal();
}
