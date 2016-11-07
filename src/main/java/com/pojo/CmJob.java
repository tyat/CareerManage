package com.pojo;

import java.util.Collection;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmJob {
    private Integer jid;
    private String jname;
    private Boolean jtype;
    private Integer jstate;
    private String jinfo;
    private Collection<CmEmp> cmEmpsByJid;
    private Collection<CmRecruit> cmRecruitsByJid;
    private Collection<CmUnemp> cmUnempsByJid;

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public Boolean getJtype() {
        return jtype;
    }

    public void setJtype(Boolean jtype) {
        this.jtype = jtype;
    }

    public Integer getJstate() {
        return jstate;
    }

    public void setJstate(Integer jstate) {
        this.jstate = jstate;
    }

    public String getJinfo() {
        return jinfo;
    }

    public void setJinfo(String jinfo) {
        this.jinfo = jinfo;
    }

    public CmJob(Integer jid, String jname) {
        this.jid = jid;
        this.jname = jname;
    }

    public CmJob() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmJob cmJob = (CmJob) o;

        if (jid != null ? !jid.equals(cmJob.jid) : cmJob.jid != null) return false;
        if (jname != null ? !jname.equals(cmJob.jname) : cmJob.jname != null) return false;
        if (jtype != null ? !jtype.equals(cmJob.jtype) : cmJob.jtype != null) return false;
        if (jstate != null ? !jstate.equals(cmJob.jstate) : cmJob.jstate != null) return false;
        if (jinfo != null ? !jinfo.equals(cmJob.jinfo) : cmJob.jinfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jid != null ? jid.hashCode() : 0;
        result = 31 * result + (jname != null ? jname.hashCode() : 0);
        result = 31 * result + (jtype != null ? jtype.hashCode() : 0);
        result = 31 * result + (jstate != null ? jstate.hashCode() : 0);
        result = 31 * result + (jinfo != null ? jinfo.hashCode() : 0);
        return result;
    }

    public Collection<CmEmp> getCmEmpsByJid() {
        return cmEmpsByJid;
    }

    public void setCmEmpsByJid(Collection<CmEmp> cmEmpsByJid) {
        this.cmEmpsByJid = cmEmpsByJid;
    }

    public Collection<CmRecruit> getCmRecruitsByJid() {
        return cmRecruitsByJid;
    }

    public void setCmRecruitsByJid(Collection<CmRecruit> cmRecruitsByJid) {
        this.cmRecruitsByJid = cmRecruitsByJid;
    }

    public Collection<CmUnemp> getCmUnempsByJid() {
        return cmUnempsByJid;
    }

    public void setCmUnempsByJid(Collection<CmUnemp> cmUnempsByJid) {
        this.cmUnempsByJid = cmUnempsByJid;
    }
}
