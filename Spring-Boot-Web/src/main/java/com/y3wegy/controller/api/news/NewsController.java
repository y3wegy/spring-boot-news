package com.y3wegy.controller.api.news;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.y3wegy.service.user.NewService;

@RequestMapping({"/News"})
@Controller
public class NewsController {

    private static final Logger logger = Logger.getLogger(NewsController.class);

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
