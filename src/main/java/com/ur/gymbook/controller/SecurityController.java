package com.ur.gymbook.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ur.gymbook.mapper.AdminMapper;
import com.ur.gymbook.mapper.ReservationRecordMapper;
import com.ur.gymbook.mapper.UserMapper;
import com.ur.gymbook.mapper.VenueMapper;
import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.ReservationRecord;
import com.ur.gymbook.model.User;
import com.ur.gymbook.model.Venue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private VenueMapper venueMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    protected void writeJSON2Response(Object out, HttpServletResponse response) {
        try {
            response.getWriter().print(out);
            log.debug("SERVER TO FRONT END:" + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Resource
    private ReservationRecordMapper reservationRecordMapper;
//    @RequestMapping(value = "/adminName", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentAdminName(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Admin currentAdmin =(Admin)session.getAttribute("AdminObj");
//        return currentAdmin.getAdminName();
//    }
//
//    @RequestMapping(value = "/adId", method = RequestMethod.GET)
//    @ResponseBody
//    public int currentAdId(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Admin currentAdmin =(Admin)session.getAttribute("AdminObj");
//        return currentAdmin.getAdId();
//    }

    @RequestMapping(value = "/currentAdmin", method = RequestMethod.GET)
    @ResponseBody
    public List<Admin> currentAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin currentAdmin =(Admin)session.getAttribute("AdminObj");
        String adminName = currentAdmin.getAdminName();
        Admin adminData = adminMapper.findAdminByName(adminName);
        List<Admin> list = new ArrayList<>();
        list.add(adminData);
        return list;
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    @ResponseBody
    public List<User> currentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("userObj");
        String userName = currentUser.getUserName();
        User userData = userMapper.findUserByName(userName);
        List<User> list = new ArrayList<>();
        list.add(userData);
        return list;
    }

    @RequestMapping(value = "/currentUserRecord", method = RequestMethod.GET)
    @ResponseBody
    public List<ReservationRecord> currentUserRecord(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("userObj");
        String userName = currentUser.getUserName();
        User userData = userMapper.findUserByName(userName);
        int userId = userData.getUserId();
        List<ReservationRecord> rr = reservationRecordMapper.findRecordByUserId(userId);
        return rr;
    }

    @PostMapping(value = "/bookVenue")
    public void bookVenue(@RequestBody String parameters, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("userObj");
        String userName = currentUser.getUserName();
        User userData = userMapper.findUserByName(userName);
        int userId = userData.getUserId();
        String userPhone = userData.getUserPhone();
        String userEmail = userData.getUserEmail();

        JSONObject paraJson = JSONObject.parseObject(parameters);
        String gymName = paraJson.getString("gymName");
        Date visitDate = paraJson.getSqlDate("date");
        log.debug("FRONT END TO SERVER: " + visitDate);
        String visitTime = paraJson.getString("visitTime");
//        java.util.Date createTime = paraJson.getSqlDate("createTime");
        java.util.Date createTime = new java.util.Date();

        Venue v = venueMapper.findByNameAndDate(gymName, visitDate);
        ReservationRecord rr = new ReservationRecord(0, gymName, userId, userName,
                userPhone, userEmail, visitDate, visitTime, createTime);
        int f = venueMapper.isAvailable(visitTime, gymName, visitDate);
//        int f = venueMapper.isAvailableSixteen(gymName, visitDate);
        JSONObject resultJson = new JSONObject();
        int s = venueMapper.isGymAvailable(gymName, visitDate);
        if(f == 1 && s == 1) {
            int ret = reservationRecordMapper.insertRecord(rr);
            venueMapper.updateTime(visitTime, gymName, visitDate);
            if (ret > 0) {
                resultJson.put("flag", true);
            }

        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);

/**
 *
 * select * from venue v where v.gym_name=?可能会查到多条记录！！！需要结合date一起查
 *
 */
    }


}
