package com.y3wegy.base.web.bean.user;

import com.y3wegy.base.web.bean.Project;
import com.y3wegy.base.web.bean.user.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author : y3wegy
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
