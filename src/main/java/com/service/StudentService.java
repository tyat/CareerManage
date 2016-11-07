package com.service;

import com.pojo.CmStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
@Service("studentService")
public class StudentService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //张小丽：根据学生学号查询学生信息
    public CmStudent findStuBySno(String sno){
        String hsql="select new com.pojo.CmStudent(s.sid, s.sno,s.sname,s.ssex,s.spro,s.sgrade,s.sclass) from CmStudent s where s.sno=? and s.sstate!=1 and s.sstate!=2";
        List<CmStudent>data=(List<CmStudent>) hibernateTemplate.find(hsql,sno);
        if (data.size()>0){
            CmStudent cmStudent=data.get(0);
            return  cmStudent;
        }
        return  null;
    }
    //张小丽：根据学生学号查询学生信息
    public CmStudent findStuBySid(int sid){
        String hsql="select new com.pojo.CmStudent(s.sid, s.sno,s.sname,s.ssex,s.spro,s.sgrade,s.sclass) from CmStudent s where s.sid=? and s.sstate=3";
        List<CmStudent>data=(List<CmStudent>) hibernateTemplate.find(hsql,sid);
        CmStudent cmStudent=data.get(0);
        return  cmStudent;
    }
}
