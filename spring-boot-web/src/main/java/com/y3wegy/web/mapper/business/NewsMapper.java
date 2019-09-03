package com.y3wegy.web.mapper.business;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.y3wegy.base.web.bean.business.News;

/**
 * @author y3wegy
 */
@Mapper
public interface NewsMapper {

    /**
     * -------------------------------------------------------------
     * author       date        comment
     * Chen,y3wegy   6/14/2018   init version
     * -------------------------------------------------------------
     *
     * @return List
     * @description list all News
     */
    List<News> list();
}
