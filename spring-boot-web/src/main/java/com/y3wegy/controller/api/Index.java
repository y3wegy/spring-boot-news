package com.y3wegy.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author y3wegy
 */
@RequestMapping("/api")
@RestController
public class Index {
    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    @RequestMapping("/unAuthorized")
    public String errorPage() {
        return "/error/unAuthorized";
    }

    @RequiresUser
    @RequestMapping(path = "/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", required = false) String name, @RequestBody @Nullable String request) throws ServiceExeption {
        logger.info(String.format("hello controller %s", name));
        Map<String,Object> param = JackSonHelper.jsonStr2Obj(request, new TypeReference<HashMap>(){});
        param.put("name",name);

        return JackSonHelper.obj2JsonStr(new ResponseJson().data(param));
    }
}
