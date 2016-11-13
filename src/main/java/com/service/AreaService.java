package com.service;

import com.pojo.CmArea;
import com.tools.InputData;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/10/25.
 */
@Service("areaService")
public class AreaService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //zxl：查询数据库中所有的省份
    public List<CmArea> findAllArea(){
        String hsql = "select distinct new com.pojo.CmArea(m.aprovince)  from  CmArea m";
        List<CmArea>data= (List<CmArea>) hibernateTemplate.find(hsql);
        return  data;
    }
    //zxl：查询某省份下数据库中所有的城市
    public List<CmArea> findCityByAprovince(String aprovince){
        String hsql="select  new com.pojo.CmArea(m.aid,m.aprovince,m.acity)  from  CmArea m where m.aprovince=?";
        List<CmArea>data=(List<CmArea>) hibernateTemplate.find(hsql,aprovince);
        return data;
    }

    //按aid查询地区——ly
    public CmArea findByAid(int aid){
        String hsql = "from CmArea a where a.aid = ?";
        List<CmArea> data = (List<CmArea>) hibernateTemplate.find(hsql,aid);
        if(data.get(0)!=null){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    /*TianYu 上传excel*/
    public String uploadArea(String path){
        InputData input = new InputData();
        //Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        //Transaction t = session.beginTransaction();
        try {
            //t.begin();
            List<CmArea>  ls = input.inputArea(path);
            for (CmArea ca : ls){
                //hibernateTemplate.save(ca);
                hibernateTemplate.saveOrUpdate(ca);
                hibernateTemplate.flush();
                //session.save(ca);
                //session.flush();
            }
           // t.commit();
            //session.close();
            return "导入成功！";
        } catch (IOException e) {
            return "数据格式错误！";
        }
    }

}
