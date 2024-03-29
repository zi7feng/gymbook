package com.ur.gymbook.mapper;

import com.ur.gymbook.model.Maintain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MaintainMapper {
    /*
    根据Ad_id查看Maintain关系
     */
    @Select("select * from MAINTAIN where Ad_id = #{adId}")
    Maintain findMaintainByAdId(int adId);

    /*
    根据Gym_id查看Maintain关系
     */
    @Select("select * from MAINTAIN where Gym_id = #{gymId}")
    Maintain findMaintainByGymId(int gymId);

    /*
    创建Maintain关系
     */
    @Insert("insert into MAINTAIN (Ad_id, Gym_id, Date) values" +
            "(#{m.adId}, #{m.gymId}, #{m.date})")
    void setMaintain(Maintain m);

    /*
    删除Maintain关系
     */
    @Delete("delete from MAINTAIN where Ad_id = #{m.adId} and Gym_id = #{m.gymId} and Date = #{m.date}")
    void deleteMaintain(Maintain m);
}
