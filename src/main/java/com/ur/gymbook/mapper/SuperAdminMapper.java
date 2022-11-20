package com.ur.gymbook.mapper;

import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.SuperAdmin;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SuperAdminMapper {
    /*
     * 查询所有Super Admin
     */
    @Select("select * from SUPER_ADMINISTRATOR")
    /*
    @Results(id="superAdminMap", value={
            @Result(column="Su_id", property="suId", jdbcType = JdbcType.INTEGER, id=true),
            @Result(column="Su_user_name", property="suUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column="Su_user_pwd", property="suUserPwd", jdbcType=JdbcType.VARCHAR)
    })
     */
    List<SuperAdmin> listSu();

    /*
     * 登录
     */
    @Select("select * from SUPER_ADMINISTRATOR su " +
            "where su.Su_user_name = #{suUserName} " +
            "and su.Su_user_pwd = #{suUserPwd}")
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
    void activatedStatusSwitch(Admin admin);
}