package com.y3wegy.mapper.business;

import com.y3wegy.Application;
import com.y3wegy.bean.news.News;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class NewsMapperTest {

    private static final Logger logger = Logger.getLogger(NewsMapperTest.class);

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void testQuery() {
        List<News> newsList = newsMapper.list();
        assertAll(
                () -> assertNotNull(newsList),
                () -> assertEquals("1", newsList.get(0).getId())
        );
        logger.info(newsList.get(0));
    }
}