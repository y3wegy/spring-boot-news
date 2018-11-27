package com.y3wegy.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rui
 */
@ControllerAdvice("com.y3wegy.controller")
public class ControllerExceptionAdvice {

    private static final Logger logger = Logger.getLogger(ControllerExceptionAdvice.class);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   11/12/2018     fix unauthorizedUrl in application.yml not work issue
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
        mav.addObject("author", "Rui");
        mav.setViewName("/error/unAuthorized");
        return mav;
    }
}
