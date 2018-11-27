package com.y3wegy.mapper.master.user;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.y3wegy.bean.user.SecurityUser;
import com.y3wegy.bean.user.UserRole;
import com.y3wegy.bean.user.UserSex;

/**
 * @author Rui
 */
@Repository("UserMapper")
public interface UserMapper {

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     * @param userName
     * @return
     */
    @Select("SELECT ID,USER_NAME,NICK_NAME,USER_SEX,USER_ROLE,PASSWORD FROM USERS WHERE USER_NAME = #{userName}")
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
     * @author     @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     * @param user
     */
    @Insert("INSERT INTO USERS(ID,USER_NAME,NICK_NAME,PASSWORD,USER_ROLE,USER_SEX) VALUES(#{id},#{userName},#{nickName}, #{password},#{userRole},#{userSex})")
    void insert(SecurityUser user);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     * @param user
     */
    @Update("UPDATE USERS SET NICK_NAME =#{nickName} WHERE ID =#{id}")
    void update(SecurityUser user);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     * @param id
     */
    @Delete("DELETE FROM USERS WHERE ID =#{id}")
    void delete(String id);

}
