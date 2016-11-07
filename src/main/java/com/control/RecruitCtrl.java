package com.control;

import com.ResObj.RecruitResObj;
import com.service.InterService;
import com.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by w on 2016/10/31.
 */
@Controller
public class RecruitCtrl {
    @Autowired
    private RecruitService recruitService;
    @Autowired
    private InterService interService;

    //获取所有招聘信息列表——ly
    @RequestMapping(value = "/recruit/findAllRecruits",method = RequestMethod.GET )
    public String findAll(ModelMap modelMap){
        List<RecruitResObj> recruitList = recruitService.findAll();
        modelMap.addAttribute("recruitList",recruitList);
        //查询面试人数
        int InterCount = interService.findByRidCount(1);
        modelMap.addAttribute("InterCount",InterCount);
        return "system/meeting/selectAllMeeting";
    }
}
