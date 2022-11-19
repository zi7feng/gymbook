package com.ur.gymbook.mapper;

import com.ur.gymbook.model.Admin;
import org.apache.ibatis.annotations.*;

public interface AdminMapper {
    /*
     * 注册
     */
    @Insert("insert into ADMINISTRATOR (Admin_name, Admin_pwd, Admin_phone, Admin_email) values " +
            "(#{admin.adminName}, #{admin.adminPwd}, #{admin.adminPhone}, #{admin.adminEmail})")
    @Options(useGeneratedKeys=true, keyProperty="adId", keyColumn="Ad_id")
    void adminRegister(Admin admin);

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



}
