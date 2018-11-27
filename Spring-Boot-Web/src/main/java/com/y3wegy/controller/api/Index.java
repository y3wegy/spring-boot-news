package com.y3wegy.controller.api;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * @author Rui
 */
@Controller
public class Index {
    private static final Logger logger = Logger.getLogger(Index.class);

    @RequestMapping(path = "/api")
    @ResponseBody
    public String api() {
        return "This  is First Spring Boot Project";
    }

    @RequestMapping("/unAuthorized")
    public String errorPage() {
        return "/error/unAuthorized";
    }

    @RequiresUser
    @RequestMapping(path = "/api/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", required = false) String name, @RequestBody @Nullable String request) {
        logger.info(String.format("hello controller %s", name));
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(request).getAsJsonObject();
        jsonObject.add("name", new JsonPrimitive(name));
        return "{\"response\":" + jsonObject.toString() + "}";
    }
}
