package com.y3wegy.web.rpc.cloudservice.feignclient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.web.ResponseJson;

/**
 * @author y3wegy
 */
public interface UserFeignClient {

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * y3wegy   1/8/2019     init version
     * -------------------------------------------------------------
     * @param securityUser
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    ResponseJson queryUser(@RequestBody SecurityUser securityUser);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * y3wegy   1/8/2019     init version
     * -------------------------------------------------------------
     * @param securityUser
     * @return
     */
    @RequestMapping(value = "/queryRole", method = RequestMethod.POST)
    ResponseJson queryUserRoleLs(@RequestBody SecurityUser securityUser);
}
