package com.ur.gymbook.mapper;

import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.SuperAdmin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SuperAdminMapper {
    /*
     * 查询所有Super Admin
     */
    @Select("select * from SUPER_ADMINISTRATOR")
    List<SuperAdmin> listSu();

    /*
     * 登录
     */
    @Select("select * from SUPER_ADMINISTRATOR su " +
            "where su.Super_user_name = #{suUserName} " +
            "and su.Super_user_pwd = #{suUserPwd}")
    SuperAdmin findByNameAndPassword(@Param("suUserName") String suUserName, @Param("suUserPwd") String suUserPwd);

    /*
     * 查询所有Admin
     */
    @Select("select * from ADMINISTRATOR")
    List<Admin> listAdmin();

    /*
     * 切换Admin的Activated_status
     */
    @Update("update ADMINISTRATOR a set a.Activated_status = #{admin.activatedStatus} where a.Ad_id = #{admin.adId}")
    int activatedStatusSwitch(Admin admin);
}