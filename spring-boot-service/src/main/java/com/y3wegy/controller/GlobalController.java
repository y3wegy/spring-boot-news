package com.y3wegy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * /**
 *
 * @author Chen, Rui
 * @create 6/14/2018
 * @description
 **/
@Controller
public class GlobalController {

    @Value("${service.customMsg}")
    private String customMsg;

    @RequestMapping("version")
    public String version() throws JsonProcessingException {
        return JackSonHelper.getObjectMapper().writeValueAsString(new ResponseJson().data(customMsg));
    }

}
