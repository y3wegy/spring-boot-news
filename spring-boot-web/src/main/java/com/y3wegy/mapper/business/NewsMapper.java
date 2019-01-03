package com.y3wegy.mapper.business;

import com.y3wegy.base.web.bean.business.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository("NewsMapper")
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
