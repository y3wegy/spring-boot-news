package com.y3wegy.web.provider.controller;

import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String version() throws ServiceExeption {
        return JackSonHelper.obj2JsonStr(new ResponseJson(customMsg));
    }

    @RequestMapping(path = "/")
    public String api() throws ServiceExeption {
        return JackSonHelper.obj2JsonStr(new ResponseJson("This  is First Spring Boot Project"));
    }
}