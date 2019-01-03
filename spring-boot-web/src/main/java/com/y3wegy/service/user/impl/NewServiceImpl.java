package com.y3wegy.service.user.impl;

import java.util.List;

import com.y3wegy.base.web.bean.business.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y3wegy.mapper.business.NewsMapper;
import com.y3wegy.service.user.NewService;

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
