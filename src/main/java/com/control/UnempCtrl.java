package com.control;
import com.ResObj.ResUnempObj;
import com.pojo.*;
import com.service.EmpService;
import com.service.StudentService;
//import com.service.UnempServive;
import com.service.UnempService;
import com.tools.DateConvert;

import com.tools.PageBean;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
@Controller
@RequestMapping("/unemp")
public class UnempCtrl {
    @Autowired
    private UnempService unempServive;
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmpService empService;
    //zxl：用ajax查询该学生的信息，返回到前台面
    @RequestMapping(value = "/selectStuBySno", method = RequestMethod.GET)
    @ResponseBody
    public String selectStuBySno(@RequestParam(value = "key", required = true) String key){
        CmStudent cmStudent=studentService.findStuBySno(key);
        CmUnemp cmUnemp=new CmUnemp();
        CmEmp cmEmp=new CmEmp();
        if (cmStudent!=null){
            cmUnemp=unempServive.findBySid1(cmStudent.getSid());
            cmEmp=empService. findEmpBySid(cmStudent.getSid());
        }else{
            cmUnemp=unempServive.findBySno(key);
            cmEmp=empService.findEmpBySno(key);
        }
        String s="";
        if (cmUnemp!=null){
            s="notnull";
        }else{
            if (cmEmp!=null){
                s="inemp";
            }else{
                if(cmStudent!=null){
                    s=cmStudent.getSid()+","+cmStudent.getSno()+","+cmStudent.getSname()+"," +cmStudent.getSsex()+
                            ","+cmStudent.getSpro()+","+cmStudent.getSgrade()+","+cmStudent.getSclass();
                    //System.out.print(s);
                }else{
                    s="null";
                }
            }

        }
        return  s;
    }
    //zxl：添加未就业生
    @RequestMapping(value = "/addUnEmp",method = RequestMethod.POST)
    public ModelAndView addUnEmp(String sid,String sno,String addsname,int addssex,String addsbirth,String addspro,
                                 String addsgrade, String addsclass,String addscode,String addsphone,String addsemail,
                                 String addsdetail, int did, String jid, String uesalary, String uetime, String ueschool,
                                 String uemajor,int uesuccess, ModelMap modelMap) throws Exception{
        ModelAndView mv=new ModelAndView();
        if (!sid.equals("")){
            CmStudent cmStudent=new CmStudent();
            cmStudent.setSid(Integer.parseInt(sid));
            CmDirection cmDirection=new CmDirection();
            cmDirection.setDid(did);
            if (!uesalary.equals("")){
                CmJob cmJob=new CmJob();
                cmJob.setJid(Integer.parseInt(jid));
                Date date=new DateConvert().StringtoDate(uetime);
                CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,cmJob,Integer.parseInt(uesalary),date);
                boolean flag= unempServive.addUnEmp(cmUnemp);
                if (flag){
                    mv.setViewName("redirect:/direction/selectAllDirection3");
                }
            }else{
                CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,ueschool,uemajor,uesuccess);
                boolean flag= unempServive.addUnEmp(cmUnemp);
                if (flag){
                    mv.setViewName("redirect:/direction/selectAllDirection3");
                }
            }
        }else {
            //学生表操作
            boolean flagsex=false;
            if (addssex==1){
                flagsex=true;
            }else {
                flagsex=true;
            }
            CmStudent cmStudent=new CmStudent(sno,addsname,flagsex,new DateConvert().StringtoDate(addsbirth),addspro,Integer.parseInt(addsgrade),Integer.parseInt(addsclass),addsphone,addsemail,addscode,addsdetail);
            CmDirection cmDirection=new CmDirection();
            cmDirection.setDid(did);
            if (!uesalary.equals("")){
                CmJob cmJob=new CmJob();
                cmJob.setJid(Integer.parseInt(jid));
                Date date=new DateConvert().StringtoDate(uetime);
                CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,cmJob,Integer.parseInt(uesalary),date);
                boolean flag= unempServive.addUnEmp2(cmStudent,cmUnemp);
                if (flag){
                    mv.setViewName("redirect:/direction/selectAllDirection3");
                }
            }else{
                CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,ueschool,uemajor,uesuccess);
                boolean flag= unempServive.addUnEmp2(cmStudent,cmUnemp);
                if (flag){
                    mv.setViewName("redirect:/direction/selectAllDirection3");
                }
            }
        }
        return mv;
    }
    //zxl：修改未就业
    @RequestMapping(value = "/updateUnEmp",method = RequestMethod.POST)
    public ModelAndView updateUnEmp(int sid, int did, String jid, String uesalary, String uetime, String ueschool, String uemajor, int uesuccess, ModelMap modelMap) throws  Exception{
        ModelAndView mv=new ModelAndView();
        CmDirection cmDirection=new CmDirection();
        cmDirection.setDid(did);
        if (!(did==2||did==5)){
            Date date=new DateConvert().StringtoDate(uetime);
            boolean flag= unempServive.updateUnEmp(sid,did,Integer.parseInt(jid),Integer.parseInt(uesalary),date);
            if (flag){
                mv.setViewName("redirect:/direction/selectAllDirection4?sid="+sid);
                // System.out.println("这是一个傻逼的sid-----------------"+sid);
            }
        }else{
//            String ueschool0=new String(ueschool.getBytes("iso-8859-1"),"utf-8");
//            String uemajor0=new String(uemajor.getBytes("iso-8859-1"),"utf-8");
            boolean flag= unempServive.updateUnEmp2(sid,did,ueschool,uemajor,uesuccess);
            if (flag){
                mv.setViewName("redirect:/direction/selectAllDirection4?sid="+sid);

            }
        }
        return mv;
    }
    //zxl：统计未就业情况分布
    @RequestMapping(value = "/DrawUnEmp",method = RequestMethod.GET)
    public  String DrawNotEmp(ModelMap modelMap){
        int index0=unempServive.findSumNotEmp(0);
        int index1=unempServive.findSumNotEmp(1);
        int index2=unempServive.findSumNotEmp(2);
        int index3=unempServive.findSumNotEmp(3);
        int index4=unempServive.findSumNotEmp(4);
        int index5=unempServive.findSumNotEmp(5);
        modelMap.put("index0",index0);
        modelMap.put("index1",index1);
        modelMap.put("index2",index2);
        modelMap.put("index3",index3);
        modelMap.put("index4",index4);
        modelMap.put("index5",index5);
        return "/system/not-employed/DrawNotEmp";
    }


    /**
     * 查询显示所有未就业学生
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findAllUnemp")
    public String FindAllUnemp(ModelMap modelMap,@RequestParam("page") String page){
        //每页显示的条数
        int pageSize = 5;
        PageBean pageBean = new PageBean(Integer.parseInt(page),pageSize);
        List<ResUnempObj> UnempList = unempServive.findAllUnempPage(pageBean);
        //计算未就业生总数
        int total = unempServive.UnEmpCount();
        String pageCode = this.genPagation(total, Integer.parseInt(page), pageSize);
        modelMap.addAttribute("UnempList",UnempList);
        modelMap.put("pageCode",pageCode);
        return "system/not-employed/selectAllNotEmp";
    }

    /**
     * 查询该年级下该班级下所有未就业学生信息
     * @param sgrade
     * @param sclass
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findUnEmpStuBySclass")
    public String findUnEmpStuBySclass(String sgrade,String sclass,ModelMap modelMap){
        List<ResUnempObj> dataList = unempServive.findUnempBySclass(Integer.parseInt(sgrade),Integer.parseInt(sclass));
        modelMap.addAttribute("dataList",dataList);
        return "/system/not-employed/SgradeSclassUnemp";
    }
    /**
     * 按条件搜索未就业学生信息
     * @param searchtext,searchType
     * @return
     */
    @RequestMapping(value = "/findByUnEmp")
    @ResponseBody
    public ModelAndView findByName(ModelMap modelMap, String searchtext, String searchType) throws Exception{
        ModelAndView mv = new ModelAndView();
        System.out.println(searchType);
        System.out.println(searchtext);
        String searchtext0=new String(searchtext.getBytes("iso-8859-1"),"utf-8");
        if(searchType.equals("sgrade")) {
            List<ResUnempObj> listdata = unempServive.FindBySgrade(Integer.parseInt(searchtext0));
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("sname")){
            List<ResUnempObj> listdata = unempServive.FindBySname(searchtext0);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("dname")){
            List<ResUnempObj> listdata = unempServive.FindByDname(searchtext0);
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }
        System.out.println("返回到页面------------");
        mv.setViewName("system/not-employed/NotEmpSearch");
        return mv;
    }

    /**
     * 删除已就业学生信息
     * @param ueid
     * @return
     */
    @RequestMapping(value = "/delUnEmp")
    @ResponseBody
    public ModelAndView DelUnEmp(@RequestParam("ueid") String ueid){
        System.out.println(ueid);
        ModelAndView mv = new ModelAndView();
        Boolean isSucc = unempServive.DelUnEmp(Integer.parseInt(ueid));
        if(isSucc){
            mv.setViewName("redirect:findAllUnemp");
            return mv;
        }
        return null;
    }
    /**
     * 分页处理
     * @param totalNum 总页数
     * @param currentPage 当前页
     * @param pageSize 一页显示几条
     * @return
     */
    private String genPagation(int totalNum, int currentPage, int pageSize){
        int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<li><a href='/unemp/findAllUnemp?page=1'>首页</a></li>");
        if(currentPage==1) {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else {
            pageCode.append("<li><a href='/unemp/findAllUnemp?page="+(currentPage-1)+"'>上一页</a></li>");
        }
        for(int i=currentPage-2;i<=currentPage+2;i++) {
            if(i<1||i>totalPage) {
                continue;
            }
            if(i==currentPage) {
                pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
            } else {
                pageCode.append("<li><a href='/unemp/findAllUnemp?page="+i+"'>"+i+"</a></li>");
            }
        }
        if(currentPage==totalPage) {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        } else {
            pageCode.append("<li><a href='/unemp/findAllUnemp?page="+(currentPage+1)+"'>下一页</a></li>");
        }
        pageCode.append("<li><a href='/unemp/findAllUnemp?page="+totalPage+"'>尾页</a></li>");
        return pageCode.toString();
    }
    /*TianYu 未就业学生数据导入*/
    @RequestMapping(value = "/inputUnemp")
    public String inputUnemp(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
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
        msg = unempServive.uploadUnemp(path+"\\"+fileName);
        model.addAttribute("file", msg);
        System.out.println(msg);
        return "/system/admin/inputData";
    }

    /*TianYu 导出就业生数据*/
    @RequestMapping(value = "/outputUnemp")
    public ResponseEntity<byte[]> Download(HttpServletRequest httpServletRequest) throws IOException {
        File file = new File(unempServive.outUnemp());
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = file.getName();
        httpHeaders.setContentDispositionFormData("attachment",java.net.URLEncoder.encode(fileName,"ISO-8859-1"));
        httpHeaders.setContentType(MediaType.parseMediaType("application/xls"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }

    //按sid添加未就业生——ly
    @RequestMapping(value = "/addUnEmpBySid",method = RequestMethod.POST)
    public ModelAndView addUnEmpBySid(int sid,int did, String jid, String uesalary, String uetime, String ueschool,
                                 String uemajor,int uesuccess,RedirectAttributes attr) throws Exception{
        System.out.println("sid---"+sid);
        ModelAndView mv=new ModelAndView();
        CmStudent cmStudent=studentService.findBySid(sid);
        System.out.println("cmStudent---"+cmStudent.getSname());
        CmDirection cmDirection=new CmDirection();
        cmDirection.setDid(did);
        attr.addAttribute("sid", sid);
        if (!uesalary.equals("")){
            CmJob cmJob=new CmJob();
            cmJob.setJid(Integer.parseInt(jid));
            Date date=new DateConvert().StringtoDate(uetime);
            CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,cmJob,Integer.parseInt(uesalary),date);
            System.out.println("cmUnemp---"+cmUnemp.getCmStudentBySid().getSname());
            boolean flag= unempServive.addUnEmp(cmUnemp);
            if (flag){
                mv.setViewName("redirect:/grade/findStudentDetail");
            }
        }else{
            CmUnemp cmUnemp=new CmUnemp(cmStudent,cmDirection,ueschool,uemajor,uesuccess);
            boolean flag= unempServive.addUnEmp(cmUnemp);
            if (flag){
                mv.setViewName("redirect:/grade/findStudentDetail");
            }
        }
        return mv;
    }
}
