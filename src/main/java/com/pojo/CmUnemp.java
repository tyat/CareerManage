package com.pojo;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_unemp", schema = "career", catalog = "")
public class CmUnemp {
    private int ueid;
    private Integer uesalary;
    private Date uetime;
    private String ueschool;
    private String uemajor;
    private Integer uesuccess;
    private int uestate;
    private CmDirection cmDirectionByDid;
    private CmJob cmJobByJid;
    private CmStudent cmStudentBySid;

    @Id
    @Column(name = "ueid")
    public int getUeid() {
        return ueid;
    }

    public void setUeid(int ueid) {
        this.ueid = ueid;
    }

    @Basic
    @Column(name = "uesalary", nullable = true)
    public Integer getUesalary() {
        return uesalary;
    }

    public void setUesalary(Integer uesalary) {
        this.uesalary = uesalary;
    }

    @Basic
    @Column(name = "uetime", nullable = true)
    public Date getUetime() {
        return uetime;
    }

    public void setUetime(Date uetime) {
        this.uetime = uetime;
    }

    @Basic
    @Column(name = "ueschool", nullable = true, length = 50)
    public String getUeschool() {
        return ueschool;
    }

    public void setUeschool(String ueschool) {
        this.ueschool = ueschool;
    }

    @Basic
    @Column(name = "uemajor", nullable = true, length = 50)
    public String getUemajor() {
        return uemajor;
    }

    public void setUemajor(String uemajor) {
        this.uemajor = uemajor;
    }

    @Basic
    @Column(name = "uesuccess", nullable = true)
    public Integer getUesuccess() {
        return uesuccess;
    }

    public void setUesuccess(Integer uesuccess) {
        this.uesuccess = uesuccess;
    }

    @Basic
    @Column(name = "uestate", nullable = false)
    public int getUestate() {
        return uestate;
    }

    public void setUestate(int uestate) {
        this.uestate = uestate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmUnemp cmUnemp = (CmUnemp) o;

        if (ueid != cmUnemp.ueid) return false;
        if (uestate != cmUnemp.uestate) return false;
        if (uesalary != null ? !uesalary.equals(cmUnemp.uesalary) : cmUnemp.uesalary != null) return false;
        if (uetime != null ? !uetime.equals(cmUnemp.uetime) : cmUnemp.uetime != null) return false;
        if (ueschool != null ? !ueschool.equals(cmUnemp.ueschool) : cmUnemp.ueschool != null) return false;
        if (uemajor != null ? !uemajor.equals(cmUnemp.uemajor) : cmUnemp.uemajor != null) return false;
        if (uesuccess != null ? !uesuccess.equals(cmUnemp.uesuccess) : cmUnemp.uesuccess != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ueid;
        result = 31 * result + (uesalary != null ? uesalary.hashCode() : 0);
        result = 31 * result + (uetime != null ? uetime.hashCode() : 0);
        result = 31 * result + (ueschool != null ? ueschool.hashCode() : 0);
        result = 31 * result + (uemajor != null ? uemajor.hashCode() : 0);
        result = 31 * result + (uesuccess != null ? uesuccess.hashCode() : 0);
        result = 31 * result + uestate;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "did", referencedColumnName = "did", nullable = false)
    public CmDirection getCmDirectionByDid() {
        return cmDirectionByDid;
    }

    public void setCmDirectionByDid(CmDirection cmDirectionByDid) {
        this.cmDirectionByDid = cmDirectionByDid;
    }

    @ManyToOne
    @JoinColumn(name = "jid", referencedColumnName = "jid")
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
}
