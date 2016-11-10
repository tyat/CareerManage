package com.ResObj;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by w on 2016/10/31.
 */
@XmlRootElement
public class UnempResObj {
    private Integer ueid;
    private Integer uesalary;
    private Date uetime;
    private String ueschool;
    private String uemajor;
    private Integer uesuccess;
    private Integer uestate;

    private Integer jid;
    private String jname;

    private Integer sid;
    private String sno;
    private String sname;
    private Boolean ssex;
    private String spro;
    private Integer sgrade;
    private Integer sclass;
    private Integer smark;
    private String sassess;
    private Integer sstate;
    private String sdetail;

    private int did;
    private String dname;
    private Integer dstate;

    public UnempResObj() {
    }

    public UnempResObj(Integer ueid, Integer uesalary, Date uetime, String ueschool, String uemajor, Integer uesuccess, Integer uestate, Integer jid, String jname, Integer sid, String sno, String sname, Boolean ssex, String spro, Integer sgrade, Integer sclass, Integer smark, String sassess, Integer sstate, String sdetail, int did, String dname, Integer dstate) {
        this.ueid = ueid;
        this.uesalary = uesalary;
        this.uetime = uetime;
        this.ueschool = ueschool;
        this.uemajor = uemajor;
        this.uesuccess = uesuccess;
        this.uestate = uestate;
        this.jid = jid;
        this.jname = jname;
        this.sid = sid;
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.spro = spro;
        this.sgrade = sgrade;
        this.sclass = sclass;
        this.smark = smark;
        this.sassess = sassess;
        this.sstate = sstate;
        this.sdetail = sdetail;
        this.did = did;
        this.dname = dname;
        this.dstate = dstate;
    }

    public Integer getUeid() {
        return ueid;
    }

    public void setUeid(Integer ueid) {
        this.ueid = ueid;
    }

    public Integer getUesalary() {
        return uesalary;
    }

    public void setUesalary(Integer uesalary) {
        this.uesalary = uesalary;
    }

    public Date getUetime() {
        return uetime;
    }

    public void setUetime(Date uetime) {
        this.uetime = uetime;
    }

    public String getUeschool() {
        return ueschool;
    }

    public void setUeschool(String ueschool) {
        this.ueschool = ueschool;
    }

    public String getUemajor() {
        return uemajor;
    }

    public void setUemajor(String uemajor) {
        this.uemajor = uemajor;
    }

    public Integer getUesuccess() {
        return uesuccess;
    }

    public void setUesuccess(Integer uesuccess) {
        this.uesuccess = uesuccess;
    }

    public Integer getUestate() {
        return uestate;
    }

    public void setUestate(Integer uestate) {
        this.uestate = uestate;
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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public Integer getSmark() {
        return smark;
    }

    public void setSmark(Integer smark) {
        this.smark = smark;
    }

    public String getSassess() {
        return sassess;
    }

    public void setSassess(String sassess) {
        this.sassess = sassess;
    }

    public Integer getSstate() {
        return sstate;
    }

    public void setSstate(Integer sstate) {
        this.sstate = sstate;
    }

    public String getSdetail() {
        return sdetail;
    }

    public void setSdetail(String sdetail) {
        this.sdetail = sdetail;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getDstate() {
        return dstate;
    }

    public void setDstate(Integer dstate) {
        this.dstate = dstate;
    }
}
