package com.y3wegy.web.controller.api.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.y3wegy.web.service.business.NewService;

@RequestMapping({"/api/news"})
@Controller
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewService newService;

    @RequestMapping("/list")
    @ResponseBody
    public String listNews() {
        /*List<News> newsList = newService.list();
        Gson gson = new Gson();
        String newsJsonStr = gson.toJson(newsList);
        return newsJsonStr;*/
        return "[{}]";
    }

}
