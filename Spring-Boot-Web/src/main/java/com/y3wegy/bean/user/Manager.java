package com.y3wegy.bean.user;

import java.util.List;

import com.y3wegy.bean.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: y3weg
 * Date: 22-Sep-17-022
 * Time: 22:44:45
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Manager extends User {
    private List<Project> projectList;

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
