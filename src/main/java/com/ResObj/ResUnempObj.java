package com.ResObj;


import java.util.Date;

/**
 * Created by LENOVO on 2016/10/29.
 */
public class ResUnempObj {

    private Integer ueid;
    private Integer sid;
    private Integer jid;
    private Integer did;
    private Integer uesalary;
    private Date uetime;
    private String ueschool;
    private String uemajor;
    private Integer uesuccess;
    private Integer uestate;
    private String jname;
    private String sname;
    private Boolean ssex;
    private String spro;
    private Integer sgrade;
    private Integer sclass;
    private String dname;

    public ResUnempObj() {
    }

    public ResUnempObj(Integer ueid, Integer sid, Integer jid, Integer did, Integer uesalary, Date uetime, String ueschool, String uemajor, Integer uesuccess, Integer uestate, String jname, String sname, Boolean ssex, String spro, Integer sgrade, Integer sclass, String dname) {
        this.ueid = ueid;
        this.sid = sid;
        this.jid = jid;
        this.did = did;
        this.uesalary = uesalary;
        this.uetime = uetime;
        this.ueschool = ueschool;
        this.uemajor = uemajor;
        this.uesuccess = uesuccess;
        this.uestate = uestate;
        this.jname = jname;
        this.sname = sname;
        this.ssex = ssex;
        this.spro = spro;
        this.sgrade = sgrade;
        this.sclass = sclass;
        this.dname = dname;
    }

    public Integer getUeid() {
        return ueid;
    }

    public void setUeid(Integer ueid) {
        this.ueid = ueid;
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

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
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

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
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

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}