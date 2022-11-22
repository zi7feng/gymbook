package com.ur.gymbook.service.impl;

import com.ur.gymbook.mapper.AdminMapper;
import com.ur.gymbook.mapper.VenueMapper;
import com.ur.gymbook.model.Admin;
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
    @Override
    public Admin findByNameAndPassword(String adminName, String adminPwd) {
        return adminMapper.findByNameAndPassword(adminName,adminPwd);
    }

    @Override
    public List<Venue> findScheduleByNameAndDate(String gymName, Date date) {
        return venueMapper.findScheduleByNameAndDate(gymName, date);
    }

    @Override
    public int insertSchedule(String gymName, int unitPrice, int gymStatus, Date date) {
        return venueMapper.insertSchedule(gymName, unitPrice, gymStatus, date);
    }

    @Override
    public int updateSchedule(String gymName, int unitPrice, int gymStatus, Date date) {
        return venueMapper.updateSchedule(gymName, unitPrice, gymStatus, date);
    }

    @Override
    public int insertAdmin(String adminName, String adminPwd) {
        Admin admin = new Admin();
        admin.setAdminName(adminName);
        admin.setAdminPwd(adminPwd);
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public Admin findAdminByName(String adminName) {
        return adminMapper.findAdminByName(adminName);
    }

    @Override
    public int updateAdminMyself(int adId, String adminPwd, String adminPhone, String adminEmail) {
        Admin admin = new Admin();
        admin.setAdId(adId);
        admin.setAdminPwd(adminPwd);
        admin.setAdminPhone(adminPhone);
        admin.setAdminEmail(adminEmail);
        return adminMapper.updateAdminMyself(admin);
    }

}
