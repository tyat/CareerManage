package com.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
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
    //zxl：字符串转时间
    public Timestamp StringtoTime(String s)throws  Exception{
        DateFormat df = DateFormat.getDateInstance();
        Date d = df.parse(s);
        long da = d.getTime();
        Timestamp ts = new Timestamp(da);
        return  ts;
    }
    //zxl：字符串转时间
    public Timestamp StringtoTime2(String s)throws  Exception{
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df1.format( StringtoDate2(s));
        Timestamp ts = Timestamp.valueOf(time);
//        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d = (Date) f.parseObject(s);
//        Timestamp ts = new Timestamp(d.getTime());
        return  ts;
    }
    //zxl：字符串转date
    public java.sql.Date StringtoDate(String s) throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(s);
        return  new java.sql.Date(date.getTime());
    }
    //zxl：字符串转date
    public java.sql.Date StringtoDate2(String s) throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sdf.parse(s);
        return  new java.sql.Date(date.getTime());
    }
    //zxl：得到系统sql时间
    public java.sql.Date SysDate() throws  Exception{
        String dt = new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(dt);
        return  new java.sql.Date(date.getTime());
    }
//    public Timestamp subDate(Timestamp date) throws Exception{
//        String dt = new String(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
//        dt=dt.substring(0,10);
//        System.out.print("这是一二个-----------"+dt);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date0=sdf.parse(dt);
//        long da = date0.getTime();
//        Timestamp ts = new Timestamp(date0.getTime());
//        return ts;
//    }
}
