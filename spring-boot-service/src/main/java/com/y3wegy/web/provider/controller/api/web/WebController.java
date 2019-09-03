package com.y3wegy.web.provider.controller.api.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.MenuDto;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.web.provider.service.web.MenuService;

/**
 * @author y3wegy
 */
@RequestMapping("/api/web")
@RestController
public class WebController {
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu")
    public String getMenuData() throws ServiceException {
        List<MenuDto> rootList = menuService.getMenuData();
        ArrayNode menuArr = JackSonHelper.getObjectMapper().createArrayNode();
        rootList.forEach(node -> menuArr.add(node.toJson()));
        //String menuInfoStr = JackSonHelper.obj2JsonStr(rootList);
        return JackSonHelper.obj2JsonStr(new ResponseJson(menuArr.toString()), 2);
    }
}
