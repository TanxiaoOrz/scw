package com.example.scw.service.impl;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.scw.mapper.UserMapper;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.vo.UserLogin;
import com.example.scw.service.UserService;

@Service
public class UserServiceImpl implements UserService, InitializingBean {

    @Autowired
    UserMapper userMapper;

    @Value("${config.token-out}")
    int tokenOutSecond;

    @Value("${config.token-pass}")
    String tokenPass;

    private ConcurrentHashMap<Integer, User> userStores = new ConcurrentHashMap<>();
    private Algorithm algorithm;

    @Override
    public String login(UserLogin user) {
        User userLogin = userMapper.getUserLogin(user.getUserCode(), user.getUserPass());
        return getTokenFromUser(userLogin);
    }

    private String getTokenFromUser(User user) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, tokenOutSecond);

        String token = JWT.create()
                .withClaim("userStatus", user.getUserStatus())
                .withClaim("userid", user.getUserId().toString())
                .withExpiresAt(instance.getTime())
                .sign(algorithm);

        userStores.put(user.getUserId(), user);

        return token;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        algorithm = Algorithm.HMAC256(tokenPass);
    }

}
