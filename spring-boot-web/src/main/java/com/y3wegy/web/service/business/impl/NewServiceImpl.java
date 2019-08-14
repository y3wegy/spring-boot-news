package com.y3wegy.web.service.business.impl;

import com.y3wegy.base.web.bean.business.News;
import com.y3wegy.web.mapper.business.NewsMapper;
import com.y3wegy.web.service.business.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author y3wegy
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