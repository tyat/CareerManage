package com.service;

import com.ResObj.ResUnempObj;
import com.pojo.CmUnemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by w on 2016/11/10.
 */
@Service("unempService")
public class UnempService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private JobService jobService;

    //编辑非考研学生期望——ly
    public boolean updateFkyExpectation(int sid,int jid,int uesalary){
        CmUnemp unemp = this.findBySid(sid);
        if(unemp!=null){
            unemp.setCmJobByJid(jobService.findByJid(jid));
            unemp.setUesalary(uesalary);
            hibernateTemplate.saveOrUpdate(unemp);
            return true;
        }
        return false;
    }

    //编辑考研学生期望——ly
    public boolean updateKyExpectation(int sid,String ueschool,String uemajor){
        CmUnemp unemp = this.findBySid(sid);
        if(unemp!=null){
            unemp.setUeschool(ueschool);
            unemp.setUemajor(uemajor);
            hibernateTemplate.saveOrUpdate(unemp);
            return true;
        }
        return false;
    }

    //按sid查询未就业生——ly
    public CmUnemp findBySid(int sid){
        String hsql = "from CmUnemp u where u.cmStudentBySid.sid = ?";
        List<CmUnemp> data = (List<CmUnemp>) hibernateTemplate.find(hsql,sid);
        if(data.size()>0){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //张小丽：根据id查询未就业学生是否存在
    /*public CmUnemp findBySid(int sid){
        String hsql="select new com.pojo.CmUnemp(un.ueid) from CmUnemp un where un.cmStudentBySid.sid=? and un.uestate=0";
        List<?>data=hibernateTemplate.find(hsql,sid);
        if (data.size()>0){
            CmUnemp cmUnemp=(CmUnemp) data.get(0);
            return  cmUnemp;
        }
        return  null;
    }*/
    //张小丽根据学号查询未就业生是否存在
    public CmUnemp findBySno(String sno){
        String hsql="select new com.pojo.CmUnemp(un.ueid) from CmUnemp un where un.cmStudentBySid.sno=? and un.uestate=0";
        List<?>data=hibernateTemplate.find(hsql,sno);
        if (data.size()>0){
            CmUnemp cmUnemp=(CmUnemp) data.get(0);
            return  cmUnemp;
        }
        return  null;
    }
    //张小丽：添加未就业生
    public boolean addUnEmp(CmUnemp cmUnemp){
        hibernateTemplate.save(cmUnemp);
        return true;
    }
    //张小丽：根据id查询为就业学生信息
    public CmUnemp findUnEmpBySid(int sid){
        String hsql="select un from CmUnEmp un where  un.cmStudentBySid.sid=?";
        List<CmUnemp>data= (List<CmUnemp>) hibernateTemplate.find(hsql,sid);
        return data.get(0);
    }
    //张小丽：修改未就业生信息,非考研保研人员
    public  boolean updateUnEmp(int sid, int did, int jid,int uesalary,Date date){
        String hsql="update CmUnemp un set un.cmDirectionByDid.did=?,un.cmJobByJid.jid=?,un.uesalary=?,un.uetime=?,un.ueschool=null,un.uemajor=null ,un.uesuccess=null where un.cmStudentBySid.sid=?";
        hibernateTemplate.bulkUpdate(hsql,did,jid,uesalary,date,sid);
        return  true;
    }
    //张小丽：修改未就业生信息，考研保研人员
    public  boolean updateUnEmp2(int sid, int  did,String ueschool,String uemajor,int uesuccess){
        String hsql="update CmUnemp un set un.cmDirectionByDid.did=?,un.cmJobByJid.jid=null ,un.uesalary=null ,un.uetime=null ,un.ueschool=?,un.uemajor=? ,un.uesuccess=? where un.cmStudentBySid.sid=?";
        hibernateTemplate.bulkUpdate(hsql,did,ueschool,uemajor,uesuccess,sid);
        return  true;
    }
    //张小丽：计算准备就业的有多少人
    public  int findSumNotEmp(int did){
        String hsql="select  count(*) from CmUnemp un where un.cmDirectionByDid.did=? and un.uestate=0";
        List<Long>data=(List<Long>) hibernateTemplate.find(hsql,did);
        return new Integer(String.valueOf(data.get(0)));
    }

    /**
     * 查询所有未就业学生信息
     * @return
     */
    public List<ResUnempObj> FindAllUnemp(){
        String hsql = "select new com.ResObj.ResUnempObj(unemp.ueid,stu.sid,job.jid,dir.did,unemp.uesalary,unemp.uetime,unemp.ueschool,unemp.uemajor,unemp.uesuccess,unemp.uestate,job.jname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,dir.dname) " +
                "from CmUnemp unemp " +
                "inner join unemp.cmStudentBySid stu " +
                "inner join unemp.cmJobByJid job " +
                "inner join unemp.cmDirectionByDid dir " +
                "where unemp.uestate = 0";
        List<ResUnempObj> data = (List<ResUnempObj>) hibernateTemplate.find(hsql);
        System.out.println("++++++++++++++++++++++++++++++++");
        return data;
    }

    /**
     * 按学生姓名查询未就业学生信息
     * @return
     */
    public List<ResUnempObj> FindBySname(String sname){
        System.out.println(sname);
        String hsql = "select new com.ResObj.ResUnempObj(unemp.ueid,stu.sid,job.jid,dir.did,unemp.uesalary,unemp.uetime,unemp.ueschool,unemp.uemajor,unemp.uesuccess,unemp.uestate,job.jname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,dir.dname) " +
                "from CmUnemp unemp " +
                "inner join unemp.cmStudentBySid stu inner join unemp.cmJobByJid job " +
                "inner join unemp.cmDirectionByDid dir " +
                "where stu.sname like ?";
        List<ResUnempObj> data = (List<ResUnempObj>) hibernateTemplate.find(hsql,"%"+sname+"%");
        System.out.println(data);
        return data;
    }

    /**
     * 按岗位查询未就业学生信息
     * @return
     */
    public List<ResUnempObj> FindByJname(String jname){
        System.out.println(jname);
        String hsql = "select new com.ResObj.ResUnempObj(unemp.ueid,stu.sid,job.jid,dir.did,unemp.uesalary,unemp.uetime,unemp.ueschool,unemp.uemajor,unemp.uesuccess,unemp.uestate,job.jname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,dir.dname) " +
                "from CmUnemp unemp " +
                "inner join unemp.cmStudentBySid stu inner join unemp.cmJobByJid job inner join unemp.cmDirectionByDid dir " +
                "where stu.jname like ?";
        List<ResUnempObj> data = (List<ResUnempObj>) hibernateTemplate.find(hsql,"%"+jname+"%");
        return data;
    }
    /**
     * 按班级查询已就业学生信息
     * @return
     */
    public List<ResUnempObj> FindBySclass(String sclass){
        System.out.println(sclass);
        String hsql = "select new com.ResObj.ResUnempObj(unemp.ueid,stu.sid,job.jid,dir.did,unemp.uesalary,unemp.uetime,unemp.ueschool,unemp.uemajor,unemp.uesuccess,unemp.uestate,job.jname,stu.sname,stu.ssex,stu.spro,stu.sgrade,stu.sclass,dir.dname) " +
                "from CmUnemp unemp " +
                "inner join unemp.cmStudentBySid stu inner join unemp.cmJobByJid job inner join unemp.cmDirectionByDid dir " +
                "where stu.scalss like ?";
        List<ResUnempObj> data = (List<ResUnempObj>) hibernateTemplate.find(hsql,"%"+sclass+"%");
        return data;
    }
    /**
     * 删除未就业学生信息
     * @param ueid
     * @return
     */
    public boolean DelUnEmp(Integer ueid){
        System.out.println(ueid);
        String hsql="update CmUnemp unemp set unemp.uestate=1 where unemp.ueid = ?";
        hibernateTemplate.bulkUpdate(hsql,ueid);
        System.out.println("******************************");
        return true;
    }
}
