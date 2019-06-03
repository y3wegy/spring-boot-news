package com.y3wegy.base.web.bean.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import com.y3wegy.base.tools.JackSonHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author y3wegy
 */
@Entity
public class MenuDto implements Comparable<MenuDto> {
    private String id;
    private String menuCd;
    private String displayName;
    private int level;
    private float order;
    private boolean disabled;
    private MenuType menuType;
    private String parentMenuId;
    private final List<MenuDto> childMenuList = new ArrayList<>();
    private MenuDto parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuCd() {
        return menuCd;
    }

    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getOrder() {
        return order;
    }

    public void setOrder(float order) {
        this.order = order;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public boolean isRoot() {
        return StringUtils.isEmpty(parentMenuId);
    }

    public boolean isLeaf() {
        return CollectionUtils.isEmpty(getChildMenuList());
    }

    public List<MenuDto> getChildMenuList() {
        return childMenuList;
    }

    public void addChildMenu(MenuDto menuDto) {
        this.childMenuList.add(menuDto);
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public MenuDto getParent() {
        return parent;
    }

    public void setParent(MenuDto parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(MenuDto menuDto) {
        if (this.getOrder() >= menuDto.getOrder()) {
            return 1;
        } else if (this.getOrder() < menuDto.getOrder()) {
            return -1;
        } else {
            return 0;
        }
    }

    public static final String LABEL_ID = "id";
    public static final String LABEL_MENU_CD = "menuCd";
    public static final String LABEL_DISPLAY_NAME = "displayName";
    public static final String LABEL_ORDER = "order";
    public static final String LABEL_DISABLED = "disabled";
    public static final String LABEL_TYPE = "menuType";
    public static final String LABEL_CHILD = "childMenuList";

    /**
     * -------------------------------------------------------------
     *
     * @return
     * @author @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     * convert POJO to json node
     */
    public ObjectNode toJson() {
        ObjectMapper objectMapper = JackSonHelper.getObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.set(LABEL_ID, new TextNode(getId()));
        objectNode.set(LABEL_MENU_CD, new TextNode(getMenuCd()));
        objectNode.set(LABEL_DISPLAY_NAME, new TextNode(getDisplayName()));
        objectNode.set(LABEL_DISABLED, BooleanNode.valueOf(isDisabled()));
        objectNode.set(LABEL_TYPE, new TextNode(getMenuType().name()));
        objectNode.set(LABEL_ORDER, new FloatNode(getOrder()));
        if (!CollectionUtils.isEmpty(getChildMenuList())) {
            ArrayNode children = objectMapper.createArrayNode();
            getChildMenuList().forEach(child -> {
                children.add(child.toJson());
            });
            objectNode.set(LABEL_CHILD, children);
        }
        return objectNode;
    }
}
