package com.ur.gymbook.service;

import com.ur.gymbook.model.ReservationRecord;
import com.ur.gymbook.model.User;
import com.ur.gymbook.model.Venue;

import java.util.Date;
import java.util.List;

public interface IUserService {
    User findByNameAndPassword(String name, String password);

    User findUser(String name);

    int insertUser(User user);

    List<Venue> findAllSchedule();

    List<Venue> fuzzSearch(String keyWord);

    int updateUserMyself(int userId, String userPwd, int Age, String userPhone, String userEmail);


    List<ReservationRecord> fuzzSearch2(String keyWord, String userName, int id);
//    ReservationRecord findAllRecordById(int userId);
//    int insertRecord(int gymId, int userId, Date visitDate, String visitTime, java.util.Date createTime);

    int deleteSchedule(ReservationRecord reservationRecord);
}
