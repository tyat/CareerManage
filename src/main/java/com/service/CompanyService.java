package com.service;

import com.ResObj.ResCompanyObj;
import com.pojo.CmArea;
import com.pojo.CmCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LENOVO on 2016/10/20.
 * 企业信息的业务逻辑层
 */
@Service("companyService")
public class CompanyService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /*
    查询企业信息---ly
     */
    public List<CmCompany>  FindAll(){
        String hsql = "from CmCompany c where c.cstate = 0";
        List<CmCompany> data = (List<CmCompany>) hibernateTemplate.find(hsql);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }
    //张小丽：添加公司
    public boolean addCompany(CmCompany cmCompany){
        hibernateTemplate.save(cmCompany);
        return   true;
    }
    /*
    删除企业该条记录
     */
    public boolean DelCompany(CmCompany cid){
       // hibernateTemplate.delete();
        return true;
    }
    //张小丽：根据企业id查找企业信息，为修改企业信息做准备
    public  CmCompany  findCompByCid(int cid){
        String  hsql="select new com.pojo.CmCompany(c.cid,c.cname,c.chr,c.cphone,c.cemail,c.cinfo,c.cmark,c.caddress) " +
                "from CmCompany c where c.cid=?";
        List<CmCompany> cmCompanies=( List<CmCompany>) hibernateTemplate.find(hsql,cid);
        return   cmCompanies.get(0);
    }
    public  CmCompany  findCompByCid2(int cid){
        String  hsql="select new com.pojo.CmCompany(c.cid,c.cname,c.chr,c.cphone,c.cemail,c.cinfo,c.cmark,c.caddress) " +
                "from CmCompany c where c.cid=?";
        List<?> cmCompanies=hibernateTemplate.find(hsql,cid);
        return   (CmCompany) cmCompanies.get(0);
    }
    //张小丽：修改公司信息
    public boolean updateCompany(int cid,String cname,String chr,String cphone,String cemail,
                                 String cinfo,String cmark,String caddress,int city){
      // CmCompany cmCompany= this.findCompByCid2(cid);
        String hsql="update CmCompany c set c.cname=?,c.chr=?,c.cphone=?,c.cemail=?,c.cinfo=?,c.cmark=?,c.caddress=?,c.cmAreaByAid.aid=?  " +
                "where c.cid=?";
        hibernateTemplate.bulkUpdate(hsql,cname,chr,cphone,cemail,cinfo,cmark,caddress,city,cid);
      //  hibernateTemplate.saveOrUpdate(cmCompany);
        return   true;
    }
    //张小丽：查询所有公司
    public List<CmCompany>findAllCompany(){
        String hsql="select new com.pojo.CmCompany(c.cid,c.cname) from CmCompany c";
        List<CmCompany>data=(List<CmCompany>) hibernateTemplate.find(hsql);
        return   data;
    }
    //张小丽：根据学生的id查询其所去的公司
    public CmCompany findCompanyBySid(int sid){
        String hsql="select new com.pojo.CmCompany(c.cid,c.cname)  from CmInter i  " +
                "  inner join i.cmRecruitByRid r " +
                "  inner  join r.cmCompanyByCid c " +
                " where  i.cmStudentBySid.sid=? and i.isuccess=1" +
                "  ORDER BY i.itime desc";
        List<CmCompany>data=(List<CmCompany>) hibernateTemplate.find(hsql,sid);
        return   data.get(0);
    }

    /**
     *  查询所有企业信息
     * @return
     */
    public List<ResCompanyObj> FindALLCompany(){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate,rec.rid,job.jid,job.jname) from CmCompany comp inner join comp.cmRecruitsByCid rec inner join rec.cmJobByJid job where comp.cstate=0";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql);
        ResCompanyObj res = (ResCompanyObj)data.get(0);
        System.out.println(res.getCname());
        return data;
    }

    /**
     * 按公司ID 查询该公司信息
     * @param cid
     * @return
     */
    public List<CmCompany> findCompByCid1(Integer cid){
        System.out.println(cid);
        String hsql = "select new com.pojo.CmCompany(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cemail,comp.cinfo,comp.cmark,comp.caddress,comp.ctime,comp.cstate) from CmCompany comp where comp.cid = ?";
        List<CmCompany> data = (List<CmCompany>) hibernateTemplate.find(hsql,cid);
        CmCompany res = (CmCompany)data.get(0);
        System.out.println(res.getCname());
        return data;
    }

    /**
     * 按企业名搜索相关企业信息
     * @param cname
     * @return
     */
    public List<ResCompanyObj> FindByCName(String cname){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate,rec.rid,job.jid,job.jname) from CmRecruit rec inner join rec.cmCompanyByCid comp inner join rec.cmJobByJid job where comp.cname like ?";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,"%"+cname+"%");
        return data;
    }

    /**
     * 按联系人搜索相关企业信息
     * @param chr
     * @return
     */
    public List<ResCompanyObj> FindByCHr(String chr){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate,rec.rid,job.jid,job.jname) from CmRecruit rec inner join rec.cmCompanyByCid comp inner join rec.cmJobByJid job  where comp.chr like ?";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,"%"+chr+"%");

        return data;
    }

    /**
     * 按岗位搜索相关企业信息
     * @param jname
     * @return
     */
    public List<ResCompanyObj> FindByCJname(String jname){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate,rec.rid,job.jid,job.jname) from CmRecruit rec inner join rec.cmCompanyByCid comp inner join rec.cmJobByJid job  where job.jname like ?";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,"%"+jname+"%");

        return data;
    }

    public int CompanyCount(){
        String hsql = "select count(*) from CmCompany comp where comp.cstate = 0";
        //Integer total = hibernateTemplate.;
        return 1;
    }
    /**
     *  删除该条企业信息记录
     * @param cid
     * @return
     */
    public boolean DelCompany(Integer cid){
        System.out.println(cid);
        String hsql="update CmCompany comp set comp.cstate=1 where comp.cid = ?";
        hibernateTemplate.bulkUpdate(hsql,cid);
        System.out.println("******************************");
        return true;
    }

    public CmCompany findByCompCid(Integer cid){
        String hsql = "from CmCompany comp where comp.cid = ?";
        List<?> data = hibernateTemplate.find(hsql,cid);
        System.out.println(data.size());
        if(!data.isEmpty()){
            return (CmCompany) data.get(0);
        }
        return null;
    }
}
