package com.control;
import com.pojo.CmStudent;
import com.pojo.CmUnemp;
import com.service.*;
import com.ResObj.EmpResObj;
import com.pojo.CmStudent;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    //按班级查找学生——ly
    @RequestMapping(value = "/findBySclass",method = RequestMethod.GET )
    public String findBySclass(@RequestParam("spro")String spro,ModelMap modelMap){
        List<CmStudent> studentList = studentService.findBySpro(spro);
        modelMap.addAttribute("studentList",studentList);
        return "system/studentsinfo/StudentsSearch";
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

    //删除学生——ly
    @RequestMapping(value = "/delStudent",method = RequestMethod.POST )
    public String delStudent(@RequestParam("sid")int sid,ModelMap modelMap){
        boolean ResMsg = studentService.delStudent(sid);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","删除成功！");
        }else{
            modelMap.addAttribute("ResMsg","删除失败！");
        }
        return "system/studentsinfo/studentInfo";
    }

    //编辑学生信息——ly
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST )
    public String updateStudent(@RequestParam("sid")int sid,ModelMap modelMap){
        boolean ResMsg = studentService.delStudent(sid);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败!");
        }
        return "system/studentsinfo/studentInfo";
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
            Integer sgrade = Integer.parseInt(searchtext);
            studentList = studentService.findBySgrade(sgrade);
            modelMap.addAttribute("studentList",studentList);
        }else if(type==1){
            studentList = studentService.findBySpro(searchtext);
            modelMap.addAttribute("studentList",studentList);
        }else{
            studentList = studentService.findBySname(searchtext);
            modelMap.addAttribute("studentList",studentList);
        }
        System.out.println("学生列表： "+studentList);
        return "system/studentsinfo/StudentsSearch";
    }
}
