package com.y3wegy.base;

import java.time.format.DateTimeFormatter;

/**
 * @author y3wegy
 */
public interface DateConstants {

    String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
}
