package com.y3wegy.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author y3wegy
 */
@ControllerAdvice("com.y3wegy.controller")
public class ControllerExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * y3wegy   11/12/2018     fix unauthorizedUrl in application.yml not work issue
     * -------------------------------------------------------------
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL(), ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("author", "y3wegy");
        mav.setViewName("/error/unAuthorized");
        return mav;
    }
}
