package com.y3wegy.base.web.bean.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author y3weg
 * @date 17-Mar-17
 */
public class SecurityUser extends User{

    @JsonIgnore
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
