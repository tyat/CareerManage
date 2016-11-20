package com.control;

import com.ResObj.ResEmpObj;
import com.pojo.*;
import com.service.*;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */
@Controller
@RequestMapping("/emp")
public class EmpCtrl {
    @Autowired
    private EmpService empService;
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private  StudentService studentService;
    @Autowired
    private AreaService areaService;
    //zxl：添加就业生
    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    //int add_time,int add_time2
    public ModelAndView addEmp(String sid,String sno,String addsname,int addssex,String addsbirth,String addspro,String addsgrade,
                               String addsclass,String addscode,String addsphone,String addsemail,String addsdetail,
                               String cname,String chr,String cphone,int city,String caddress,String cemail,
                               String etime,String itime,int Icity,String iaddress,String itype,int job,int Jcity,
                               int rsalary,int ewq,String einfo,String cinfo,String cmark,ModelMap modelMap) throws  Exception{
        ModelAndView mv=new ModelAndView();
        if (!sid.equals("")){
            //公司表操作
            CmArea cmArea1=new CmArea();
            cmArea1.setAid(city);
            CmCompany  cmCompany=new CmCompany(cname,chr,cphone,cemail,cinfo,cmark,caddress,cmArea1,new DateConvert().convert());
            //招聘表操作
            CmArea  cmArea2=new CmArea();
            cmArea2.setAid(Jcity);
            CmJob  cmJob=new CmJob();
            cmJob.setJid(job);
            //new DateConvert().convert()
            CmRecruit cmRecruit=new CmRecruit(cmArea2,cmCompany,new Date(),new Date(),cmJob,"",0,rsalary);
            //面试表操作
            CmArea cmArea3=new CmArea();
            CmStudent cmStudent=new CmStudent();
            cmStudent.setSid(Integer.parseInt(sid));
            cmArea3.setAid(Icity);
//            String sadd_time=add_time+"";
//            String sadd_time2=add_time2*5+"";
//            if (add_time<10){
//                sadd_time="0"+sadd_time;
//            }
//            if (add_time2*5<10){
//                sadd_time2="0"+sadd_time2;
//            }
            String []dates=itime.split("-");
            for (int i=0;i<dates.length;i++){
                if (Integer.parseInt(dates[i])<10){
                    dates[i]="0"+dates[i];
                }
            }
            itime=dates[0]+"-"+dates[1]+"-"+dates[2];
            //new DateConvert().StringtoUtilDate(itime+" " +sadd_time+":"+sadd_time2+":00")
            CmInter cmInter=new CmInter(cmArea3,cmRecruit,cmStudent,iaddress,1,new DateConvert().StringtoUtilDate(itime),itype);
            //就业是生表操作
            CmUser cmUser=new CmUser();
            cmUser.setUid(0);
            boolean flag=false;
            if (ewq==1){
                flag=true;
            }
            CmEmp cmEmp=new CmEmp(cmUser,cmJob,cmStudent,new DateConvert().StringtoTime(etime),einfo,rsalary,new DateConvert().SysDate(),flag);
            boolean flag1=empService.addEmp(cmCompany,cmRecruit,cmInter,cmEmp);
            if (flag){
                mv.setViewName("redirect:/emp/forAddEmp2");
            }
        }else{
            //学生表操作
            boolean flagsex=false;
            if (addssex==1){
                flagsex=true;
            }else {
                flagsex=true;
            }
            CmStudent cmStudent=new CmStudent(sno,addsname,flagsex,new DateConvert().StringtoDate(addsbirth),addspro,Integer.parseInt(addsgrade),Integer.parseInt(addsclass),addsphone,addsemail,addscode,addsdetail);
            //公司表操作
            CmArea cmArea1=new CmArea();
            cmArea1.setAid(city);
            CmCompany  cmCompany=new CmCompany(cname,chr,cphone,cemail,cinfo,cmark,caddress,cmArea1,new DateConvert().convert());
            //招聘表操作
            CmArea  cmArea2=new CmArea();
            cmArea2.setAid(Jcity);
            CmJob  cmJob=new CmJob();
            cmJob.setJid(job);
            // DateConvert().convert(),new DateConvert().convert()
            CmRecruit cmRecruit=new CmRecruit(cmArea2,cmCompany, new Date(),new Date(),cmJob,"",0,rsalary);
            //面试表操作
            CmArea cmArea3=new CmArea();
            cmArea3.setAid(Icity);
//            String sadd_time=add_time+"";
//            String sadd_time2=add_time2*5+"";
//            if (add_time<10){
//                sadd_time="0"+sadd_time;
//            }
//            if (add_time2*5<10){
//                sadd_time2="0"+sadd_time2;
//            }
            String []dates=itime.split("-");
            for (int i=0;i<dates.length;i++){
                if (Integer.parseInt(dates[i])<10){
                    dates[i]="0"+dates[i];
                }
            }
            itime=dates[0]+"-"+dates[1]+"-"+dates[2];
            CmInter cmInter=new CmInter(cmArea3,cmRecruit,cmStudent,iaddress,1,new DateConvert().StringtoUtilDate(itime),itype);
            //就业是生表操作
            CmUser cmUser=new CmUser();
            cmUser.setUid(0);
            boolean flag=false;
            if (ewq==1){
                flag=true;
            }
            CmEmp cmEmp=new CmEmp(cmUser,cmJob,cmStudent,new DateConvert().StringtoTime(etime),einfo,rsalary,new DateConvert().SysDate(),flag);
            boolean flag1=empService.addEmpStu(cmStudent,cmCompany,cmRecruit,cmInter,cmEmp);
            if (flag){
                mv.setViewName("redirect:/emp/forAddEmp2");
            }
        }

        return mv;
    }
    //zxl：修改就业生
    @RequestMapping(value = "/updateEmp",method = RequestMethod.POST)
    public ModelAndView updateEmp(int sid, int user, String etime,
                                  int esalary, String einfo, int ewq,ModelMap modelMap) throws  Exception{
        ModelAndView mv=new ModelAndView();
        boolean flag=empService.updateEmp(sid,user,etime,esalary,einfo,ewq);
        if (flag){
            mv.setViewName("redirect:/emp/forUpdateEmp2?sid="+sid);
        }
        return mv;
    }
    //zxl：为添加自己找工作的就业生准备forAddEmp
    @RequestMapping(value = "/forAddEmp", method = RequestMethod.GET)
    public String forAddEmp(ModelMap modelMap){
        List<CmJob>data=jobService.findAllJob();
        modelMap.addAttribute("allJob",data);
        List<CmArea>data1=areaService.findAllArea();
        modelMap.addAttribute("allAreaList",data1);

        return  "/system/employed/EmpAdd";
    }
    //zxl：为添加自己找工作的就业生准备forAddEmp
    @RequestMapping(value = "/forAddEmp2", method = RequestMethod.GET)
    public String forAddEmp2(ModelMap modelMap){
        List<CmJob>data=jobService.findAllJob();
        modelMap.addAttribute("allJob",data);
        List<CmArea>data1=areaService.findAllArea();
        modelMap.addAttribute("allAreaList",data1);
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","添加成功！");
        return  "/system/employed/EmpAdd";
    }




    //zxl：为修改就业生信息做准备，查询出所有的公司，推荐人以及岗位
    @RequestMapping(value = "/forUpdateEmp", method = RequestMethod.GET)
    public String updateEmp(int sid,ModelMap modelMap) throws Exception{
        //查询所有管理员
        List<CmUser>data1=userService.findAllUser();
        //查询该同学的工作
        CmJob cmJob=jobService.findBySid(sid);
        //查询该同学就职公司
        CmCompany cmCompany=companyService.findCompanyBySid(sid);
        //查询该同学的基本信息
        CmStudent cmStudent=studentService.findStuBySid(sid);
        CmEmp cmEmp=empService.findEmpBySid(sid);
        CmUser cmUser=userService.findUserByEmp(cmEmp.getEid());
        //cmEmp.setEtime(new DateConvert().subDate(cmEmp.getEtime()));
        modelMap.addAttribute("allUser",data1);
        modelMap.addAttribute("findBySid",cmJob);
        modelMap.addAttribute("findCompanyBySid",cmCompany);
        modelMap.addAttribute("findStuBySid",cmStudent);
        modelMap.addAttribute("findEmpBySid",cmEmp);
        modelMap.addAttribute("findUserByEmp",cmUser);
        return  "/system/employed/EmpUpdate";
    }
    //zxl：为修改就业生信息做准备，查询出所有的公司，推荐人以及岗位
    @RequestMapping(value = "/forUpdateEmp2", method = RequestMethod.GET)
    public String updateEmp2(int sid,ModelMap modelMap){
        //查询所有管理员
        List<CmUser>data1=userService.findAllUser();
        //查询该同学的工作
        CmJob cmJob=jobService.findBySid(sid);
        //查询该同学就职公司
        CmCompany cmCompany=companyService.findCompanyBySid(sid);
        //查询该同学的基本信息
        CmStudent cmStudent=studentService.findStuBySid(sid);
        CmEmp cmEmp=empService.findEmpBySid(sid);
        CmUser cmUser=userService.findUserByEmp(cmEmp.getEid());
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","修改成功！");
        modelMap.addAttribute("allUser",data1);
        modelMap.addAttribute("findBySid",cmJob);
        modelMap.addAttribute("findCompanyBySid",cmCompany);
        modelMap.addAttribute("findStuBySid",cmStudent);
        modelMap.addAttribute("findEmpBySid",cmEmp);
        modelMap.addAttribute("findUserByEmp",cmUser);
        return  "/system/employed/EmpUpdate";
    }


    /**
     * 查询显示所有已就业学生
     * @return
     */
    @RequestMapping(value = "/findAllEmp")
    public String FindAllEmp(ModelMap modelMap,@RequestParam("page") String page){
        //每页显示的条数
        int pageSize = 5;
        //处理分页类
        PageBean pageBean = new PageBean(Integer.parseInt(page),pageSize);
        List<ResEmpObj> empList = empService.FindAllEmp(pageBean);
        //计算就业生总数
        int total = empService.EmpCount();
        String pageCode = this.genPagation(total, Integer.parseInt(page), pageSize);
        modelMap.addAttribute("empList",empList);
        modelMap.put("pageCode",pageCode);
        return "system/employed/selectAllEmp";
    }


    /**
     * 按Cid查询该公司下所有已就业学生信息
     * @param cid
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findEmpStuByCid")
    public String findEmpStuByCid(String cid,ModelMap modelMap){
        List<ResEmpObj> empStu = empService.findEmpStuByCid(Integer.parseInt(cid));
        modelMap.addAttribute("empStu",empStu);
        return "/system/employed/CompEmpstu";
    }
    /**
     * 按Jname查询该岗位下所有已就业学生信息
     * @param jid
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findEmpStuByJname")
    public String findEmpStuByJname(String jid,ModelMap modelMap){
        List<ResEmpObj> empStu = empService.findEmpStuByJname(Integer.parseInt(jid));
        modelMap.addAttribute("empStu",empStu);
        return "/system/employed/JnameEmpstu";
    }

    /**
     * 查询该年级下该班级下的就业生信息
     * @param sgrade
     * @param sclass
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findEmpStuBySclass")
    public String findEmpStuBySclass(String sgrade,String sclass,ModelMap modelMap){
        List<ResEmpObj> empStu = empService.findEmpStuBySclass(Integer.parseInt(sgrade),Integer.parseInt(sclass));
        modelMap.addAttribute("empStu",empStu);
        return "/system/employed/SgradeSclassEmpstu";
    }

    /**
     * 按条件搜索已就业学生信息
     * @param startDate,endDate,searchtext,searchType
     * @return
     */
    @RequestMapping(value = "/findByEmp")
    @ResponseBody
    public ModelAndView findByName(ModelMap modelMap,String startDate,String endDate,String searchtext, String searchType) throws Exception{
        ModelAndView mv = new ModelAndView();
        System.out.println(searchType);
        System.out.println(searchtext);
        String searchtext0=new String(searchtext.getBytes("iso-8859-1"),"utf-8");
        if(searchType.equals("cname")) {
            List<ResEmpObj> listdata = empService.FindByCname(searchtext0);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("jname")){
            List<ResEmpObj> listdata = empService.FindByJname(searchtext0);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("sname")){
            List<ResEmpObj> listdata = empService.FindBySname(searchtext0);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("sgrade")){
            List<ResEmpObj> listdata = empService.FindBySgrade(Integer.parseInt(searchtext0));
            System.out.println(listdata);
            modelMap.addAttribute("listdata", listdata);
        }else {
            System.out.println(startDate);
            System.out.println(endDate);
            List<ResEmpObj> Empdata = empService.FindByEtime(startDate,endDate);
            modelMap.addAttribute("listdata",Empdata);
        }
        System.out.println("返回到页面------------");
        mv.setViewName("system/employed/EmpSearch");
        return mv;
    }

    /**
     * 删除已就业学生信息
     * @param eid
     * @return
     */
    @RequestMapping(value = "/delEmp")
    @ResponseBody
    public ModelAndView DelEmp(@RequestParam("eid") String eid){
        System.out.println(eid);
        ModelAndView mv = new ModelAndView();
        Boolean isSucc = empService.DelEmp(Integer.parseInt(eid));
        if(isSucc){
            mv.setViewName("redirect:findAllEmp");
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
        pageCode.append("<li><a href='/emp/findAllEmp?page=1'>首页</a></li>");
        if(currentPage==1) {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else {
            pageCode.append("<li><a href='/emp/findAllEmp?page="+(currentPage-1)+"'>上一页</a></li>");
        }
        for(int i=currentPage-2;i<=currentPage+2;i++) {
            if(i<1||i>totalPage) {
                continue;
            }
            if(i==currentPage) {
                pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
            } else {
                pageCode.append("<li><a href='/emp/findAllEmp?page="+i+"'>"+i+"</a></li>");
            }
        }
        if(currentPage==totalPage) {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        } else {
            pageCode.append("<li><a href='/emp/findAllEmp?page="+(currentPage+1)+"'>下一页</a></li>");
        }
        pageCode.append("<li><a href='/emp/findAllEmp?page="+totalPage+"'>尾页</a></li>");
        return pageCode.toString();
    }

    /*TianYu 就业生数据导入*/
    @RequestMapping(value = "/inputEmp")
    public String inputEmp(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
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
        msg = empService.uploadEmp(path+"\\"+fileName);
        model.addAttribute("file", msg);
        System.out.println(msg);
        return "/system/admin/inputData";
    }

    /*TianYu 导出就业生数据*/
    @RequestMapping(value = "/outputEmp")
    public ResponseEntity<byte[]> Download(HttpServletRequest httpServletRequest) throws IOException {
        File file = new File(empService.outputEmp());
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = file.getName();
        httpHeaders.setContentDispositionFormData("attachment",java.net.URLEncoder.encode(fileName,"ISO-8859-1"));
        httpHeaders.setContentType(MediaType.parseMediaType("application/xls"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders,HttpStatus.CREATED);
    }
}
