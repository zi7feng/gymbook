package com.ur.gymbook.mapper;

import com.ur.gymbook.model.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    /*
     * 注册
     */
    @Insert("insert into ADMINISTRATOR (Admin_name, Admin_pwd) values " +
            "(#{adminName}, #{adminPwd})")
    @Options(useGeneratedKeys=true, keyProperty="adId", keyColumn="Ad_id")
    int insertAdmin(Admin admin);

    /*
     * 登录
     */
    @Select("select * from ADMINISTRATOR a " +
            "where a.Admin_name = #{adminName} " +
            "and a.Admin_pwd = #{adminPwd}")
    Admin findByNameAndPassword(@Param(value = "adminName") String adminName, @Param(value = "adminPwd") String adminPwd);

    /*
     * 查看用户名是否已存在
     */
    @Select("select * from ADMINISTRATOR a " +
            "where a.Admin_name = #{adminName}")
    Admin findAdminByName(@Param("adminName") String adminName);


    @Update("update ADMINISTRATOR set Admin_pwd = #{adminPwd}, Admin_phone = #{adminPhone}, Admin_email = #{adminEmail}" +
            "where Ad_id = #{adId}")
    int updateAdminMyself(Admin admin);


}
