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
    //zxl：查询所有动向
    public List<CmDirection> findAllDirection(){
        String hsql="from CmDirection d";
        List<CmDirection>data=(List<CmDirection>) hibernateTemplate.find(hsql);
        return  data;

    }
    //zxl:查询就业生的就业动向
    public CmDirection findDirByUeid(int ueid){
        String hsql="select  new com.pojo.CmDirection(d.did, d.dname,d.dstate)  from  CmUnemp un inner  join  un.cmDirectionByDid d where un.ueid=? and d.dstate=0";
        List<CmDirection>data=(List<CmDirection>) hibernateTemplate.find(hsql,ueid);
        return  data.get(0);
    }
    /**
     * 显示所有未就业学生的准备方向
     * @return
     */
    public List<CmDirection> FindAllDirection(){
        String hsql = "select new com.pojo.CmDirection(dir.did,dir.dname,dir.dstate) from CmDirection dir where dir.dstate = 0";
        List<CmDirection> data = (List<CmDirection>) hibernateTemplate.find(hsql);
        CmDirection cmDirection = (CmDirection)data.get(0);
        System.out.println(cmDirection.getDname());
        return data;
    }

    /**
     *添加未就业学生的准备方向
     * @param cmDirection
     * @return
     */
    public boolean addDirection(CmDirection cmDirection){
        hibernateTemplate.save(cmDirection);
        return true;
    }
    /**
     * 删除没有学生选择的方向
     * @param did
     * @return
     */
    public boolean DelCmDirection(Integer did){
        System.out.println(did);
        String hsql = "update CmDirection dir set dir.dstate=1 where dir.did = ?";
        hibernateTemplate.bulkUpdate(hsql,did);
        return true;
    }
}
