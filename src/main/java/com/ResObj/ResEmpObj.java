package com.ResObj;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by LENOVO on 2016/10/29.
 */
public class ResEmpObj {

    private Integer eid;
    private Integer uid;
    private Integer sid;
    private Integer jid;
    private Date etime;
    private Integer esalary;
    private String einfo;
    private Integer estate;
    private Boolean ewq;
    private Date eleave;
    private String ereason;
    private String jname;
    private Boolean jtype;
    private String uname;
    private String sname;
    private Boolean ssex;
    private String spro;
    private Integer sgrade;
    private Integer sclass;
    private Integer rid;
    private Integer cid;
    private String cname;
    private Integer iid;
    private Integer isuccess;

    public ResEmpObj() {
    }

    public ResEmpObj(Integer eid, Integer uid, Integer sid, Integer jid, Date etime, Integer esalary, String einfo, Integer estate, Boolean ewq, Date eleave, String ereason, String jname, Boolean jtype, String uname, String sname, Boolean ssex, String spro, Integer sgrade, Integer sclass, Integer rid, Integer cid, String cname, Integer iid, Integer isuccess) {
        this.eid = eid;
        this.uid = uid;
        this.sid = sid;
        this.jid = jid;
        this.etime = etime;
        this.esalary = esalary;
        this.einfo = einfo;
        this.estate = estate;
        this.ewq = ewq;
        this.eleave = eleave;
        this.ereason = ereason;
        this.jname = jname;
        this.jtype = jtype;
        this.uname = uname;
        this.sname = sname;
        this.ssex = ssex;
        this.spro = spro;
        this.sgrade = sgrade;
        this.sclass = sclass;
        this.rid = rid;
        this.cid = cid;
        this.cname = cname;
        this.iid = iid;
        this.isuccess = isuccess;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Integer getEsalary() {
        return esalary;
    }

    public void setEsalary(Integer esalary) {
        this.esalary = esalary;
    }

    public String getEinfo() {
        return einfo;
    }

    public void setEinfo(String einfo) {
        this.einfo = einfo;
    }

    public Integer getEstate() {
        return estate;
    }

    public void setEstate(Integer estate) {
        this.estate = estate;
    }

    public Boolean getEwq() {
        return ewq;
    }

    public void setEwq(Boolean ewq) {
        this.ewq = ewq;
    }

    public Date getEleave() {
        return eleave;
    }

    public void setEleave(Date eleave) {
        this.eleave = eleave;
    }

    public String getEreason() {
        return ereason;
    }

    public void setEreason(String ereason) {
        this.ereason = ereason;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public Boolean getJtype() {
        return jtype;
    }

    public void setJtype(Boolean jtype) {
        this.jtype = jtype;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
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
}

