package com.service;

import com.pojo.CmUser;
import com.tools.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TianYu on 2016/10/19.
 * User业务逻辑层
 */
@Service("userService")
public class UserService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /** 添加用户 */
    public boolean addUser(CmUser user){
        hibernateTemplate.save(user);
        return true;
    }

    /** 根据用户名查找用户 */
    public CmUser findByUname(String uname){
        String hsql = "from CmUser u where u.uname = ?";
        List<?> data = hibernateTemplate.find(hsql,uname);
        if(!data.isEmpty()){
            return (CmUser) data.get(0);
        }
        return null;
    }
    //zxl：登录
    public CmUser findlogin(String uname,String upwd){
        String hsql = "from CmUser u where u.uname = ? and u.upwd = ? and u.ustate!=4";
        upwd=new MD5().getMD5String(upwd);
        List<?> data = hibernateTemplate.find(hsql,uname,upwd);
        if(!data.isEmpty()){
            return (CmUser) data.get(0);
        }
        return null;
    }
    //zxl：查询所有的用户
    public List<CmUser> findAllUser(){
        String hsql="select  new com.pojo.CmUser(u.uid,u.urname) from CmUser u where u.uid!=0 ";
        List<CmUser>data=(List<CmUser>)hibernateTemplate.find(hsql);
        return  data;
    }
    //zxl：查询所有用户详细信息
    public List<CmUser>findAllUsers(){
        String hsql="select  new com.pojo.CmUser(u.uid,u.uname,u.urname, u.upwd, u.uemail, u.uphone,u.urank) from CmUser u where u.ustate=0 and u.uid!=0 ";
        List<CmUser>data=(List<CmUser>)hibernateTemplate.find(hsql);
        return  data;
    }
    //zxl：根据id和密码进行查询
    public  CmUser  findByUidUpwd(int uid, String oldpwd){
        String hsql = "from CmUser u where u.uid = ? and u.upwd = ? and u.ustate!=4";
        oldpwd=new MD5().getMD5String(oldpwd);
        List<?> data = hibernateTemplate.find(hsql,uid,oldpwd);
        if (data.size()>0){
            return (CmUser) data.get(0);
        }
        return null;
    }
    //zxl：修改密码
    public  boolean updateUpwd(int uid, String oldpwd,String newpwd){
       CmUser cmUser=findByUidUpwd(uid,oldpwd);
        newpwd=new MD5().getMD5String(newpwd);
        if(cmUser!=null){
           String hsql="update CmUser u set u.upwd=? where u.uid=?";
            hibernateTemplate.bulkUpdate(hsql,newpwd,uid);
            return true;
        }
        return  false;
    }
    //zxl：根据用户名查询用户是否存在
     public CmUser findUser(String uname){
         String hsql="select new com.pojo.CmUser(u.uname,u.upwd) from CmUser u where u.uname=?";
         List<CmUser>data=(List<CmUser>)hibernateTemplate.find(hsql,uname);
         if (data.size()>0){
             return  data.get(0);
         }
         return null;
     }
    //zxl：修改用户信息
    public boolean  updateUser(int uid1,String urname,String uphone,String uemail){
        String hsql="update CmUser u set u.urname=?,u.uphone=?,u.uemail=?  where u.uid=?";
        hibernateTemplate.bulkUpdate(hsql,urname,uphone,uemail,uid1);
        return true;
    }
    //zxl：根据id查询
    public CmUser  findUserByUid(int uid){
        String hsql = "from CmUser u where u.uid = ?";
        List<?> data = hibernateTemplate.find(hsql,uid);
        if(!data.isEmpty()){
            return (CmUser) data.get(0);
        }
        return null;
    }
    //zxl：修改学生权限
     public  boolean updateUrank(int uid,int urank){
         String hsql="update CmUser u set u.urank=?  where u.uid=?";
         hibernateTemplate.bulkUpdate(hsql,urank,uid);
         return  true;
     }
    //zxl:查询就业生的推荐人
    public CmUser findUserByEmp(int eid){
        String hsql="select  new com.pojo.CmUser(u.uid,u.uname,u.urname, u.upwd, u.uemail, u.uphone,u.urank) " +
                "from CmEmp e inner join e.cmUserByUid u where e.eid=?";
        List<CmUser>data=(List<CmUser>) hibernateTemplate.find(hsql,eid);
        return  data.get(0);
    }


    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService us = (UserService)ac.getBean("userService") ;
        CmUser cu = us.findByUname("admin");
        System.out.print(cu.getUid()+"-----------");
    }


}
