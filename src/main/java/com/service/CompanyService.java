package com.service;

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
    public List<CmCompany>  FindALLCompany(){
        String hsql = "from CmCompany c where c.cstate = 0";
        List<CmCompany> data = (List<CmCompany>) hibernateTemplate.find(hsql);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }//
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
        String  hsql="select new com.pojo.CmCompany(c.cid,c.cname,c.chr,c.cphone,c.cemail,c.cinfo,c.cmark,c.caddress) from CmCompany c where c.cid=?";
        List<CmCompany> cmCompanies=( List<CmCompany>) hibernateTemplate.find(hsql,cid);
        return   cmCompanies.get(0);
    }
    public  CmCompany  findCompByCid2(int cid){
        String  hsql="select new com.pojo.CmCompany(c.cid,c.cname,c.chr,c.cphone,c.cemail,c.cinfo,c.cmark,c.caddress) from CmCompany c where c.cid=?";
        List<?> cmCompanies=hibernateTemplate.find(hsql,cid);
        return   (CmCompany) cmCompanies.get(0);
    }
    //张小丽：修改公司信息
    public boolean updateCompany(int cid,String cname,String chr,String cphone,String cemail,
                                 String cinfo,String cmark,String caddress,int city){
       CmCompany cmCompany= this.findCompByCid2(cid);
//        CmArea cmArea=new CmArea();
//        cmArea.setAid(city);
//        cmCompany.setCaddress(caddress);
//        cmCompany.setCemail(cemail);
//        cmCompany.setChr(chr);
//        cmCompany.setCinfo(cinfo);
//        cmCompany.setCmark(cmark);
//        cmCompany.setCname(cname);
//        cmCompany.setCmAreaByAid(cmArea);
//        cmCompany.setCphone(cphone);
        String hsql="update CmCompany c set c.cname=?,c.chr=?,c.cphone=?,c.cemail=?,c.cinfo=?,c.cmark=?,c.caddress=?  where c.cid=?";
        hibernateTemplate.bulkUpdate(hsql,cname,chr,cphone,cemail,cinfo,cmark,caddress,cid);
      //  hibernateTemplate.saveOrUpdate(cmCompany);
        return   true;
    }
}
