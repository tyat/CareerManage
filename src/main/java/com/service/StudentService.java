package com.service;

/**
 * Created by w on 2016/10/26.
 */

import com.ResObj.EmpResObj;
import com.ResObj.UnempResObj;
import com.pojo.CmEmp;
import com.pojo.CmStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    //增加学生——ly
    public boolean addStudent(int sid){

        return false;
    }

    //删除学生——ly
    public boolean delStudent(int sid){
        CmStudent student = this.findBySid(sid);
        if(student!=null){
            student.setSstate(1);
            hibernateTemplate.saveOrUpdate(student);
            return true;
        }
        return false;
    }

    //编辑学生信息——ly
    public boolean updateStudent(int sid,String sphone,String semail){
        CmStudent student = this.findBySid(sid);
        if(student!=null){
            student.setSphone(sphone);
            student.setSemail(semail);
            hibernateTemplate.saveOrUpdate(student);
            return true;
        }
        return false;
    }

    //查询所有学生——ly
    public List<CmStudent> findAll(){
        String hsql = "from CmStudent s where s.sstate = 0";
        List<CmStudent> data = (List<CmStudent>) hibernateTemplate.find(hsql);
        System.out.println("所有学生数量：   "+data.size());
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按姓名模糊查询学生——ly
    public List<CmStudent> findBySname(String sname){
        String hsql = "from CmStudent s where s.sname like ? and s.sstate = 0";
        List<CmStudent> data = (List<CmStudent>) hibernateTemplate.find(hsql,"%"+sname+"%");
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按年级查询学生——ly
    public List<CmStudent> findBySgrade(int sgrade){
        String hsql = "from CmStudent s where s.sgrade = ? and s.sstate = 0";
        List<CmStudent> data = (List<CmStudent>) hibernateTemplate.find(hsql,sgrade);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按专业模糊查询学生——ly
    public List<CmStudent> findBySpro(String spro){
        String hsql = "from CmStudent s where s.spro like ? and s.sstate = 0";
        List<CmStudent> data = (List<CmStudent>) hibernateTemplate.find(hsql,"%"+spro+"%");
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按sid查询学生——ly
    public CmStudent findBySid(int sid){
        String hsql = "from CmStudent s where s.sid = ?";
        List<CmStudent> data = (List<CmStudent>) hibernateTemplate.find(hsql,sid);
        if(data.get(0)!=null){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //学生是否已就业——ly
    public boolean isEmp(int sid){
        System.out.println("sid------"+sid);
        String hsql = "from CmEmp e where e.cmStudentBySid.sid = ? and e.estate = 0";//不考虑离职情况
        List<CmEmp> empList = (List<CmEmp>) hibernateTemplate.find(hsql,sid);
        System.out.println("empList------"+empList);
        if(empList.size()>0){
            return true;
        }
        return false;
    }

    //按sid查询已就业学生——ly
    public EmpResObj findEmpBySid(int sid){
        String hsql = "select new EmpResObj(s.sid,s.sno,s.sname,s.ssex,s.spro,s.sgrade,s.sclass,s.sphone,s.semail,s.scode,s.smark,s.sassess,s.sstate,s.sdetail,e.jid,j.jname,i.isuccess,i.rid,r.cid,c.cname) " +
                "from CmStudent s " +
                "inner join s.cmEmpsBySid e " +
                "inner join e.cmJobByJid j " +
                "inner join s.cmIntersBySid i " +
                "inner join i.cmRecruitByRid r " +
                "inner join r.cmCompanyByCid c " +
                "where s.sid = ? and s.sstate = 0 and i.isuccess = 1";//面试成功且就业
        List<EmpResObj> empResObjList = (List<EmpResObj>) hibernateTemplate.find(hsql,sid);
        if(empResObjList.get(0)!=null){
            return empResObjList.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按sid查询未就业学生——ly
    public UnempResObj findUnempBySid(int sid){
        String hsql = "select new UnempResObj(u.ueid,u.uesalary,u.uetime,u.ueschool,u.uemajor,u.uesuccess,u.uestate,u.jid,j.jname,u.sid,s.sno,s.sname,s.ssex,s.spro,s.sgrade,s.sclass,s.smark,s.sassess,s.sstate,s.sdetail) " +
                "from CmUnemp u " +
                "inner join u.cmStudentBySid s " +
                "inner join u.cmJobByJid j " +
                "where u.sid = ? and u.uestate = 0";
        List<UnempResObj> stuList = (List<UnempResObj>) hibernateTemplate.find(hsql,sid);
        if(stuList.get(0)!=null){
            return stuList.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

}
