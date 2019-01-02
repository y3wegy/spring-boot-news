package com.y3wegy.mapper.business;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.y3wegy.bean.news.News;

/**
 * @author y3wegy
 */
@Mapper
@Repository("NewsMapper")
public interface NewsMapper {

    /**
     * -------------------------------------------------------------
     *  author       date        comment
     *  Chen,Rui   6/14/2018   init version
     *-------------------------------------------------------------
     * @description list all News
     * @return List
     */
    List<News> list();
}
