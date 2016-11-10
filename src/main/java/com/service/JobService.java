package com.service;

import com.pojo.CmJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by w on 2016/11/5.
 */
@Service("jobService")
public class JobService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    //查询所有招聘岗位——ly
    public List<CmJob> findAll(){
        String hsql = "from CmJob j where j.jstate = 0";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql);
        if(data.size()>0){
            System.out.println(data.get(0).getJname());
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按jid查询工作岗位——ly
    public CmJob findByJid(int jid){
        String hsql = "from CmJob j where j.jid = ?";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql,jid);
        if(data.get(0)!=null){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }
}
