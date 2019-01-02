package com.y3wegy.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rui
 */
@Controller
public class Index {
    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    @RequestMapping(path = "/api")
    @ResponseBody
    public String api() throws JsonProcessingException {
        return JackSonHelper.getObjectMapper().writeValueAsString(new ResponseJson().data("This  is First Spring Boot Project"));
    }

    @RequestMapping(path = "/api/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", required = false) String name, @RequestBody @Nullable String request) throws ServiceExeption {
        logger.info(String.format("hello controller %s", name));

        Map<String,Object> param = JackSonHelper.jsonStr2Obj(request, HashMap.class);
        param.put("name",name);

        return JackSonHelper.obj2JsonStr(new ResponseJson().data(param));
    }
}
