package com.example.scw.service;

import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.AuthorityException;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.pojo.vo.UserLogin;

public interface UserService {

    String login(UserLogin user) throws ParameterException;

    User getUserByToken(String token, String type) throws AuthorityException;

}
