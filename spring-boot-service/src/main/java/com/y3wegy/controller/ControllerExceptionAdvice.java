package com.y3wegy.controller;

import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author y3wegy
 */
@ControllerAdvice("com.y3wegy.controller")
public class ControllerExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    /**
     * -------------------------------------------------------------
     * @author      @date        @comment
     * y3wegy   11/12/2018     fix unauthorizedUrl in application.yml not work issue
     * -------------------------------------------------------------
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceExeption.class)
    public String handleError(HttpServletRequest req, Exception ex) throws ServiceExeption {
        logger.error("Request: " + req.getRequestURL(), ex);

        return JackSonHelper.obj2JsonStr(new ResponseJson().fail("exception"));
    }
}
