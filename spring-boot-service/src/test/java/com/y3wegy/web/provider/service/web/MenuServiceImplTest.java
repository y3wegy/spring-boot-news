package com.y3wegy.web.provider.service.web;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.MenuDto;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.web.provider.CloudServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CloudServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MenuServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImplTest.class);

    @Autowired
    private MenuService menuService;

    @Test
    void getMenuData() throws ServiceException {
        List<MenuDto> root = menuService.getMenuData();
        ArrayNode arrayNode = JackSonHelper.getObjectMapper().createArrayNode();
        root.forEach(node -> arrayNode.add(node.toJson()));
        logger.info(JackSonHelper.obj2JsonStr(new ResponseJson(arrayNode.toString()), 2));
    }
}
