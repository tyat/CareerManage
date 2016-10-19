package com.pojo;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_direction", schema = "career", catalog = "")
public class CmDirection {
    private int did;
    private String dname;
    private int dstate;
    private Collection<CmUnemp> cmUnempsByDid;

    @Id
    @Column(name = "did")
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Basic
    @Column(name = "dname", nullable = false, length = 50)
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Basic
    @Column(name = "dstate", nullable = false)
    public int getDstate() {
        return dstate;
    }

    public void setDstate(int dstate) {
        this.dstate = dstate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmDirection that = (CmDirection) o;

        if (did != that.did) return false;
        if (dstate != that.dstate) return false;
        if (dname != null ? !dname.equals(that.dname) : that.dname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = did;
        result = 31 * result + (dname != null ? dname.hashCode() : 0);
        result = 31 * result + dstate;
        return result;
    }

    @OneToMany(mappedBy = "cmDirectionByDid")
    public Collection<CmUnemp> getCmUnempsByDid() {
        return cmUnempsByDid;
    }

    public void setCmUnempsByDid(Collection<CmUnemp> cmUnempsByDid) {
        this.cmUnempsByDid = cmUnempsByDid;
    }
}
