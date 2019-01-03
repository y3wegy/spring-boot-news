package com.y3wegy.mapper.master.user;

import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.UserRole;
import com.y3wegy.base.web.bean.user.UserSex;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author y3wegy
 */
@Repository("UserMapper")
public interface UserMapper {

    /**
     * -------------------------------------------------------------
     *
     * @return
     * @author @date        @comment
     * y3wegy   11/22/2018     init version
     * -------------------------------------------------------------
     * @param userName
     */
    @Select("SELECT ID,USER_NAME,NICK_NAME,USER_SEX,USER_ROLE,PASSWORD FROM USER WHERE USER_NAME = #{userName}")
    @Results({
            @Result(property = "id", column = "ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "userName", column = "USER_NAME"),
            @Result(property = "nickName", column = "NICK_NAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "userRole", column = "USER_ROLE", javaType = UserRole.class),
            @Result(property = "userSex", column = "USER_SEX", javaType = UserSex.class)

    })
    List<SecurityUser> get(String userName);

    /**
     * -------------------------------------------------------------
     *
     * @param user
     * @author @date        @comment
     * y3wegy   11/22/2018     init version
     * -------------------------------------------------------------
     */
    @Insert("INSERT INTO USERS(ID,USER_NAME,NICK_NAME,PASSWORD,USER_ROLE,USER_SEX) VALUES(#{id},#{userName},#{nickName}, #{password},#{userRole},#{userSex})")
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
