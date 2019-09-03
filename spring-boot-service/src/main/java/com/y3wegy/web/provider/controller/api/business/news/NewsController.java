package com.y3wegy.web.provider.controller.api.business.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.business.News;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.web.provider.service.business.NewService;

/**
 * @author y3wegy
 */
@RequestMapping({"/api/news"})
@RestController
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewService newService;

    @RequestMapping("/list")
    public String listNews() throws ServiceException {
        List<News> newsList = newService.list();
        String newStr = JackSonHelper.obj2JsonStr(newsList);
        String responseStr = JackSonHelper.obj2JsonStr(new ResponseJson().success(newStr));
        logger.info(String.format("response data:%s", responseStr));
        return responseStr;
    }

}
