package com.control;
import com.pojo.CmStudent;
import com.ResObj.EmpResObj;
import com.ResObj.InterResObj;
import com.pojo.CmArea;
import com.service.AreaService;
import com.service.InterService;
import com.service.StudentService;
import com.service.UnempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by w on 2016/10/26.
 */
@Controller
@RequestMapping("/student")
public class StudentCtrl {
    @Autowired
    private StudentService studentService;
    @Autowired
    private InterService interService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private UnempService unempService;


    //张小丽：根据id查询学生
    @RequestMapping(value = "/findStuBySid",method = RequestMethod.GET )
    public  String findStuBySid(int sid,ModelMap modelMap){
        CmStudent cmStudent=studentService.findStuBySid(sid);
        modelMap.addAttribute("cmStudent",cmStudent);
        return "/system/not-employed/NotEmpUpdate";
    }
    //张小丽：根据id查询学生
    @RequestMapping(value = "/findStuBySid2",method = RequestMethod.GET )
    public  String findStuBySid2(int sid,ModelMap modelMap){
        CmStudent cmStudent=studentService.findStuBySid(sid);
        modelMap.addAttribute("cmStudent",cmStudent);
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","修改成功！");
        return "/system/not-employed/NotEmpUpdate";
    }

    //获取所有学生列表——ly
    @RequestMapping(value = "/findAllStudents",method = RequestMethod.GET )
    public String findAll(ModelMap modelMap){
        List<CmStudent> studentList = studentService.findAll();
        modelMap.addAttribute("studentList",studentList);
        return "system/studentsinfo/AllStudentsList";
    }

    //按年级查找学生——ly
    @RequestMapping(value = "/findBySgrade",method = RequestMethod.GET )
    public String findBySgrade(@RequestParam("sgrade")int sgrade,ModelMap modelMap){
        List<CmStudent> studentList = studentService.findBySgrade(sgrade);
        modelMap.addAttribute("studentList",studentList);
        return "system/studentsinfo/StudentsSearch";
    }

    //按专业班级查找学生——ly
    @RequestMapping(value = "/student/findBySclass",method = RequestMethod.POST)
    public String findBySclass(@RequestParam("spro")String spro,@RequestParam("sclass")int sclass,ModelMap modelMap) throws UnsupportedEncodingException {
        List<CmStudent> studentList = studentService.findBySclass(spro,sclass);
        modelMap.addAttribute("studentList",studentList);
        return "system/studentsinfo/StudentsSearch";
    }

    //按学号查找学生——ly
    @RequestMapping(value = "/student/findBySno",method = RequestMethod.POST)
    @ResponseBody
    public CmStudent findBySno(String sno, ModelMap modelMap) throws UnsupportedEncodingException {
        CmStudent student = studentService.findBySno(sno);
        List<CmArea> areaList = areaService.findAllArea();
        modelMap.addAttribute("areaList",areaList);
        System.out.println("findBySno-----"+student);
        return student;
    }

    //按学生编号查询学生信息——ly
    @RequestMapping(value = "/findBySid",method = RequestMethod.GET )
    public String findBySid(@RequestParam("sid") int sid, ModelMap modelMap){
        boolean isemp = studentService.isEmp(sid);
        System.out.println("isemp------"+isemp);
        modelMap.addAttribute("isemp",isemp);
        if(isemp){
            EmpResObj empResObj = studentService.findEmpBySid(sid);
            modelMap.addAttribute("student", empResObj);
        }else{
            CmStudent student = studentService.findBySid(sid);
            modelMap.addAttribute("student",student);
        }
        return "system/studentsinfo/StudentInfo";
    }

    //查询学生的面试记录——ly
    @RequestMapping(value = "/student/findInterBySid",method = RequestMethod.GET )
    public String findInterBySid(@RequestParam("sid") int sid, ModelMap modelMap){
        List<InterResObj> interList = interService.findInterBySid(sid);
        modelMap.addAttribute("interList", interList);
        System.out.println("interList---"+interList);
        return "system/meeting/ThisMeetStudents";
    }

    //删除学生——ly
    @RequestMapping(value = "/student/delStudent",method = RequestMethod.GET )
    public String delStudent(@RequestParam("sid")int sid,ModelMap modelMap){
        boolean ResMsg = studentService.delStudent(sid);
        System.out.println("delStudent---"+ResMsg);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","删除成功！");
        }else{
            modelMap.addAttribute("ResMsg","删除失败！");
        }
        return "redirect:/student/findAllStudents";
    }

    //编辑前——ly
    @RequestMapping(value = "/student/updateStudentPro",method = RequestMethod.GET )
    @ResponseBody
    public CmStudent updateStudentPro(@RequestParam("sid") int sid){
        CmStudent student = studentService.findBySid(sid);
        return student;
    }

    //编辑学生信息——ly
    @RequestMapping(value = "/student/updateStudent",method = RequestMethod.POST )
    public String updateStudent(int sid,String sphone,String semail,ModelMap modelMap,RedirectAttributes attr){
        boolean ResMsg = studentService.updateStudent(sid,sphone,semail);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败!");
        }
        attr.addAttribute("sid", sid);
        return "redirect:/student/findBySid";
    }

    //编辑学生期望——ly
    @RequestMapping(value = "/student/updateExpectation",method = RequestMethod.POST )
    public String updateExpectation(int sid,String dname,String str1,String str2,ModelMap modelMap,RedirectAttributes attr){
        boolean ResMsg = false;
        if(dname.equals("考研")){
            ResMsg = unempService.updateKyExpectation(sid,str1,str2);
        }else{
            Integer jid = Integer.parseInt(str1);
            Integer uesalary = Integer.parseInt(str2);
            ResMsg = unempService.updateFkyExpectation(sid,jid,uesalary);
        }
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败!");
        }
        attr.addAttribute("sid", sid);
        return "redirect:/grade/findStudentDetail";
    }

    //编辑学生能力认定——ly
    @RequestMapping(value = "/student/updateAbility",method = RequestMethod.POST )
    public String updateAbility(int sid,int smark,String sassess,ModelMap modelMap,RedirectAttributes attr){
        boolean ResMsg = studentService.updateAbility(sid,smark,sassess);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败!");
        }
        attr.addAttribute("sid", sid);
        return "redirect:/grade/findStudentDetail";
    }

    //搜索学生——ly
    @RequestMapping(value = "/query",method = RequestMethod.POST )
    public String query(int type, String searchtext, ModelMap modelMap) throws UnsupportedEncodingException {
        System.out.println("type------"+type);
        System.out.println("searchtext------"+searchtext);
        //字符集转码
        //String search = new String(searchtext.getBytes("iso-8859-1"),"utf-8");
        List<CmStudent> studentList = new ArrayList<CmStudent> ();
        if(type==0){
            studentList = studentService.findBySname(searchtext);
            modelMap.addAttribute("studentList",studentList);
        }else if(type==1){
            studentList = studentService.findBySpro(searchtext);
            modelMap.addAttribute("studentList",studentList);
        }else{
            Integer sgrade = Integer.parseInt(searchtext);
            studentList = studentService.findBySgrade(sgrade);
            modelMap.addAttribute("studentList",studentList);
        }
        System.out.println("学生列表： "+studentList);
        return "system/studentsinfo/StudentsSearch";
    }
}
