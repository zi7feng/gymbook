package com.ur.gymbook.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationRecord {
    private int id;
    private int gymId;
    private int userId;
    @JSONField(format = "yyyy-MM-dd")
    private Date visitDate;
    private String visitTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Override
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }
}
