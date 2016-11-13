package com.control;

import com.ResObj.ResEmpObj;
import com.pojo.*;
import com.service.*;
import com.tools.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    @Autowired
    private AreaService areaService;
    //张小丽：添加就业生
    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    public ModelAndView addEmp(int sid,String cname,String chr,String cphone,int city,String caddress,String cemail,
                         String etime,String itime,int Icity,String iaddress,String itype,int job,int Jcity,
                         int rsalary,int ewq,String einfo,String cinfo,String cmark, int add_time,int add_time2,ModelMap modelMap) throws  Exception{
      System.out.print("zheshiyige9999999999999999-----------"+add_time+"---------------"+add_time2);
        ModelAndView mv=new ModelAndView();
        CmArea cmArea1=new CmArea();
        cmArea1.setAid(city);
        CmCompany  cmCompany=new CmCompany(cname,chr,cphone,cemail,cinfo,cmark,caddress,cmArea1,new DateConvert().convert());
        CmRecruit cmRecruit=new CmRecruit();
        CmArea  cmArea2=new CmArea();
        cmArea2.setAid(Jcity);
        cmRecruit.setCmAreaByAid(cmArea2);
        cmRecruit.setCmCompanyByCid(cmCompany);
        cmRecruit.setRstart(new DateConvert().convert());
        cmRecruit.setRend(new DateConvert().convert());
        CmJob  cmJob=new CmJob();
        cmJob.setJid(job);
        cmRecruit.setCmJobByJid(cmJob);
        cmRecruit.setRinfo("");
        cmRecruit.setRnum(0);
        cmRecruit.setRsalary(rsalary);
        CmInter cmInter=new CmInter();
        CmArea cmArea3=new CmArea();
        cmArea3.setAid(Icity);
        cmInter.setCmAreaByAid(cmArea3);
        cmInter.setCmRecruitByRid(cmRecruit);
        CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        cmInter.setCmStudentBySid(cmStudent);
        cmInter.setIaddress(iaddress);
        cmInter.setIsuccess(1);
        String sadd_time=add_time+"";
        String sadd_time2=add_time2*5+"";
        if (add_time<10){
            sadd_time="0"+sadd_time;
        }
        if (add_time2*5<10){
            sadd_time2="0"+sadd_time2;
        }
        String []dates=itime.split("-");
        for (int i=0;i<dates.length;i++){
            if (Integer.parseInt(dates[i])<10){
                dates[i]="0"+dates[i];
            }
        }
        itime=dates[0]+"-"+dates[1]+"-"+dates[2];
      //  System.out.println("这是一个时间xxx------------"+itime+" " +sadd_time+":"+sadd_time2+";00");
        cmInter.setItime(new DateConvert().StringtoTime2(itime+" " +sadd_time+":"+sadd_time2+":00"));
        cmInter.setItype(itype);
        CmEmp cmEmp=new CmEmp();
        CmUser cmUser=new CmUser();
        cmUser.setUid(0);
        cmEmp.setCmUserByUid(cmUser);
        cmEmp.setCmJobByJid(cmJob);
        cmEmp.setCmStudentBySid(cmStudent);
        cmEmp.setEtime(new DateConvert().StringtoTime(etime));
        cmEmp.setEinfo(einfo);
        cmEmp.setEsalary(rsalary);
        cmEmp.setEleave(new DateConvert().SysDate());
        boolean flag=false;
        if (ewq==1){
            flag=true;
        }
        cmEmp.setEwq(flag);
        boolean flag1=empService.addEmp(cmCompany,cmRecruit,cmInter,cmEmp);
        if (flag){
            mv.setViewName("redirect:/emp/forAddEmp2");
        }
        return mv;
    }
    //张小丽：修改就业生
    @RequestMapping(value = "/updateEmp",method = RequestMethod.POST)
    public ModelAndView updateEmp(int sid, int user, String etime,
                            int esalary, String einfo, int ewq,ModelMap modelMap) throws  Exception{
        ModelAndView mv=new ModelAndView();
        boolean flag=empService.updateEmp(sid,user,etime,esalary,einfo,ewq);
        if (flag){
            mv.setViewName("redirect:/emp/forUpdateEmp2?sid="+sid);
        }
        return mv;
    }
    //张小丽：为添加自己找工作的就业生准备forAddEmp
    @RequestMapping(value = "/forAddEmp", method = RequestMethod.GET)
    public String forAddEmp(ModelMap modelMap){
        List<CmJob>data=jobService.findAllJob();
        modelMap.addAttribute("allJob",data);
        List<CmArea>data1=areaService.findAllArea();
        modelMap.addAttribute("allAreaList",data1);

        return  "/system/employed/EmpAdd";
    }
    //张小丽：为添加自己找工作的就业生准备forAddEmp
    @RequestMapping(value = "/forAddEmp2", method = RequestMethod.GET)
    public String forAddEmp2(ModelMap modelMap){
        List<CmJob>data=jobService.findAllJob();
        modelMap.addAttribute("allJob",data);
        List<CmArea>data1=areaService.findAllArea();
        modelMap.addAttribute("allAreaList",data1);
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","添加成功！");
        return  "/system/employed/EmpAdd";
    }




    //张小丽：为修改就业生信息做准备，查询出所有的公司，推荐人以及岗位
    @RequestMapping(value = "/forUpdateEmp", method = RequestMethod.GET)
    public String updateEmp(int sid,ModelMap modelMap) throws Exception{
        //查询所有管理员
        List<CmUser>data1=userService.findAllUser();
        //查询该同学的工作
        CmJob cmJob=jobService.findBySid(sid);
        //查询该同学就职公司
        CmCompany cmCompany=companyService.findCompanyBySid(sid);
        //查询该同学的基本信息
        CmStudent cmStudent=studentService.findStuBySid(sid);
        CmEmp cmEmp=empService.findEmpBySid(sid);
        //cmEmp.setEtime(new DateConvert().subDate(cmEmp.getEtime()));
        modelMap.addAttribute("allUser",data1);
        modelMap.addAttribute("findBySid",cmJob);
        modelMap.addAttribute("findCompanyBySid",cmCompany);
        modelMap.addAttribute("findStuBySid",cmStudent);
        modelMap.addAttribute("findEmpBySid",cmEmp);
        return  "/system/employed/EmpUpdate";
    }
    //张小丽：为修改就业生信息做准备，查询出所有的公司，推荐人以及岗位
    @RequestMapping(value = "/forUpdateEmp2", method = RequestMethod.GET)
    public String updateEmp2(int sid,ModelMap modelMap){
        //查询所有管理员
        List<CmUser>data1=userService.findAllUser();
        //查询该同学的工作
        CmJob cmJob=jobService.findBySid(sid);
        //查询该同学就职公司
        CmCompany cmCompany=companyService.findCompanyBySid(sid);
        //查询该同学的基本信息
        CmStudent cmStudent=studentService.findStuBySid(sid);
        CmEmp cmEmp=empService.findEmpBySid(sid);
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","修改成功！");
        modelMap.addAttribute("allUser",data1);
        modelMap.addAttribute("findBySid",cmJob);
        modelMap.addAttribute("findCompanyBySid",cmCompany);
        modelMap.addAttribute("findStuBySid",cmStudent);
        modelMap.addAttribute("findEmpBySid",cmEmp);
        return  "/system/employed/EmpUpdate";
    }

    /**
     * 查询显示所有已就业学生
     * @return
     */
    @RequestMapping(value = "/findAllEmp")
    public ModelAndView FindAllEmp(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        List<ResEmpObj> empList = empService.FindAllEmp();
        System.out.println(empList);
        modelMap.addAttribute("empList",empList);
        mv.setViewName("system/employed/selectAllEmp");
        return mv;
    }

    /**
     * 按条件搜索已就业学生信息
     * @param searchtext,searchType
     * @return
     */
    @RequestMapping(value = "/findByEmp")
    @ResponseBody
    public ModelAndView findByName(ModelMap modelMap, String searchtext, String searchType){
        ModelAndView mv = new ModelAndView();
        System.out.println(searchType);
        System.out.println(searchtext);
        if(searchType.equals("cname")) {
            List<ResEmpObj> listdata = empService.FindByCname(searchtext);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("jname")){
            List<ResEmpObj> listdata = empService.FindByJname(searchtext);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("sname")){
            List<ResEmpObj> listdata = empService.FindBySname(searchtext);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }
        System.out.println("返回到页面------------");
        mv.setViewName("system/employed/EmpSearch");
        return mv;
    }

    /**
     * 删除已就业学生信息
     * @param eid
     * @return
     */
    @RequestMapping(value = "/delEmp")
    @ResponseBody
    public ModelAndView DelEmp(@RequestParam("eid") String eid){
        System.out.println(eid);
        ModelAndView mv = new ModelAndView();
        Boolean isSucc = empService.DelEmp(Integer.parseInt(eid));
        if(isSucc){
            mv.setViewName("redirect:findAllEmp");
            return mv;
        }
        return null;
    }

    /*TianYu 就业生数据导入*/
    @RequestMapping(value = "/inputEmp")
    public String inputEmp(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String msg;
        String fileName = file.getOriginalFilename();
        System.out.println("File------------"+path+"\\"+fileName);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            msg = "文件上传失败！";
        }
        msg = empService.uploadEmp(path+"\\"+fileName);
        model.addAttribute("file", msg);
        System.out.println(msg);
        return "/system/admin/inputData";
    }

    /*TianYu 导出就业生数据*/
    @RequestMapping(value = "/outputEmp")
    public ModelAndView OutputEmp(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        String path = empService.outputEmp();
        mv.setViewName("upload/"+path);
        return mv;
    }
}
