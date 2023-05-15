package com.example.scw.service.impl;

import com.example.scw.mapper.NotificationMapper;
import com.example.scw.pojo.entity.Notification;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.DataException;
import com.example.scw.pojo.exception.ServerException;
import com.example.scw.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationImpl implements NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public List<Notification> getNotificationAll(User user) {
        return notificationMapper.getNotificationAll(user.getUserId());
    }

    @Override
    public List<Notification> getNotificationUnread(User user) {
        return notificationMapper.getNotificationUnread(user.getUserId());
    }

    @Override
    public String setNotification(User user) {
        Integer x=notificationMapper.setNotification(user.getUserId());
        return "设置已读成功";
    }
}
