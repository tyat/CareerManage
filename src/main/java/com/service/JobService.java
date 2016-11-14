package com.service;

import com.pojo.CmJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by w on 2016/11/5.
 */
@Service("jobService")
public class JobService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    //查询所有招聘岗位——ly
    public List<CmJob> findAll(){
        String hsql = "from CmJob j where j.jstate = 0";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql);
        if(data.size()>0){
            System.out.println(data.get(0).getJname());
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按jid查询工作岗位——ly
    public CmJob findByJid(int jid) {
        String hsql = "from CmJob j where j.jid = ?";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql, jid);
        if (data.get(0) != null) {
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }
    //zxl：查询所有的岗位
    public List<CmJob> findAllJob(){
       String hsql="select new com.pojo.CmJob(j.jid,j.jname) from CmJob j";
        List<CmJob>data=(List<CmJob>) hibernateTemplate.find(hsql);
        return  data;
    }
    //zxl：根据学生id查询其就业岗位
    public  CmJob findBySid(int sid){
        String hsql="select  new com.pojo.CmJob(j.jid,j.jname) from CmInter i" +
                "  inner join i.cmRecruitByRid r " +
                "  inner  join r.cmJobByJid j " +
                " where  i.cmStudentBySid.sid=? and i.isuccess=1" +
                "  ORDER BY i.itime desc";
        List<CmJob>data=(List<CmJob>) hibernateTemplate.find(hsql,sid);
        return  data.get(0);
    }
    //zxl：根据招聘id查询招聘信息
    public CmJob findRecruitByRid(int rid){
        String hsql="select new com.pojo.CmJob(j.jid,j.jname) from CmRecruit r inner join r.cmJobByJid j where r.rid=?";
        List<CmJob>data=(List<CmJob>) hibernateTemplate.find(hsql,rid);
        return  data.get(0);
    }

    /**
     * 查询显示所有的岗位信息
     * @return
     */
    public List<CmJob> findAllJobInfo(){
        String hsql = "select new com.pojo.CmJob(job.jid,job.jname,job.jtype, job.jstate,job.jinfo) " +
                "from CmJob job where job.jstate = 0";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql);
        System.out.println(data.size());
        return data;
    }
    /**
     * 按岗位类型jtype查询该类型下所有的岗位标签
     * @return
     */
    public List<CmJob> findJobByJtype(boolean jtype){
        String hsql = "select new com.pojo.CmJob(job.jid,job.jname,job.jtype,job.jstate,job.jinfo) " +
                "from CmJob job where job.jstate = 0 and job.jtype = ?";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql,jtype);
        System.out.println(data.size());
        return data;
    }

    public List<CmJob> findJobById(int jid){
        String hsql = "select new com.pojo.CmJob(job.jid,job.jname,job.jtype, job.jstate,job.jinfo) from CmJob job where job.jstate = 0 and job.jid = ?";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql,jid);
        System.out.println(data.size());
        return data;
    }

    /**
     * 按Cid查询该公司有多少招聘岗位
     * @param cid
     * @return
     */
    public List<CmJob> findJobByCid(int cid){
        String hsql = "select new com.pojo.CmJob(job.jid,job.jname,job.jtype, job.jstate,job.jinfo) from CmRecruit rec " +
                "inner join rec.cmJobByJid job where job.jstate = 0 and rec.cmCompanyByCid.cid = ?";
        List<CmJob> data = (List<CmJob>) hibernateTemplate.find(hsql,cid);
        System.out.println(data.size());
        return data;
    }
    /**
     *添加岗位标签
     * @param cmJob
     * @return
     */
    public boolean addJob(CmJob cmJob){
        hibernateTemplate.save(cmJob);
        return true;
    }
}
