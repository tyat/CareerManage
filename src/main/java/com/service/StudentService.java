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

    //编辑学生能力认定——ly
    public boolean updateAbility(int sid,int smark,String sassess){
        CmStudent student = this.findBySid(sid);
        if(student!=null){
            student.setSmark(smark);
            student.setSassess(sassess);
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

    //按专业班级查询学生——ly
    public List<CmStudent> findBySclass(String spro,int sclass){
        System.out.println("spro---"+spro);
        System.out.println("sclass---"+sclass);
        String hsql = "from CmStudent s where s.spro like ? and s.sclass = ? and s.sstate = 0";
        Object[] value = {'%'+spro+'%', sclass};
        List<CmStudent> data = (List<CmStudent>) hibernateTemplate.find(hsql,value);
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
        if(data.size()>0){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按学号查询学生——ly
    public CmStudent findBySno(String sno){
        String hsql = "from CmStudent s where s.sno = ?";
        List<CmStudent> data = (List<CmStudent>) hibernateTemplate.find(hsql,sno);
        if(data.size()>0){
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
        String hsql = "select new com.ResObj.EmpResObj(s.sid,s.sno,s.sname,s.ssex,s.spro,s.sgrade,s.sclass,s.sphone,s.semail,s.scode,s.smark,s.sassess,s.sstate,s.sdetail,e.jid,j.jname,i.isuccess,i.rid,r.cid,c.cname) " +
                "from CmStudent s " +
                "inner join s.cmEmpsBySid e " +
                "inner join e.cmJobByJid j " +
                "inner join s.cmIntersBySid i " +
                "inner join i.cmRecruitByRid r " +
                "inner join r.cmCompanyByCid c " +
                "where s.sid = ? and s.sstate = 0 and i.isuccess = 1";//面试成功且就业
        List<EmpResObj> empResObjList = (List<EmpResObj>) hibernateTemplate.find(hsql,sid);
        if(empResObjList.size()>0){
            return empResObjList.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按sid查询未就业学生——ly
    public UnempResObj findUnempBySid(int sid){
        String hsql = "select new com.ResObj.UnempResObj(u.ueid,u.uesalary,u.uetime,u.ueschool,u.uemajor,u.uesuccess,u.uestate,j.jid,j.jname,s.sid,s.sno,s.sname,s.ssex,s.spro,s.sgrade,s.sclass,s.smark,s.sassess,s.sstate,s.sdetail,d.did,d.dname,d.dstate) " +
                "from CmUnemp u " +
                "inner join u.cmStudentBySid s " +
                "inner join u.cmJobByJid j " +
                "inner join u.cmDirectionByDid d " +
                "where s.sid = ? and s.sstate = 0";
        List<UnempResObj> unempList = (List<UnempResObj>) hibernateTemplate.find(hsql,sid);
        if(unempList.size()>0){
            return unempList.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

}
