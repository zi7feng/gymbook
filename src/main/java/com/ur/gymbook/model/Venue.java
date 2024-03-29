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
public class Venue {
    private int gymId;
    private String gymName;
    private int unitPrice;
    private int gymStatus;
    private int fourteen;
    private int fifteen;
    private int sixteen;
    private int seventeen;
    private int eighteen;
    private int nineteen;
    private int twenty;
    @JSONField(format = "yyyy-MM-dd")
    private Date date;

    @Override
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }

}
