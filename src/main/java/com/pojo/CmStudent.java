package com.pojo;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmStudent {
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
    private Collection<CmEmp> cmEmpsBySid;
    private Collection<CmGrade> cmGradesBySid;
    private Collection<CmInter> cmIntersBySid;
    private Collection<CmUnemp> cmUnempsBySid;

    public CmStudent() {

    }

    public CmStudent(Integer sid, String sno, String sname, Boolean ssex, String spro, Integer sgrade, Integer sclass) {
        this.sid = sid;
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.spro = spro;
        this.sgrade = sgrade;
        this.sclass = sclass;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmStudent cmStudent = (CmStudent) o;

        if (sid != null ? !sid.equals(cmStudent.sid) : cmStudent.sid != null) return false;
        if (sno != null ? !sno.equals(cmStudent.sno) : cmStudent.sno != null) return false;
        if (sname != null ? !sname.equals(cmStudent.sname) : cmStudent.sname != null) return false;
        if (ssex != null ? !ssex.equals(cmStudent.ssex) : cmStudent.ssex != null) return false;
        if (sbirth != null ? !sbirth.equals(cmStudent.sbirth) : cmStudent.sbirth != null) return false;
        if (spro != null ? !spro.equals(cmStudent.spro) : cmStudent.spro != null) return false;
        if (sgrade != null ? !sgrade.equals(cmStudent.sgrade) : cmStudent.sgrade != null) return false;
        if (sclass != null ? !sclass.equals(cmStudent.sclass) : cmStudent.sclass != null) return false;
        if (sphone != null ? !sphone.equals(cmStudent.sphone) : cmStudent.sphone != null) return false;
        if (semail != null ? !semail.equals(cmStudent.semail) : cmStudent.semail != null) return false;
        if (scode != null ? !scode.equals(cmStudent.scode) : cmStudent.scode != null) return false;
        if (smark != null ? !smark.equals(cmStudent.smark) : cmStudent.smark != null) return false;
        if (sassess != null ? !sassess.equals(cmStudent.sassess) : cmStudent.sassess != null) return false;
        if (sstate != null ? !sstate.equals(cmStudent.sstate) : cmStudent.sstate != null) return false;
        if (sdetail != null ? !sdetail.equals(cmStudent.sdetail) : cmStudent.sdetail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid != null ? sid.hashCode() : 0;
        result = 31 * result + (sno != null ? sno.hashCode() : 0);
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (ssex != null ? ssex.hashCode() : 0);
        result = 31 * result + (sbirth != null ? sbirth.hashCode() : 0);
        result = 31 * result + (spro != null ? spro.hashCode() : 0);
        result = 31 * result + (sgrade != null ? sgrade.hashCode() : 0);
        result = 31 * result + (sclass != null ? sclass.hashCode() : 0);
        result = 31 * result + (sphone != null ? sphone.hashCode() : 0);
        result = 31 * result + (semail != null ? semail.hashCode() : 0);
        result = 31 * result + (scode != null ? scode.hashCode() : 0);
        result = 31 * result + (smark != null ? smark.hashCode() : 0);
        result = 31 * result + (sassess != null ? sassess.hashCode() : 0);
        result = 31 * result + (sstate != null ? sstate.hashCode() : 0);
        result = 31 * result + (sdetail != null ? sdetail.hashCode() : 0);
        return result;
    }

    public Collection<CmEmp> getCmEmpsBySid() {
        return cmEmpsBySid;
    }

    public void setCmEmpsBySid(Collection<CmEmp> cmEmpsBySid) {
        this.cmEmpsBySid = cmEmpsBySid;
    }

    public Collection<CmGrade> getCmGradesBySid() {
        return cmGradesBySid;
    }

    public void setCmGradesBySid(Collection<CmGrade> cmGradesBySid) {
        this.cmGradesBySid = cmGradesBySid;
    }

    public Collection<CmInter> getCmIntersBySid() {
        return cmIntersBySid;
    }

    public void setCmIntersBySid(Collection<CmInter> cmIntersBySid) {
        this.cmIntersBySid = cmIntersBySid;
    }

    public Collection<CmUnemp> getCmUnempsBySid() {
        return cmUnempsBySid;
    }

    public void setCmUnempsBySid(Collection<CmUnemp> cmUnempsBySid) {
        this.cmUnempsBySid = cmUnempsBySid;
    }
}