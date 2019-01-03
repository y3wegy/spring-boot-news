package com.y3wegy.service.user;

import com.y3wegy.base.web.bean.business.News;

import java.util.List;


/**
 * @author rui
 */
@FunctionalInterface
public interface NewService {

    List<News> list();
}
