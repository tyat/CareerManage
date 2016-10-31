package com.pojo;

import java.util.Collection;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmArea {
    private Integer aid;
    private String aprovince;
    private String acity;
    private Collection<CmCompany> cmCompaniesByAid;
    private Collection<CmInter> cmIntersByAid;
    private Collection<CmRecruit> cmRecruitsByAid;

    public CmArea() {
    }

    public CmArea(String aprovince) {
        this.aprovince = aprovince;
    }

    public CmArea(Integer aid, String aprovince) {
        this.aid = aid;
        this.aprovince = aprovince;
    }


    public CmArea(Integer aid, String aprovince, String acity) {
        this.aid = aid;
        this.aprovince = aprovince;
        this.acity = acity;
    }

    public CmArea(Integer aid, String aprovince, String acity, Collection<CmCompany> cmCompaniesByAid, Collection<CmInter> cmIntersByAid, Collection<CmRecruit> cmRecruitsByAid) {
        this.aid = aid;
        this.aprovince = aprovince;
        this.acity = acity;
        this.cmCompaniesByAid = cmCompaniesByAid;
        this.cmIntersByAid = cmIntersByAid;
        this.cmRecruitsByAid = cmRecruitsByAid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAprovince() {
        return aprovince;
    }

    public void setAprovince(String aprovince) {
        this.aprovince = aprovince;
    }

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

        if (aid != null ? !aid.equals(cmArea.aid) : cmArea.aid != null) return false;
        if (aprovince != null ? !aprovince.equals(cmArea.aprovince) : cmArea.aprovince != null) return false;
        if (acity != null ? !acity.equals(cmArea.acity) : cmArea.acity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid != null ? aid.hashCode() : 0;
        result = 31 * result + (aprovince != null ? aprovince.hashCode() : 0);
        result = 31 * result + (acity != null ? acity.hashCode() : 0);
        return result;
    }

    public Collection<CmCompany> getCmCompaniesByAid() {
        return cmCompaniesByAid;
    }

    public void setCmCompaniesByAid(Collection<CmCompany> cmCompaniesByAid) {
        this.cmCompaniesByAid = cmCompaniesByAid;
    }

    public Collection<CmInter> getCmIntersByAid() {
        return cmIntersByAid;
    }

    public void setCmIntersByAid(Collection<CmInter> cmIntersByAid) {
        this.cmIntersByAid = cmIntersByAid;
    }

    public Collection<CmRecruit> getCmRecruitsByAid() {
        return cmRecruitsByAid;
    }

    public void setCmRecruitsByAid(Collection<CmRecruit> cmRecruitsByAid) {
        this.cmRecruitsByAid = cmRecruitsByAid;
    }
}
