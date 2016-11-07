package com.control;

import com.pojo.CmDirection;
import com.pojo.CmJob;
import com.pojo.CmStudent;
import com.service.DirectionService;
import com.service.JobService;
import com.service.StudentService;
import com.service.UnempServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
@Controller
@RequestMapping("/direction")
public class DirectionCtrl {
    @Autowired
    private DirectionService directionService;
    @Autowired
    private JobService jobService;
    @Autowired
    private StudentService studentService;
    //张小丽：查询所有动向
    @RequestMapping(value = "/selectAllDirection",method = RequestMethod.GET)
    public String selectAllDirection(ModelMap modelMap){
        List<CmDirection> data=directionService.findAllDirection();
        List<CmJob> data1=jobService.findAllJob();
        modelMap.put("allDirection",data);
        modelMap.put("allJob",data1);
        return  "/system/not-employed/NotEmpAdd";
    }
    //张小丽：查询所有动向
    @RequestMapping(value = "/selectAllDirection2",method = RequestMethod.GET)
    public String selectAllDirection2(int sid,ModelMap modelMap){
        CmStudent cmStudent=studentService.findStuBySid(sid);
        List<CmDirection> data=directionService.findAllDirection();
        List<CmJob> data1=jobService.findAllJob();
        modelMap.put("allDirection",data);
        modelMap.put("allJob",data1);
        modelMap.put("cmStudent",cmStudent);
        return  "/system/not-employed/NotEmpUpdate";
    }
}
