package com.y3wegy.service.user;

import java.util.List;

import com.y3wegy.bean.news.News;

/**
 * @author rui
 */
@FunctionalInterface
public interface NewService {

    List<News> list();
}
