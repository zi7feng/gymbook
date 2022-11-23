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
    @Select("select * from RESERVATION_RECORD where User_id = #{userId}")
    List<ReservationRecord> findRecordByUserName(int userId);

    /*
    创建预约记录
     */
    @Insert("insert into RESERVATION_RECORD (Gym_id, User_id, Visit_date, Visit_time, Create_time) " +
            "values (#{record.gymId}, #{record.userId}, #{record.visitDate}, #{record.visitTime}, #{record.createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "Id")
    void setRecord(ReservationRecord record);

    /*
    删除预约记录
     */
    @Delete("delete from RESERVATION_RECORD where Id = #{id}")
    void deleteRecord(int id);
}
