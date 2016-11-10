package com.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    //张小丽：字符串转date
    public java.sql.Date StringtoDate(String s) throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sdf.parse(s);
        return  new java.sql.Date(date.getTime());

    }
}
