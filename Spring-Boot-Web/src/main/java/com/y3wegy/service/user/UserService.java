package com.y3wegy.service.user;

import com.y3wegy.bean.user.User;
import com.y3wegy.bean.user.UserRole;

/**
 *
 * @author y3weg
 * @date 17-Mar-17
 */
public interface UserService {
    /**
     * -------------------------------------------------------------
     *  author       date        comment
     *  Chen,Rui   6/14/2018   init version
     *-------------------------------------------------------------
     * @description user login
     * @param securityUser
     * @return
     */
    UserRole login(User securityUser);
}
