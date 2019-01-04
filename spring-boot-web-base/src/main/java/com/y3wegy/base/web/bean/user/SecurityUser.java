package com.y3wegy.base.web.bean.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author y3wegy
 * @date 17-Mar-17
 */
public class SecurityUser extends User{

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
