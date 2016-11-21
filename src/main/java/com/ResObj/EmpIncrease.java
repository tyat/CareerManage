package com.ResObj;

import java.util.Date;

/**
 * Created by TianYu on 2016/11/21.
 */
public class EmpIncrease {
    private Date thismonth;
    private Date beformonth;
    private String data;

    public EmpIncrease(Date thismonth, Date beformonth, String data) {
        this.thismonth = thismonth;
        this.beformonth = beformonth;
        this.data = data;
    }

    public EmpIncrease() {
    }

    public Date getThismonth() {
        return thismonth;
    }

    public void setThismonth(Date thismonth) {
        this.thismonth = thismonth;
    }

    public Date getBeformonth() {
        return beformonth;
    }

    public void setBeformonth(Date beformonth) {
        this.beformonth = beformonth;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
