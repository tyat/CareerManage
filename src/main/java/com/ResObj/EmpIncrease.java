package com.ResObj;

import java.util.Date;

/**
 * Created by TianYu on 2016/11/21.
 */
public class EmpIncrease {
    private String thismonth;
    private String beformonth;
    private String data;

    public EmpIncrease(String thismonth, String beformonth, String data) {
        this.thismonth = thismonth;
        this.beformonth = beformonth;
        this.data = data;
    }

    public EmpIncrease() {
    }

    public String getThismonth() {
        return thismonth;
    }

    public void setThismonth(String thismonth) {
        this.thismonth = thismonth;
    }

    public String getBeformonth() {
        return beformonth;
    }

    public void setBeformonth(String beformonth) {
        this.beformonth = beformonth;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}