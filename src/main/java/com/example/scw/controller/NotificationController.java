package com.example.scw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.ServerException;
import com.example.scw.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scw.pojo.entity.Notification;
import com.example.scw.pojo.vo.Vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/notification")
@Api(tags = "通知接口")
@CrossOrigin
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/unread")
    @ApiOperation(value = "获取用户未阅读的通知")
    public Vo<List<Notification>> getNotificationUnread(@RequestAttribute(name = "User") @ApiIgnore User user) {
        return new Vo<>(notificationService.getNotificationUnread(user));
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取用户所有通知")
    public Vo<List<Notification>> getNotificationAll(@RequestAttribute(name = "User") @ApiIgnore User user) {
        return new Vo<>(notificationService.getNotificationAll(user));
    }

    @PostMapping("/read")
    @ApiOperation(value = "将未读的通知设为已读")
    public Vo<String> readNotification(@RequestAttribute(name = "User") @ApiIgnore User user) throws ServerException {
        return new Vo<>(notificationService.setNotification(user));
    }
}
