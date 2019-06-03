package com.y3wegy.web.service.web.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.MenuDto;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.web.rpc.cloudservice.feignclient.WebFeignClient;
import com.y3wegy.web.service.web.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author y3wegy
 */
@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private WebFeignClient webFeignClient;

    @Override
    public List<MenuDto> getMenuData() throws ServiceExeption {
        ResponseJson responseJson = webFeignClient.getMenuData();
        List<MenuDto> menuDtoList = JackSonHelper.jsonStr2Obj((String) responseJson.getData(), new TypeReference<List<MenuDto>>() {
        });
        return menuDtoList;
    }
}
