package com.pojo;

import java.util.Collection;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmDirection {
    private Integer did;
    private String dname;
    private Integer dstate;
    private Collection<CmUnemp> cmUnempsByDid;

    public CmDirection() {
    }


    public CmDirection(Integer did, String dname,Integer dstate) {
        this.dstate = dstate;
        this.did = did;
        this.dname = dname;
    }

    public CmDirection(String dname) {
        this.dname = dname;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getDstate() {
        return dstate;
    }

    public void setDstate(Integer dstate) {
        this.dstate = dstate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmDirection that = (CmDirection) o;

        if (did != null ? !did.equals(that.did) : that.did != null) return false;
        if (dname != null ? !dname.equals(that.dname) : that.dname != null) return false;
        if (dstate != null ? !dstate.equals(that.dstate) : that.dstate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = did != null ? did.hashCode() : 0;
        result = 31 * result + (dname != null ? dname.hashCode() : 0);
        result = 31 * result + (dstate != null ? dstate.hashCode() : 0);
        return result;
    }

    public Collection<CmUnemp> getCmUnempsByDid() {
        return cmUnempsByDid;
    }

    public void setCmUnempsByDid(Collection<CmUnemp> cmUnempsByDid) {
        this.cmUnempsByDid = cmUnempsByDid;
    }
}
