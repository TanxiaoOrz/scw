package com.example.scw.utils;

import com.example.scw.mapper.NotificationMapper;
import com.example.scw.pojo.ToCreateNotification;
import com.example.scw.pojo.entity.Notification;
import com.example.scw.pojo.exception.DataException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationCreateUtils {

    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    SnowFlakeUtils snowFlakeUtils;

    public boolean createNotification(ToCreateNotification notifyEntity, Integer publisher) throws DataException {
        String content = notifyEntity.getNotificationString();
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setPublisher(publisher);
        notification.setNotId((int) snowFlakeUtils.nextId());
        Integer integer = notificationMapper.newNotification(notification);
        notificationMapper.linkAllStudent(notification.getNotId());
        return integer == 1;
    }
}
