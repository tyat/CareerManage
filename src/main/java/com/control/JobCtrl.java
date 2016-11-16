package com.control;

import com.pojo.CmJob;
import com.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
@Controller
@RequestMapping("/job")
public class JobCtrl {
    @Autowired
    private JobService jobService;

    /**
     * 查询显示所有的岗位信息
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findAllJob")
    public ModelAndView findAllJob(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        List<CmJob> dataList = jobService.findAllJobInfo();
        for(CmJob job : dataList){
            System.out.println(job.getJtype());
            //按岗位类型jtype查询该类型下所有的岗位标签
            List<CmJob> jobList = jobService.findJobByJtype(job.getJtype());
            modelMap.addAttribute("jobList",jobList);
        }
        System.out.println(dataList);
        modelMap.put("dataList",dataList);
        mv.setViewName("system/admin/gangweiInfo");
        return mv;
    }

    /**
     * 添加岗位标签
     * @param jname
     * @param jtype
     * @param jinfo
     * @return
     */
    @RequestMapping(value = "/addJob")
    @ResponseBody
    public ModelAndView addJob(String jname, Boolean jtype, String jinfo){
        ModelAndView mv = new ModelAndView();
        System.out.println(jname);
        System.out.println(jtype);
        CmJob cmJob = new CmJob(jname,jtype,jinfo);
        boolean isSucc = jobService.addJob(cmJob);
        if(isSucc) {
            mv.setViewName("redirect:/job/findAllJob");
            return mv;
        }
        return null;
    }
}
