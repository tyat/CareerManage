package com.service;

import com.pojo.CmEmp;
import com.tools.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

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
    //张小丽：添加就业生
    public  boolean addEmp(CmEmp cmEmp){
        hibernateTemplate.update(cmEmp);
        return  true;
    }
    //张小丽：根据学生id查询该就业生的就业信息
    public  CmEmp findEmpBySid(int sid){
        String hsql="from CmEmp e where e.cmStudentBySid.sid=? and e.estate!=2";
       List<?> data= hibernateTemplate.find(hsql,sid);
        if (data.size()>0){
            return  (CmEmp) data.get(0);
        }
        return  null;
    }
    //张小丽：根据学生id查询该就业生的就业信息
    public  CmEmp findEmpBySno(String sno){
        String hsql="from CmEmp e where e.cmStudentBySid.sno=? and e.estate!=2";
        List<?> data= hibernateTemplate.find(hsql,sno);
        if (data.size()>0){
            return  (CmEmp) data.get(0);
        }
        return  null;
    }
    //张小丽：修改学生信息
    public boolean updateEmp(int sid, int user, String etime,int estate, String eleave,String ereason,
                             int esalary, String einfo, int ewq)throws  Exception{
        //CmEmp cmEmp=this.findEmpBySid(sid);
        boolean flag=true;
        if (ewq==0){
            flag=true;
        }else{
            flag=false;
        }
        Timestamp timestamp=new DateConvert().StringtoTime(etime);
        Date date=new DateConvert().StringtoDate(eleave);
        String hsql="update CmEmp e set e.cmUserByUid.uid=?,e.etime=?,e.estate=?,e.eleave=?," +
                "e.ereason=?,e.esalary=?,e.einfo=?,e.ewq=? where e.cmStudentBySid.sid=?";
        hibernateTemplate.bulkUpdate(hsql,user,timestamp,estate,date,ereason,esalary,einfo,flag,sid);
        return  true;

    }
}
