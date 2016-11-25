package com.control;

import com.ResObj.UnempResObj;
import com.pojo.*;
import com.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    private JobService jobService;
    @Autowired
    private DirectionService directionService;
    @Autowired
    private UnempService unempService;

    //显示学生的成绩统计信息——ly
    @RequestMapping(value = "/grade/findStudentDetail",method = RequestMethod.GET )
    public String findStudentDetail(@RequestParam("sid")int sid, ModelMap modelMap){
        System.out.println("findStudentDetail------");
        System.out.println("sid------"+sid);
        Boolean flag = studentService.isUnemp(sid);
        modelMap.addAttribute("isUnemp",flag);
        if(flag){
            CmUnemp unemp = unempService.findBySid(sid);
            UnempResObj unempResObj = new UnempResObj();
            if(unemp.getCmDirectionByDid().getDid()==2||unemp.getCmDirectionByDid().getDid()==5){
                unempResObj = studentService.findUnempBySid2(sid);
            }else{
                unempResObj = studentService.findUnempBySid1(sid);
            }
            modelMap.addAttribute("student",unempResObj);
        }else{
            CmStudent student = studentService.findBySid(sid);
            modelMap.addAttribute("student",student);
            List<CmDirection> allDirection = directionService.findAllDirection();
            modelMap.addAttribute("allDirection",allDirection);
        }
        List<CmJob> jobList = jobService.findAll();
        modelMap.addAttribute("jobList",jobList);
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

    /*TianYu 成绩数据导入*/
    @RequestMapping(value = "/grade/inputGrade")
    public String inputGrade(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws Exception {
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
        msg = gradeService.uploadGrade(path+"\\"+fileName);
        model.addAttribute("file", msg);
        System.out.println(msg);
        return "/system/admin/inputData";
    }

    /*TianYu 成绩数据导出*/
    @RequestMapping(value = "/grade/outgrade",method = RequestMethod.GET )
    public ResponseEntity<byte[]> Download(@RequestParam("sid")int sid,HttpServletRequest httpServletRequest) throws IOException {
        System.out.println("outCtrl--------"+sid);
        File file = new File(gradeService.outputGrade(sid));
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = file.getName();
        httpHeaders.setContentDispositionFormData("attachment",java.net.URLEncoder.encode(fileName,"ISO-8859-1"));
        httpHeaders.setContentType(MediaType.parseMediaType("application/xls"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }
}
