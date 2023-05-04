package com.example.scw.pojo;

import com.example.scw.pojo.exception.DataException;

public interface ToCreateNotification {

    /**
     * 通过方法生成该对象的通知语句，通知对象有业务层决定
     * @return 产生的Notification字符串语句
     */
    String getNotificationString() throws DataException;

}
