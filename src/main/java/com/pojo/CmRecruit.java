package com.pojo;

import java.util.Collection;
import java.util.Date;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmRecruit {
    private Integer rid;
    private Boolean rsex;
    private Integer rsalary;
    private Date rstart;
    private Date rend;
    private Integer rnum;
    private String rinfo;
    private Integer rstate;
    private Collection<CmInter> cmIntersByRid;
    private CmJob cmJobByJid;
    private CmCompany cmCompanyByCid;
    private CmArea cmAreaByAid;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmRecruit cmRecruit = (CmRecruit) o;

        if (rid != null ? !rid.equals(cmRecruit.rid) : cmRecruit.rid != null) return false;
        if (rsex != null ? !rsex.equals(cmRecruit.rsex) : cmRecruit.rsex != null) return false;
        if (rsalary != null ? !rsalary.equals(cmRecruit.rsalary) : cmRecruit.rsalary != null) return false;
        if (rstart != null ? !rstart.equals(cmRecruit.rstart) : cmRecruit.rstart != null) return false;
        if (rend != null ? !rend.equals(cmRecruit.rend) : cmRecruit.rend != null) return false;
        if (rnum != null ? !rnum.equals(cmRecruit.rnum) : cmRecruit.rnum != null) return false;
        if (rinfo != null ? !rinfo.equals(cmRecruit.rinfo) : cmRecruit.rinfo != null) return false;
        if (rstate != null ? !rstate.equals(cmRecruit.rstate) : cmRecruit.rstate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        result = 31 * result + (rsex != null ? rsex.hashCode() : 0);
        result = 31 * result + (rsalary != null ? rsalary.hashCode() : 0);
        result = 31 * result + (rstart != null ? rstart.hashCode() : 0);
        result = 31 * result + (rend != null ? rend.hashCode() : 0);
        result = 31 * result + (rnum != null ? rnum.hashCode() : 0);
        result = 31 * result + (rinfo != null ? rinfo.hashCode() : 0);
        result = 31 * result + (rstate != null ? rstate.hashCode() : 0);
        return result;
    }

    public Collection<CmInter> getCmIntersByRid() {
        return cmIntersByRid;
    }

    public void setCmIntersByRid(Collection<CmInter> cmIntersByRid) {
        this.cmIntersByRid = cmIntersByRid;
    }

    public CmJob getCmJobByJid() {
        return cmJobByJid;
    }

    public void setCmJobByJid(CmJob cmJobByJid) {
        this.cmJobByJid = cmJobByJid;
    }

    public CmCompany getCmCompanyByCid() {
        return cmCompanyByCid;
    }

    public void setCmCompanyByCid(CmCompany cmCompanyByCid) {
        this.cmCompanyByCid = cmCompanyByCid;
    }

    public CmArea getCmAreaByAid() {
        return cmAreaByAid;
    }

    public void setCmAreaByAid(CmArea cmAreaByAid) {
        this.cmAreaByAid = cmAreaByAid;
    }
}
