package com.service;

import com.ResObj.InterResObj;
import com.pojo.CmInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by w on 2016/10/26.
 */
@Service("interService")
public class InterService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    //增加面试学生——ly
    public boolean addInter(int iid){

        return false;
    }

    //删除面试学生——ly
    public boolean delInter(int iid){
        CmInter inter = this.findByIid(iid);
        if(inter!=null){
            inter.setIstate(1);
            hibernateTemplate.saveOrUpdate(inter);
            return true;
        }
        return false;
    }

    //编辑面试学生——ly
    public boolean updateInter(int iid,int isuccess){
        CmInter inter = this.findByIid(iid);
        if(inter!=null){
            inter.setIsuccess(isuccess);
            hibernateTemplate.saveOrUpdate(inter);
            return true;
        }
        return false;
    }

    //按不同条件查询面试学生——ly
    public List<InterResObj> query(int rid,int type,String searchtext){
        String hsql = "select new InterResObj(i.iid,i.isuccess,i.sid,s.sname,s.ssex,s.sbirth,s.spro,s.sgrade,s.sclass,s.sphone) " +
                "from CmInter i " +
                "inner join i.cmStudentBySid s "  +
                "where i.istate = 0 and i.cmRecruitByRid.rid = ? ";
        if(type==0){
            hsql = hsql + "and s.sname like ?";
        }else if(type==1){
            hsql = hsql + "and s.sgrade like ?";
        }else{
            hsql = hsql + "and s.spro like ?";
        }
        List<InterResObj> data = (List<InterResObj>)hibernateTemplate.find(hsql,rid,'%'+searchtext+'%');
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按iid查询面试学生——ly
    public CmInter findByIid(int iid){
        String hsql = "from CmInter i where i.iid = ?";
        List<CmInter> data = (List<CmInter>)hibernateTemplate.find(hsql,iid);
        if(data.get(0)!=null){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //查询面试人数——ly
    public int findByRidCount(int rid){
        String hsql = "select count(*) from CmInter i where i.istate = 0 and i.cmRecruitByRid.rid = ?";
        List<?> data = hibernateTemplate.find(hsql,rid);
        System.out.println("面试人数："+Integer.parseInt(data.get(0).toString()));
        return Integer.parseInt(data.get(0).toString());
    }

    //查询面试人员信息——ly
    public List<InterResObj> findByRid(int rid){
        String hsql = "select new InterResObj(i.iid,i.isuccess,i.sid,s.sname,s.ssex,s.sbirth,s.spro,s.sgrade,s.sclass,s.sphone) " +
                "from CmInter i " +
                "inner join i.cmStudentBySid s "  +
                "where i.istate = 0 and i.cmRecruitByRid.rid = ?";
        List<InterResObj> data = (List<InterResObj>)hibernateTemplate.find(hsql,rid);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }
}
