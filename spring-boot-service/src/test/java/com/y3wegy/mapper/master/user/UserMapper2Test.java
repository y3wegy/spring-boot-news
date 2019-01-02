package com.y3wegy.mapper.master.user;

import com.y3wegy.CloudServiceApplication;
import com.y3wegy.base.web.bean.user.Manager;
import com.y3wegy.base.web.bean.user.SecurityUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CloudServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserMapper2Test {

    @Autowired
    private UserMapper2 userMapper2;

    @Test
    public void testSimpleQuery() throws Exception {
        List<SecurityUser> userBeanList = userMapper2.findUserByUserName("y3wegy");
        Assertions.assertTrue(!CollectionUtils.isEmpty(userBeanList));
        Assertions.assertEquals("1", userBeanList.get(0).getId());
    }

    @Test
    public void testTableJoinQuery() throws Exception {
        Manager manager = userMapper2.findMangerById("1");
        Assertions.assertNotNull(manager);
        Assertions.assertNotNull(manager.getProjectList());
        Assertions.assertEquals("Chinese",manager.getProjectList().get(0).getName());
    }
}