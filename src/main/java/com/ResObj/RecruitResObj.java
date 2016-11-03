package com.ResObj;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by w on 2016/10/31.
 */
public class RecruitResObj {
    private Integer rid;
    private Boolean rsex;
    private Integer rsalary;
    private Object rstart;
    private Object rend;
    private Integer rnum;
    private String rinfo;
    private Integer rstate;

    private Integer aid;
    private String aprovince;
    private String acity;

    private String iaddress;
    private String itype;
    private Object itime;

    private Integer jid;
    private String jname;

    private Integer cid;
    private String cname;
    private String chr;
    private String cphone;
    private String cemail;

    public RecruitResObj() {
    }

    public RecruitResObj(Integer rid, Boolean rsex, Integer rsalary, Object rstart, Object rend, Integer rnum, String rinfo, Integer rstate, Integer aid, String aprovince, String acity, String iaddress, String itype, Object itime, Integer jid, String jname, Integer cid, String cname, String chr, String cphone, String cemail) {
        this.rid = rid;
        this.rsex = rsex;
        this.rsalary = rsalary;
        this.rstart = stringToTimestamp(rstart.toString());
        this.rend = stringToTimestamp(rend.toString());
        this.rnum = rnum;
        this.rinfo = rinfo;
        this.rstate = rstate;
        this.aid = aid;
        this.aprovince = aprovince;
        this.acity = acity;
        this.iaddress = iaddress;
        this.itype = itype;
        this.itime = stringToTimestamp(itime.toString());
        this.jid = jid;
        this.jname = jname;
        this.cid = cid;
        this.cname = cname;
        this.chr = chr;
        this.cphone = cphone;
        this.cemail = cemail;
    }

    public static Timestamp stringToTimestamp(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(dateStr);
            date.getTime();
            cal.setTime(date);
            return new Timestamp(cal.getTimeInMillis());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(new Date());
        return new Timestamp(cal.getTimeInMillis());
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Boolean getRsex() {
        return rsex;
    }

    public void setRsex(Boolean rsex) {
        this.rsex = rsex;
    }

    public Integer getRsalary() {
        return rsalary;
    }

    public void setRsalary(Integer rsalary) {
        this.rsalary = rsalary;
    }

    public Object getRstart() {
        return rstart;
    }

    public void setRstart(Object rstart) {
        this.rstart = rstart;
    }

    public Object getRend() {
        return rend;
    }

    public void setRend(Object rend) {
        this.rend = rend;
    }

    public Integer getRnum() {
        return rnum;
    }

    public void setRnum(Integer rnum) {
        this.rnum = rnum;
    }

    public String getRinfo() {
        return rinfo;
    }

    public void setRinfo(String rinfo) {
        this.rinfo = rinfo;
    }

    public Integer getRstate() {
        return rstate;
    }

    public void setRstate(Integer rstate) {
        this.rstate = rstate;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAprovince() {
        return aprovince;
    }

    public void setAprovince(String aprovince) {
        this.aprovince = aprovince;
    }

    public String getAcity() {
        return acity;
    }

    public void setAcity(String acity) {
        this.acity = acity;
    }

    public String getIaddress() {
        return iaddress;
    }

    public void setIaddress(String iaddress) {
        this.iaddress = iaddress;
    }

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    public Object getItime() {
        return itime;
    }

    public void setItime(Object itime) {
        this.itime = itime;
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }
}
