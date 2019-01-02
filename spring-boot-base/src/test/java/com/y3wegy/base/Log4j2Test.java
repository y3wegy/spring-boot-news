package com.y3wegy.base;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2Test {
    private static final Logger logger = LoggerFactory.getLogger(Log4j2Test.class);

    @Test
    public void testAsync() {
        for (int i = 0; i < 80000; i++) {
            logger.info("ccc");
            logger.warn("ccc");
            logger.error("ccc");
        }
    }
}
