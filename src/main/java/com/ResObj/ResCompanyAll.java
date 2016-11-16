package com.ResObj;

import java.util.Date;

/**
 * Created by TianYu on 2016/11/16.
 */
public class ResCompanyAll {
    private Integer cid;
    private String cname;
    private String chr;
    private String cphone;
    private String cemail;
    private String cinfo;
    private String cmark;
    private String caddress;
    private Date ctime;
    private String jname;

    public ResCompanyAll() {
    }

    public ResCompanyAll(Integer cid, String cname, String chr, String cphone, String cemail, String cinfo, String cmark, String caddress, Date ctime, String jname) {
        this.cid = cid;
        this.cname = cname;
        this.chr = chr;
        this.cphone = cphone;
        this.cemail = cemail;
        this.cinfo = cinfo;
        this.cmark = cmark;
        this.caddress = caddress;
        this.ctime = ctime;
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

    public String getCinfo() {
        return cinfo;
    }

    public void setCinfo(String cinfo) {
        this.cinfo = cinfo;
    }

    public String getCmark() {
        return cmark;
    }

    public void setCmark(String cmark) {
        this.cmark = cmark;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }
}
