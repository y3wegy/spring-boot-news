package com.y3wegy.web.provider.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;

/**
 * @author y3wegy
 */
@ControllerAdvice("com.y3wegy.web.provider.controller")
public class ControllerExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    /**
     * -------------------------------------------------------------
     *
     * @param req
     * @param ex
     * @return
     * @author @date        @comment
     * y3wegy   11/12/2018     fix unauthorizedUrl in application.yml not work issue
     * -------------------------------------------------------------
     */
    @ExceptionHandler(ServiceException.class)
    public String handleError(HttpServletRequest req, Exception ex) throws ServiceException {
        logger.error("Request: " + req.getRequestURL(), ex);

        return JackSonHelper.obj2JsonStr(new ResponseJson().fail("exception"));
    }
}
