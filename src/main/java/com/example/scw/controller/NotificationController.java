package com.example.scw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scw.pojo.entity.Notification;
import com.example.scw.pojo.vo.Vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/notification")
@Api(tags = "通知接口")
public class NotificationController {

    @GetMapping("/unread")
    @ApiOperation(value = "获取用户未阅读的通知")
    public Vo<List<Notification>> getNotificationUnread(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取用户所有通知")
    public Vo<List<Notification>> getNotificationAll(HttpServletRequest request) {
        return null;
    }

    @PostMapping("/read")
    @ApiOperation(value = "将未读的通知设为已读")
    public Vo<String> readNotification(HttpServletRequest request) {
        return null;
    }
}
