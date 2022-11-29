package com.ur.gymbook.controller;

import com.alibaba.fastjson.JSONObject;
import com.ur.gymbook.mapper.ReservationRecordMapper;
import com.ur.gymbook.mapper.VenueMapper;
import com.ur.gymbook.model.ReservationRecord;
import com.ur.gymbook.model.User;
import com.ur.gymbook.model.Venue;
import com.ur.gymbook.service.IUserService;
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
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    IUserService userService;

    @Resource
    private ReservationRecordMapper reservationRecordMapper;

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

    @PostMapping(value = "/isRegistered")
    public void isRegistered(@RequestParam(value = "username") String userName,
                             HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + userName);
        JSONObject resultJson = new JSONObject();
        if(userService.findUser(userName)==null) {
            resultJson.put("result", true);
        } else {
            resultJson.put("result", false);
        }
        writeJSON2Response(resultJson, response);
    }

    @PostMapping(value = "/register")
    public void userRegister(@RequestParam(value = "userName") String userName,
                             @RequestParam(value = "userPwd") String userPwd,
                             @RequestParam(value = "Age") int Age,
                             @RequestParam(value = "Gender") String Gender,
                             @RequestParam(value = "userPhone") String userPhone,
                             @RequestParam(value = "userEmail") String userEmail,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        User user = new User(0, userName, userPwd, Age, Gender, userPhone, userEmail);
//        User user = new User(0, userName, userPwd, Gender, userPhone, userEmail);
        log.debug(user.toString());
        log.debug("SAVE TO DB:" + user);
        JSONObject resultJson = new JSONObject();
        if(userService.insertUser(user) > 0) {
            resultJson.put("result", true);
        } else {
            resultJson.put("result", false);
        }
        writeJSON2Response(resultJson, response);
    }



    @PostMapping(value = "/login")
    public void userLogin(@RequestParam(value = "userName") String userName,
                          @RequestParam(value = "userPwd") String userPwd,
                          HttpServletRequest request, HttpServletResponse response) {
        log.debug("FRONT END TO SERVER:" + userName + " " + userPwd);
        JSONObject resultJson = new JSONObject();
        User user = userService.findByNameAndPassword(userName, userPwd);
        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userObj", user);
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
            log.debug("User logout");
        } catch (Exception e) {
            log.error(e.toString());
            result.put("flag", false);
        }
        writeJSON2Response(result, response);
    }

    @PostMapping(value="/findAllSchedule")
    public List<Venue> findAllSchedule(HttpServletRequest request, HttpServletResponse response) {
        List<Venue> venues = userService.findAllSchedule();
        log.debug("SERVER Get Venue List");
        return venues;

    }

//    @PostMapping(value="/findAllRecordById")
////    @RequestMapping(method = RequestMethod.GET)
//    public List<ReservationRecord> findAllRecordById(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        User currentUser = (User)session.getAttribute("userObj");
//        String userName = currentUser.getUserName();
//        User userData = userService.findUser(userName);
//        int userId = userData.getUserId();
////        JSONObject paraJson = JSONObject.parseObject(parameters);
////        int userId = paraJson.getIntValue("userId");
//        log.debug("FRONT END TO SERVER: " + "FIND RECORD BY ID" + userId);
//        List<ReservationRecord> rr = userService.findAllRecordById(userId);
//        return rr;
//    }


    @PostMapping(value="/fuzzSearch")
    public List<Venue> fuzzSearch(@RequestBody String parameters, HttpServletRequest request, HttpServletResponse response) {
        JSONObject paraJson = JSONObject.parseObject(parameters);
        String keyWord = paraJson.getString("keyWord");
        log.debug("FRONT END TO SERVER: " + "fuzzSearch" + keyWord);
        List<Venue> venues = userService.fuzzSearch(keyWord);
        return venues;
    }

    @PostMapping(value = "/fuzzSearch2")
    public List<ReservationRecord> fuzzSearch2(@RequestBody String parameters, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("userObj");
        String userName = currentUser.getUserName();
        JSONObject paraJson = JSONObject.parseObject(parameters);
        String keyWord = paraJson.getString("keyWord");
        log.debug("FRONT END TO SERVER: " + "fuzzSearch2" + keyWord);
        List<ReservationRecord> rr = userService.fuzzSearch2(keyWord,userName);
        return rr;
    }

    @PostMapping(value="/updateUserMyself")
    public void updateUserMyself(@RequestBody String parameters,
                                 HttpServletRequest request,HttpServletResponse response) {
        log.debug("FRONT END TO SERVER" + "UPDATE USER INFO");
        JSONObject paraJson = JSONObject.parseObject(parameters);
        String userPwd = paraJson.getString("userPwd");
        int userId = paraJson.getIntValue("userId");
        int Age = paraJson.getIntValue("Age");
        String userPhone = paraJson.getString("userPhone");
        String userEmail = paraJson.getString("userEmail");
        JSONObject resultJson = new JSONObject();
        int ret = userService.updateUserMyself(userId, userPwd, Age, userPhone, userEmail);
        if(ret>0) {
            resultJson.put("flag", true);
        } else {
            resultJson.put("flag", false);
        }
        writeJSON2Response(resultJson, response);
    }


    @PostMapping(value = "/deleteSchedule")
    public void deleteSchedule(@RequestBody ReservationRecord reservationRecord, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FRONT END TO SERVER: " + "DELETE SCHEDULE");
        JSONObject resultJson = new JSONObject();
        ReservationRecord r = reservationRecordMapper.findRecordById(reservationRecord.getId());
        String vTime;
        switch (r.getVisitTime()) {
            case "14:00-15:00" :
                vTime = "fourteen";
                break;
            case "15:00-16:00" :
                vTime = "fifteen";
                break;
            case "16:00-17:00" :
                vTime = "sixteen";
                break;
            case "17:00-18:00" :
                vTime = "seventeen";
                break;
            case "18:00-19:00" :
                vTime = "eighteen";
                break;
            case "19:00-20:00" :
                vTime = "nineteen";
                break;
            case "20:00-21:00" :
                vTime = "twenty";
                break;
            default:
                vTime = "";

        }
        Date date = r.getVisitDate();
        String name = r.getGymName();
        int ret = userService.deleteSchedule(reservationRecord);
        if(ret > 0) {
            resultJson.put("flag", true);
            venueMapper.updateTime1(vTime, name, date);
        } else {
            resultJson.put("flag",false);
        }
        writeJSON2Response(resultJson, response);
    }
//    @PostMapping(value = "/getAllInfo")
//    public void getAllInfo(@RequestParam(value = "limit", defaultValue = "10") Integer limit,
//                           @RequestParam(value = "offset", defaultValue = "1") Integer offset,
//                           @RequestParam(value = "sort") String sort,
//                           @RequestParam(value = "order") String order,
//                           HttpServletRequest request, HttpServletResponse response) {
//        Venue venue = (Venue) request.getSession().getAttribute("venueObj");
//        log.debug("SERVER Get Venue: " + venue);
//        int size = venueService.
//    }
}
