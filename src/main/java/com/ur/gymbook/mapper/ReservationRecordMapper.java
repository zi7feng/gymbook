package com.ur.gymbook.mapper;

import com.ur.gymbook.model.ReservationRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReservationRecordMapper {
    /*
    查询所有预约记录
     */
    @Select("select * from RESERVATION_RECORD")
    List<ReservationRecord> listReservationRecord();

    @Select("select Id, Gym_name, User_name, User_phone, User_email, Visit_Date, Visit_time, Create_time from RESERVATION_RECORD join USER where Gym_name = #{gymName} and Visit_date = #{visitDate}")
    List<ReservationRecord> findRecordByNameAndDate(ReservationRecord reservationRecord);

    /*
    根据userId查询预约记录
     */
//    @Select("select * from RESERVATION_RECORD where User_id = #{userId}")
//    List<ReservationRecord> findRecordByUserId(int userId);

    @Select("select Id, Gym_name, User_name, User_phone, User_email, Visit_Date, Visit_time, Create_time from RESERVATION_RECORD r, USER u where r.user_id=#{userId} and u.user_id=#{userId}")
    List<ReservationRecord> findRecordByUserId(int userId);

    @Select("select * from reservation_record where id = #{id}")
    ReservationRecord findRecordById(int id);


    /*
    创建预约记录
     */
    @Insert("insert into RESERVATION_RECORD (Gym_id, User_id, Visit_date, Visit_time, Create_time) " +
            "values (#{record.gymId}, #{record.userId}, #{record.visitDate}, #{record.visitTime}, #{record.createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "Id")
    int setRecord(ReservationRecord record);

    /*
    删除预约记录
     */

    @Delete("delete from RESERVATION_RECORD where Id = #{id}")
    int deleteSchedule(ReservationRecord reservationRecord);

    @Insert("insert into RESERVATION_RECORD (Gym_name, User_id, Visit_date, Visit_time, Create_time) " +
            "values (#{gymName}, #{userId}, #{visitDate}, #{visitTime}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "Id")
    int insertRecord(ReservationRecord record);

//    @Select("select * from RESERVATION_RECORD where user_name = #{userName}")
//    List<ReservationRecord> findRecordByUserName(String userName);

    @Select("select Id, Gym_name, User_name, User_phone, User_email, Visit_Date, Visit_time, Create_time from RESERVATION_RECORD r, USER u " +
            "where (r.gym_name like concat('%', #{keyWord},'%') " +
            "or r.visit_date like concat('%', #{keyWord},'%')) " +
            "and u.user_name = #{userName} and r.user_id = #{userId}")
    List<ReservationRecord> fuzzSearch(@Param("keyWord") String keyWord, @Param("userName") String userName, @Param("userId") int userId);
}


