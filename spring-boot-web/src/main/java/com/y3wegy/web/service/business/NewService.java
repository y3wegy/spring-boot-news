package com.y3wegy.web.service.business;

import java.util.List;

import com.y3wegy.base.web.bean.business.News;

/**
 * @author rui
 */
@FunctionalInterface
public interface NewService {

    List<News> list();
}
