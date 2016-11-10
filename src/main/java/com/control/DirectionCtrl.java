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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    //张小丽：查询所有动向
    @RequestMapping(value = "/selectAllDirection3",method = RequestMethod.GET)
    public String selectAllDirection3(ModelMap modelMap){
        List<CmDirection> data=directionService.findAllDirection();
        List<CmJob> data1=jobService.findAllJob();
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","添加成功！");
        modelMap.put("allDirection",data);
        modelMap.put("allJob",data1);
        return  "/system/not-employed/NotEmpAdd";
    }
    //张小丽：查询所有动向
    @RequestMapping(value = "/selectAllDirection4",method = RequestMethod.GET)
    public String selectAllDirection4(int sid,ModelMap modelMap){
        CmStudent cmStudent=studentService.findStuBySid(sid);
        List<CmDirection> data=directionService.findAllDirection();
        List<CmJob> data1=jobService.findAllJob();
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","修改成功！");
        modelMap.put("allDirection",data);
        modelMap.put("allJob",data1);
        modelMap.put("cmStudent",cmStudent);
        return  "/system/not-employed/NotEmpUpdate";
    }


    /**
     * 显示所有未就业学生的准备方向
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findAllDirection")
    public ModelAndView FindAllDirection(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        List<CmDirection> DirList = directionService.FindAllDirection();
        System.out.println(DirList);
        modelMap.addAttribute("DirList",DirList);
        mv.setViewName("system/admin/fangxiang");
        return mv;
    }

    /**
     * 添加未就业学生的准备方向
     * @param direction
     * @return
     */
    @RequestMapping(value = "/addDirection")
    @ResponseBody
    public ModelAndView AddDirection(String direction){
        ModelAndView mv = new ModelAndView();
        System.out.println(direction);
        CmDirection cmDirection = new CmDirection(direction);
        boolean isSucc = directionService.addDirection(cmDirection);
        if(isSucc) {
            mv.setViewName("redirect:/direction/findAllDirection");
            return mv;
        }
        return null;
    }

    /**
     *删除没有学生选择的方向
     * @param did
     * @return
     */
    @RequestMapping(value = "/delDiretion")
    @ResponseBody
    public ModelAndView DelDirection(@RequestParam("did") String did){
        ModelAndView mv = new ModelAndView();
        System.out.println(did);
        boolean isSucc = directionService.DelCmDirection(Integer.parseInt(did));
        if(isSucc) {
            mv.setViewName("redirect:/direction/findAllDirection");
            return mv;
        }
        return null;
    }
}
