package com.y3wegy.bean.project;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: y3weg
 * Date: 22-Sep-17-022
 * Time: 22:34:37
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Project {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
