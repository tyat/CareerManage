package com.ResObj;

import java.util.Date;

/**
 * Created by LENOVO on 2016/10/26.
 */
public class ResCompanyObj {
    private Integer cid;
    private String cname;
    private String chr;
    private String cphone;
    private Integer cstate;
    private Integer rid;
    private Integer jid;
    private String jname;
    private Integer iid;
    private Date itime;
    private Integer isuccess;

    public ResCompanyObj() {
    }

    public ResCompanyObj(Integer cid, String cname, String chr, String cphone, Integer cstate, Integer rid, Integer jid, String jname) {
        this.cid = cid;
        this.cname = cname;
        this.chr = chr;
        this.cphone = cphone;
        this.cstate = cstate;
        this.rid = rid;
        this.jid = jid;
        this.jname = jname;
    }

    public ResCompanyObj(Integer cid, String cname, String chr, String cphone, Integer cstate, Integer rid, Integer iid, Date itime, Integer isuccess) {
        this.cid = cid;
        this.cname = cname;
        this.chr = chr;
        this.cphone = cphone;
        this.cstate = cstate;
        this.rid = rid;
        this.iid = iid;
        this.itime = itime;
        this.isuccess = isuccess;
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

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
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

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Date getItime() {
        return itime;
    }

    public void setItime(Date itime) {
        this.itime = itime;
    }

    public Integer getIsuccess() {
        return isuccess;
    }

    public void setIsuccess(Integer isuccess) {
        this.isuccess = isuccess;
    }
}
