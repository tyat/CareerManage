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
    //张小丽：添加就业生
    @RequestMapping(value = "/addEmp",method = RequestMethod.GET)
    public String addEmp(int sid, int jid, int uid, String etime, int esalary, String ereason,String einfo, boolean ewq, ModelMap modelMap) throws  Exception{

        String ereason0=new String(ereason.getBytes("iso-8859-1"),"utf-8");
        String einfo0=new String(einfo.getBytes("iso-8859-1"),"utf-8"); CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        CmJob  cmJob=new CmJob();
        cmJob.setJid(jid);
        CmUser cmUser=new CmUser();
        cmUser.setUid(uid);
        DateFormat df = DateFormat.getDateInstance();
        Date d = df.parse(etime);
        long da = d.getTime();
        Timestamp ts = new Timestamp(da);
        CmEmp cmEmp=new CmEmp(esalary,ts,ereason0,einfo0,ewq,cmStudent,cmJob,cmUser);
        boolean flag=empService.addEmp(cmEmp);
        if (flag){
            modelMap.addAttribute("state","10001");
            modelMap.addAttribute("info","添加成功！");
        }
        return "/system/employed/EmpAdd";
    }
    //张小丽：修改就业生
    @RequestMapping(value = "/updateEmp",method = RequestMethod.GET)
    public ModelAndView updateEmp(int sid, int user, String etime,int estate, String eleave,String ereason,
                            int esalary, String einfo, int ewq,ModelMap modelMap) throws  Exception{
        ModelAndView mv=new ModelAndView();
        String ereason0=new String(ereason.getBytes("iso-8859-1"),"utf-8");
        String einfo0=new String(einfo.getBytes("iso-8859-1"),"utf-8");
       // System.out.print("这是一个时间----------"+eleave.length());
        if(eleave.length()<=10){
            eleave= eleave+" 00:00:00";
        }else {
            eleave=eleave.substring(0,19);
        }
        if(etime.length()<=10){
            etime= etime+" 00:00:00";
        }else {
            etime=etime.substring(0,19);
        }

        boolean flag=empService.updateEmp(sid,user,etime,estate,eleave,ereason0,esalary,einfo0,ewq);
        if (flag){
            mv.setViewName("redirect:/emp/forUpdateEmp2?sid="+sid);
        }
        return mv;
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
}
