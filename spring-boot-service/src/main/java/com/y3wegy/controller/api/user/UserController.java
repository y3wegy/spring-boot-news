package com.y3wegy.controller.api.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.UserRole;
import com.y3wegy.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    @RequestMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String login(@RequestBody SecurityUser securityUserBean) throws JsonProcessingException {
        logger.info("enter login");
        /*String lastUser = (String) redisTemplate.opsForValue().get("UserName");
        logger.info(lastUser);
        redisTemplate.opsForValue().set("UserName",name);*/
        UserRole userRole = userService.login(securityUserBean);
        securityUserBean.setUserRole(userRole);
        ResponseJson responseJson = new ResponseJson().success(securityUserBean);
        return JackSonHelper.getObjectMapper().writeValueAsString(responseJson);
    }
}
