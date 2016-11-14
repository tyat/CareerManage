package com.ResObj;

import java.util.Date;
/**
 * Created by w on 2016/10/31.
 */
public class RecruitResObj {
    private Integer rid;
    private Boolean rsex;
    private Integer rsalary;
    private Date rstart;
    private Date rend;
    private Integer rnum;
    private String rinfo;
    private Integer rstate;

    private Integer aid;
    private String aprovince;
    private String acity;

    private Integer jid;
    private String jname;

    private Integer cid;
    private String cname;
    private String chr;
    private String cphone;
    private String cemail;

    public RecruitResObj() {
    }

    public RecruitResObj(Integer rid, Boolean rsex, Integer rsalary, Date rstart, Date rend, Integer rnum, String rinfo, Integer rstate, Integer aid, String aprovince, String acity, Integer jid, String jname, Integer cid, String cname, String chr, String cphone, String cemail) {
        this.rid = rid;
        this.rsex = rsex;
        this.rsalary = rsalary;
        this.rstart = rstart;
        this.rend = rend;
        this.rnum = rnum;
        this.rinfo = rinfo;
        this.rstate = rstate;
        this.aid = aid;
        this.aprovince = aprovince;
        this.acity = acity;
        this.jid = jid;
        this.jname = jname;
        this.cid = cid;
        this.cname = cname;
        this.chr = chr;
        this.cphone = cphone;
        this.cemail = cemail;
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

    public Date getRstart() {
        return rstart;
    }

    public void setRstart(Date rstart) {
        this.rstart = rstart;
    }

    public Date getRend() {
        return rend;
    }

    public void setRend(Date rend) {
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
