package com.service;

import com.pojo.CmEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/10/30.
 */
@Service("empService")
public class EmpService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //
    public  boolean updateEmp(CmEmp cmEmp){
        String hsql="";
        hibernateTemplate.update(cmEmp);
       // hibernateTemplate.update(hsql,"",2);
        return  true;
    }
    public  boolean addEmp(CmEmp cmEmp){
        hibernateTemplate.update(cmEmp);
        return  true;
    }
}
