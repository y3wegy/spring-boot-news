package com.y3wegy.web.service.web;

import java.util.List;

import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.web.bean.web.MenuDto;

/**
 * @author y3wegy
 */
public interface WebService {
    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     * @return
     */
    List<MenuDto> getMenuData() throws ServiceExeption;
}
