package com.y3wegy.mapper.business;

import com.y3wegy.WebApplication;
import com.y3wegy.base.web.bean.business.News;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class NewsMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(NewsMapperTest.class);

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void testQuery() {
        List<News> newsList = newsMapper.list();
        assertAll(
                () -> assertNotNull(newsList),
                () -> assertEquals("1", newsList.get(0).getId())
        );
        logger.info(String.valueOf(newsList.get(0)));
    }
}