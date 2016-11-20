package com.ResObj;

import java.sql.Timestamp;
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
    private Date ctime;
    private Integer rid;
    private Integer sid;
    private String sno;
    private String sname;
    private Boolean ssex;
    private Date sbirth;
    private String spro;
    private Integer sgrade;
    private Integer sclass;
    private String sphone;
    private String semail;
    private String scode;
    private Integer smark;
    private String sassess;
    private Integer sstate;
    private String sdetail;
    private Integer jid;
    private String jname;
    private Integer iid;
    private Date itime;
    private Integer isuccess;
    private Date etime;

    public ResCompanyObj() {
    }

    public ResCompanyObj(Integer cid, String cname, Integer rid, Integer sid, String sno, String sname, Boolean ssex, String spro, Integer sgrade, Integer sclass, String sphone, String semail, String scode, Integer smark, String sassess, Integer sstate, String sdetail, Integer jid, String jname, Integer iid, Integer isuccess, Date etime) {
        this.cid = cid;
        this.cname = cname;
        this.rid = rid;
        this.sid = sid;
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.spro = spro;
        this.sgrade = sgrade;
        this.sclass = sclass;
        this.sphone = sphone;
        this.semail = semail;
        this.scode = scode;
        this.smark = smark;
        this.sassess = sassess;
        this.sstate = sstate;
        this.sdetail = sdetail;
        this.jid = jid;
        this.jname = jname;
        this.iid = iid;
        this.isuccess = isuccess;
        this.etime = etime;
    }

    public ResCompanyObj(Integer cid, String cname, Date etime, String sname, Boolean ssex, String spro, Integer sgrade, Integer sclass, String sphone, Integer jid, String jname) {
        this.cid = cid;
        this.cname = cname;
        this.etime = etime;
        this.sname = sname;
        this.ssex = ssex;
        this.spro = spro;
        this.sgrade = sgrade;
        this.sclass = sclass;
        this.sphone = sphone;
        this.jid = jid;
        this.jname = jname;
    }

    public ResCompanyObj(Integer cid, String cname, String chr, String cphone, Integer cstate, Integer rid) {
        this.cid = cid;
        this.cname = cname;
        this.chr = chr;
        this.cphone = cphone;
        this.cstate = cstate;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
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

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
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

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }
}
