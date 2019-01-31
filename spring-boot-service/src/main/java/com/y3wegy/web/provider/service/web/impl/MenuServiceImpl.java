package com.y3wegy.web.provider.service.web.impl;

import java.util.*;

import com.y3wegy.web.provider.service.web.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.y3wegy.base.web.bean.web.MenuDto;
import com.y3wegy.web.provider.mapper.master.web.MenuMapper;

/**
 * @author y3wegy
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuDto> getMenuData() {
        List<MenuDto> menuDtoList = menuMapper.getSimpleMenuData();
        return classifyMenu(menuDtoList);
    }

    /**
     *-------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     * link parent and child menu list
     * @param simpleMenuList
     * @return
     */
    public List<MenuDto> classifyMenu(List<MenuDto> simpleMenuList) {
        Map<String, TreeSet<MenuDto>> parentChildMap = new HashMap<>(1);

        simpleMenuList.forEach(menuDto -> {
            String parentId = menuDto.getParentMenuId();
            if (!CollectionUtils.isEmpty(parentChildMap.get(parentId))) {
                parentChildMap.get(parentId).add(menuDto);
            } else {
                TreeSet<MenuDto> newTreeSet = new TreeSet<>();
                newTreeSet.add(menuDto);
                parentChildMap.put(menuDto.getParentMenuId(), newTreeSet);
            }

        });
        simpleMenuList.forEach(menuDto -> {
            String menuId = menuDto.getId();
            if (parentChildMap.containsKey(menuId)) {
                parentChildMap.get(menuId).forEach(item -> {
                    item.setParent(menuDto);
                    menuDto.addChildMenu(item);
                });
            }
        });
        /*  key null is root node*/
        return new ArrayList<>(parentChildMap.get(null));
    }
}
