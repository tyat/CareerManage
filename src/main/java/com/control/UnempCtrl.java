package com.control;
import com.pojo.*;
import com.service.EmpService;
import com.service.StudentService;
import com.service.UnempServive;
import com.tools.DateConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
/**
 * Created by Administrator on 2016/11/3.
 */
@Controller
@RequestMapping("/unemp")
public class UnempCtrl {
    @Autowired
    private UnempServive unempServive;
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmpService empService;
    //张小丽：用ajax查询该学生的信息，返回到前台面
    @RequestMapping(value = "/selectStuBySno", method = RequestMethod.GET)
    @ResponseBody
    public String selectStuBySno(@RequestParam(value = "key", required = true) String key){
        CmStudent cmStudent=studentService.findStuBySno(key);
        CmUnemp cmUnemp=new CmUnemp();
        CmEmp cmEmp=new CmEmp();
        if (cmStudent!=null){
            cmUnemp=unempServive.findBySid(cmStudent.getSid());
            cmEmp=empService. findEmpBySid(cmStudent.getSid());
        }else{
            cmUnemp=unempServive.findBySno(key);
            cmEmp=empService.findEmpBySno(key);
        }
        String s="";
        if (cmUnemp!=null){
            s="notnull";
        }else{
            if (cmEmp!=null){
                s="inemp";
            }else{
                if(cmStudent!=null){
                    s=cmStudent.getSid()+","+cmStudent.getSno()+","+cmStudent.getSname()+"," +cmStudent.getSsex()+
                            ","+cmStudent.getSpro()+","+cmStudent.getSgrade()+","+cmStudent.getSclass();
                    //System.out.print(s);
                }else{
                    s="null";
                }
            }

        }
        return  s;
    }


    //张小丽：添加未就业生
    @RequestMapping(value = "/addUnEmp",method = RequestMethod.GET)
    public String addUnEmp(int sid, int did, String jid, String uesalary, String uetime, String ueschool, String uemajor, int uesuccess, ModelMap modelMap) throws Exception{
        CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        CmDirection cmDirection=new CmDirection();
        cmDirection.setDid(did);
       if (!uesalary.equals("")){
           CmJob cmJob=new CmJob();
           cmJob.setJid(Integer.parseInt(jid));
           Date date=new DateConvert().StringtoDate(uetime+" 00:00:00");
           CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,cmJob,Integer.parseInt(uesalary),date);
           boolean flag= unempServive.addUnEmp(cmUnemp);
           if (flag){
               modelMap.addAttribute("state","10001");
               modelMap.addAttribute("info","添加成功！");
           }
       }else{
           String ueschool0=new String(ueschool.getBytes("iso-8859-1"),"utf-8");
           String uemajor0=new String(uemajor.getBytes("iso-8859-1"),"utf-8");
           CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,ueschool0,uemajor0,uesuccess);
           boolean flag= unempServive.addUnEmp(cmUnemp);
           if (flag){
               modelMap.addAttribute("state","10001");
               modelMap.addAttribute("info","添加成功！");
           }
       }
        return "/system/not-employed/NotEmpAdd";
    }
    @RequestMapping(value = "/updateUnEmp",method = RequestMethod.GET)
    public String updateUnEmp(int sid, int did, String jid, String uesalary, String uetime, String ueschool, String uemajor, int uesuccess, ModelMap modelMap) throws  Exception{
        CmDirection cmDirection=new CmDirection();
        cmDirection.setDid(did);
        if (!uesalary.equals("")){
            Date date=new DateConvert().StringtoDate(uetime+" 00:00:00");
            boolean flag= unempServive.updateUnEmp(sid,did,Integer.parseInt(jid),Integer.parseInt(uesalary),date);
            if (flag){
                modelMap.addAttribute("state","10001");
                modelMap.addAttribute("info","添加成功！");
            }
        }else{
            String ueschool0=new String(ueschool.getBytes("iso-8859-1"),"utf-8");
            String uemajor0=new String(uemajor.getBytes("iso-8859-1"),"utf-8");
            boolean flag= unempServive.updateUnEmp2(sid,did,ueschool0,uemajor0,uesuccess);
            if (flag){
                modelMap.addAttribute("state","10001");
                modelMap.addAttribute("info","修改成功！");
            }
        }
        return  "/system/not-employed/NotEmpUpdate";
    }
    @RequestMapping(value = "/DrawUnEmp",method = RequestMethod.GET)
    public  String DrawNotEmp(ModelMap modelMap){
        int index0=unempServive.findSumNotEmp(0);
        int index1=unempServive.findSumNotEmp(1);
        int index2=unempServive.findSumNotEmp(2);
        int index3=unempServive.findSumNotEmp(3);
        int index4=unempServive.findSumNotEmp(4);
        int index5=unempServive.findSumNotEmp(5);
        modelMap.put("index0",index0);
        modelMap.put("index1",index1);
        modelMap.put("index2",index2);
        modelMap.put("index3",index3);
        modelMap.put("index4",index4);
        modelMap.put("index5",index5);
        return "/system/not-employed/DrawNotEmp";
    }


}
