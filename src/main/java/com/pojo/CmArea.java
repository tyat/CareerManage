package com.pojo;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_area", schema = "career", catalog = "")
public class CmArea {
    private int aid;
    private String aprovince;
    private String acity;
    private Collection<CmCompany> cmCompaniesByAid;
    private Collection<CmInter> cmIntersByAid;
    private Collection<CmRecruit> cmRecruitsByAid;

    @Id
    @Column(name = "aid")
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "aprovince", nullable = false, length = 50)
    public String getAprovince() {
        return aprovince;
    }

    public void setAprovince(String aprovince) {
        this.aprovince = aprovince;
    }

    @Basic
    @Column(name = "acity", nullable = false, length = 50)
    public String getAcity() {
        return acity;
    }

    public void setAcity(String acity) {
        this.acity = acity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmArea cmArea = (CmArea) o;

        if (aid != cmArea.aid) return false;
        if (aprovince != null ? !aprovince.equals(cmArea.aprovince) : cmArea.aprovince != null) return false;
        if (acity != null ? !acity.equals(cmArea.acity) : cmArea.acity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (aprovince != null ? aprovince.hashCode() : 0);
        result = 31 * result + (acity != null ? acity.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cmAreaByAid")
    public Collection<CmCompany> getCmCompaniesByAid() {
        return cmCompaniesByAid;
    }

    public void setCmCompaniesByAid(Collection<CmCompany> cmCompaniesByAid) {
        this.cmCompaniesByAid = cmCompaniesByAid;
    }

    @OneToMany(mappedBy = "cmAreaByAid")
    public Collection<CmInter> getCmIntersByAid() {
        return cmIntersByAid;
    }

    public void setCmIntersByAid(Collection<CmInter> cmIntersByAid) {
        this.cmIntersByAid = cmIntersByAid;
    }

    @OneToMany(mappedBy = "cmAreaByAid")
    public Collection<CmRecruit> getCmRecruitsByAid() {
        return cmRecruitsByAid;
    }

    public void setCmRecruitsByAid(Collection<CmRecruit> cmRecruitsByAid) {
        this.cmRecruitsByAid = cmRecruitsByAid;
    }
}
