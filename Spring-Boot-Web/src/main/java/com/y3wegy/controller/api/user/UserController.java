package com.y3wegy.controller.api.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author y3weg
 * @date 18-Mar-17
 * @RestController == @Controller + @ResponseBody  :only print content ,can't use for error link
 * @Controller for error link
 */
@RequestMapping("/api/user")
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String login(@RequestBody SecurityUser securityUserBean) throws JsonProcessingException {
        Subject currentUser = SecurityUtils.getSubject();
        /*String lastUser = (String) redisTemplate.opsForValue().get("UserName");
        logger.info(lastUser);
        redisTemplate.opsForValue().set("UserName",name);*/
        try {
            currentUser.logout();
            currentUser.login(new UsernamePasswordToken(securityUserBean.getUserName(), securityUserBean.getPassword()));
        } catch (AuthenticationException e) {
            logger.error("Login Failed", e);
        }
        ResponseJson responseJson = new ResponseJson();
        if (currentUser.isAuthenticated()) {
            SecurityUser user = (SecurityUser) currentUser.getPrincipal();
            responseJson.data(user);
        } else {
            responseJson.fail(securityUserBean);
        }
        return JackSonHelper.getObjectMapper().writeValueAsString(responseJson);

    }
}
