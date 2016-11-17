package com.control;
import com.pojo.CmEmp;
import com.pojo.CmStudent;
import com.ResObj.EmpResObj;
import com.ResObj.InterResObj;
import com.pojo.CmArea;
import com.pojo.CmUnemp;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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


    //zxl：根据id查询学生
    @RequestMapping(value = "/findStuBySid",method = RequestMethod.GET )
    public  String findStuBySid(int sid,ModelMap modelMap){
        CmStudent cmStudent=studentService.findStuBySid(sid);
        modelMap.addAttribute("cmStudent",cmStudent);
        return "/system/not-employed/NotEmpUpdate";
    }
    //zxl：根据id查询学生
    @RequestMapping(value = "/findStuBySid2",method = RequestMethod.GET )
    public  String findStuBySid2(int sid,ModelMap modelMap){
        CmStudent cmStudent=studentService.findStuBySid(sid);
        modelMap.addAttribute("cmStudent",cmStudent);
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","修改成功！");
        return "/system/not-employed/NotEmpUpdate";
    }

    //zxl：用ajax查询该学生的信息，返回到前台面
    @RequestMapping(value = "/selectStudentBySno", method = RequestMethod.GET)
    @ResponseBody
    public String selectStudentBySno(@RequestParam(value = "key", required = true) String key){
        CmStudent cmStudent=studentService.findStuBySno(key);
        CmUnemp cmUnemp=new CmUnemp();
        CmEmp cmEmp=new CmEmp();
        String s="";
        if (cmStudent!=null){
            s=cmStudent.getSid()+","+cmStudent.getSno()+","+cmStudent.getSname()+"," +cmStudent.getSsex()+
                    ","+cmStudent.getSpro()+","+cmStudent.getSgrade()+","+cmStudent.getSclass();
        }else{
            s="null";
        }
        return  s;
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
    @RequestMapping(value = "/findBySclass",method = RequestMethod.POST)
    public String findBySclass(@RequestParam("spro")String spro,@RequestParam("sclass")int sclass,ModelMap modelMap) throws UnsupportedEncodingException {
        List<CmStudent> studentList = studentService.findBySclass(spro,sclass);
        modelMap.addAttribute("studentList",studentList);
        return "system/studentsinfo/StudentsSearch";
    }

    //按学号查找学生——ly
    @RequestMapping(value = "/findBySno",method = RequestMethod.POST)
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
        //查询面试次数
        int InterTimes = interService.findBySidTimes(sid);
        modelMap.addAttribute("InterTimes",InterTimes);
        return "system/studentsinfo/StudentInfo";
    }

    //查询学生的面试记录——ly
    @RequestMapping(value = "/findInterBySid",method = RequestMethod.GET )
    public String findInterBySid(@RequestParam("sid") int sid, ModelMap modelMap){
        List<InterResObj> interList = interService.findInterBySid(sid);
        modelMap.addAttribute("interList", interList);
        System.out.println("interList---"+interList);
        return "system/meeting/ThisStudentInterview";
    }

    //删除学生——ly
    @RequestMapping(value = "/delStudent",method = RequestMethod.GET )
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
    @RequestMapping(value = "/updateStudentPro",method = RequestMethod.GET )
    @ResponseBody
    public CmStudent updateStudentPro(@RequestParam("sid") int sid){
        CmStudent student = studentService.findBySid(sid);
        return student;
    }

    //编辑学生信息——ly
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST )
    public String updateStudent(int sid,int sgrade,int sclass,String sphone,String semail,ModelMap modelMap,RedirectAttributes attr){
        boolean ResMsg = studentService.updateStudent(sid,sgrade,sclass,sphone,semail);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败!");
        }
        attr.addAttribute("sid", sid);
        return "redirect:/student/findBySid";
    }

    //编辑学生期望——ly
    @RequestMapping(value = "/updateExpectation",method = RequestMethod.POST )
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
    @RequestMapping(value = "/updateAbility",method = RequestMethod.POST )
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

    /*TianYu 学生数据导入*/
    @RequestMapping(value = "/inputStudent")
    public String inputStudent(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
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
        msg = studentService.uploadStudent(path+"\\"+fileName);
        model.addAttribute("file", msg);
        System.out.println(msg);
        return "/system/admin/inputData";
    }
}
