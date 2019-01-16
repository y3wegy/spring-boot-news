package com.y3wegy.rpc.impl;

import org.springframework.stereotype.Component;

import com.y3wegy.base.web.bean.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;

/**
 * @author y3wegy
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public ResponseJson queryUser(SecurityUser securityUser) {
        return new ResponseJson("queryUser", false, "-1", null);
    }

    @Override
    public ResponseJson queryUserRoleLs(SecurityUser securityUser) {
        return new ResponseJson("queryUserRoleLs", false, "-1", null);
    }
}
