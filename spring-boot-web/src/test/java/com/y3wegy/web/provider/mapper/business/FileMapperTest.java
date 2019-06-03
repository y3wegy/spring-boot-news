package com.y3wegy.web.provider.mapper.business;

import com.y3wegy.web.WebApplication;
import com.y3wegy.web.bean.business.FileDto;
import com.y3wegy.web.mapper.business.FileMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FileMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(FileMapperTest.class);

    @Autowired
    private FileMapper fileMapper;

    @Test
    void testUpload() {
        FileDto fileDto = new FileDto();
        fileDto.setFileName("test.txt");
        fileDto.setContent("Content".getBytes());
        fileDto.setLastUpdateBy("y3wegy");
        fileMapper.save(fileDto);
        logger.info(fileDto.getId());
        Assertions.assertNotNull(fileDto.getId());
    }
}
