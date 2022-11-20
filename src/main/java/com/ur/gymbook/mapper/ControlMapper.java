package com.ur.gymbook.mapper;

import com.ur.gymbook.model.Control;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ControlMapper {
    /*
     设置Control关系
     */
    @Insert("insert into CONTROL values (#{suId}, #{adId})")
    void setControl(@Param("suId") int suId, @Param("adId") int adId);

    /*
     删除Control关系
     */
    @Delete("delete from CONTROL c where c.Su_id = #{suId} and c.Ad_id = #{adId}")
    void deleteControl(@Param("suId") int suId, @Param("adId") int adId);

    /*
     查看Su_id对应的所有Control
     */
    @Select("select * from CONTROL c where c.Su_id = #{suId}")
    List<Control> checkControlBySuId(int suId);

    /*
     查看Admin_id对应的所有Control
     */
    @Select("select * from CONTROL c where c.admin_id = #{adminId}")
    List<Control> checkControlByAdminId(int adminId);
}
