package com.ur.gymbook.model;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int userId;
    private String userName;
    private String userPwd;
    private int age;
    private String userPhone;
    private int gender;
    private String userMail;

    @Override
    public String toString() {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
