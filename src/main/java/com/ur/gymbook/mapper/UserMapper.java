package com.ur.gymbook.mapper;

import com.ur.gymbook.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * user is existed
     */
    @Select("select u.User_name from USER u whrere u.User_id =#{userName}")
    User findUserByName(@Param("userName") String userName);
}
