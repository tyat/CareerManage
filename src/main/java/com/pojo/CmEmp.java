package com.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_emp", schema = "career", catalog = "")
public class CmEmp {
    private int eid;
    private Serializable etime;
    private int esalary;
    private String einfo;
    private int estate;
    private Boolean ewq;
    private Date eleave;
    private String ereason;
    private CmJob cmJobByJid;
    private CmStudent cmStudentBySid;
    private CmUser cmUserByUid;

    @Id
    @Column(name = "eid")
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "etime", nullable = false)
    public Serializable getEtime() {
        return etime;
    }

    public void setEtime(Serializable etime) {
        this.etime = etime;
    }

    @Basic
    @Column(name = "esalary", nullable = false)
    public int getEsalary() {
        return esalary;
    }

    public void setEsalary(int esalary) {
        this.esalary = esalary;
    }

    @Basic
    @Column(name = "einfo", nullable = true, length = 255)
    public String getEinfo() {
        return einfo;
    }

    public void setEinfo(String einfo) {
        this.einfo = einfo;
    }

    @Basic
    @Column(name = "estate", nullable = false)
    public int getEstate() {
        return estate;
    }

    public void setEstate(int estate) {
        this.estate = estate;
    }

    @Basic
    @Column(name = "ewq", nullable = true)
    public Boolean getEwq() {
        return ewq;
    }

    public void setEwq(Boolean ewq) {
        this.ewq = ewq;
    }

    @Basic
    @Column(name = "eleave", nullable = true)
    public Date getEleave() {
        return eleave;
    }

    public void setEleave(Date eleave) {
        this.eleave = eleave;
    }

    @Basic
    @Column(name = "ereason", nullable = true, length = 255)
    public String getEreason() {
        return ereason;
    }

    public void setEreason(String ereason) {
        this.ereason = ereason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmEmp cmEmp = (CmEmp) o;

        if (eid != cmEmp.eid) return false;
        if (esalary != cmEmp.esalary) return false;
        if (estate != cmEmp.estate) return false;
        if (etime != null ? !etime.equals(cmEmp.etime) : cmEmp.etime != null) return false;
        if (einfo != null ? !einfo.equals(cmEmp.einfo) : cmEmp.einfo != null) return false;
        if (ewq != null ? !ewq.equals(cmEmp.ewq) : cmEmp.ewq != null) return false;
        if (eleave != null ? !eleave.equals(cmEmp.eleave) : cmEmp.eleave != null) return false;
        if (ereason != null ? !ereason.equals(cmEmp.ereason) : cmEmp.ereason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (etime != null ? etime.hashCode() : 0);
        result = 31 * result + esalary;
        result = 31 * result + (einfo != null ? einfo.hashCode() : 0);
        result = 31 * result + estate;
        result = 31 * result + (ewq != null ? ewq.hashCode() : 0);
        result = 31 * result + (eleave != null ? eleave.hashCode() : 0);
        result = 31 * result + (ereason != null ? ereason.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "jid", referencedColumnName = "jid", nullable = false)
    public CmJob getCmJobByJid() {
        return cmJobByJid;
    }

    public void setCmJobByJid(CmJob cmJobByJid) {
        this.cmJobByJid = cmJobByJid;
    }

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false)
    public CmStudent getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(CmStudent cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    public CmUser getCmUserByUid() {
        return cmUserByUid;
    }

    public void setCmUserByUid(CmUser cmUserByUid) {
        this.cmUserByUid = cmUserByUid;
    }
}
