package com.ur.gymbook.service;

import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.Venue;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface IAdminService {
    Admin findByNameAndPassword(String adminName, String adminPwd);

    List<Venue> findScheduleByNameAndDate(String gymName, Date date);

    int insertSchedule(String gymName, int unitPrice, int gymStatus, Date date);

    int updateSchedule(String gymName, int unitPrice, int gymStatus, Date date);

}
