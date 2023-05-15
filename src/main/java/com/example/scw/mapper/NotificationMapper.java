package com.example.scw.mapper;

import com.example.scw.pojo.entity.Notification;
import com.example.scw.pojo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Insert("insert into notification (NotId, Publisher, Content) VALUES (#{notId}, #{publisher}, #{content})")
    Integer newNotification(Notification notification);

    @Insert("insert into not_to_user (Notification, User, Status)  (select #{NotId},UserId,0 from user where UserType = 'student')")
    Integer linkAllStudent(@Param("NotId")Integer notId);

    @Insert("insert into not_to_user (Notification, User, Status) values (#{NotId},#{UserId},0)")
    Integer link(@Param("NotId")Integer notId,@Param("User")Integer userId);

    @Select("SELECT * FROM scw.notification where NotId in(select Notification from not_to_user where User=#{User})")
    List<Notification> getNotificationAll(@Param("User")Integer userId);

    @Select("SELECT * FROM scw.notification where NotId in(select Notification from not_to_user where User=#{User} and Status = 0)")
    List<Notification> getNotificationUnread(@Param("User")Integer userId);

    @Update("update not_to_user set Status = 1 where User=#{User}")
    Integer setNotification(@Param("User")Integer userId);

}
