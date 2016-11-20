package com.service;

import com.ResObj.ResCompanyAll;
import com.ResObj.ResCompanyObj;
import com.pojo.CmArea;
import com.pojo.CmCompany;
import com.tools.InputData;
import com.tools.OutputData;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    //zxl：添加公司
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
    //zxl：根据企业id查找企业信息，为修改企业信息做准备
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
    //zxl：修改公司信息
    public boolean updateCompany(int cid,String cname,String chr,String cphone,String cemail,
                                 String cinfo,String cmark,String caddress,int city){
        // CmCompany cmCompany= this.findCompByCid2(cid);
        String hsql="update CmCompany c set c.cname=?,c.chr=?,c.cphone=?,c.cemail=?,c.cinfo=?,c.cmark=?,c.caddress=?,c.cmAreaByAid.aid=?  " +
                "where c.cid=?";
        hibernateTemplate.bulkUpdate(hsql,cname,chr,cphone,cemail,cinfo,cmark,caddress,city,cid);
        //  hibernateTemplate.saveOrUpdate(cmCompany);
        return   true;
    }
    //zxl：查询所有公司
    public List<CmCompany>findAllCompany(){
        String hsql="select new com.pojo.CmCompany(c.cid,c.cname) from CmCompany c";
        List<CmCompany>data=(List<CmCompany>) hibernateTemplate.find(hsql);
        return   data;
    }
    //zxl：根据学生的id查询其所去的公司
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
    public List<CmCompany> FindALLCompany(){
        String hsql = "select new com.pojo.CmCompany(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate) " +
                "from CmCompany comp " +
                "where comp.cstate = 0";
        List<CmCompany> data = (List<CmCompany>) hibernateTemplate.find(hsql);
        int total = data.size();
        System.out.println(total);
        return data;
    }

    /**
     * 查看该公司下的在岗学生数量
     * @return
     */
    public int StuCountByCid(int cid){
        String hsql = "select count(*) from CmStudent stu inner join stu.cmIntersBySid inter inner join inter.cmRecruitByRid rec " +
                "where rec.cmCompanyByCid.cid = ? and inter.isuccess=1";
        List<?> total = hibernateTemplate.find(hsql,cid);
        System.out.println(Integer.parseInt(total.get(0).toString()));
        return Integer.parseInt(total.get(0).toString());
    }
    /**
     *  查询该公司该岗位下的学生信息
     * @return
     */
    public List<ResCompanyObj> findStuInfoByJname(int cid,int jid){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,rec.rid,s.sid,s.sno,s.sname,s.ssex,s.spro,s.sgrade,s.sclass,s.sphone,s.semail,s.scode,s.smark,s.sassess,s.sstate,s.sdetail,job.jid,job.jname,inter.iid,inter.isuccess) " +
                "from CmCompany comp " +
                "inner join comp.cmRecruitsByCid rec " +
                "inner join rec.cmIntersByRid inter " +
                "inner join rec.cmJobByJid job " +
                "inner join inter.cmStudentBySid s " +
                "where comp.cid=? and job.jid=? and inter.isuccess=1 ";
        Object[] value = {cid,jid};
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,value);
        int total = data.size();
        System.out.println(total);
        return data;
    }
    /**
     *  查询该公司下的所有学生信息
     * @return
     */
    public List<ResCompanyObj> findCompStuInfo(int cid){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.ctime,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,stu.sphone,job.jid,job.jname) " +
                "from CmCompany comp " +
                "inner join comp.cmRecruitsByCid rec " +
                "inner join rec.cmIntersByRid inter " +
                "inner join rec.cmJobByJid job " +
                "inner join inter.cmStudentBySid stu " +
                "where comp.cid=? and inter.isuccess=1 ";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,cid);
        System.out.println(data.size());
        return data;
    }
    /**
     *  按姓名查询该公司下的所有学生信息
     * @return
     */
    public List<ResCompanyObj> findCompStuInfoBySname(String sname){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.ctime,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,stu.sphone,job.jid,job.jname) " +
                "from CmCompany comp " +
                "inner join comp.cmRecruitsByCid rec " +
                "inner join rec.cmIntersByRid inter " +
                "inner join rec.cmJobByJid job " +
                "inner join inter.cmStudentBySid stu " +
                "where comp.cid=? and stu.sname=? and inter.isuccess=1 ";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,sname);
        System.out.println(data.size());
        return data;
    }
    /**
     *  按年级查询该公司下的所有学生信息
     * @return
     */
    public List<ResCompanyObj> findCompStuInfoBySgrade(int sgrade){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.ctime,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,stu.sphone,job.jid,job.jname) " +
                "from CmCompany comp " +
                "inner join comp.cmRecruitsByCid rec " +
                "inner join rec.cmIntersByRid inter " +
                "inner join rec.cmJobByJid job " +
                "inner join inter.cmStudentBySid stu " +
                "where comp.cid=? and stu.sgrade=? and inter.isuccess=1 ";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,sgrade);
        System.out.println(data.size());
        return data;
    }
    /**
     *  按岗位查询该公司下的所有学生信息
     * @return
     */
    public List<ResCompanyObj> findCompStuInfoByJname(String janme){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.ctime,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,stu.sphone,job.jid,job.jname) " +
                "from CmCompany comp " +
                "inner join comp.cmRecruitsByCid rec " +
                "inner join rec.cmIntersByRid inter " +
                "inner join rec.cmJobByJid job " +
                "inner join inter.cmStudentBySid stu " +
                "where comp.cid=? and job.jname=? and inter.isuccess=1 ";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,janme);
        System.out.println(data.size());
        return data;
    }
    /**
     * 按公司ID 查询该公司信息
     * @param cid
     * @return
     */
    public List<CmCompany> findByCompCid(int cid){
        System.out.println(cid);
        String hsql = "select new com.pojo.CmCompany(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cemail,comp.cinfo,comp.cmark,comp.caddress,comp.ctime,comp.cstate) " +
                "from CmCompany comp where comp.cid = ?";
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
    public List<CmCompany> FindByCName(String cname){
        String hsql = "select new com.pojo.CmCompany(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate) " +
                "from CmCompany comp " +
                "where comp.cname like ? and comp.cstate = 0";
        List<CmCompany> data = (List<CmCompany>) hibernateTemplate.find(hsql,"%"+cname+"%");
        return data;
    }

    /**
     * 按联系人搜索相关企业信息
     * @param chr
     * @return
     */
    public List<CmCompany> FindByCHr(String chr){
        String hsql = "select new com.pojo.CmCompany(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate) " +
                "from CmCompany comp " +
                "where comp.chr like ? and comp.cstate = 0";
        List<CmCompany> data = (List<CmCompany>) hibernateTemplate.find(hsql,"%"+chr+"%");
        return data;
    }

    /**
     * 按岗位搜索相关企业信息
     * @param jname
     * @return
     */
    public List<ResCompanyObj> FindByCJname(String jname){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cstate,rec.rid,inter.iid,inter.itime,inter.isuccess) " +
                "from CmCompany comp " +
                "inner join comp.cmRecruitsByCid rec " +
                "inner join rec.cmIntersByRid inter " +
                "where comp.jname like ?";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,"%"+jname+"%");

        return data;
    }

    /**
     * 统计公司数量
     * @return
     */
    public int CompanyCount(){
        String hsql = "select count(*) from CmCompany comp where comp.cstate = 0";
        List<?> total = hibernateTemplate.find(hsql);
        System.out.println(Integer.parseInt(total.get(0).toString()));
        return Integer.parseInt(total.get(0).toString());
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

    /*TianYu 上传excel*/
    public String uploadCompany(String path){
        InputData input = new InputData();
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            List<CmCompany>  ls = input.inputCompany(path);
            for (CmCompany cc : ls){
                session.save(cc);
            }
            session.close();
            return "导入成功！";
        } catch (IOException e) {
            return "数据格式错误！";
        } catch (Exception e) {
            return "数据读写错误！";
        }
    }

    /*TianYu 导出公司数据*/
    public String outputCompany(){
        String hsql = "select new com.ResObj.ResCompanyAll(comp.cid,comp.cname,comp.chr,comp.cphone,comp.cemail,comp.cinfo,comp.cmark,comp.caddress,comp.ctime,job.jname) from CmCompany comp inner join comp.cmRecruitsByCid rec inner join rec.cmJobByJid job where comp.cstate=0";
        List<ResCompanyAll> data = (List<ResCompanyAll>) hibernateTemplate.find(hsql);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("公司信息表");
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell = row1.createCell(0);
        row1.setHeight((short)20);
        cell.setCellValue("公司信息");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        HSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("cid");
        row2.createCell(1).setCellValue("公司名称");
        row2.createCell(2).setCellValue("HR姓名");
        row2.createCell(3).setCellValue("HR电话");
        row2.createCell(4).setCellValue("邮箱");
        row2.createCell(5).setCellValue("公司简介");
        row2.createCell(6).setCellValue("地址");
        row2.createCell(7).setCellValue("添加时间");
        row2.createCell(8).setCellValue("岗位名称");
        row2.createCell(9).setCellValue("备注");
        int rownum = 2;
        // 在sheet里创建数据
        for(ResCompanyAll es : data){
            HSSFRow row = sheet.createRow(rownum);
                row.createCell(0).setCellValue(es.getCid());
                row.createCell(1).setCellValue(es.getCname());
                row.createCell(2).setCellValue(es.getChr());
                row.createCell(3).setCellValue(es.getCphone());
                row.createCell(4).setCellValue(es.getCemail());
                row.createCell(5).setCellValue(es.getCinfo());
                row.createCell(6).setCellValue(es.getCaddress());
                row.createCell(7).setCellValue(es.getCtime());
                row.createCell(8).setCellValue(es.getJname());
                row.createCell(9).setCellValue(es.getCmark());
                rownum++;
        }
        OutputData od = new OutputData();
        String file = od.fileNameConvert(wb,"公司信息");
        return file;
    }

    /*查询该公司下所有学生信息*/
    public String outputComStu(int cid){
        String hsql = "select new com.ResObj.ResCompanyObj(comp.cid,comp.cname,comp.ctime,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,stu.sphone,job.jid,job.jname) " +
                "from CmCompany comp " +
                "inner join comp.cmRecruitsByCid rec " +
                "inner join rec.cmIntersByRid inter " +
                "inner join rec.cmJobByJid job " +
                "inner join inter.cmStudentBySid stu " +
                "where comp.cid=? and inter.isuccess=1 ";
        List<ResCompanyObj> data = (List<ResCompanyObj>) hibernateTemplate.find(hsql,cid);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("公司学生信息表");
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell = row1.createCell(0);
        row1.setHeight((short)20);
        cell.setCellValue(data.get(0).getCname()+"公司学生信息");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
        HSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("sid");
        row2.createCell(1).setCellValue("姓名");
        row2.createCell(2).setCellValue("性别");
        row2.createCell(3).setCellValue("学号");
        row2.createCell(4).setCellValue("专业");
        row2.createCell(5).setCellValue("年级");
        row2.createCell(6).setCellValue("班级");
        row2.createCell(7).setCellValue("联系方式");
        row2.createCell(8).setCellValue("邮箱");
        row2.createCell(9).setCellValue("就业岗位");
        row2.createCell(10).setCellValue("星级");
        int rownum = 2;
        for(ResCompanyObj es : data){
            HSSFRow row = sheet.createRow(rownum);
            System.out.println("ResCom___"+data.get(0).getSname()+data.get(0).getSsex());
            System.out.println(es.getSname());
                row.createCell(0).setCellValue(es.getSid());           /*此处抛空指针异常*/
                row.createCell(1).setCellValue(es.getSname());
                row.createCell(2).setCellValue(es.getSsex());
                row.createCell(3).setCellValue(es.getSno());
                row.createCell(4).setCellValue(es.getSpro());
                row.createCell(5).setCellValue(es.getSgrade());
                row.createCell(6).setCellValue(es.getSclass());
                row.createCell(7).setCellValue(es.getSphone());
                row.createCell(8).setCellValue(es.getSemail());
                row.createCell(9).setCellValue(es.getJname());
                row.createCell(10).setCellValue(es.getSmark());
                rownum++;
        }
        OutputData od = new OutputData();
        String file = od.fileNameConvert(wb,"公司学生信息");
        return file;
    }


}
