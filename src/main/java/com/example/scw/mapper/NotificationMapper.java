package com.example.scw.mapper;

import com.example.scw.pojo.entity.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NotificationMapper {

    @Insert("insert into notification (NotId, Publisher, Content) VALUES (#{notId}, #{publisher}, #{content})")
    Integer newNotification(Notification notification);

    @Insert("insert into not_to_user (Notification, User, Status)  (select #{NotId},UserId,0 from user where UserType = 'student')")
    Integer linkAllStudent(@Param("NotId")Integer notId);

    @Insert("insert into not_to_user (Notification, User, Status) values (#{NotId},#{UserId},0)")
    Integer link(@Param("NotId")Integer notId,@Param("User")Integer userId);

    Notification getUserNot(@Param("UserId")Integer userId);

}
