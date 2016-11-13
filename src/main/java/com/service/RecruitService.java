package com.service;

import com.ResObj.RecruitResObj;
import com.pojo.CmRecruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by w on 2016/10/31.
 */
@Service("recruitService")
public class RecruitService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AreaService areaService;
    //增加招聘信息——ly
    public boolean addRecruit(int cid,int aid,int jid,int rsalary,Boolean rsex,int rnum,String rend,String rinfo) throws ParseException {
        CmRecruit recruit = new CmRecruit();
        recruit.setCmCompanyByCid(companyService.findCompByCid(cid));
        recruit.setCmAreaByAid(areaService.findByAid(aid));
        recruit.setCmJobByJid(jobService.findByJid(jid));
        recruit.setRsex(rsex);
        recruit.setRsalary(rsalary);
        recruit.setRnum(rnum);
        recruit.setRstart(new Timestamp(new Date().getTime()));//发布时间为当前系统时间
        DateFormat df = DateFormat.getDateInstance();
        Date d = df.parse(rend);
        long da = d.getTime();
        Timestamp ts = new Timestamp(da);
        System.out.println("ts--------"+ts);
        recruit.setRend(ts);
        recruit.setRinfo(rinfo);
        recruit.setRstate(0);
        try {
            hibernateTemplate.save(recruit);
        }catch (Exception e){
            System.out.println("添加招聘信息出错");
        }
        return true;
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
    public boolean updateRecruit(int rid,int cid,int aid,int jid,int rsalary,Boolean rsex,int rnum,String rend,String rinfo) throws ParseException {
        CmRecruit recruit = this.findByRid(rid);
        if(recruit!=null){
            recruit.setCmJobByJid(jobService.findByJid(jid));
            recruit.setCmAreaByAid(areaService.findByAid(aid));
            recruit.setCmCompanyByCid(companyService.findCompByCid(cid));
            recruit.setRsalary(rsalary);
            recruit.setRsex(rsex);
            recruit.setRnum(rnum);
            DateFormat df = DateFormat.getDateInstance();
            Date d = df.parse(rend);
            long da = d.getTime();
            Timestamp ts = new Timestamp(da);
            recruit.setRend(ts);
            recruit.setRinfo(rinfo);
            hibernateTemplate.saveOrUpdate(recruit);
            return true;
        }
        return false;
    }

    //按企业名称模糊查询招聘信息——ly
    public List<RecruitResObj> findByCname(String cname){
        String hsql = "select new com.ResObj.RecruitResObj(r.rid,r.rsex,r.rsalary,r.rstart,r.rend,r.rnum,r.rinfo,r.rstate,a.aid,a.aprovince,a.acity,j.jid,j.jname,c.cid,c.cname,c.chr,c.cphone,c.cemail) " +
                "from CmRecruit r " +
                "inner join r.cmAreaByAid a " +
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

    //按rid查询招聘表——ly
    public CmRecruit findByRid(int rid){
        String hsql = "from CmRecruit r where r.rid = ?";
        List<CmRecruit> data = (List<CmRecruit>)hibernateTemplate.find(hsql,rid);
        if(data.get(0)!=null){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按rid查询招聘及面试信息——ly
    public RecruitResObj findByRid2(int rid){
        String hsql = "select new com.ResObj.RecruitResObj(r.rid,r.rsex,r.rsalary,r.rstart,r.rend,r.rnum,r.rinfo,r.rstate,a.aid,a.aprovince,a.acity,j.jid,j.jname,c.cid,c.cname,c.chr,c.cphone,c.cemail) " +
                "from CmRecruit r " +
                "inner join r.cmAreaByAid a " +
                "inner join r.cmJobByJid j " +
                "inner join r.cmCompanyByCid c " +
                "where r.rstate = 0 and r.rid = ? order by r.rstart";
        List<RecruitResObj> data = (List<RecruitResObj>) hibernateTemplate.find(hsql,rid);
        if(data.size()>0){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }


    //查询所有招聘信息——ly
    public List<RecruitResObj> findAll(){
        String hsql = "select new com.ResObj.RecruitResObj(r.rid,r.rsex,r.rsalary,r.rstart,r.rend,r.rnum,r.rinfo,r.rstate,a.aid,a.aprovince,a.acity,j.jid,j.jname,c.cid,c.cname,c.chr,c.cphone,c.cemail) " +
                "from CmRecruit r " +
                "inner join r.cmAreaByAid a " +
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
