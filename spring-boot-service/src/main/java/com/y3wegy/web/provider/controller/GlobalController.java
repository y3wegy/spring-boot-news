package com.y3wegy.web.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;

/**
 * /**
 *
 * @author y3wegy
 * @create 6/14/2018
 * @description
 **/
@RestController
public class GlobalController {

    @Value("${service.customMsg}")
    private String customMsg;

    @RequestMapping("/version")
    public String version() throws ServiceException {
        return JackSonHelper.obj2JsonStr(new ResponseJson(customMsg));
    }

    @RequestMapping(path = "/")
    public String api() throws ServiceException {
        return JackSonHelper.obj2JsonStr(new ResponseJson("This  is First Spring Boot Project"));
    }
}
