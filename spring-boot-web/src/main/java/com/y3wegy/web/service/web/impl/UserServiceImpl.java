package com.y3wegy.web.service.web.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.web.service.web.UserService;

/**
 * @author y3wegy
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseJson login(SecurityUser securityUser) {
        Subject currentUser = SecurityUtils.getSubject();
        /*String lastUser = (String) redisTemplate.opsForValue().get("UserName");
        logger.info(lastUser);
        redisTemplate.opsForValue().set("UserName",name);*/
        try {
            currentUser.logout();
            currentUser.login(new UsernamePasswordToken(securityUser.getUserName(), securityUser.getPassword()));
        } catch (AuthenticationException e) {
            logger.error("Login Failed", e);
        }
        ResponseJson responseJson = new ResponseJson();
        if (currentUser.isAuthenticated()) {
            SecurityUser user = (SecurityUser) currentUser.getPrincipal();
            responseJson.data(user);
        } else {
            responseJson.fail(securityUser);
        }
        return responseJson;
    }

    @Override
    public void logOut() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }
}
