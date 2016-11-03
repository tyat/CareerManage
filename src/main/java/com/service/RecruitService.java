package com.service;

import com.ResObj.RecruitResObj;
import com.pojo.CmRecruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by w on 2016/10/31.
 */
@Service("recruitService")
public class RecruitService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    //增加招聘信息——ly
    public boolean addRecruit(int rid){

        return false;
    }

    //删除招聘信息——ly
    public boolean delRecruit(int rid){
        CmRecruit recruit = this.findByRid(rid);
        if(recruit!=null){
            recruit.setRstate(1);
            hibernateTemplate.saveOrUpdate(recruit);
            return true;
        }
        return false;
    }

    //编辑招聘信息——ly
    public boolean updateRecruit(int rid,int cid,int jid ){
        CmRecruit recruit = this.findByRid(rid);

        if(recruit!=null){

            hibernateTemplate.saveOrUpdate(recruit);
            return true;
        }
        return false;
    }

    //按企业名称模糊查询所有招聘信息——ly
    public List<RecruitResObj> query(String cname){
        String hsql = "select new RecruitResObj(r.rid,r.rsex,r.rsalary,r.rstart,r.rend,r.rnum,r.rinfo,r.rstate,a.aid,a.aprovince,a.acity,i.iaddress,i.itype,i.itime,j.jid,j.jname,c.cid,c.cname,c.chr,c.cphone,c.cemail) " +
                "from CmRecruit r " +
                "inner join r.cmIntersByRid i " +
                "inner join i.cmAreaByAid a " +
                "inner join r.cmJobByJid j " +
                "inner join r.cmCompanyByCid c " +
                "where r.rstate = 0 and c.cname like ? order by r.rstart";
        List<RecruitResObj> data = (List<RecruitResObj>) hibernateTemplate.find(hsql,'%'+cname+'%');
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按rid查询招聘信息——ly
    public CmRecruit findByRid(int rid){
        String hsql = "from CmRecruit r where r.rid = ?";
        List<CmRecruit> data = (List<CmRecruit>)hibernateTemplate.find(hsql,rid);
        if(data.get(0)!=null){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //查询所有招聘信息——ly
    public List<RecruitResObj> findAll(){
        String hsql = "select new RecruitResObj(r.rid,r.rsex,r.rsalary,r.rstart,r.rend,r.rnum,r.rinfo,r.rstate,a.aid,a.aprovince,a.acity,i.iaddress,i.itype,i.itime,j.jid,j.jname,c.cid,c.cname,c.chr,c.cphone,c.cemail) " +
                "from CmRecruit r " +
                "inner join r.cmIntersByRid i " +
                "inner join i.cmAreaByAid a " +
                "inner join r.cmJobByJid j " +
                "inner join r.cmCompanyByCid c " +
                "where r.rstate = 0 order by r.rstart";
        List<RecruitResObj> data = (List<RecruitResObj>) hibernateTemplate.find(hsql);
        System.out.println("所有招聘信息数量：   "+data.size());
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

}
