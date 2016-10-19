package com.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_student", schema = "career", catalog = "")
public class CmStudent {
    private int sid;
    private String sno;
    private String sname;
    private Boolean ssex;
    private Date sbirth;
    private String spro;
    private int sgrade;
    private int sclass;
    private String sphone;
    private String semail;
    private String scode;
    private Integer smark;
    private String sassess;
    private int sstate;
    private String sdetail;
    private Collection<CmEmp> cmEmpsBySid;
    private Collection<CmGrade> cmGradesBySid;
    private Collection<CmInter> cmIntersBySid;
    private Collection<CmUnemp> cmUnempsBySid;

    @Id
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "sno", nullable = false, length = 50)
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Basic
    @Column(name = "sname", nullable = false, length = 50)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "ssex", nullable = true)
    public Boolean getSsex() {
        return ssex;
    }

    public void setSsex(Boolean ssex) {
        this.ssex = ssex;
    }

    @Basic
    @Column(name = "sbirth", nullable = false)
    public Date getSbirth() {
        return sbirth;
    }

    public void setSbirth(Date sbirth) {
        this.sbirth = sbirth;
    }

    @Basic
    @Column(name = "spro", nullable = false, length = 50)
    public String getSpro() {
        return spro;
    }

    public void setSpro(String spro) {
        this.spro = spro;
    }

    @Basic
    @Column(name = "sgrade", nullable = false)
    public int getSgrade() {
        return sgrade;
    }

    public void setSgrade(int sgrade) {
        this.sgrade = sgrade;
    }

    @Basic
    @Column(name = "sclass", nullable = false)
    public int getSclass() {
        return sclass;
    }

    public void setSclass(int sclass) {
        this.sclass = sclass;
    }

    @Basic
    @Column(name = "sphone", nullable = false, length = 50)
    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    @Basic
    @Column(name = "semail", nullable = false, length = 50)
    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    @Basic
    @Column(name = "scode", nullable = false, length = 50)
    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    @Basic
    @Column(name = "smark", nullable = true)
    public Integer getSmark() {
        return smark;
    }

    public void setSmark(Integer smark) {
        this.smark = smark;
    }

    @Basic
    @Column(name = "sassess", nullable = true, length = 255)
    public String getSassess() {
        return sassess;
    }

    public void setSassess(String sassess) {
        this.sassess = sassess;
    }

    @Basic
    @Column(name = "sstate", nullable = false)
    public int getSstate() {
        return sstate;
    }

    public void setSstate(int sstate) {
        this.sstate = sstate;
    }

    @Basic
    @Column(name = "sdetail", nullable = true, length = 255)
    public String getSdetail() {
        return sdetail;
    }

    public void setSdetail(String sdetail) {
        this.sdetail = sdetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmStudent cmStudent = (CmStudent) o;

        if (sid != cmStudent.sid) return false;
        if (sgrade != cmStudent.sgrade) return false;
        if (sclass != cmStudent.sclass) return false;
        if (sstate != cmStudent.sstate) return false;
        if (sno != null ? !sno.equals(cmStudent.sno) : cmStudent.sno != null) return false;
        if (sname != null ? !sname.equals(cmStudent.sname) : cmStudent.sname != null) return false;
        if (ssex != null ? !ssex.equals(cmStudent.ssex) : cmStudent.ssex != null) return false;
        if (sbirth != null ? !sbirth.equals(cmStudent.sbirth) : cmStudent.sbirth != null) return false;
        if (spro != null ? !spro.equals(cmStudent.spro) : cmStudent.spro != null) return false;
        if (sphone != null ? !sphone.equals(cmStudent.sphone) : cmStudent.sphone != null) return false;
        if (semail != null ? !semail.equals(cmStudent.semail) : cmStudent.semail != null) return false;
        if (scode != null ? !scode.equals(cmStudent.scode) : cmStudent.scode != null) return false;
        if (smark != null ? !smark.equals(cmStudent.smark) : cmStudent.smark != null) return false;
        if (sassess != null ? !sassess.equals(cmStudent.sassess) : cmStudent.sassess != null) return false;
        if (sdetail != null ? !sdetail.equals(cmStudent.sdetail) : cmStudent.sdetail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (sno != null ? sno.hashCode() : 0);
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (ssex != null ? ssex.hashCode() : 0);
        result = 31 * result + (sbirth != null ? sbirth.hashCode() : 0);
        result = 31 * result + (spro != null ? spro.hashCode() : 0);
        result = 31 * result + sgrade;
        result = 31 * result + sclass;
        result = 31 * result + (sphone != null ? sphone.hashCode() : 0);
        result = 31 * result + (semail != null ? semail.hashCode() : 0);
        result = 31 * result + (scode != null ? scode.hashCode() : 0);
        result = 31 * result + (smark != null ? smark.hashCode() : 0);
        result = 31 * result + (sassess != null ? sassess.hashCode() : 0);
        result = 31 * result + sstate;
        result = 31 * result + (sdetail != null ? sdetail.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cmStudentBySid")
    public Collection<CmEmp> getCmEmpsBySid() {
        return cmEmpsBySid;
    }

    public void setCmEmpsBySid(Collection<CmEmp> cmEmpsBySid) {
        this.cmEmpsBySid = cmEmpsBySid;
    }

    @OneToMany(mappedBy = "cmStudentBySid")
    public Collection<CmGrade> getCmGradesBySid() {
        return cmGradesBySid;
    }

    public void setCmGradesBySid(Collection<CmGrade> cmGradesBySid) {
        this.cmGradesBySid = cmGradesBySid;
    }

    @OneToMany(mappedBy = "cmStudentBySid")
    public Collection<CmInter> getCmIntersBySid() {
        return cmIntersBySid;
    }

    public void setCmIntersBySid(Collection<CmInter> cmIntersBySid) {
        this.cmIntersBySid = cmIntersBySid;
    }

    @OneToMany(mappedBy = "cmStudentBySid")
    public Collection<CmUnemp> getCmUnempsBySid() {
        return cmUnempsBySid;
    }

    public void setCmUnempsBySid(Collection<CmUnemp> cmUnempsBySid) {
        this.cmUnempsBySid = cmUnempsBySid;
    }
}
