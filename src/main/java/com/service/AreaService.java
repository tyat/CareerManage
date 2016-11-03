package com.service;

import com.pojo.CmArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
@Service("areaService")
public class AreaService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //张小丽：查询数据库中所有的省份
    public List<CmArea> findAllArea(){
        String hsql = "select distinct new com.pojo.CmArea(m.aprovince)  from  CmArea m";
        List<CmArea>data= (List<CmArea>) hibernateTemplate.find(hsql);
        return  data;
    }
    //张小丽：查询某省份下数据库中所有的城市
    public List<CmArea> findCityByAprovince(String aprovince){
        String hsql="select  new com.pojo.CmArea(m.aid,m.aprovince,m.acity)  from  CmArea m where m.aprovince=?";
        List<CmArea>data=(List<CmArea>) hibernateTemplate.find(hsql,aprovince);
        return data;
    }
}
