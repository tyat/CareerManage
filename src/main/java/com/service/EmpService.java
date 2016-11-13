package com.service;

import com.ResObj.ResEmpObj;
import com.pojo.*;
import com.tools.DateConvert;
import com.tools.InputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */
@Service("empService")
public class EmpService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private  JobService jobService;
    //张小丽：添加就业生
    public  boolean addEmp(CmCompany cmCompany, CmRecruit cmRecruit,CmInter cmInter, CmEmp cmEmp){
      hibernateTemplate.save(cmCompany);
        hibernateTemplate.save(cmRecruit);
        hibernateTemplate.save(cmInter);
        hibernateTemplate.save(cmEmp);
        String hsql="update CmUnemp ue set ue.uestate=1 where ue.cmStudentBySid.sid = ?";
        hibernateTemplate.bulkUpdate(hsql,cmEmp.getCmStudentBySid().getSid());
        return  true;
    }
    //张小丽：添加就业生
    public boolean  addEmp2(int rid,int sid,String esalary,String etime,int ewq,int uid,String einfo)throws Exception{
       CmJob cmJob=jobService.findRecruitByRid(rid);
        //CmEmp inemp=new EmpService().findEmpBySid(sid);
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
        cmEmp.setCmStudentBySid(cmStudent);
        cmEmp.setEleave(new DateConvert().SysDate());
        hibernateTemplate.save(cmEmp);
        return  true;
    }
    //张小丽：根据学生id查询该就业生的就业信息
    public  CmEmp findEmpBySid(int sid){
        System.out.println("这是一个id-----"+sid);
        String hsql="from CmEmp e where e.cmStudentBySid.sid=? and e.estate!=2";
       List<?> data= hibernateTemplate.find(hsql,sid);
        if (data.size()>0){
            return  (CmEmp) data.get(0);
        }
        return  null;
    }
    //张小丽：根据学生sno查询该就业生的就业信息
    public  CmEmp findEmpBySno(String sno){
        String hsql="from CmEmp e where e.cmStudentBySid.sno=? and e.estate!=2";
        List<?> data= hibernateTemplate.find(hsql,sno);
        if (data.size()>0){
            return  (CmEmp) data.get(0);
        }
        return  null;
    }
    //张小丽：修改学生信息
    public boolean updateEmp(int sid, int user, String etime, int esalary, String einfo, int ewq)throws  Exception{
        //CmEmp cmEmp=this.findEmpBySid(sid);
        boolean flag=true;
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
    /**
     *查询所有已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindAllEmp(){
        System.out.println("****************************");
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,user.uname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname) from CmJob job inner join job.cmRecruitsByJid rec inner join job.cmEmpsByJid emp inner join rec.cmCompanyByCid comp inner join emp.cmStudentBySid stu inner join emp.cmUserByUid user where emp.estate = 0";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql);
        return data;
    }

    /**
     * 按学生姓名查询已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindBySname(String sname){
        System.out.println(sname);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,user.uname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname) from CmJob job inner join job.cmRecruitsByJid rec inner join job.cmEmpsByJid emp inner join rec.cmCompanyByCid comp inner join emp.cmStudentBySid stu inner join emp.cmUserByUid user where stu.sname like ?";
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
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,user.uname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname) from CmJob job inner join job.cmRecruitsByJid rec inner join job.cmEmpsByJid emp inner join rec.cmCompanyByCid comp inner join emp.cmStudentBySid stu inner join emp.cmUserByUid user where job.jname like ?";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,"%"+jname+"%");
        return data;
    }
    /**
     * 按企业名称查询已就业学生信息
     * @return
     */
    public List<ResEmpObj> FindByCname(String cname){
        System.out.println(cname);
        String hsql = "select new com.ResObj.ResEmpObj(emp.eid,user.uid,stu.sid,job.jid,emp.etime,emp.esalary,emp.einfo,emp.estate,emp.ewq,emp.eleave,emp.ereason,job.jname,user.uname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,rec.rid,comp.cid,comp.cname) from CmJob job inner join job.cmRecruitsByJid rec inner join job.cmEmpsByJid emp inner join rec.cmCompanyByCid comp inner join emp.cmStudentBySid stu inner join emp.cmUserByUid user where comp.cname like ?";
        List<ResEmpObj> data = (List<ResEmpObj>) hibernateTemplate.find(hsql,"%"+cname+"%");
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
        try {
            List<CmEmp>  ls = input.inputEmp(path);
            for (CmEmp cc : ls){
                hibernateTemplate.save(cc);
                hibernateTemplate.flush();
            }
            return "导入成功！";
        } catch (IOException e) {
            return "数据格式错误！";
        }
    }
}
