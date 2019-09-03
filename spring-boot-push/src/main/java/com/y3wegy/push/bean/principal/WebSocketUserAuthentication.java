package com.y3wegy.push.bean.principal;

import java.security.Principal;

/**
 * @author e631876
 */
public class WebSocketUserAuthentication implements Principal {

    /**
     * 用户身份标识符
     */
    private String userName;

    public WebSocketUserAuthentication(String userName) {
        this.userName = userName;
    }

    @Override
    public String getName() {
        return userName;
    }
}
