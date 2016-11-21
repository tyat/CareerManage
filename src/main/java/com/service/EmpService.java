package com.service;

import com.ResObj.EmpStu;
import com.ResObj.ResEmpObj;
import com.pojo.*;
import com.tools.DateConvert;
import com.tools.InputData;
import com.tools.OutputData;
import com.tools.PageBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

/**
 * Created by Administrator on 2016/10/30.
 */
@Service("empService")
public class EmpService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private  JobService jobService;
    @Autowired
    private UnempService unempService;
    @Autowired
    private  StudentService studentService;
    //zxl：添加就业生
    public  boolean addEmp(CmCompany cmCompany, CmRecruit cmRecruit,CmInter cmInter, CmEmp cmEmp){
        hibernateTemplate.save(cmCompany);
        hibernateTemplate.save(cmRecruit);
        hibernateTemplate.save(cmInter);
        hibernateTemplate.save(cmEmp);
        String hsql="update CmUnemp ue set ue.uestate=1 where ue.cmStudentBySid.sid = ?";
        hibernateTemplate.bulkUpdate(hsql,cmEmp.getCmStudentBySid().getSid());
        return  true;
    }
    //zxl：添加就业生,该学生不存在
    public  boolean addEmpStu(CmStudent cmStudent,CmCompany cmCompany, CmRecruit cmRecruit,CmInter cmInter, CmEmp cmEmp){
        hibernateTemplate.save(cmStudent);
        hibernateTemplate.save(cmCompany);
        hibernateTemplate.save(cmRecruit);
        hibernateTemplate.save(cmInter);
        hibernateTemplate.save(cmEmp);
        String hsql="update CmUnemp ue set ue.uestate=1 where ue.cmStudentBySid.sid = ?";
        hibernateTemplate.bulkUpdate(hsql,cmEmp.getCmStudentBySid().getSid());
        return  true;
    }
    //zxl：添加就业生
    public boolean  addEmp2(int rid,int sid,String esalary,String etime,int ewq,int uid,String einfo)throws Exception{
        CmJob cmJob=jobService.findRecruitByRid(rid);
        //  CmEmp inemp=new EmpService().findEmpBySid(sid);
        CmEmp cmEmp=new CmEmp();
        cmEmp.setEtime(new DateConvert().StringtoTime(etime));
        if (ewq==1){
            cmEmp.setEwq(true);
        }else {
            cmEmp.setEwq(false);
        }
        cmEmp.setEsalary(Integer.parseInt(esalary));
        cmEmp.setEinfo(einfo);
        cmEmp.setCmJobByJid(cmJob);
        CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        CmUser cmUser=new CmUser();
        cmUser.setUid(uid);
        cmEmp.setCmStudentBySid(cmStudent);
        cmEmp.setEleave(new DateConvert().SysDate());
        cmEmp.setCmUserByUid(cmUser);
        hibernateTemplate.save(cmEmp);
        unempService.delUnEmp(sid);
        return  true;
    }
    //zxl：添加就业生
    public boolean  addEmp3(int iid,int sid,String esalary,String etime,int ewq,int uid,String einfo)throws Exception{
        CmJob cmJob=jobService.findRecruitByIid(iid);
        //  CmEmp inemp=new EmpService().findEmpBySid(sid);
        CmEmp cmEmp=new CmEmp();
        cmEmp.setEtime(new DateConvert().StringtoTime(etime));
        if (ewq==1){
            cmEmp.setEwq(true);
        }else {
            cmEmp.setEwq(false);
        }
        cmEmp.setEsalary(Integer.parseInt(esalary));
        cmEmp.setEinfo(einfo);
        cmEmp.setCmJobByJid(cmJob);
        CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        CmUser cmUser=new CmUser();
        cmUser.setUid(uid);
        cmEmp.setCmStudentBySid(cmStudent);
        cmEmp.setEleave(new DateConvert().SysDate());
        cmEmp.setCmUserByUid(cmUser);
        hibernateTemplate.save(cmEmp);
        unempService.delUnEmp(sid);
        return  true;
    }
    //zxl：根据学生id查询该就业生的就业信息
    public  CmEmp findEmpBySid(int sid){
        String hsql="from CmEmp e where e.cmStudentBySid.sid=? and e.estate!=2";
        List<?> data= hibernateTemplate.find(hsql,sid);
        if (data.size()>0){
            return  (CmEmp) data.get(0);
        }
        return  null;
    }
    //zxl：根据学生sno查询该就业生的就业信息
    public  CmEmp findEmpBySno(String sno){
        String hsql="from CmEmp e where e.cmStudentBySid.sno=? and e.estate!=2";
        List<?> data= hibernateTemplate.find(hsql,sno);
        if (data.size()>0){
            return  (CmEmp) data.get(0);
        }
        return  null;
    }
    //zxl：修改就业学生信息
    public boolean updateEmp(int sid, int user, String etime, int esalary, String einfo, int ewq)throws  Exception{
        //CmEmp cmEmp=this.findEmpBySid(sid);
        boolean flag=false;
        if (ewq==1){
            flag=true;
        }else{
            flag=false;
        }
        Timestamp timestamp=new DateConvert().StringtoTime(etime);
        String hsql="update CmEmp e set e.cmUserByUid.uid=?,e.etime=?," +
                " e.esalary=?,e.einfo=?,e.ewq=? where e.cmStudentBySid.sid=?";
        hibernateTemplate.bulkUpdate(hsql,user,timestamp,esalary,einfo,flag,sid);
        return  true;

    }
    //zxl：计算就业生数量
    public int findAllEmpCount(){
        String hsql="select  count(*) from CmEmp e inner join e.cmStudentBySid s where e.estate=0 and s.sgrade=?";
      //  int sgrade=studentService.findSgrage();
       // System.out.println("这是一个年级-----------------------"+sgrade);
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        if(month>=9){
            year=year-3;
        }else if (month<9){
            year=year-4;
        }
        List<Long>data=(List<Long>) hibernateTemplate.find(hsql,year);
        if (data.size()>0){
            return new Integer(String.valueOf(data.get(0)));
        }
        return 0;
    }
    //zxl;计算从事开发岗的人数
    public int findEmpCountByType(boolean jtype){
        String hsql="select  count(*) from CmEmp e inner join e.cmStudentBySid s inner  join e.cmJobByJid j where e.estate=0 and s.sgrade=? and j.jtype=?";
        //  int sgrade=studentService.findSgrage();
        // System.out.println("这是一个年级-----------------------"+sgrade);
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        if(month>=9){
            year=year-3;
        }else if (month<9){
            year=year-4;
        }
        List<Long>data=(List<Long>) hibernateTemplate.find(hsql,year,jtype);
        if (data.size()>0){
            return new Integer(String.valueOf(data.get(0)));
        }
        return 0;
    }

    /**
     *查询所有已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindAllEmp(final PageBean pageBean){
        final String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 " +
                "order by emp.etime desc ";
        final List<ResEmpObj> list = hibernateTemplate.execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                List list2 = session.createQuery(hsql).setFirstResult(pageBean.getStart()).setMaxResults(pageBean.getPageSize()).list();
                return list2;
            }
        });
        System.out.println(list.size());
        return list;
    }
    /**
     *查询开发岗所有已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindAllKaifaEmp(boolean jtype){
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 and job.jtype=? " +
                "order by emp.etime desc ";
        List<ResEmpObj> list = (List<ResEmpObj> )hibernateTemplate.find(hsql,jtype);
        System.out.println(list.size());
        return list;
    }
    /**
     *查询非开发岗所有已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindAllFeiKaifaEmp(boolean jtype){
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 and job.jtype=? " +
                "order by emp.etime desc ";
        List<ResEmpObj> list = (List<ResEmpObj> )hibernateTemplate.find(hsql,jtype);
        System.out.println(list.size());
        return list;
    }
    /**
     *按Cid查询该公司下所有已就业学生信息
     * @return
     */
    public List<ResEmpObj> findEmpStuByCid(int cid){
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where comp.cid=? and emp.estate=0 and inter.isuccess=1";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,cid);
        System.out.println(data.size());
        return data;
    }
    /**
     *按Jname查询该岗位下所有已就业学生信息
     * @return
     */
    public List<ResEmpObj> findEmpStuByJname(int jid){
        System.out.println(jid);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where job.jid=? and emp.estate=0 and inter.isuccess=1";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,jid);
        System.out.println(data.size());
        return data;
    }
    /**
     * 查询该年级下该班级下的就业生信息
     * @param sgrade
     * @param sclass
     * @return
     */
    public List<ResEmpObj> findEmpStuBySclass(int sgrade,int sclass){
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where  stu.sgrade=? and stu.sclass=? and emp.estate=0 and inter.isuccess=1";
        Object[] value = {sgrade,sclass};
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,value);
        System.out.println(data.size());
        return data;
    }
    /**
     * 统计就业生数量
     * @return
     */
    public int EmpCount(){
        String hsql = "select count(*) from CmEmp emp where emp.estate = 0";
        List<?> total = hibernateTemplate.find(hsql);
        System.out.println(Integer.parseInt(total.get(0).toString()));
        return Integer.parseInt(total.get(0).toString());
    }
    /**
     * 按学生姓名查询已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindBySname(String sname){
        System.out.println(sname);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 and stu.sname like ?";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,"%"+sname+"%");
        System.out.println(data);
        return data;
    }

    /**
     * 按岗位查询已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindByJname(String jname){
        System.out.println(jname);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 and job.jname like ?";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,"%"+jname+"%");
        return data;
    }
    /**
     * 按企业名称查询已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindByCname(String cname){
        System.out.println(cname);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 and comp.cname like ?";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,"%"+cname+"%");
        return data;
    }
    /**
     * 按年级查询已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindBySgrade(int sgrade){
        System.out.println(sgrade);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 and stu.sgrade = ?";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,sgrade);
        return data;
    }
    /**
     * 按时间段查询已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindByEtime(String startDate,String endDate){
        System.out.println(startDate);
        System.out.println(endDate);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,job.jtype,user.urname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname,inter.iid,inter.isuccess) " +
                "from CmStudent stu " +
                "inner join stu.cmIntersBySid inter " +
                "inner join inter.cmRecruitByRid rec " +
                "inner join rec.cmCompanyByCid comp " +
                "inner join rec.cmJobByJid job " +
                "inner join stu.cmEmpsBySid emp " +
                "inner join emp.cmUserByUid user " +
                "where emp.estate=0 and inter.isuccess=1 and TO_DAYS(emp.etime)>=TO_DAYS(?) and TO_DAYS(emp.etime)<=TO_DAYS(?)";
        Object[] value = {startDate,endDate};
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,value);
        return data;
    }
    /**
     * 删除已就业学生信息
     * @param eid
     * @return
     */
    public boolean DelEmp(Integer eid){
        System.out.println(eid);
        String hsql="update CmEmp emp set emp.estate=1 where emp.eid = ?";
        hibernateTemplate.bulkUpdate(hsql,eid);
        System.out.println("******************************");
        return true;
    }

    /*TianYu 上传excel*/
    public String uploadEmp(String path){
        InputData input = new InputData();
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            List<CmEmp>  ls = input.inputEmp(input.ConvertPath(path));
            for (CmEmp cc : ls){
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

    /*TianYu 导出就业生数据*/
    public String outputEmp() {
        String hql = "select new com.ResObj.EmpStu(cs.sid, cs.sno, cs.sname, cs.ssex, cs.sbirth, cs.spro, cs.sgrade, cs.sclass, cs.sphone, cs.semail, cs.scode, cs.smark, cs.sassess, cs.sstate, cs.sdetail, cc.cname, cj.jname, ce.etime, ce.esalary, ce.ewq, ce.eleave, ce.ereason) " +
                "from CmStudent cs inner join cs.cmEmpsBySid ce inner join ce.cmJobByJid cj inner join cj.cmRecruitsByJid cr inner join cr.cmCompanyByCid cc";
        List<EmpStu> ls =(List<EmpStu>)hibernateTemplate.find(hql);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("就业生信息表");
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell = row1.createCell(0);
        row1.setHeight((short)20);
        cell.setCellValue("就业生信息");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
        HSSFRow row2 = sheet.createRow(1);
        // 创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("sid");
        row2.createCell(1).setCellValue("学号");
        row2.createCell(2).setCellValue("姓名");
        row2.createCell(3).setCellValue("性别");
        row2.createCell(4).setCellValue("专业");
        row2.createCell(5).setCellValue("年级");
        row2.createCell(6).setCellValue("班级");
        row2.createCell(7).setCellValue("电话");
        row2.createCell(8).setCellValue("就业企业");
        row2.createCell(9).setCellValue("岗位名称");
        row2.createCell(10).setCellValue("实习日期");
        row2.createCell(11).setCellValue("实习补贴");
        row2.createCell(12).setCellValue("是否网签");
        row2.createCell(13).setCellValue("离职时间");
        row2.createCell(14).setCellValue("离职原因");
        int rownum = 2;
        // 在sheet里创建数据
        for(EmpStu es : ls){
            HSSFRow row = sheet.createRow(rownum);
            row.createCell(0).setCellValue(es.getSid());
            row.createCell(1).setCellValue(es.getSno());
            row.createCell(2).setCellValue(es.getSname());
            if(es.getSsex()){
                row.createCell(3).setCellValue("女");
            }else{
                row.createCell(3).setCellValue("男");
            }
            row.createCell(4).setCellValue(es.getSpro());
            row.createCell(5).setCellValue(es.getSgrade());
            row.createCell(6).setCellValue(es.getSclass());
            row.createCell(7).setCellValue(es.getSphone());
            row.createCell(8).setCellValue(es.getCname());
            row.createCell(9).setCellValue(es.getJname());
            row.createCell(10).setCellValue(es.getEtime());
            row.createCell(11).setCellValue(es.getEsalary());
            row.createCell(12).setCellValue(es.getEwq());
            row.createCell(13).setCellValue(es.getEleave());
            row.createCell(14).setCellValue(es.getEreason());
            rownum++;
        }
        OutputData od = new OutputData();
        String file = od.fileNameConvert(wb,"就业生信息");
        return file;
    }
}
