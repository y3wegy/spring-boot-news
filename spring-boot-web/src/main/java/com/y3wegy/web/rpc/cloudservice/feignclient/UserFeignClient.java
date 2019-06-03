package com.y3wegy.web.rpc.cloudservice.feignclient;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.web.ResponseJson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author y3wegy
 * if want use @RequestLine ,need use default feign contract when build feign client manually.
 * consumes config send msg format , produces config receive msg format
 */
public interface UserFeignClient {

    /**
     * -------------------------------------------------------------
     *
     * @param securityUser
     * @return
     * @author @date        @comment
     * y3wegy   1/8/2019     init version
     * -------------------------------------------------------------
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseJson queryUser(@RequestBody SecurityUser securityUser);

    /**
     * -------------------------------------------------------------
     *
     * @param securityUser
     * @return
     * @author @date        @comment
     * y3wegy   1/8/2019     init version
     * -------------------------------------------------------------
     */
    @RequestMapping(value = "/queryRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseJson queryUserRoleLs(@RequestBody SecurityUser securityUser);
}
