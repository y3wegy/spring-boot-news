package com.y3wegy.controller.api.news;

import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import com.y3wegy.base.web.bean.business.News;
import com.y3wegy.service.user.NewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String listNews() throws ServiceExeption {
        List<News> newsList = newService.list();
        String newStr = JackSonHelper.obj2JsonStr(newsList);
        String responseStr = JackSonHelper.obj2JsonStr(new ResponseJson().success(newStr));
        logger.info(String.format("response data:%s", responseStr));
        return responseStr;
    }

}
