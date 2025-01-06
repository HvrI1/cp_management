package com.wsn.cp_management.mapper;

import com.wsn.cp_management.pojo.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("select * from notice where type=#{type}")
    List<Notice> getInfo(String type);

    @Select("select * from notice limit #{pageNum},15")
    List<Notice> getCont(Integer pageNum);

    @Insert("insert into notice  (date,title,content,type,create_time,update_time) " +
            "values(#{date},#{title},#{content},#{type},now(),now())")
    void insertNotice(Notice notice);

    @Delete("delete from notice where id=#{id}")
    void deleteNotice(Integer id);

    @Update("update notice set date=#{date},title=#{title},content=#{content},type=#{type} " +
            ",update_time=now() where id =#{id}")
    void updateNotice(Notice notice);

    @Select("SELECT * FROM notice WHERE type = '公告' LIMIT #{pageNum}, 7; ")
    List<Notice> getLimit(Integer pageNum);

    @Select("SELECT * FROM notice WHERE type = '活动' LIMIT #{pageNum}, 7; ")
    List<Notice> getLimitActivity(Integer pageNum);

    @Select("select count(*) from notice where type = '公告'")
    Integer getInfoTotal();

    @Select("select count(*) from notice where type = '活动'")
    Integer getActivityTotal();

    @Select("select count(*) from notice")
    Integer getTotal();
}
