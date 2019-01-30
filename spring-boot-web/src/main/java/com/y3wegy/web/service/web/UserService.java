package com.y3wegy.web.service.web;

import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;

/**
 * @author y3wegy
 */
public interface UserService {
    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/29/2019     init version
     * -------------------------------------------------------------
     * @param securityUserBean
     * @return
     */
    ResponseJson login(SecurityUser securityUserBean);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/29/2019     init version
     * -------------------------------------------------------------
     * @return
     */
    void logOut();
}
