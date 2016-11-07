package com.service;

import com.pojo.CmDirection;
import com.pojo.CmJob;
import com.pojo.CmUnemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
@Service("unempService")
public class UnempServive {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //张小丽：根据id查询未就业学生是否存在
    public CmUnemp findBySid(int sid){
        String hsql="select new com.pojo.CmUnemp(un.ueid) from CmUnemp un where un.cmStudentBySid.sid=? and un.uestate=0";
        List<?>data=hibernateTemplate.find(hsql,sid);
        if (data.size()>0){
            CmUnemp cmUnemp=(CmUnemp) data.get(0);
            return  cmUnemp;
        }
        return  null;
    }
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
}
