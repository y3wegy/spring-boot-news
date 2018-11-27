package com.y3wegy.mapper.master.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.y3wegy.bean.user.Manager;
import com.y3wegy.bean.user.SecurityUser;

/**
 * @author Rui
 */
@Repository("UserMapper2")
public interface UserMapper2 {

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     * @param userName
     * @return
     */
    List<SecurityUser> findUserByUserName(@Param("userName") String userName);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     * @param id
     * @return
     */
    Manager findMangerById(@Param("id") String id);

}
