package com.ur.gymbook.mapper;
import com.ur.gymbook.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /*
     查看用户名是否已存在
     */
    @Select("select * from USER u where u.User_name =#{userName}")
    User findUserByName(String userName);

    /*
     * 注册
     */
    @Insert("insert into USER (User_name, User_pwd, Age, Gender, User_phone, User_email) values " +
            "(#{user.userName}, #{user.userPwd}, #{user.age}, #{user.gender}, #{user.userPhone}, #{user.userEmail})")
    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="User_id")
    void userRegister(User user);

    /*
     * 登录
     */
    @Select("select * from USER u " +
            "where u.User_name = #{userName} " +
            "and u.User_pwd = #{userPwd}")
    User findByNameAndPassword(@Param(value = "userName") String userName, @Param(value = "userPwd") String userPwd);

}
