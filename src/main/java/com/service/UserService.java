package com.service;

import com.pojo.CmUser;
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

    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService us = (UserService)ac.getBean("userService") ;
        CmUser cu = us.findByUname("admin");
        System.out.print(cu.getUid()+"-----------");
    }


}
