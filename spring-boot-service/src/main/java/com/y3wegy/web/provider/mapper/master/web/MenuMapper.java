package com.y3wegy.web.provider.mapper.master.web;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.y3wegy.base.web.bean.web.MenuDto;

/**
 * @author y3wegy
 */
@Mapper
public interface MenuMapper {
    /**
     * -------------------------------------------------------------
     *
     * @return List<MenuDto>
     * @author @date        @comment
     * Chen, Rui   1/29/2019     init version
     * -------------------------------------------------------------
     * only get DB data, no link info . childMenuList is empty now
     */
    List<MenuDto> getSimpleMenuData();
}
