package com.ur.gymbook.mapper;

import com.ur.gymbook.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * user is existed
     */
    @Select("select * from USER u where u.User_name =#{userName}")
    User findUserByName(@Param("userName") String userName);

//    @Select("select * from USER where user_id=#{userId}")
//    User findUserById(@Param("userId") int userId);

    /**
     * register
     * @param user
     */
    @Insert("insert into USER values(#{userId}, #{userName}, #{userPwd},#{Age}, #{Gender}, #{userPhone}, #{userEmail})")
//    @Insert("Insert into User values(#{userId}, #{userName}, #{userPwd},#{Gender}, #{userPhone}, #{userEmail})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "userId")
    int insertUser(User user);

    /**
     * login
     */
    @Select("select * from user u where u.user_name=#{userName} and u.user_pwd=#{userPwd}")
    User findByNameAndPassword(String userName, String userPwd);

    @Update("update user set user_pwd = #{userPwd}, age = #{Age}, user_phone=#{userPhone}, user_email =#{userEmail} " +
            "where user_id = #{userId}")
    int updateUserMyself(User user);

}
