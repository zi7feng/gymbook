package com.ur.gymbook.controller;

import com.alibaba.fastjson.JSONObject;
import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.SuperAdmin;
import com.ur.gymbook.service.ISuperAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/superAdmin")
public class SuperAdminController {
    @Resource
    ISuperAdminService superAdminService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    protected void writeJSON2Response(Object out, HttpServletResponse response) {
        try {
            response.getWriter().print(out);
            log.debug("SERVER TO FRONT END:" + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/listSu")
    public List<SuperAdmin> listSu() {
        return superAdminService.listSu();
    }

    @GetMapping("/listAdmin")
    public List<Admin> listAdmin() {
        return superAdminService.listAdmin();
    }

    @PostMapping(value = "/login")
    public void adminLogin(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + username + " " + password);
        JSONObject resultJson = new JSONObject();
        SuperAdmin admin = superAdminService.findByNameAndPassword(username,password);
        if(admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("SuperAdminObj",admin);
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
            log.debug("Super Admin logout");
        } catch (Exception e) {
            log.error(e.toString());
            result.put("flag", false);
        }
        writeJSON2Response(request, response);
    }

    @PostMapping(value = "/updatePassword")
    public void superAdminUpdate(@RequestBody SuperAdmin superAdmin,
                           HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "Edit SU "+ superAdmin.getSuUserName() + " " + superAdmin.getSuUserPwd());
        JSONObject resultJson = new JSONObject();
        int ret = superAdminService.updateAccount(superAdmin);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/insertAccount")
    public void superAdminInsert(@RequestBody SuperAdmin superAdmin,
                           HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "INSERT SU "+ superAdmin.getSuUserName() + " " + superAdmin.getSuUserPwd());
        JSONObject resultJson = new JSONObject();
        int ret = superAdminService.insertAccount(superAdmin);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/activateAccount")
    public void changeAdminStatus(@RequestBody Admin admin,
                                 HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "CHANGE STATUS");
        JSONObject resultJson = new JSONObject();
        int ret = superAdminService.activatedStatusSwitch(admin);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/deleteAccount")
    public void changeAdminStatus(@RequestBody SuperAdmin superAdmin,
                                  HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "DELETE");
        JSONObject resultJson = new JSONObject();
        int ret = superAdminService.deleteAccount(superAdmin);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/deleteAdminAccount")
    public void deleteAdminAccount(@RequestBody Admin admin,
                                  HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "DELETE ADMIN");
        JSONObject resultJson = new JSONObject();
        int ret = superAdminService.deleteAdminAccount(admin);
        if(ret > 0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }

}
