package com.y3wegy.web.provider.service.business;

import com.y3wegy.base.web.bean.business.News;

import java.util.List;

/**
 * @author y3wegy
 */
@FunctionalInterface
public interface NewService {

    /**
     * -------------------------------------------------------------
     *
     * @return
     * @author @date             @comment
     * y3wegy   02-Jan-19-002       init version
     * -------------------------------------------------------------
     */
    List<News> list();
}
