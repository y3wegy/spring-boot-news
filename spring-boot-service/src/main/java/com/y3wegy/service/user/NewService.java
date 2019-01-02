package com.y3wegy.service.user;

import java.util.List;

import com.y3wegy.bean.news.News;

/**
 * @author y3wegy
 */
@FunctionalInterface
public interface NewService {

    /**
     * -------------------------------------------------------------
     * @author     @date             @comment
     * y3wegy   02-Jan-19-002       init version
     * -------------------------------------------------------------
     * @return
     */
    List<News> list();
}
