package com.ur.gymbook.service.impl;

import com.ur.gymbook.mapper.ReservationRecordMapper;
import com.ur.gymbook.mapper.UserMapper;
import com.ur.gymbook.mapper.VenueMapper;
import com.ur.gymbook.model.ReservationRecord;
import com.ur.gymbook.model.User;
import com.ur.gymbook.model.Venue;
import com.ur.gymbook.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private VenueMapper venueMapper;

    @Resource
    private ReservationRecordMapper reservationRecordMapper;

    @Override
    public User findByNameAndPassword(String name, String password) {
        return userMapper.findByNameAndPassword(name, password);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User findUser(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public List<Venue> findAllSchedule() {
        return venueMapper.findAllSchedule();
    }

    @Override
    public List<Venue> fuzzSearch(String keyWord) {
        return venueMapper.fuzzSearch(keyWord);
    }

    @Override
    public int updateUserMyself(int userId, String userPwd, int Age, String userPhone, String userEmail) {
        User user = new User();
        user.setUserId(userId);
        user.setUserPwd(userPwd);
        user.setAge(Age);
        user.setUserPhone(userPhone);
        user.setUserEmail(userEmail);
        return userMapper.updateUserMyself(user);
    }

    @Override
    public List<ReservationRecord> fuzzSearch2(String keyWord, String userName, int id) {
        return reservationRecordMapper.fuzzSearch(keyWord, userName, id);
    }

    @Override
    public int deleteSchedule(ReservationRecord reservationRecord) {
        return reservationRecordMapper.deleteSchedule(reservationRecord);
    }

    @Override
    public int canDelete(ReservationRecord reservationRecord) {
        return reservationRecordMapper.canDelete(reservationRecord);
    }


//    @Override
//    public ReservationRecord findAllRecordById(int userId) {
//        return reservationRecordMapper.findRecordByUserId(userId);
//    }
}
