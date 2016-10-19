package com.pojo;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_user", schema = "career", catalog = "")
public class CmUser {
    private int uid;
    private String uname;
    private String urname;
    private String upwd;
    private String uemail;
    private String uphone;
    private int urank;
    private int ustate;
    private Collection<CmEmp> cmEmpsByUid;

    public CmUser() {
    }

    public CmUser(String uname, String urname, String upwd, String uemail, String uphone, int urank, int ustate) {
        this.uname = uname;
        this.urname = urname;
        this.upwd = upwd;
        this.uemail = uemail;
        this.uphone = uphone;
        this.urank = urank;
        this.ustate = ustate;
    }

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "uname", nullable = false, length = 50)
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "urname", nullable = false, length = 50)
    public String getUrname() {
        return urname;
    }

    public void setUrname(String urname) {
        this.urname = urname;
    }

    @Basic
    @Column(name = "upwd", nullable = false, length = 50)
    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    @Basic
    @Column(name = "uemail", nullable = false, length = 50)
    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    @Basic
    @Column(name = "uphone", nullable = false, length = 50)
    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    @Basic
    @Column(name = "urank", nullable = false)
    public int getUrank() {
        return urank;
    }

    public void setUrank(int urank) {
        this.urank = urank;
    }

    @Basic
    @Column(name = "ustate", nullable = false)
    public int getUstate() {
        return ustate;
    }

    public void setUstate(int ustate) {
        this.ustate = ustate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmUser cmUser = (CmUser) o;

        if (uid != cmUser.uid) return false;
        if (urank != cmUser.urank) return false;
        if (ustate != cmUser.ustate) return false;
        if (uname != null ? !uname.equals(cmUser.uname) : cmUser.uname != null) return false;
        if (urname != null ? !urname.equals(cmUser.urname) : cmUser.urname != null) return false;
        if (upwd != null ? !upwd.equals(cmUser.upwd) : cmUser.upwd != null) return false;
        if (uemail != null ? !uemail.equals(cmUser.uemail) : cmUser.uemail != null) return false;
        if (uphone != null ? !uphone.equals(cmUser.uphone) : cmUser.uphone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (urname != null ? urname.hashCode() : 0);
        result = 31 * result + (upwd != null ? upwd.hashCode() : 0);
        result = 31 * result + (uemail != null ? uemail.hashCode() : 0);
        result = 31 * result + (uphone != null ? uphone.hashCode() : 0);
        result = 31 * result + urank;
        result = 31 * result + ustate;
        return result;
    }

    @OneToMany(mappedBy = "cmUserByUid")
    public Collection<CmEmp> getCmEmpsByUid() {
        return cmEmpsByUid;
    }

    public void setCmEmpsByUid(Collection<CmEmp> cmEmpsByUid) {
        this.cmEmpsByUid = cmEmpsByUid;
    }
}
