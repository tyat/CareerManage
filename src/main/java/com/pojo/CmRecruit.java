package com.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_recruit", schema = "career", catalog = "")
public class CmRecruit {
    private int rid;
    private Boolean rsex;
    private int rsalary;
    private Timestamp rstart;
    private Timestamp rend;
    private int rnum;
    private String rinfo;
    private int rstate;
    private Collection<CmInter> cmIntersByRid;
    private CmArea cmAreaByAid;
    private CmCompany cmCompanyByCid;
    private CmJob cmJobByJid;

    @Id
    @Column(name = "rid")
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "rsex", nullable = true)
    public Boolean getRsex() {
        return rsex;
    }

    public void setRsex(Boolean rsex) {
        this.rsex = rsex;
    }

    @Basic
    @Column(name = "rsalary", nullable = false)
    public int getRsalary() {
        return rsalary;
    }

    public void setRsalary(int rsalary) {
        this.rsalary = rsalary;
    }

    @Basic
    @Column(name = "rstart", nullable = false)
    public Timestamp getRstart() {
        return rstart;
    }

    public void setRstart(Timestamp rstart) {
        this.rstart = rstart;
    }

    @Basic
    @Column(name = "rend", nullable = false)
    public Timestamp getRend() {
        return rend;
    }

    public void setRend(Timestamp rend) {
        this.rend = rend;
    }

    @Basic
    @Column(name = "rnum", nullable = false)
    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    @Basic
    @Column(name = "rinfo", nullable = false, length = 255)
    public String getRinfo() {
        return rinfo;
    }

    public void setRinfo(String rinfo) {
        this.rinfo = rinfo;
    }

    @Basic
    @Column(name = "rstate", nullable = false)
    public int getRstate() {
        return rstate;
    }

    public void setRstate(int rstate) {
        this.rstate = rstate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmRecruit cmRecruit = (CmRecruit) o;

        if (rid != cmRecruit.rid) return false;
        if (rsalary != cmRecruit.rsalary) return false;
        if (rnum != cmRecruit.rnum) return false;
        if (rstate != cmRecruit.rstate) return false;
        if (rsex != null ? !rsex.equals(cmRecruit.rsex) : cmRecruit.rsex != null) return false;
        if (rstart != null ? !rstart.equals(cmRecruit.rstart) : cmRecruit.rstart != null) return false;
        if (rend != null ? !rend.equals(cmRecruit.rend) : cmRecruit.rend != null) return false;
        if (rinfo != null ? !rinfo.equals(cmRecruit.rinfo) : cmRecruit.rinfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + (rsex != null ? rsex.hashCode() : 0);
        result = 31 * result + rsalary;
        result = 31 * result + (rstart != null ? rstart.hashCode() : 0);
        result = 31 * result + (rend != null ? rend.hashCode() : 0);
        result = 31 * result + rnum;
        result = 31 * result + (rinfo != null ? rinfo.hashCode() : 0);
        result = 31 * result + rstate;
        return result;
    }

    @OneToMany(mappedBy = "cmRecruitByRid")
    public Collection<CmInter> getCmIntersByRid() {
        return cmIntersByRid;
    }

    public void setCmIntersByRid(Collection<CmInter> cmIntersByRid) {
        this.cmIntersByRid = cmIntersByRid;
    }

    @ManyToOne
    @JoinColumn(name = "aid", referencedColumnName = "aid", nullable = false)
    public CmArea getCmAreaByAid() {
        return cmAreaByAid;
    }

    public void setCmAreaByAid(CmArea cmAreaByAid) {
        this.cmAreaByAid = cmAreaByAid;
    }

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "cid", nullable = false)
    public CmCompany getCmCompanyByCid() {
        return cmCompanyByCid;
    }

    public void setCmCompanyByCid(CmCompany cmCompanyByCid) {
        this.cmCompanyByCid = cmCompanyByCid;
    }

    @ManyToOne
    @JoinColumn(name = "jid", referencedColumnName = "jid", nullable = false)
    public CmJob getCmJobByJid() {
        return cmJobByJid;
    }

    public void setCmJobByJid(CmJob cmJobByJid) {
        this.cmJobByJid = cmJobByJid;
    }
}
