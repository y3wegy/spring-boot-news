package com.y3wegy.bean.user;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author y3weg
 * @date 17-Mar-17
 */
public class SecurityUser extends User{

    @JsonInclude
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
