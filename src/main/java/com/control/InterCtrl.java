package com.control;

import com.ResObj.InterResObj;
import com.ResObj.ResUnempObj;
import com.pojo.CmArea;
import com.pojo.CmUser;
import com.service.*;
import com.tools.PageBean;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
    @Autowired
    private UnempService unempService;
    @Autowired
    private EmpService empService;
    @Autowired
    private UserService userService;

    //查询所有面试记录——ly
    @RequestMapping(value = "/inter/findAll",method = RequestMethod.GET )
    public String findAll(String page,ModelMap modelMap){
        //当前页
        modelMap.addAttribute("page",page);
        //每页显示的条数
        int pageSize = 10;
        modelMap.addAttribute("pageSize",pageSize);
        //处理分页类
        PageBean pageBean = new PageBean(Integer.parseInt(page),pageSize);
        List<InterResObj> interList = interService.findAll(pageBean);
        modelMap.addAttribute("interList",interList);
        List<CmUser>userList=userService.findAllUser();
        modelMap.addAttribute("userList",userList);
        //总条数
        int totalCount = interService.findAllCount();
        modelMap.addAttribute("totalCount",totalCount);
        //总页数
        int pageCount = (totalCount % pageSize == 0)?(totalCount / pageSize):(totalCount / pageSize +1);
        modelMap.addAttribute("pageCount",pageCount);
        String pageCode = this.findAllgenPagation(totalCount, Integer.parseInt(page), pageSize);
        modelMap.put("pageCode",pageCode);
        return "system/meeting/AllInterviews";
    }

    //分页处理——ly
    private String findAllgenPagation(int totalNum, int currentPage, int pageSize){
        int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<li><a href='/inter/findAll?page=1'>首页</a></li>");
        if(currentPage==1) {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else {
            pageCode.append("<li><a href='/inter/findAll?page="+(currentPage-1)+"'>上一页</a></li>");
        }
        for(int i=currentPage-2;i<=currentPage+2;i++) {
            if(i<1||i>totalPage) {
                continue;
            }
            if(i==currentPage) {
                pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
            } else {
                pageCode.append("<li><a href='/inter/findAll?page="+i+"'>"+i+"</a></li>");
            }
        }
        if(currentPage==totalPage) {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        } else {
            pageCode.append("<li><a href='/inter/findAll?page="+(currentPage+1)+"'>下一页</a></li>");
        }
        pageCode.append("<li><a href='/inter/findAll?page="+totalPage+"'>尾页</a></li>");
        return pageCode.toString();
    }

    //查询面试人员信息——ly
    @RequestMapping(value = "/inter/findByRid",method = RequestMethod.GET )
    public String findByRid(@RequestParam("rid")int rid, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<InterResObj> interList = interService.findByRid(rid);
        session.setAttribute("interList",interList);
        List<CmArea> areaList = areaService.findAllArea();
        session.setAttribute("areaList",areaList);
        session.setAttribute("rid",rid);
        List<CmUser>userList=userService.findAllUser();
        session.setAttribute("userList",userList);
        return "system/meeting/ThisMeetStudents";
    }

    //添加面试学生前——ly
    @RequestMapping(value = "/inter/addpro",method = RequestMethod.GET )
    public String addpro(int rid,ModelMap modelMap,HttpServletRequest request){
        HttpSession session = request.getSession();
        List<ResUnempObj> unempList = unempService.FindAllUnemp();
        modelMap.addAttribute("unempList",unempList);
        System.out.println("未就业生列表： "+unempList);
        session.setAttribute("rid", rid);
        return "system/meeting/InterviewAdd";
    }

    //按姓名搜索未就业生——ly
    @RequestMapping(value = "/inter/findUnempBySname",method = RequestMethod.POST )
    public String findUnempBySname(int rid,String sname,ModelMap modelMap, RedirectAttributes attr){
        List<ResUnempObj> unempList = unempService.FindBySname(sname);
        modelMap.addAttribute("unempList",unempList);
        System.out.println("查询结果——未就业生列表： "+unempList);
        List<CmUser>userList=userService.findAllUser();
        modelMap.addAttribute("userList",userList);
        attr.addAttribute("rid", rid);
        return "system/meeting/InterviewAdd";
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

    //批量增加面试学生——ly
    @RequestMapping(value = "/inter/addInters",method = RequestMethod.POST )
    public String addInters(String sid,int rid, int aid, String iaddress, String itype, String itime, ModelMap modelMap, RedirectAttributes attr) throws ParseException {
        System.out.println("checkbox---"+sid);
        boolean ResMsg = interService.addInters(sid, rid, aid, iaddress, itype, itime);
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
    public String updateInter(int iid, int isuccess, int rid,@RequestParam(value = "isuccleave",required = false)String isuccleave, int sid, String esalary, String etime, int ewq, int uid, String einfo,
                              ModelMap modelMap, RedirectAttributes attr) throws java.lang.Exception,ParseException {
        System.out.println("isuccleave----"+isuccleave);
        System.out.println("esalary----"+esalary);
        boolean ResMsg = interService.updateInter(iid,isuccess,isuccleave);
        if(ResMsg){
            if (isuccess==1){
                boolean flag=empService.addEmp2(rid,sid,esalary,etime,ewq,uid,einfo);
                if (flag){
                    modelMap.addAttribute("addEmp2","修改成功！");
                }else{
                    modelMap.addAttribute("addEmp2","修改失败，该生已就业请先删除就业信息！");
                }

            }
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败！");
        }
        attr.addAttribute("rid", rid);
        return "redirect:/inter/findByRid";
    }

    //编辑面试学生——ly
    @RequestMapping(value = "/inter/updateInter2",method = RequestMethod.POST )
    public String updateInter2(int iid,int isuccess,@RequestParam(value = "isuccleave",required = false)String isuccleave, int sid, String esalary, String etime, int ewq, int uid, String einfo,ModelMap modelMap) throws java.lang.Exception, ParseException {
        boolean ResMsg = interService.updateInter(iid,isuccess,isuccleave);
        if(ResMsg){
            if (isuccess==1){
                boolean flag=empService.addEmp3(iid,sid,esalary,etime,ewq,uid,einfo);
                if (flag){
                    modelMap.addAttribute("addEmp2","修改成功！");
                }else{
                    modelMap.addAttribute("addEmp2","修改失败，该生已就业请先删除就业信息！");
                }

            }
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败！");
        }
        return "redirect:/inter/findAll";
    }

    //编辑面试学生——ly
    @RequestMapping(value = "/inter/updateInter3",method = RequestMethod.POST )
    public String updateInter3(int iid,int sid,int isuccess,@RequestParam(value = "isuccleave",required = false)String isuccleave, String esalary, String etime, int ewq, int uid, String einfo,ModelMap modelMap,RedirectAttributes attr)  throws java.lang.Exception,  ParseException {
        System.out.println("isuccleave---"+isuccleave);
        boolean ResMsg = interService.updateInter(iid,isuccess,isuccleave);
        if(ResMsg){
            if (isuccess==1) {
                boolean flag = empService.addEmp3(iid, sid, esalary, etime, ewq, uid, einfo);
                if (flag) {
                    modelMap.addAttribute("addEmp2", "修改成功！");
                } else {
                    modelMap.addAttribute("addEmp2", "修改失败，该生已就业请先删除就业信息！");
                }
            }
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败！");
        }
        attr.addAttribute("sid", sid);
        return "redirect:/student/findInterBySid";
    }

    //搜索该招聘信息下的面试学生——ly
    @RequestMapping(value = "/inter/query",method = RequestMethod.POST )
    public String query(int rid, int type, String searchtext, @RequestParam(value = "date",required = false) String date, ModelMap modelMap){
        System.out.println("rid------"+rid);
        System.out.println("type------"+type);
        System.out.println("searchtext------"+searchtext);
        String spl[] = searchtext.split(",");
        List<InterResObj> interList = new ArrayList<>();
        if(type==3){
            System.out.println("searchtext----"+spl[spl.length-1]);
            interList = interService.FindByDate(rid,spl[spl.length-1],date);
        }else{
            interList = interService.query(rid,type,searchtext);
        }
        modelMap.addAttribute("interList",interList);
        System.out.println("面试学生列表： "+interList);
        modelMap.addAttribute("rid",rid);
        List<CmUser>userList=userService.findAllUser();
        modelMap.addAttribute("userList",userList);
        return "system/meeting/ThisMeetStudents";
    }

    //搜索面试学生——ly
    @RequestMapping(value = "/inter/query2",method = RequestMethod.POST )
    public String query2(int type, String searchtext, @RequestParam(value = "date",required = false) String date, ModelMap modelMap){
        System.out.println("type------"+type);
        System.out.println("searchtext------"+searchtext);
        String spl[] = searchtext.split(",");
        List<InterResObj> interList = new ArrayList<>();
        if(type==3){
            System.out.println("searchtext----"+spl[spl.length-1]);
            interList = interService.FindByDate2(spl[spl.length-1],date);
        }else{
            interList = interService.query2(type,searchtext);
        }
        modelMap.addAttribute("interList",interList);
        System.out.println("面试学生列表： "+interList);
        List<CmUser>userList=userService.findAllUser();
        modelMap.addAttribute("userList",userList);
        return "system/meeting/InterviewSearch";
    }

    //今天参加面试的学生——ly
    @RequestMapping(value = "/inter/findByDay",method = RequestMethod.GET )
    public String findByDay(ModelMap modelMap){
        List<InterResObj> interList = interService.findByDay();
        modelMap.addAttribute("interList",interList);
        return "system/meeting/InterviewSearch";
    }

    /*TianYu 导出面试数据*/
    @RequestMapping(value = "/inter/outputInter")
    public ResponseEntity<byte[]> Download(HttpServletRequest httpServletRequest) throws IOException {
        File file = new File(interService.outputInter());
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = file.getName();
        httpHeaders.setContentDispositionFormData("attachment",java.net.URLEncoder.encode(fileName,"ISO-8859-1"));
        httpHeaders.setContentType(MediaType.parseMediaType("application/xls"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }

}
