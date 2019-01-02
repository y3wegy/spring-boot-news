package com.y3wegy.service.user.impl;

import com.y3wegy.bean.news.News;
import com.y3wegy.mapper.business.NewsMapper;
import com.y3wegy.service.user.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chen, Rui
 */
@Service("NewService")
public class NewServiceImpl implements NewService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> list() {
        return newsMapper.list();
    }
}
