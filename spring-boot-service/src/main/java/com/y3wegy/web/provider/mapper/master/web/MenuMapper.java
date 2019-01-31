package com.y3wegy.web.provider.mapper.master.web;

import com.y3wegy.base.web.bean.web.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author y3wegy
 */
@Mapper
public interface MenuMapper {
    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/29/2019     init version
     * -------------------------------------------------------------
     * only get DB data, no link info . childMenuList is empty now
     * @return List<MenuDto>
     */
    List<MenuDto> getSimpleMenuData();
}
