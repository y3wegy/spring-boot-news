package com.y3wegy.web.service.web;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.web.ResponseJson;

/**
 * @author y3wegy
 */
public interface UserService {
    /**
     * -------------------------------------------------------------
     *
     * @param securityUserBean
     * @return
     * @author @date        @comment
     * Chen, Rui   1/29/2019     init version
     * -------------------------------------------------------------
     */
    ResponseJson login(SecurityUser securityUserBean);

    /**
     * -------------------------------------------------------------
     *
     * @return
     * @author @date        @comment
     * Chen, Rui   1/29/2019     init version
     * -------------------------------------------------------------
     */
    void logOut();
}
