package com.y3wegy.web.provider.mapper.master.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.UserRole;
import com.y3wegy.base.web.bean.user.UserSex;
import com.y3wegy.web.provider.CloudServiceApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CloudServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private SecurityUser user;

    @BeforeEach
    void setUp() {
        userMapper.delete("99");

        user = new SecurityUser();
        user.setUserName("y3wegy");
        user.setPassword("y3wegy");
        user.setUserRoleList(Arrays.asList(UserRole.USER));
        user.setNickName("y3wegy");
        user.setUserSex(UserSex.FEMALE);
        user.setId("99");
    }

    @Test
    public void testComplexQuery() {
        List<SecurityUser> userBeanList = userMapper.queryUserByUserName("y3wegy");
        assertTrue(!CollectionUtils.isEmpty(userBeanList));
        assertEquals("1", userBeanList.get(0).getId());
        assertEquals(UserRole.USER, userBeanList.get(0).getUserRoleList().get(0));
    }

    @Test
    void testInsert() {
        userMapper.insert(user);
        final List<SecurityUser> userBeanList = userMapper.queryUserByUserName("y3wegy");
        assertAll(
                () -> Assert.assertEquals("1", userBeanList.get(0).getId()),
                () -> Assert.assertEquals("y3wegy", userBeanList.get(0).getUserName()),
                () -> Assert.assertEquals("y3wegy", userBeanList.get(0).getNickName()));
    }

    @Test
    void testUpdate() {
        userMapper.insert(user);

        SecurityUser newUser = new SecurityUser();
        newUser.setId("3");
        newUser.setNickName("New");
        userMapper.update(newUser);

        List<SecurityUser> userBeanList1 = userMapper.queryUserByUserName("y3wegy");
        assertAll(
                () -> Assert.assertEquals("New", userBeanList1.get(0).getNickName()));
    }

    @Test
    void testQueryUserRoleByUserName() {
        List<UserRole> userRoleList = userMapper.queryUserRolesByUserName("y3wegy");
        assertAll(
                () -> assertTrue(CollectionUtils.isNotEmpty(userRoleList)));
    }
}
