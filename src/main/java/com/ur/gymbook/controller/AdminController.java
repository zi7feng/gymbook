package com.ur.gymbook.controller;

import com.alibaba.fastjson.JSONObject;
import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.SuperAdmin;
import com.ur.gymbook.model.Venue;
import com.ur.gymbook.service.IAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    IAdminService adminService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    protected void writeJSON2Response(Object out, HttpServletResponse response) {
        try {
            response.getWriter().print(out);
            log.debug("SERVER TO FRONT END:" + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/login")
    public void adminLogin(@RequestParam(value = "adminName") String username,
                           @RequestParam(value = "adminPwd") String password,
                           HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + username + " " + password);
        JSONObject resultJson = new JSONObject();
        Admin admin = adminService.findByNameAndPassword(username,password);
        if(admin != null && admin.getActivatedStatus() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("AdminObj", admin);
            resultJson.put("result", true);
        } else {
            resultJson.put("result", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response) {
        JSONObject result = new JSONObject();
        try {
            request.getSession().invalidate();
            result.put("flag", true);
            log.debug("Admin logout");
        } catch (Exception e) {
            log.error(e.toString());
            result.put("flag", false);
        }
        writeJSON2Response(result, response);
    }

    @PostMapping(value = "/findScheduleByNameAndDate")
    public List<Venue> adminLogin(@RequestBody String parameters,HttpServletRequest request,HttpServletResponse response) {
        JSONObject paraJson = JSONObject.parseObject(parameters);
        String gymName = paraJson.getString("gymName");
        Date date = paraJson.getSqlDate("date");
        log.debug("FRONT END TO SERVER: " + "FIND SCHEDULE "+ gymName + " " + date);
        List<Venue> venues = adminService.findScheduleByNameAndDate(gymName,date);
        return venues;
    }

    @PostMapping(value = "/insertSchedule")
    public void insertSchedule(@RequestBody String parameters,
                                 HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "INSERT SCHEDULE");
        JSONObject paraJson = JSONObject.parseObject(parameters);
        String gymName = paraJson.getString("gymName");
        int unitPrice = paraJson.getIntValue("unitPrice");
        int gymStatus = paraJson.getIntValue("gymStatus");
        Date date = paraJson.getSqlDate("date");
        JSONObject resultJson = new JSONObject();
        int ret = adminService.insertSchedule(gymName, unitPrice, gymStatus, date);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/updateSchedule")
    public void updateSchedule(@RequestBody String parameters,
                               HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "UPDATE SCHEDULE");
        JSONObject paraJson = JSONObject.parseObject(parameters);
        String gymName = paraJson.getString("gymName");
        int unitPrice = paraJson.getIntValue("unitPrice");
        int gymStatus = paraJson.getIntValue("gymStatus");
        Date date = paraJson.getSqlDate("date");
        JSONObject resultJson = new JSONObject();
        int ret = adminService.updateSchedule(gymName, unitPrice, gymStatus, date);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/adminRegister")
    public void adminRegister(@RequestParam(value = "adminName") String adminName,
                              @RequestParam(value = "adminPwd") String adminPwd,
                               HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "UPDATE SCHEDULE");
        JSONObject resultJson = new JSONObject();
        Admin ret1 = adminService.findAdminByName(adminName);
        if(ret1 != null) {
            resultJson.put("result", false);
        }else{
            resultJson.put("result", true);
            int ret2 = adminService.insertAdmin(adminName, adminPwd);
            if(ret2 > 0) {
                resultJson.put("flag", true);
            } else {
                resultJson.put("flag", false);
            }
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/updateAdminMyself")
    public void updateAdminMyself(@RequestBody String parameters,
                               HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "UPDATE SCHEDULE");
        JSONObject paraJson = JSONObject.parseObject(parameters);
        String adminPwd = paraJson.getString("adminPwd");
        int adId = paraJson.getIntValue("adId");
        String adminEmail = paraJson.getString("adminEmail");
        String adminPhone = paraJson.getString("adminPhone");
        JSONObject resultJson = new JSONObject();
        int ret = adminService.updateAdminMyself(adId, adminPwd, adminPhone, adminEmail);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

}
