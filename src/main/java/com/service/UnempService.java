package com.service;

import com.pojo.CmUnemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by w on 2016/11/10.
 */
@Service("unempService")
public class UnempService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private JobService jobService;

    //编辑非考研学生期望——ly
    public boolean updateFkyExpectation(int sid,int jid,int uesalary){
        CmUnemp unemp = this.findBySid(sid);
        if(unemp!=null){
            unemp.setCmJobByJid(jobService.findByJid(jid));
            unemp.setUesalary(uesalary);
            hibernateTemplate.saveOrUpdate(unemp);
            return true;
        }
        return false;
    }

    //编辑考研学生期望——ly
    public boolean updateKyExpectation(int sid,String ueschool,String uemajor){
        CmUnemp unemp = this.findBySid(sid);
        if(unemp!=null){
            unemp.setUeschool(ueschool);
            unemp.setUemajor(uemajor);
            hibernateTemplate.saveOrUpdate(unemp);
            return true;
        }
        return false;
    }

    //按sid查询未就业生——ly
    public CmUnemp findBySid(int sid){
        String hsql = "from CmUnemp u where u.cmStudentBySid.sid = ?";
        List<CmUnemp> data = (List<CmUnemp>) hibernateTemplate.find(hsql,sid);
        if(data.size()>0){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }
}
