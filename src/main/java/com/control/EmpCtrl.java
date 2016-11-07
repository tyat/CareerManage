package com.control;

import com.pojo.*;
import com.service.*;
import com.tools.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */
@Controller
@RequestMapping("/emp")
public class EmpCtrl {
    @Autowired
    private EmpService empService;
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private  StudentService studentService;
    //张小丽：添加就业生
    @RequestMapping(value = "/addEmp",method = RequestMethod.GET)
    public String addEmp(int sid, int jid, int uid, String etime, int esalary, String einfo, boolean ewq, ModelMap modelMap) throws  Exception{
        CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        CmJob  cmJob=new CmJob();
        cmJob.setJid(jid);
        CmUser cmUser=new CmUser();
        cmUser.setUid(uid);
        DateFormat df = DateFormat.getDateInstance();
        Date d = df.parse(etime);
        long da = d.getTime();
        Timestamp ts = new Timestamp(da);
        CmEmp cmEmp=new CmEmp(esalary,ts,einfo,ewq,cmStudent,cmJob,cmUser);
        boolean flag=empService.addEmp(cmEmp);
        if (flag){
            modelMap.addAttribute("state","10001");
            modelMap.addAttribute("info","添加成功！");
        }
        return "/system/employed/EmpAdd";
    }
    //张小丽：添加就业生
    @RequestMapping(value = "/updateEmp",method = RequestMethod.GET)
    public String updateEmp(int sid, int user, String etime,int estate, String eleave,String ereason,
                            int esalary, String einfo, int ewq,ModelMap modelMap) throws  Exception{
        eleave=eleave.substring(0,19);
        boolean flag=empService.updateEmp(sid,user,etime,estate,eleave,ereason,esalary,einfo,ewq);
        if (flag){
            modelMap.addAttribute("state","10001");
            modelMap.addAttribute("info","修改成功！");
        }
        return "/system/employed/EmpUpdate";
    }
    //张小丽：跳转到添加就业生页面，查询所有的用户，查询所有的岗位
    @RequestMapping(value = "/forAddEmp", method = RequestMethod.GET)
    public String forAddEmp(ModelMap modelMap){
        List<CmUser>data1=userService.findAllUser();
        List<CmJob>data2=jobService.findAllJob();
        List<CmCompany>data3=companyService.findAllCompany();
        modelMap.addAttribute("allUser",data1);
        modelMap.addAttribute("allJob",data2);
        modelMap.addAttribute("allCompany",data3);
        return  "/system/employed/EmpAdd";
    }
    //张小丽：为修改就业生信息做准备，查询出所有的公司，推荐人以及岗位
    @RequestMapping(value = "/forUpdateEmp", method = RequestMethod.GET)
    public String updateEmp(int sid,ModelMap modelMap){
        //查询所有管理员
        List<CmUser>data1=userService.findAllUser();
        //查询该同学的工作
        CmJob cmJob=jobService.findBySid(sid);
        //查询该同学就职公司
        CmCompany cmCompany=companyService.findCompanyBySid(sid);
        //查询该同学的基本信息
        CmStudent cmStudent=studentService.findStuBySid(sid);
        CmEmp cmEmp=empService.findEmpBySid(sid);
        modelMap.addAttribute("allUser",data1);
        modelMap.addAttribute("findBySid",cmJob);
        modelMap.addAttribute("findCompanyBySid",cmCompany);
        modelMap.addAttribute("findStuBySid",cmStudent);
        modelMap.addAttribute("findEmpBySid",cmEmp);
        return  "/system/employed/EmpUpdate";
    }
}
