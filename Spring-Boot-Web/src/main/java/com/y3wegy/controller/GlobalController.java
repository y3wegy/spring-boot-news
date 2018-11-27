package com.y3wegy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * /**
 * @author Chen, Rui
 * @create 6/14/2018
 * @description
 **/
@Controller
public class GlobalController {

    @RequestMapping("testUnauthorized")
    public String errorPage() {
        return "/error/unAuthorized";
    }

}
