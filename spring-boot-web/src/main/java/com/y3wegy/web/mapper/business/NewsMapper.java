package com.y3wegy.web.mapper.business;

import com.y3wegy.base.web.bean.business.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author y3wegy
 */
@Mapper
public interface NewsMapper {

    /**
     * -------------------------------------------------------------
     *  author       date        comment
     *  Chen,y3wegy   6/14/2018   init version
     *-------------------------------------------------------------
     * @description list all News
     * @return List
     */
    List<News> list();
}
