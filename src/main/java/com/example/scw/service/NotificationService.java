package com.example.scw.service;


import com.example.scw.pojo.entity.Notification;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.ServerException;


import java.util.List;

public interface NotificationService {

    List<Notification> getNotificationAll(User user);

    List<Notification> getNotificationUnread(User user);

    String setNotification(User user) throws ServerException;

}
