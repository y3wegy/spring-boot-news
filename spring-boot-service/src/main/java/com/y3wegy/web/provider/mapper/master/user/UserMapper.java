package com.y3wegy.web.provider.mapper.master.user;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.UserRole;

/**
 * @author y3wegy
 */
@Repository("UserMapper")
public interface UserMapper {

    /**
     * -------------------------------------------------------------
     *
     * @param userName
     * @return
     * @author @date        @comment
     * y3wegy   11/22/2018     init version
     * -------------------------------------------------------------
     */
    List<SecurityUser> queryUserByUserName(@Param("userName") String userName);

    /**
     * -------------------------------------------------------------
     *
     * @param userName
     * @return
     * @author @date        @comment
     * y3wegy   11/22/2018     init version
     * -------------------------------------------------------------
     */
    @Select("select r.ROLE_NAME from USERS u left join USER_ROLES ur ON u.ID = ur.USER_ID JOIN ROLE r ON ur.ROLE_ID = r.ID AND  u.USER_NAME = #{userName}")
    @Results({
            @Result(property = "userRole", column = "ROLE_NAME", javaType = UserRole.class)
    })
    List<UserRole> queryUserRolesByUserName(String userName);

    /**
     * -------------------------------------------------------------
     *
     * @param user
     * @author @date        @comment
     * y3wegy   11/22/2018     init version
     * -------------------------------------------------------------
     */
    @Insert("INSERT INTO USERS(ID,USER_NAME,NICK_NAME,PASSWORD,USER_SEX) VALUES(#{id},#{userName},#{nickName}, #{password},#{userSex})")
    void insert(SecurityUser user);

    /**
     * -------------------------------------------------------------
     *
     * @param user
     * @author @date        @comment
     * y3wegy   11/22/2018     init version
     * -------------------------------------------------------------
     */
    @Update("UPDATE USERS SET NICK_NAME =#{nickName} WHERE ID =#{id}")
    void update(SecurityUser user);

    /**
     * -------------------------------------------------------------
     *
     * @param id
     * @author @date        @comment
     * y3wegy   11/22/2018     init version
     * -------------------------------------------------------------
     */
    @Delete("DELETE FROM USERS WHERE ID =#{id}")
    void delete(String id);

}
