package com.control;

import com.pojo.CmUser;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TianYu on 2016/10/19.
 * User控制层
 */
@Controller
@RequestMapping("/user")
public class UserCtrl {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/register",method =RequestMethod.GET )
    @ResponseBody
    public Map<String,String> register(String uname, String urname,String upwd, String uemail,String uphone,int urank,int ustate){
        CmUser myUsers = new CmUser(uname,urname,upwd,uemail,uphone,urank,ustate);
        userService.addUser(myUsers);
        Map<String,String> data = new HashMap<String,String>();
        data.put("state","1");
        data.put("info","插入成功!");
        return data;
    }
    //张小丽：登陆
    @RequestMapping(value = "/login",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> login(String uname,String upwd){
        CmUser myUsers = new CmUser(uname,upwd);
        CmUser cmUser=userService.findlogin(uname,upwd);
        Map<String,Object> data = new HashMap<String,Object>();
        if (cmUser!=null){
            data.put("state","1000");
            data.put("info","登陆成功!");
            data.put("loginUser",cmUser);
        }else{
            data.put("state","1001");
            data.put("info","登陆失败!");

        }
        return data;
    }

}
