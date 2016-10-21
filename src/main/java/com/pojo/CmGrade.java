package com.pojo;

/**
 * Created by LENOVO on 2016/10/20.
 */
public class CmGrade {
    private Integer gid;
    private String gxq;
    private String gxn;
    private String gkcm;
    private String gcj;
    private Integer gfslx;
    private String gbkcj;
    private Integer gxf;
    private Integer glx;
    private CmStudent cmStudentBySid;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGxq() {
        return gxq;
    }

    public void setGxq(String gxq) {
        this.gxq = gxq;
    }

    public String getGxn() {
        return gxn;
    }

    public void setGxn(String gxn) {
        this.gxn = gxn;
    }

    public String getGkcm() {
        return gkcm;
    }

    public void setGkcm(String gkcm) {
        this.gkcm = gkcm;
    }

    public String getGcj() {
        return gcj;
    }

    public void setGcj(String gcj) {
        this.gcj = gcj;
    }

    public Integer getGfslx() {
        return gfslx;
    }

    public void setGfslx(Integer gfslx) {
        this.gfslx = gfslx;
    }

    public String getGbkcj() {
        return gbkcj;
    }

    public void setGbkcj(String gbkcj) {
        this.gbkcj = gbkcj;
    }

    public Integer getGxf() {
        return gxf;
    }

    public void setGxf(Integer gxf) {
        this.gxf = gxf;
    }

    public Integer getGlx() {
        return glx;
    }

    public void setGlx(Integer glx) {
        this.glx = glx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmGrade cmGrade = (CmGrade) o;

        if (gid != null ? !gid.equals(cmGrade.gid) : cmGrade.gid != null) return false;
        if (gxq != null ? !gxq.equals(cmGrade.gxq) : cmGrade.gxq != null) return false;
        if (gxn != null ? !gxn.equals(cmGrade.gxn) : cmGrade.gxn != null) return false;
        if (gkcm != null ? !gkcm.equals(cmGrade.gkcm) : cmGrade.gkcm != null) return false;
        if (gcj != null ? !gcj.equals(cmGrade.gcj) : cmGrade.gcj != null) return false;
        if (gfslx != null ? !gfslx.equals(cmGrade.gfslx) : cmGrade.gfslx != null) return false;
        if (gbkcj != null ? !gbkcj.equals(cmGrade.gbkcj) : cmGrade.gbkcj != null) return false;
        if (gxf != null ? !gxf.equals(cmGrade.gxf) : cmGrade.gxf != null) return false;
        if (glx != null ? !glx.equals(cmGrade.glx) : cmGrade.glx != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid != null ? gid.hashCode() : 0;
        result = 31 * result + (gxq != null ? gxq.hashCode() : 0);
        result = 31 * result + (gxn != null ? gxn.hashCode() : 0);
        result = 31 * result + (gkcm != null ? gkcm.hashCode() : 0);
        result = 31 * result + (gcj != null ? gcj.hashCode() : 0);
        result = 31 * result + (gfslx != null ? gfslx.hashCode() : 0);
        result = 31 * result + (gbkcj != null ? gbkcj.hashCode() : 0);
        result = 31 * result + (gxf != null ? gxf.hashCode() : 0);
        result = 31 * result + (glx != null ? glx.hashCode() : 0);
        return result;
    }

    public CmStudent getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(CmStudent cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }
}
