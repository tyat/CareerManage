package com.control;

import com.ResObj.InterResObj;
import com.service.InterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by w on 2016/10/26.
 */
@Controller
public class InterCtrl {
    @Autowired
    private InterService interService;

    //查询面试人员信息——ly
    @RequestMapping(value = "/inter/findByRid",method = RequestMethod.GET )
    public String findByRid(@RequestParam("rid")int rid, ModelMap modelMap){
        List<InterResObj> interList = interService.findByRid(rid);
        modelMap.addAttribute("interList",interList);
        return "system/meeting/ThisMeetStudents";
    }

    //搜索面试学生——ly
    @RequestMapping(value = "/inter/query",method = RequestMethod.POST )
    public String query(int rid,int type, String searchtext, ModelMap modelMap){
        System.out.println("type------"+type);
        System.out.println("searchtext------"+searchtext);
        List<InterResObj> interList = interService.query(rid,type,searchtext);
        modelMap.addAttribute("interList",interList);
        System.out.println("面试学生列表： "+interList);
        return "system/meeting/MeetSearch";
    }

}
