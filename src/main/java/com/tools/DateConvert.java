package com.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/31.
 */
public class DateConvert {
    public Timestamp convert() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return ts;
    }
    //张小丽：字符串转时间
    public Timestamp StringtoTime(String s)throws  Exception{
        DateFormat df = DateFormat.getDateInstance();
        Date d = df.parse(s);
        long da = d.getTime();
        Timestamp ts = new Timestamp(da);
        return  ts;
    }
}
