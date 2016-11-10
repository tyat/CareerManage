package com.control;
import com.ResObj.ResUnempObj;
import com.pojo.*;
import com.service.EmpService;
import com.service.StudentService;
//import com.service.UnempServive;
import com.service.UnempService;
import com.tools.DateConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
@Controller
@RequestMapping("/unemp")
public class UnempCtrl {
    @Autowired
    private UnempService unempServive;
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
            cmUnemp=unempServive.findBySid1(cmStudent.getSid());
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
    public ModelAndView addUnEmp(int sid, int did, String jid, String uesalary, String uetime, String ueschool, String uemajor, int uesuccess, ModelMap modelMap) throws Exception{
        ModelAndView mv=new ModelAndView();
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
               mv.setViewName("redirect:/direction/selectAllDirection3");
           }
       }else{
           String ueschool0=new String(ueschool.getBytes("iso-8859-1"),"utf-8");
           String uemajor0=new String(uemajor.getBytes("iso-8859-1"),"utf-8");
           CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,ueschool0,uemajor0,uesuccess);
           boolean flag= unempServive.addUnEmp(cmUnemp);
           if (flag){
               mv.setViewName("redirect:/direction/selectAllDirection3");
           }
       }
        return mv;
    }
    //张小丽：修改未就业
    @RequestMapping(value = "/updateUnEmp",method = RequestMethod.GET)
    public ModelAndView updateUnEmp(int sid, int did, String jid, String uesalary, String uetime, String ueschool, String uemajor, int uesuccess, ModelMap modelMap) throws  Exception{
        ModelAndView mv=new ModelAndView();
        CmDirection cmDirection=new CmDirection();
        cmDirection.setDid(did);
        if (!uesalary.equals("")){
            Date date=new DateConvert().StringtoDate(uetime+" 00:00:00");
            boolean flag= unempServive.updateUnEmp(sid,did,Integer.parseInt(jid),Integer.parseInt(uesalary),date);
            if (flag){
                mv.setViewName("redirect:/direction/selectAllDirection4?sid="+sid);
            }
        }else{
            String ueschool0=new String(ueschool.getBytes("iso-8859-1"),"utf-8");
            String uemajor0=new String(uemajor.getBytes("iso-8859-1"),"utf-8");
            boolean flag= unempServive.updateUnEmp2(sid,did,ueschool0,uemajor0,uesuccess);
            if (flag){
                mv.setViewName("redirect:/direction/selectAllDirection4?sid="+sid);

            }
        }
        return mv;
    }
    //张小丽：统计未就业情况分布
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

    @RequestMapping(value = "/findAllUnemp")
    @ResponseBody
    public ModelAndView FindAllUnemp(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        List<ResUnempObj> UnempList = unempServive.FindAllUnemp();
        System.out.println(UnempList);
        modelMap.addAttribute("UnempList",UnempList);
        mv.setViewName("system/not-employed/selectAllNotEmp");
        return mv;
    }

    /**
     * 按条件搜索未就业学生信息
     * @param searchtext,searchType
     * @return
     */
    @RequestMapping(value = "/findByUnEmp")
    @ResponseBody
    public ModelAndView findByName(ModelMap modelMap, String searchtext, String searchType){
        ModelAndView mv = new ModelAndView();
        System.out.println(searchType);
        System.out.println(searchtext);
        if(searchType.equals("sclass")) {
            List<ResUnempObj> listdata = unempServive.FindBySclass(searchtext);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("jname")){
            List<ResUnempObj> listdata = unempServive.FindByJname(searchtext);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("sname")){
            List<ResUnempObj> listdata = unempServive.FindBySname(searchtext);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }
        System.out.println("返回到页面------------");
        mv.setViewName("system/not-employed/NotEmpSearch");
        return mv;
    }

    /**
     * 删除已就业学生信息
     * @param ueid
     * @return
     */
    @RequestMapping(value = "/delUnEmp")
    @ResponseBody
    public ModelAndView DelUnEmp(@RequestParam("ueid") String ueid){
        System.out.println(ueid);
        ModelAndView mv = new ModelAndView();
        Boolean isSucc = unempServive.DelUnEmp(Integer.parseInt(ueid));
        if(isSucc){
            mv.setViewName("redirect:findAllUnemp");
            return mv;
        }
        return null;
    }
}
