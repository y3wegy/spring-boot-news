package com.y3wegy.bean;

import java.text.SimpleDateFormat;

/**
 * @author y3wegy
 */
public interface DateConstants {

    String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    SimpleDateFormat defaultDateTimeFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
}
