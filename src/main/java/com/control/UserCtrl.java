package com.control;

import com.mysql.fabric.Response;
import com.pojo.CmUser;
import com.service.UserService;
import com.tools.MD5;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
        return "/login";
    }
    //zxl：添加用户
    @RequestMapping(value = "/register",method =RequestMethod.GET )
    @ResponseBody
    public Map<String,String> register(String uname, String urname,String upwd, String uemail,String uphone,int urank){
        CmUser myUsers = new CmUser(uname,urname,upwd,uemail,uphone,urank);
        userService.addUser(myUsers);
        Map<String,String> data = new HashMap<String,String>();
        data.put("state","10001");
        data.put("info","插入成功!");
        return data;
    }
    //zxl：登陆
    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public String  login(String uname, String upwd, ModelMap model,HttpServletRequest request){
        CmUser cmUser=userService.findlogin(uname,upwd);
        if (cmUser!=null){
          //  model.addAttribute("cmUser",cmUser);
            request.getSession().setAttribute("cmUser",cmUser);
            return "/index";
        }else{
            try{
                model.addAttribute("info","用户名或密码错误！");
                return "/login";
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "/login";
    }
    //zxl：查询所有用户
    @RequestMapping(value = "/selectAllUser",method =RequestMethod.GET)
    public  String selectAllUser(ModelMap modelMap){
        List<CmUser>data=userService.findAllUsers();
        modelMap.addAttribute("allUsers",data);
        return "/system/admin/Admins";
    }
    //zxl：添加用户
    @RequestMapping(value = "/addUser",method =RequestMethod.GET)
    public ModelAndView addUser(String uname, String urname, String upwd, String uemail, String uphone, int urank)throws  Exception{
        ModelAndView mv=new ModelAndView();
        String urname0=new String(urname.getBytes("iso-8859-1"),"utf-8");
        upwd=new MD5().getMD5String(upwd);
        CmUser  cmUser=new CmUser(uname,urname0,upwd,uemail,uphone,urank);
        boolean  flag=userService.addUser(cmUser);
        if (flag){
            mv.setViewName("redirect:/user/selectAllUser");
        }
        return  mv;
    }
    //zxl：修改密码
    @RequestMapping(value = "/updateUpwd",method =RequestMethod.POST)
    public  String updateUpwd(int uid, String oldpwd,String newpwd,ModelMap modelMap){
        boolean flag=userService.updateUpwd(uid,oldpwd,newpwd);
        if (flag){
            modelMap.addAttribute("info","修改成功！");
        }else{
            modelMap.addAttribute("info","修改失败！原始密码错误！");
        }
        return "/system/admin/adminInfo";
    }
    //zxl：查询该用户名是否存在
    @RequestMapping(value = "/selectUser",method =RequestMethod.GET)
    @ResponseBody
    public  String selectUser(@RequestParam(value = "key", required = true) String key){
        CmUser cmUser=userService.findUser(key);
        if (cmUser!=null){
            return "notnull";
        }
        return  "null";
    }
    //zxl：修改用户个人信息
    @RequestMapping(value = "/updateUser",method = RequestMethod.GET)
    public ModelAndView updateUser(int uid1,String urname,String uphone,String uemail) throws Exception{
        ModelAndView mv=new ModelAndView();
        String urname0=new String(urname.getBytes("iso-8859-1"),"utf-8");
        boolean  flag=userService.updateUser(uid1,urname0,uphone,uemail);
        if (flag){
            mv.setViewName("redirect:/user/findUserByUid?uid="+uid1);
        }
        return  mv;
    }
    //zxl：根据根据id查询学生
    @RequestMapping(value = "/findUserByUid",method = RequestMethod.GET)
    public  String findUserByUid(int uid,HttpServletRequest request,ModelMap modelMap){
        CmUser cmUser =userService.findUserByUid(uid);
        request.getSession().setAttribute("cmUser",cmUser);
        modelMap.addAttribute("info","修改成功！");
        return "/system/admin/adminInfo";
    }
    //zxl：修改用户权限
    @RequestMapping(value = "/updateUrank",method = RequestMethod.GET)
    public  ModelAndView updateUrank(int uid1,int urank1){
        ModelAndView mv =new ModelAndView();
        boolean flag=userService.updateUrank(uid1,urank1);
        if (flag){
            mv.setViewName("redirect:/user/selectAllUser");
        }
        return mv;
    }
    //zxl:退出
    @RequestMapping(value = "/quit",method = RequestMethod.GET)
    public String quit(HttpServletRequest request) throws  Exception{
        HttpSession  session=request.getSession();
        session.setAttribute("cmUser",null);
        return  "/login";
    }





}
