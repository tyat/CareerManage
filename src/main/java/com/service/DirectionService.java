package com.service;

import com.pojo.CmDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
@Service("directionService")
public class DirectionService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //张小丽：查询所有动向
    public List<CmDirection> findAllDirection(){
        String hsql="from CmDirection d";
        List<CmDirection>data=(List<CmDirection>) hibernateTemplate.find(hsql);
        return  data;

    }
}
