package com.control;

import com.ResObj.UnempResObj;
import com.pojo.CmGrade;
import com.pojo.CmStudent;
import com.service.GradeService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by w on 2016/10/24.
 */
@Controller
public class GradeCtrl {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;

    //显示学生的成绩统计信息——ly
    @RequestMapping(value = "/grade/findStudentDetail",method = RequestMethod.GET )
    public String findStudentDetail(@RequestParam("sid")int sid, ModelMap modelMap){
        System.out.println("执行controller------");
        /*CmStudent student = studentService.findBySid(sid);
        modelMap.addAttribute("student",student);*/
        UnempResObj unempResObj = studentService.findUnempBySid(sid);
        modelMap.addAttribute("unempResObj",unempResObj);
        int comcredit = gradeService.findComcredit(sid);
        System.out.println("必修学分————"+comcredit);
        modelMap.addAttribute("comcredit",comcredit);
        int opcredit = gradeService.findOpcredit(sid);
        System.out.println("选修学分————"+opcredit);
        modelMap.addAttribute("opcredit",opcredit);
        int allsubjects = gradeService.findByType(sid,0);
        System.out.println("总科目数————"+allsubjects);
        modelMap.addAttribute("allsubjects",allsubjects);
        int clearsubjects = gradeService.findByType(sid,1);
        System.out.println("清考科目数————"+clearsubjects);
        modelMap.addAttribute("clearsubjects",clearsubjects);
        int zxsubjects = gradeService.findByType(sid,2);
        System.out.println("中兴科目数————"+zxsubjects);
        modelMap.addAttribute("zxsubjects",zxsubjects);
        //中兴课程成绩列表
        List<CmGrade> zxreport = gradeService.findByKcm(sid);
        modelMap.addAttribute("zxreport",zxreport);
        return "system/studentsinfo/StudentDetail";
    }
}
