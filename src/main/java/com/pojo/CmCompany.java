package com.pojo;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_company", schema = "career", catalog = "")
public class CmCompany {
    private int cid;
    private int aid;
    private String cname;
    private String chr;
    private String cphone;
    private String cemail;
    private String cinfo;
    private String cmark;
    private String caddress;
    private Timestamp ctime;
    private int cstate;
    private CmArea cmAreaByAid;
    private Collection<CmRecruit> cmRecruitsByCid;

    @Id
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "aid")
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "cname", nullable = false, length = 100)
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Basic
    @Column(name = "chr", nullable = false, length = 50)
    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    @Basic
    @Column(name = "cphone", nullable = false, length = 50)
    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    @Basic
    @Column(name = "cemail", nullable = false, length = 50)
    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    @Basic
    @Column(name = "cinfo", nullable = true, length = 255)
    public String getCinfo() {
        return cinfo;
    }

    public void setCinfo(String cinfo) {
        this.cinfo = cinfo;
    }

    @Basic
    @Column(name = "cmark", nullable = true, length = 255)
    public String getCmark() {
        return cmark;
    }

    public void setCmark(String cmark) {
        this.cmark = cmark;
    }

    @Basic
    @Column(name = "caddress", nullable = false, length = 50)
    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    @Basic
    @Column(name = "ctime", nullable = false)
    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    @Basic
    @Column(name = "cstate", nullable = false)
    public int getCstate() {
        return cstate;
    }

    public void setCstate(int cstate) {
        this.cstate = cstate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmCompany cmCompany = (CmCompany) o;

        if (cid != cmCompany.cid) return false;
        if (aid != cmCompany.aid) return false;
        if (cstate != cmCompany.cstate) return false;
        if (cname != null ? !cname.equals(cmCompany.cname) : cmCompany.cname != null) return false;
        if (chr != null ? !chr.equals(cmCompany.chr) : cmCompany.chr != null) return false;
        if (cphone != null ? !cphone.equals(cmCompany.cphone) : cmCompany.cphone != null) return false;
        if (cemail != null ? !cemail.equals(cmCompany.cemail) : cmCompany.cemail != null) return false;
        if (cinfo != null ? !cinfo.equals(cmCompany.cinfo) : cmCompany.cinfo != null) return false;
        if (cmark != null ? !cmark.equals(cmCompany.cmark) : cmCompany.cmark != null) return false;
        if (caddress != null ? !caddress.equals(cmCompany.caddress) : cmCompany.caddress != null) return false;
        if (ctime != null ? !ctime.equals(cmCompany.ctime) : cmCompany.ctime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + aid;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        result = 31 * result + (chr != null ? chr.hashCode() : 0);
        result = 31 * result + (cphone != null ? cphone.hashCode() : 0);
        result = 31 * result + (cemail != null ? cemail.hashCode() : 0);
        result = 31 * result + (cinfo != null ? cinfo.hashCode() : 0);
        result = 31 * result + (cmark != null ? cmark.hashCode() : 0);
        result = 31 * result + (caddress != null ? caddress.hashCode() : 0);
        result = 31 * result + (ctime != null ? ctime.hashCode() : 0);
        result = 31 * result + cstate;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "aid", referencedColumnName = "aid", nullable = false)
    public CmArea getCmAreaByAid() {
        return cmAreaByAid;
    }

    public void setCmAreaByAid(CmArea cmAreaByAid) {
        this.cmAreaByAid = cmAreaByAid;
    }

    @OneToMany(mappedBy = "cmCompanyByCid")
    public Collection<CmRecruit> getCmRecruitsByCid() {
        return cmRecruitsByCid;
    }

    public void setCmRecruitsByCid(Collection<CmRecruit> cmRecruitsByCid) {
        this.cmRecruitsByCid = cmRecruitsByCid;
    }
}
