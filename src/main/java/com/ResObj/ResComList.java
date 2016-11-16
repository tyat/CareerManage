package com.ResObj;

import com.pojo.CmJob;

import java.util.Date;
import java.util.List;

/**
 * Created by LENOVO on 2016/11/11.
 */
public class ResComList {
    private Integer cid;
    private String cname;
    private String chr;
    private String cphone;
    private Integer cstate;
    private Integer rid;
    private List<CmJob> cmJobs;
    private Integer iid;
    private Date itime;
    private Integer isuccess;
    private Integer stuCount;


    public ResComList(Integer cid, String cname, String chr, String cphone, Integer cstate, Integer rid, List<CmJob> cmJobs, Integer iid, Date itime, Integer isuccess, Integer stuCount) {
        this.cid = cid;
        this.cname = cname;
        this.chr = chr;
        this.cphone = cphone;
        this.cstate = cstate;
        this.rid = rid;
        this.cmJobs = cmJobs;
        this.iid = iid;
        this.itime = itime;
        this.isuccess = isuccess;
        this.stuCount = stuCount;
    }

    public ResComList() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<CmJob> getCmJobs() {
        return cmJobs;
    }

    public void setCmJobs(List<CmJob> cmJobs) {
        this.cmJobs = cmJobs;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Date getItime() {
        return itime;
    }

    public void setItime(Date itime) {
        this.itime = itime;
    }

    public Integer getIsuccess() {
        return isuccess;
    }

    public void setIsuccess(Integer isuccess) {
        this.isuccess = isuccess;
    }

    public Integer getStuCount() {
        return stuCount;
    }

    public void setStuCount(Integer stuCount) {
        this.stuCount = stuCount;
    }
}
