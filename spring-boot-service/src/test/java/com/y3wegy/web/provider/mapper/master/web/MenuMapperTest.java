package com.y3wegy.web.provider.mapper.master.web;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.y3wegy.base.web.bean.web.MenuDto;
import com.y3wegy.web.provider.CloudServiceApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CloudServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MenuMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(MenuMapperTest.class);
    @Autowired
    private MenuMapper menuMapper;

    @Test
    void getMenuData() {
        List<MenuDto> menuDtoList = menuMapper.getSimpleMenuData();
        logger.info(String.valueOf(menuDtoList.size()));
    }
}
