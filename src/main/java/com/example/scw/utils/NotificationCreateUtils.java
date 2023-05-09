package com.example.scw.utils;

import com.example.scw.mapper.NotificationMapper;
import com.example.scw.pojo.ToCreateNotification;
import com.example.scw.pojo.entity.Notification;
import com.example.scw.pojo.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationCreateUtils {

    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    SnowFlakeUtils snowFlakeUtils;

    public boolean createNotification(ToCreateNotification notifyEntity, Integer publisher) throws DataException {
        String content = notifyEntity.createNotificationString();
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setPublisher(publisher);
        notification.setNotId((int) snowFlakeUtils.nextId());
        Integer integer = notificationMapper.newNotification(notification);
        notificationMapper.linkAllStudent(notification.getNotId());
        return integer == 1;
    }

    public boolean createNotification(ToCreateNotification notifyEntity, Integer publisher, Integer listener) throws DataException {
        String content = notifyEntity.createNotificationString();
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setPublisher(publisher);
        notification.setNotId((int) snowFlakeUtils.nextId());
        Integer integer = notificationMapper.newNotification(notification);
        notificationMapper.link(notification.getNotId(),listener);
        return integer == 1;
    }

}
