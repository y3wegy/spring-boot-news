package com.y3wegy.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author y3wegy
 */
@RestController
@RequestMapping("/api")
public class Index {
    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    @RequestMapping(path = "/")
    public String api() throws JsonProcessingException {
        return JackSonHelper.getObjectMapper().writeValueAsString(new ResponseJson().data("This  is First Spring Boot Project"));
    }
}
