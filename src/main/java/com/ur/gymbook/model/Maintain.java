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
public class Maintain {
    private int adId;
    private int gymId;
    @JSONField(format = "yyyy-MM-dd")
    private Date date;

    @Override
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
