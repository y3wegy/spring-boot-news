package com.y3wegy.service.user;

import com.y3wegy.base.web.bean.user.User;
import com.y3wegy.base.web.bean.user.UserRole;

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
     * @description user login
     * @param securityUser
     * @return
     */
    UserRole login(User securityUser);
}
