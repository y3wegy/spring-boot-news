package com.y3wegy.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.y3wegy.bean.user.SecurityUser;
import com.y3wegy.bean.user.User;
import com.y3wegy.bean.user.UserRole;
import com.y3wegy.mapper.master.user.UserMapper;
import com.y3wegy.service.user.UserService;

/**
 *
 * @author y3weg
 * @date 17-Mar-17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("UserMapper")
    private UserMapper userMapper;

    @Override
    public UserRole login(User securityUser) {
        List<SecurityUser> userBeanList = userMapper.get(securityUser.getUserName());
        return CollectionUtils.isEmpty(userBeanList) ? UserRole.UNKNOW : userBeanList.get(0).getUserRole();
    }
}
