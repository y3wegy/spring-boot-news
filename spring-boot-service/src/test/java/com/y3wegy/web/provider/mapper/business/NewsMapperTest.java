package com.y3wegy.web.provider.mapper.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.y3wegy.base.web.bean.business.News;
import com.y3wegy.web.provider.CloudServiceApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CloudServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class NewsMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(NewsMapperTest.class);

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void testQuery() {
        List<News> newsList = newsMapper.list();
        assertAll(
                () -> assertNotNull(newsList),
                () -> assertEquals("1", newsList.get(0).getId()));
        logger.info(newsList.get(0).toString());
    }
}
