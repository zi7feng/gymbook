package com.ur.gymbook.service.impl;

import com.ur.gymbook.mapper.AdminMapper;
import com.ur.gymbook.mapper.ReservationRecordMapper;
import com.ur.gymbook.mapper.VenueMapper;
import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.ReservationRecord;
import com.ur.gymbook.model.Venue;
import com.ur.gymbook.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;


@Service
public class AdminServiceImpl implements IAdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private VenueMapper venueMapper;

    @Resource
    private ReservationRecordMapper reservationRecordMapper;

    /**
     * findByNameAndPassword
     * @param adminName
     * @param adminPwd
     * @return Admin
     */
    @Override
    public Admin findByNameAndPassword(String adminName, String adminPwd) {
        return adminMapper.findByNameAndPassword(adminName,adminPwd);
    }

    /**
     * findScheduleByNameAndDate
     * @param gymName
     * @param date
     * @return List<Venue>
     */
    @Override
    public List<Venue> findScheduleByNameAndDate(String gymName, Date date) {
        return venueMapper.findScheduleByNameAndDate(gymName, date);
    }

    /**
     * insertSchedule
     * @param gymName
     * @param unitPrice
     * @param gymStatus
     * @param date
     * @return int
     */
    @Override
    public int insertSchedule(String gymName, int unitPrice, int gymStatus, Date date) {
        return venueMapper.insertSchedule(gymName, unitPrice, gymStatus, date);
    }

    /**
     * updateSchedule
     * @param gymName
     * @param unitPrice
     * @param gymStatus
     * @param date
     * @return int
     */
    @Override
    public int updateSchedule(String gymName, int unitPrice, int gymStatus, Date date) {
        return venueMapper.updateSchedule(gymName, unitPrice, gymStatus, date);
    }

    /**
     * insertAdmin
     * @param adminName
     * @param adminPwd
     * @return int
     */
    @Override
    public int insertAdmin(String adminName, String adminPwd) {
        Admin admin = new Admin();
        admin.setAdminName(adminName);
        admin.setAdminPwd(adminPwd);
        return adminMapper.insertAdmin(admin);
    }

    /**
     * findAdminByName
     * @param adminName
     * @return Admin
     */
    @Override
    public Admin findAdminByName(String adminName) {
        return adminMapper.findAdminByName(adminName);
    }

    /**
     * updateAdminMyself
     * @param adId
     * @param adminPwd
     * @param adminPhone
     * @param adminEmail
     * @return int
     */
    @Override
    public int updateAdminMyself(int adId, String adminPwd, String adminPhone, String adminEmail) {
        Admin admin = new Admin();
        admin.setAdId(adId);
        admin.setAdminPwd(adminPwd);
        admin.setAdminPhone(adminPhone);
        admin.setAdminEmail(adminEmail);
        return adminMapper.updateAdminMyself(admin);
    }

    /**
     * findRecordByNameAndDate
     * @param gymName
     * @param visitDate
     * @return List<ReservationRecord>
     */
    @Override
    public List<ReservationRecord> findRecordByNameAndDate(String gymName, Date visitDate) {
        ReservationRecord reservationRecord = new ReservationRecord();
        reservationRecord.setGymName(gymName);
        reservationRecord.setVisitDate(visitDate);
        return reservationRecordMapper.findRecordByNameAndDate(reservationRecord);
    }
}
