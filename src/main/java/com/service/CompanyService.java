package com.service;

import com.pojo.CmCompany;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LENOVO on 2016/10/20.
 * 企业信息的业务逻辑层
 */
@Service("companyService")
public class CompanyService {
    private HibernateTemplate hibernateTemplate;

    /*
    查询企业信息
     */
    public List<CmCompany>  FindALLCompany(CmCompany company){

        return null;
    }
    /*
    删除企业该条记录
     */
    public boolean DelCompany(CmCompany cid){
       // hibernateTemplate.delete();
        return true;
    }
}
