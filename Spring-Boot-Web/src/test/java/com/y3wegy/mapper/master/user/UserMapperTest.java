package com.y3wegy.mapper.master.user;

import com.y3wegy.WebApplication;
import com.y3wegy.bean.user.SecurityUser;
import com.y3wegy.bean.user.UserRole;
import com.y3wegy.bean.user.UserSex;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper.delete("3");

    }

    @Test
    void testInsert() {
        SecurityUser user = new SecurityUser();
        user.setUserName("USER_NAME3");
        user.setPassword("12345");
        user.setUserRole(UserRole.USER);
        user.setNickName("TEST3");
        user.setUserSex(UserSex.FEMALE);
        user.setId("3");
        userMapper.insert(user);
        final List<SecurityUser> userBeanList = userMapper.get("USER_NAME3");
        assertAll(
                () -> Assert.assertEquals("3", userBeanList.get(0).getId()),
                () -> Assert.assertEquals("USER_NAME3", userBeanList.get(0).getUserName()),
                () -> Assert.assertEquals("TEST3", userBeanList.get(0).getNickName()));
    }

    @Test
    void testUpdate() {
        SecurityUser user = new SecurityUser();
        user.setUserName("USER_NAME3");
        user.setPassword("12345");
        user.setUserRole(UserRole.USER);
        user.setNickName("TEST3");
        user.setUserSex(UserSex.FEMALE);
        user.setId("3");
        userMapper.insert(user);

        SecurityUser newUser = new SecurityUser();
        newUser.setId("3");
        newUser.setNickName("New");
        userMapper.update(newUser);

        List<SecurityUser> userBeanList1 = userMapper.get("USER_NAME3");
        assertAll(
                () -> Assert.assertEquals("New", userBeanList1.get(0).getNickName()));
    }
}
