package com.y3wegy.web.provider.service.web.impl;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.User;
import com.y3wegy.base.web.bean.user.UserRole;
import com.y3wegy.web.provider.mapper.master.user.UserMapper;
import com.y3wegy.web.provider.service.web.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author y3wegy
 * @date 17-Mar-17
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("UserMapper")
    private UserMapper userMapper;

    @Override
    public List<SecurityUser> queryUserByUserName(User userInfo) {
        List<SecurityUser> userBeanList = userMapper.queryUserByUserName(userInfo.getUserName());
        logger.info(String.format("query User result:%s with info :%s", userBeanList.size(), userInfo));
        return userBeanList;
    }

    @Override
    public List<UserRole> queryUserRolesByUserName(User userInfo) {
        List<UserRole> userRoleList = userMapper.queryUserRolesByUserName(userInfo.getUserName());
        logger.info(String.format("query User Role result:%s with info :%s", userRoleList.size(), userInfo));
        return userRoleList;
    }
}
