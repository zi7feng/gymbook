package com.ur.gymbook.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.ur.gymbook.mapper.AdminMapper;
import com.ur.gymbook.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {
    @Resource
    private AdminMapper adminMapper;

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
}
