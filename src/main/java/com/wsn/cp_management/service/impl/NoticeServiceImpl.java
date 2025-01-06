package com.wsn.cp_management.service.impl;

import com.wsn.cp_management.mapper.NoticeMapper;
import com.wsn.cp_management.pojo.Notice;
import com.wsn.cp_management.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getInfo() {
        String type="公告";
        List<Notice> list=noticeMapper.getInfo(type);
        return list;
    }

    @Override
    public List<Notice> getActivity() {
        String type="活动";
        List<Notice> list=noticeMapper.getInfo(type);
        return list;
    }

    @Override
    public List<Notice> getCont(Integer num) {
        Integer pageNum=(num-1)*15;
        List<Notice> list=noticeMapper.getCont(pageNum);
        return list;
    }

    @Override
    public void insertNotice(Notice notice) {
        noticeMapper.insertNotice(notice);
    }

    @Override
    public void deleteNotice(Integer id) {
        noticeMapper.deleteNotice(id);
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }

    @Override
    public List<Notice> getLimitInfo(Integer num) {
        Integer pageNum=(num-1)*7;

        List<Notice> list=noticeMapper.getLimit(pageNum);
        return list;
    }

    @Override
    public List<Notice> getLimitActivity(Integer num) {
        Integer pageNum=(num-1)*7;
        List<Notice> list=noticeMapper.getLimitActivity(pageNum);
        return list;
    }

    @Override
    public Integer getInfoTotal() {
        Integer num =noticeMapper.getInfoTotal();

        return num;
    }

    @Override
    public Integer getActivityTotal() {
        Integer num= noticeMapper.getActivityTotal();
        return num;
    }

    @Override
    public Integer getTotal() {
        return noticeMapper.getTotal();
    }
}
