package com.ur.gymbook.service;

import com.ur.gymbook.mapper.UserMapper;
import com.ur.gymbook.model.User;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;



public class UserService {
    @Autowired
    private UserMapper userMapper;

//    public Result find(User user) {
//        Result result = new Result();
//        result.setSuccess
//    }
}
