package com.example.scw.service.impl;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.scw.pojo.entity.Team;
import com.example.scw.pojo.exception.AuthorityException;
import com.example.scw.pojo.exception.ParameterException;
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
    public String login(UserLogin user) throws ParameterException {
        User userLogin = userMapper.getUserLogin(user.getUserCode(), user.getUserPass());
        if (userLogin == null) {
            throw new ParameterException("错误的用户名或密码");
        }
        return getTokenFromUser(userLogin);
    }

    @Override
    public User getUserByToken(String token,String type) throws AuthorityException {
        if (token == null) {
            throw new AuthorityException("没有登录");
        }
        try {
            JWT.require(algorithm).build().verify(token);
        }catch (TokenExpiredException e) {
            throw new AuthorityException("Token已经过期!!!");
        } catch (Exception e) {
            throw new AuthorityException("无效token");
        }
        DecodedJWT decode = JWT.decode(token);
        if (type==null||decode.getClaim("userType").asString().equals(type)) {
            return userStores.get(decode.getClaim("userId").asInt());
        }
        throw new AuthorityException("错误的登录用户类型");
    }



    private String getTokenFromUser(User user) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, tokenOutSecond);

        String token = JWT.create()
                .withClaim("userType", user.getUserType())
                .withClaim("userId", user.getUserId())
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
