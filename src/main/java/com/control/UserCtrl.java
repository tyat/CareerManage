package com.control;

import com.mysql.fabric.Response;
import com.pojo.CmUser;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by TianYu on 2016/10/19.
 * User控制层
 */
@Controller
public class UserCtrl {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "/login";
    }
    //张小丽：添加用户
    @RequestMapping(value = "/register",method =RequestMethod.GET )
    @ResponseBody
    public Map<String,String> register(String uname, String urname,String upwd, String uemail,String uphone,int urank,int ustate){
        CmUser myUsers = new CmUser(uname,urname,upwd,uemail,uphone,urank,ustate);
        userService.addUser(myUsers);
        Map<String,String> data = new HashMap<String,String>();
        data.put("state","10001");
        data.put("info","插入成功!");
        return data;
    }
    //张小丽：登陆
    @RequestMapping(value = "/login",method =RequestMethod.GET)
    public String  login(String uname, String upwd, ModelMap model,HttpServletResponse response){
        CmUser cmUser=userService.findlogin(uname,upwd);
        if (cmUser!=null){
            model.addAttribute("cmUser",cmUser);
            return "/index";
        }else{
            try{
                model.addAttribute("info","用户名或密码错误！");
                return "/login";
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "";
    }

}
