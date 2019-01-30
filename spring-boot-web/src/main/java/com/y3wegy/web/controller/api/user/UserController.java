package com.y3wegy.web.controller.api.user;

import com.y3wegy.web.service.web.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;

/**
 * @author y3wegy
 * @date 18-Mar-17
 * @RestController == @Controller + @ResponseBody  :only print content ,can't use for error link
 * @Controller for error link
 */
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String login(@RequestBody SecurityUser securityUser) throws JsonProcessingException {
        ResponseJson responseJson =userService.login(securityUser);
        return JackSonHelper.getObjectMapper().writeValueAsString(responseJson);
    }

    @RequestMapping("/logout")
    public String logout() throws ServiceExeption {
        userService.logOut();
        return JackSonHelper.obj2JsonStr(new ResponseJson());
    }
}
