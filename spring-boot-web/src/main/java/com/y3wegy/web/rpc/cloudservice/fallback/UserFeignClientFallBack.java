package com.y3wegy.web.rpc.cloudservice.fallback;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.web.rpc.cloudservice.feignclient.UserFeignClient;

/**
 * @author y3wegy
 */
//@Component
public class UserFeignClientFallBack implements UserFeignClient {
    @Override
    public ResponseJson queryUser(SecurityUser securityUser) {
        return new ResponseJson("queryUser", false, "-1", null);
    }

    @Override
    public ResponseJson queryUserRoleLs(SecurityUser securityUser) {
        return new ResponseJson("queryUserRoleLs", false, "-1", null);
    }
}
