package com.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_inter", schema = "career", catalog = "")
public class CmInter {
    private int iid;
    private String iaddress;
    private String itype;
    private Timestamp itime;
    private int isuccess;
    private int istate;
    private CmArea cmAreaByAid;
    private CmRecruit cmRecruitByRid;
    private CmStudent cmStudentBySid;

    @Id
    @Column(name = "iid")
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    @Basic
    @Column(name = "iaddress", nullable = false, length = 50)
    public String getIaddress() {
        return iaddress;
    }

    public void setIaddress(String iaddress) {
        this.iaddress = iaddress;
    }

    @Basic
    @Column(name = "itype", nullable = false, length = 50)
    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    @Basic
    @Column(name = "itime", nullable = false)
    public Timestamp getItime() {
        return itime;
    }

    public void setItime(Timestamp itime) {
        this.itime = itime;
    }

    @Basic
    @Column(name = "isuccess", nullable = false)
    public int getIsuccess() {
        return isuccess;
    }

    public void setIsuccess(int isuccess) {
        this.isuccess = isuccess;
    }

    @Basic
    @Column(name = "istate", nullable = false)
    public int getIstate() {
        return istate;
    }

    public void setIstate(int istate) {
        this.istate = istate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmInter cmInter = (CmInter) o;

        if (iid != cmInter.iid) return false;
        if (isuccess != cmInter.isuccess) return false;
        if (istate != cmInter.istate) return false;
        if (iaddress != null ? !iaddress.equals(cmInter.iaddress) : cmInter.iaddress != null) return false;
        if (itype != null ? !itype.equals(cmInter.itype) : cmInter.itype != null) return false;
        if (itime != null ? !itime.equals(cmInter.itime) : cmInter.itime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iid;
        result = 31 * result + (iaddress != null ? iaddress.hashCode() : 0);
        result = 31 * result + (itype != null ? itype.hashCode() : 0);
        result = 31 * result + (itime != null ? itime.hashCode() : 0);
        result = 31 * result + isuccess;
        result = 31 * result + istate;
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

    @ManyToOne
    @JoinColumn(name = "rid", referencedColumnName = "rid", nullable = false)
    public CmRecruit getCmRecruitByRid() {
        return cmRecruitByRid;
    }

    public void setCmRecruitByRid(CmRecruit cmRecruitByRid) {
        this.cmRecruitByRid = cmRecruitByRid;
    }

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false)
    public CmStudent getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(CmStudent cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }
}
