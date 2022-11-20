package com.ur.gymbook.mapper;

import com.ur.gymbook.model.Venue;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface VenueMapper {
    /*
     查看当前场馆是否可以预约
     */
    @Select("select Gym_status from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isGymAvailable(@Param("gymName") String gymName, @Param("date") Date date);

    /*
     查看当前时间是否可以预约
     */
    @Select("select Fourteen from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isAvailableFourteen(@Param("gymName") String gymName, @Param("date") Date date);

    @Select("select Fifteen from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isAvailableFifteen(@Param("gymName") String gymName, @Param("date") Date date);

    @Select("select Sixteen from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isAvailableSixteen(@Param("gymName") String gymName, @Param("date") Date date);

    @Select("select Seventeen from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isAvailableSeventeen(@Param("gymName") String gymName, @Param("date") Date date);

    @Select("select Eighteen from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isAvailableEighteen(@Param("gymName") String gymName, @Param("date") Date date);

    @Select("select Nineteen from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isAvailableNineteen(@Param("gymName") String gymName, @Param("date") Date date);

    @Select("select Twenty from VENUE where Gym_name = #{gymName} and Date = #{date}")
    int isAvailableTwenty(@Param("gymName") String gymName, @Param("date") Date date);

    /*
    设置场馆信息
     */
    @Insert("insert into VENUE (Gym_name, Unit_price, Gym_status, Fourteen, Fifteen, Sixteen, Seventeen, Eighteen, Nineteen, Twenty, Date) " +
            "VALUES (#{venue.gymName}, #{venue.unitPrice}, #{venue.gymStatus}, #{venue.fourteen}, #{venue.fifteen}, #{venue.sixteen}, #{venue.seventeen}, #{venue.eighteen}, #{venue.nineteen}, #{venue.twenty}, #{venue.date}")
    @Options(useGeneratedKeys=true, keyProperty="gymId", keyColumn="Gym_id")
    void setVenue(Venue venue);

    /*
    更新场馆信息
     */
    @Update("Update VENUE set (Gym_name = #{venue.gymName}, " +
            "Unit_price = #{venue.unitPrice}, " +
            "Gym_status = #{venue.gymStatus}, " +
            "Fourteen = #{venue.fourteen}, " +
            "Fifteen = #{venue.fifteen}, " +
            "Sixteen = #{venue.sixteen}, " +
            "Seventeen = #{venue.seventeen}, " +
            "Eighteen = #{venue.eighteen}, " +
            "Nineteen = #{venue.nineteen}, " +
            "Twenty = #{venue.twenty}, " +
            "Date = #{venue.date} " +
            "where Gym_id = #{venue.gymId}")
    void updateVenue(Venue venue);
}
