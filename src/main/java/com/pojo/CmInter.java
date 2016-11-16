package com.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmInter {
    private Integer iid;
    private String iaddress;
    private String itype;
    private Timestamp itime;
    private Integer isuccess;
    private Integer istate;
    private CmStudent cmStudentBySid;
    private CmRecruit cmRecruitByRid;
    private CmArea cmAreaByAid;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getIaddress() {
        return iaddress;
    }

    public void setIaddress(String iaddress) {
        this.iaddress = iaddress;
    }

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    public Timestamp getItime() {
        return itime;
    }

    public void setItime(Timestamp itime) {
        this.itime = itime;
    }

    public Integer getIsuccess() {
        return isuccess;
    }

    public void setIsuccess(Integer isuccess) {
        this.isuccess = isuccess;
    }

    public Integer getIstate() {
        return istate;
    }

    public void setIstate(Integer istate) {
        this.istate = istate;
    }
    public CmInter() {
    }

    public CmInter(CmArea cmAreaByAid, CmRecruit cmRecruitByRid, CmStudent cmStudentBySid, String iaddress, Integer isuccess, Timestamp itime, String itype) {
        this.cmAreaByAid = cmAreaByAid;
        this.cmRecruitByRid = cmRecruitByRid;
        this.cmStudentBySid = cmStudentBySid;
        this.iaddress = iaddress;
        this.isuccess = isuccess;
        this.itime = itime;
        this.itype = itype;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmInter cmInter = (CmInter) o;

        if (iid != null ? !iid.equals(cmInter.iid) : cmInter.iid != null) return false;
        if (iaddress != null ? !iaddress.equals(cmInter.iaddress) : cmInter.iaddress != null) return false;
        if (itype != null ? !itype.equals(cmInter.itype) : cmInter.itype != null) return false;
        if (itime != null ? !itime.equals(cmInter.itime) : cmInter.itime != null) return false;
        if (isuccess != null ? !isuccess.equals(cmInter.isuccess) : cmInter.isuccess != null) return false;
        if (istate != null ? !istate.equals(cmInter.istate) : cmInter.istate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iid != null ? iid.hashCode() : 0;
        result = 31 * result + (iaddress != null ? iaddress.hashCode() : 0);
        result = 31 * result + (itype != null ? itype.hashCode() : 0);
        result = 31 * result + (itime != null ? itime.hashCode() : 0);
        result = 31 * result + (isuccess != null ? isuccess.hashCode() : 0);
        result = 31 * result + (istate != null ? istate.hashCode() : 0);
        return result;
    }

    public CmStudent getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(CmStudent cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }

    public CmRecruit getCmRecruitByRid() {
        return cmRecruitByRid;
    }

    public void setCmRecruitByRid(CmRecruit cmRecruitByRid) {
        this.cmRecruitByRid = cmRecruitByRid;
    }

    public CmArea getCmAreaByAid() {
        return cmAreaByAid;
    }

    public void setCmAreaByAid(CmArea cmAreaByAid) {
        this.cmAreaByAid = cmAreaByAid;
    }
}
