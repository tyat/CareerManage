package com.pojo;

import javax.persistence.*;

/**
 * Created by TianYu on 2016/10/18.
 */
@Entity
@Table(name = "cm_grade", schema = "career", catalog = "")
public class CmGrade {
    private int gid;
    private String gxq;
    private String gxn;
    private String gkcm;
    private String gcj;
    private int gfslx;
    private String gbkcj;
    private int gxf;
    private int glx;
    private CmStudent cmStudentBySid;

    @Id
    @Column(name = "gid")
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "gxq", nullable = false, length = 50)
    public String getGxq() {
        return gxq;
    }

    public void setGxq(String gxq) {
        this.gxq = gxq;
    }

    @Basic
    @Column(name = "gxn", nullable = false, length = 50)
    public String getGxn() {
        return gxn;
    }

    public void setGxn(String gxn) {
        this.gxn = gxn;
    }

    @Basic
    @Column(name = "gkcm", nullable = false, length = 50)
    public String getGkcm() {
        return gkcm;
    }

    public void setGkcm(String gkcm) {
        this.gkcm = gkcm;
    }

    @Basic
    @Column(name = "gcj", nullable = false, length = 50)
    public String getGcj() {
        return gcj;
    }

    public void setGcj(String gcj) {
        this.gcj = gcj;
    }

    @Basic
    @Column(name = "gfslx", nullable = false)
    public int getGfslx() {
        return gfslx;
    }

    public void setGfslx(int gfslx) {
        this.gfslx = gfslx;
    }

    @Basic
    @Column(name = "gbkcj", nullable = true, length = 50)
    public String getGbkcj() {
        return gbkcj;
    }

    public void setGbkcj(String gbkcj) {
        this.gbkcj = gbkcj;
    }

    @Basic
    @Column(name = "gxf", nullable = false)
    public int getGxf() {
        return gxf;
    }

    public void setGxf(int gxf) {
        this.gxf = gxf;
    }

    @Basic
    @Column(name = "glx", nullable = false)
    public int getGlx() {
        return glx;
    }

    public void setGlx(int glx) {
        this.glx = glx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmGrade cmGrade = (CmGrade) o;

        if (gid != cmGrade.gid) return false;
        if (gfslx != cmGrade.gfslx) return false;
        if (gxf != cmGrade.gxf) return false;
        if (glx != cmGrade.glx) return false;
        if (gxq != null ? !gxq.equals(cmGrade.gxq) : cmGrade.gxq != null) return false;
        if (gxn != null ? !gxn.equals(cmGrade.gxn) : cmGrade.gxn != null) return false;
        if (gkcm != null ? !gkcm.equals(cmGrade.gkcm) : cmGrade.gkcm != null) return false;
        if (gcj != null ? !gcj.equals(cmGrade.gcj) : cmGrade.gcj != null) return false;
        if (gbkcj != null ? !gbkcj.equals(cmGrade.gbkcj) : cmGrade.gbkcj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + (gxq != null ? gxq.hashCode() : 0);
        result = 31 * result + (gxn != null ? gxn.hashCode() : 0);
        result = 31 * result + (gkcm != null ? gkcm.hashCode() : 0);
        result = 31 * result + (gcj != null ? gcj.hashCode() : 0);
        result = 31 * result + gfslx;
        result = 31 * result + (gbkcj != null ? gbkcj.hashCode() : 0);
        result = 31 * result + gxf;
        result = 31 * result + glx;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false)
    public CmStudent getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(CmStudent cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }
}
