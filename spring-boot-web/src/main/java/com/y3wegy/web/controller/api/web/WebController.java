package com.y3wegy.web.controller.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.MenuDto;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.web.service.web.WebService;

/**
 * @author y3wegy
 */
@RequestMapping("/api/web")
@RestController
public class WebController {

    @Autowired
    private WebService webService;

    @RequestMapping(path = "/siderMenu")
    public String getMenuData() throws ServiceExeption {
        List<MenuDto> menuDtoList = webService.getMenuData();
        ArrayNode menuArr = JackSonHelper.getObjectMapper().createArrayNode();
        menuDtoList.forEach(node-> menuArr.add(node.toJson()));
        return JackSonHelper.obj2JsonStr(new ResponseJson(menuArr.toString()));
    }
}
