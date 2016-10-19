package com.pojo;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_job", schema = "career", catalog = "")
public class CmJob {
    private int jid;
    private String jname;
    private boolean jtype;
    private int jstate;
    private String jinfo;
    private Collection<CmEmp> cmEmpsByJid;
    private Collection<CmRecruit> cmRecruitsByJid;
    private Collection<CmUnemp> cmUnempsByJid;

    @Id
    @Column(name = "jid")
    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    @Basic
    @Column(name = "jname", nullable = false, length = 100)
    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    @Basic
    @Column(name = "jtype", nullable = false)
    public boolean isJtype() {
        return jtype;
    }

    public void setJtype(boolean jtype) {
        this.jtype = jtype;
    }

    @Basic
    @Column(name = "jstate", nullable = false)
    public int getJstate() {
        return jstate;
    }

    public void setJstate(int jstate) {
        this.jstate = jstate;
    }

    @Basic
    @Column(name = "jinfo", nullable = true, length = 255)
    public String getJinfo() {
        return jinfo;
    }

    public void setJinfo(String jinfo) {
        this.jinfo = jinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmJob cmJob = (CmJob) o;

        if (jid != cmJob.jid) return false;
        if (jtype != cmJob.jtype) return false;
        if (jstate != cmJob.jstate) return false;
        if (jname != null ? !jname.equals(cmJob.jname) : cmJob.jname != null) return false;
        if (jinfo != null ? !jinfo.equals(cmJob.jinfo) : cmJob.jinfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jid;
        result = 31 * result + (jname != null ? jname.hashCode() : 0);
        result = 31 * result + (jtype ? 1 : 0);
        result = 31 * result + jstate;
        result = 31 * result + (jinfo != null ? jinfo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cmJobByJid")
    public Collection<CmEmp> getCmEmpsByJid() {
        return cmEmpsByJid;
    }

    public void setCmEmpsByJid(Collection<CmEmp> cmEmpsByJid) {
        this.cmEmpsByJid = cmEmpsByJid;
    }

    @OneToMany(mappedBy = "cmJobByJid")
    public Collection<CmRecruit> getCmRecruitsByJid() {
        return cmRecruitsByJid;
    }

    public void setCmRecruitsByJid(Collection<CmRecruit> cmRecruitsByJid) {
        this.cmRecruitsByJid = cmRecruitsByJid;
    }

    @OneToMany(mappedBy = "cmJobByJid")
    public Collection<CmUnemp> getCmUnempsByJid() {
        return cmUnempsByJid;
    }

    public void setCmUnempsByJid(Collection<CmUnemp> cmUnempsByJid) {
        this.cmUnempsByJid = cmUnempsByJid;
    }
}
