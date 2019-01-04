package com.y3wegy.service.user;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.User;
import com.y3wegy.base.web.bean.user.UserRole;

import java.util.List;

/**
 *
 * @author y3wegy
 * @date 17-Mar-17
 */
public interface UserService {
    /**
     * -------------------------------------------------------------
     *  author       date        comment
     *  y3wegy  6/14/2018   init version
     *-------------------------------------------------------------
     * @description user queryUserByUserName
     * @param userInfo
     * @return
     */
    List<SecurityUser> queryUserByUserName(User userInfo);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * y3wegy     1/4/2019     init version
     * -------------------------------------------------------------
     * @param userInfo
     * @return
     */
    List<UserRole> queryUserRolesByUserName(User userInfo);
}
