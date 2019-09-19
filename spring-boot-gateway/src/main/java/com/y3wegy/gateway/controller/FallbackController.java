package com.y3wegy.gateway.controller;

import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author e631876
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() throws ServiceException {
        ResponseJson responseJson = new ResponseJson();
        responseJson.fail(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), HttpStatus.SERVICE_UNAVAILABLE);
        return JackSonHelper.obj2JsonStr(responseJson);
    }
}
