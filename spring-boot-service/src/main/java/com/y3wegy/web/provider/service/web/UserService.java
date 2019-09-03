package com.y3wegy.web.provider.service.web;

import java.util.List;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.User;
import com.y3wegy.base.web.bean.user.UserRole;

/**
 * @author y3wegy
 * @date 17-Mar-17
 */
public interface UserService {
    /**
     * -------------------------------------------------------------
     * author       date        comment
     * y3wegy  6/14/2018   init version
     * -------------------------------------------------------------
     *
     * @param userInfo
     * @return
     * @description user queryUserByUserName
     */
    List<SecurityUser> queryUserByUserName(User userInfo);

    /**
     * -------------------------------------------------------------
     *
     * @param userInfo
     * @return
     * @author @date        @comment
     * y3wegy     1/4/2019     init version
     * -------------------------------------------------------------
     */
    List<UserRole> queryUserRolesByUserName(User userInfo);
}
