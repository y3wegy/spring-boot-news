package com.y3wegy.mapper.master.user;

import com.y3wegy.base.web.bean.user.Manager;
import com.y3wegy.base.web.bean.user.SecurityUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Rui
 */
@Repository("UserMapper2")
public interface UserMapper2 {

    /**
     * -------------------------------------------------------------
     *
     * @param userName
     * @return
     * @author @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     */
    List<SecurityUser> findUserByUserName(@Param("userName") String userName);

    /**
     * -------------------------------------------------------------
     *
     * @param id
     * @return
     * @author @date        @comment
     * Chen, Rui   11/22/2018     init version
     * -------------------------------------------------------------
     */
    Manager findMangerById(@Param("id") String id);

}
