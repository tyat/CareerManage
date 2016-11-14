package com.ResObj;

import java.util.Date;

/**
 * Created by w on 2016/11/1.
 */
public class InterResObj {
    private Integer iid;
    private String iaddress;
    private String itype;
    private Date itime;
    private Integer isuccess;

    private int rid;

    private int cid;
    private String cname;

    private int jid;
    private String jname;

    private int aid;
    private String aprovince;
    private String acity;

    private Integer sid;
    private String sname;
    private Boolean ssex;
    private Date sbirth;
    private String spro;
    private Integer sgrade;
    private Integer sclass;
    private String sphone;
    private String sno;

    public InterResObj() {
    }

    public InterResObj(Integer iid, String iaddress, String itype, Date itime, Integer isuccess, int rid, int aid, String aprovince, String acity, Integer sid, String sname, Boolean ssex, Date sbirth, String spro, Integer sgrade, Integer sclass, String sphone) {
        this.iid = iid;
        this.iaddress = iaddress;
        this.itype = itype;
        this.itime = itime;
        this.isuccess = isuccess;
        this.rid = rid;
        this.aid = aid;
        this.aprovince = aprovince;
        this.acity = acity;
        this.sid = sid;
        this.sname = sname;
        this.ssex = ssex;
        this.sbirth = sbirth;
        this.spro = spro;
        this.sgrade = sgrade;
        this.sclass = sclass;
        this.sphone = sphone;
    }

    public InterResObj(Integer iid, String iaddress, String itype, Date itime, Integer isuccess, int rid, int cid, String cname, int jid, String jname, int aid, String aprovince, String acity, Integer sid, String sname, String sno) {
        this.iid = iid;
        this.iaddress = iaddress;
        this.itype = itype;
        this.itime = itime;
        this.isuccess = isuccess;
        this.rid = rid;
        this.cid = cid;
        this.cname = cname;
        this.jid = jid;
        this.jname = jname;
        this.aid = aid;
        this.aprovince = aprovince;
        this.acity = acity;
        this.sid = sid;
        this.sname = sname;
        this.sno = sno;
    }

    /*public static Timestamp stringToTimestamp(String dateStr){
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
*/
    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getIsuccess() {
        return isuccess;
    }

    public void setIsuccess(Integer isuccess) {
        this.isuccess = isuccess;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Boolean getSsex() {
        return ssex;
    }

    public void setSsex(Boolean ssex) {
        this.ssex = ssex;
    }

    public Date getSbirth() {
        return sbirth;
    }

    public void setSbirth(Date sbirth) {
        this.sbirth = sbirth;
    }

    public String getSpro() {
        return spro;
    }

    public void setSpro(String spro) {
        this.spro = spro;
    }

    public Integer getSgrade() {
        return sgrade;
    }

    public void setSgrade(Integer sgrade) {
        this.sgrade = sgrade;
    }

    public Integer getSclass() {
        return sclass;
    }

    public void setSclass(Integer sclass) {
        this.sclass = sclass;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
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

    public Date getItime() {
        return itime;
    }

    public void setItime(Date itime) {
        this.itime = itime;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
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

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}
