package com.y3wegy.web.service.web;

import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.web.bean.web.MenuDto;

import java.util.List;

/**
 * @author y3wegy
 */
public interface WebService {
    /**
     * -------------------------------------------------------------
     *
     * @return
     * @author @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     */
    List<MenuDto> getMenuData() throws ServiceExeption;
}
