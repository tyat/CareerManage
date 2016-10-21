package com.pojo;

import java.util.Collection;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmUser {
    private Integer uid;
    private String uname;
    private String urname;
    private String upwd;
    private String uemail;
    private String uphone;
    private Integer urank;
    private Integer ustate;
    private Collection<CmEmp> cmEmpsByUid;

    public CmUser() {
    }

    public CmUser(String uname, String urname, String upwd, String uemail, String uphone, Integer urank, Integer ustate) {
        this.uname = uname;
        this.urname = urname;
        this.upwd = upwd;
        this.uemail = uemail;
        this.uphone = uphone;
        this.urank = urank;
        this.ustate = ustate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUrname() {
        return urname;
    }

    public void setUrname(String urname) {
        this.urname = urname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public Integer getUrank() {
        return urank;
    }

    public void setUrank(Integer urank) {
        this.urank = urank;
    }

    public Integer getUstate() {
        return ustate;
    }

    public void setUstate(Integer ustate) {
        this.ustate = ustate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmUser cmUser = (CmUser) o;

        if (uid != null ? !uid.equals(cmUser.uid) : cmUser.uid != null) return false;
        if (uname != null ? !uname.equals(cmUser.uname) : cmUser.uname != null) return false;
        if (urname != null ? !urname.equals(cmUser.urname) : cmUser.urname != null) return false;
        if (upwd != null ? !upwd.equals(cmUser.upwd) : cmUser.upwd != null) return false;
        if (uemail != null ? !uemail.equals(cmUser.uemail) : cmUser.uemail != null) return false;
        if (uphone != null ? !uphone.equals(cmUser.uphone) : cmUser.uphone != null) return false;
        if (urank != null ? !urank.equals(cmUser.urank) : cmUser.urank != null) return false;
        if (ustate != null ? !ustate.equals(cmUser.ustate) : cmUser.ustate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (urname != null ? urname.hashCode() : 0);
        result = 31 * result + (upwd != null ? upwd.hashCode() : 0);
        result = 31 * result + (uemail != null ? uemail.hashCode() : 0);
        result = 31 * result + (uphone != null ? uphone.hashCode() : 0);
        result = 31 * result + (urank != null ? urank.hashCode() : 0);
        result = 31 * result + (ustate != null ? ustate.hashCode() : 0);
        return result;
    }

    public Collection<CmEmp> getCmEmpsByUid() {
        return cmEmpsByUid;
    }

    public void setCmEmpsByUid(Collection<CmEmp> cmEmpsByUid) {
        this.cmEmpsByUid = cmEmpsByUid;
    }
}
