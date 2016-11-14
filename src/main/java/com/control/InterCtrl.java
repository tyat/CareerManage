package com.control;

import com.ResObj.InterResObj;
import com.pojo.CmArea;
import com.service.AreaService;
import com.service.InterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * Created by w on 2016/10/26.
 */
@Controller
public class InterCtrl {
    @Autowired
    private InterService interService;
    @Autowired
    private AreaService areaService;

    //查询所有面试记录——ly
    @RequestMapping(value = "/inter/findAll",method = RequestMethod.GET )
    public String findAll(ModelMap modelMap){
        List<InterResObj> interList = interService.findAll();
        modelMap.addAttribute("interList",interList);
        return "system/meeting/AllInterviews";
    }

    //查询面试人员信息——ly
    @RequestMapping(value = "/inter/findByRid",method = RequestMethod.GET )
    public String findByRid(@RequestParam("rid")int rid, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<InterResObj> interList = interService.findByRid(rid);
        session.setAttribute("interList",interList);
        List<CmArea> areaList = areaService.findAllArea();
        session.setAttribute("areaList",areaList);
        return "system/meeting/ThisMeetStudents";
    }

    //增加面试学生——ly
    @RequestMapping(value = "/inter/addInter",method = RequestMethod.POST )
    public String addInter(int sid,int rid, int aid, String iaddress, String itype, String itime, ModelMap modelMap, RedirectAttributes attr) throws ParseException {
        boolean ResMsg = interService.addInter(sid, rid, aid, iaddress, itype, itime);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","添加成功！");
        }else{
            modelMap.addAttribute("ResMsg","添加失败！");
        }
        attr.addAttribute("rid", rid);
        return "redirect:/inter/findByRid";
    }

    //删除面试学生——ly
    @RequestMapping(value = "/inter/delInter",method = RequestMethod.GET )
    public String delInter(int iid,int rid,ModelMap modelMap,RedirectAttributes attr) throws ParseException {
        boolean ResMsg = interService.delInter(iid);
        System.out.println("delInter---"+ResMsg);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","删除成功！");
        }else{
            modelMap.addAttribute("ResMsg","删除失败！");
        }
        attr.addAttribute("rid", rid);
        return "redirect:/inter/findByRid";
    }

    //删除面试学生——ly
    @RequestMapping(value = "/inter/delInter2",method = RequestMethod.GET )
    public String delInter2(int iid,ModelMap modelMap) throws ParseException {
        boolean ResMsg = interService.delInter(iid);
        System.out.println("delInter---"+ResMsg);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","删除成功！");
        }else{
            modelMap.addAttribute("ResMsg","删除失败！");
        }
        return "redirect:/inter/findAll";
    }

    //删除面试学生——ly
    @RequestMapping(value = "/inter/delInter3",method = RequestMethod.GET )
    public String delInter3(int iid,int sid,ModelMap modelMap,RedirectAttributes attr) throws ParseException {
        boolean ResMsg = interService.delInter(iid);
        System.out.println("delInter---"+ResMsg);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","删除成功！");
        }else{
            modelMap.addAttribute("ResMsg","删除失败！");
        }
        attr.addAttribute("sid", sid);
        return "redirect:/student/findInterBySid";
    }

    //编辑前——ly
    @RequestMapping(value = "/inter/findResObjByIid",method = RequestMethod.GET )
    @ResponseBody
    public InterResObj findResObjByIid(int iid, ModelMap modelMap){
        System.out.println("findByIid-----"+iid);
        InterResObj inter = interService.findResObjByIid(iid);
        //modelMap.addAttribute("inter",inter);
        return inter;
    }

    //编辑面试学生——ly
    @RequestMapping(value = "/inter/updateInter",method = RequestMethod.POST )
    public String updateInter(int iid,int isuccess,int rid,ModelMap modelMap,RedirectAttributes attr) throws ParseException {
        boolean ResMsg = interService.updateInter(iid,isuccess);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败！");
        }
        attr.addAttribute("rid", rid);
        return "redirect:/inter/findByRid";
    }

    //编辑面试学生——ly
    @RequestMapping(value = "/inter/updateInter2",method = RequestMethod.POST )
    public String updateInter2(int iid,int isuccess,ModelMap modelMap) throws ParseException {
        boolean ResMsg = interService.updateInter(iid,isuccess);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败！");
        }
        return "redirect:/inter/findAll";
    }

    //编辑面试学生——ly
    @RequestMapping(value = "/inter/updateInter3",method = RequestMethod.POST )
    public String updateInter3(int iid,int sid,int isuccess,ModelMap modelMap,RedirectAttributes attr) throws ParseException {
        boolean ResMsg = interService.updateInter(iid,isuccess);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败！");
        }
        attr.addAttribute("sid", sid);
        return "redirect:/student/findInterBySid";
    }

    //搜索该招聘信息下的面试学生——ly
    @RequestMapping(value = "/inter/query",method = RequestMethod.POST )
    public String query(int rid,int type, String searchtext, ModelMap modelMap){
        System.out.println("type------"+type);
        System.out.println("searchtext------"+searchtext);
        List<InterResObj> interList = interService.query(rid,type,searchtext);
        modelMap.addAttribute("interList",interList);
        System.out.println("面试学生列表： "+interList);
        return "system/meeting/ThisMeetStudents";
    }

    //搜索面试学生——ly
    @RequestMapping(value = "/inter/query2",method = RequestMethod.POST )
    public String query2(int type, String searchtext, ModelMap modelMap){
        System.out.println("type------"+type);
        System.out.println("searchtext------"+searchtext);
        List<InterResObj> interList = interService.query2(type,searchtext);
        modelMap.addAttribute("interList",interList);
        System.out.println("面试学生列表： "+interList);
        return "system/meeting/InterviewSearch";
    }

}
