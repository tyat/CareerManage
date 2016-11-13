package com.control;

import com.ResObj.ResCompanyObj;
import com.pojo.CmArea;
import com.pojo.CmCompany;
import com.pojo.CmUser;
import com.service.AreaService;
import com.service.CompanyService;
import com.service.UserService;
import com.tools.DateConvert;
import com.tools.InputData;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/23.
 */
@Controller
@RequestMapping("/company")
public class CompanyCtrl {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AreaService areaService;
    //张小丽：添加公司
    @RequestMapping(value = "/addCompany",method = RequestMethod.GET)
    public ModelAndView addCompany(String cname,String chr,String cphone,String cemail,
                                        String cinfo,String cmark,String caddress,int city)throws  Exception{
        ModelAndView mv = new ModelAndView();
        String cname0=new String(cname.getBytes("iso-8859-1"),"utf-8");
        String chr0=new String(chr.getBytes("iso-8859-1"),"utf-8");
        String cinfo0=new String(cinfo.getBytes("iso-8859-1"),"utf-8");
        String cmark0=new String(cmark.getBytes("iso-8859-1"),"utf-8");
        String caddress0=new String(caddress.getBytes("iso-8859-1"),"utf-8");
        CmArea cmArea=new CmArea();
        cmArea.setAid(city);
        CmCompany  cmCompany=new CmCompany(cname0,chr0,cphone,cemail,cinfo0,cmark0,caddress0,cmArea,new DateConvert().convert());
        boolean flag=  companyService.addCompany(cmCompany);
        if (flag){
            mv.setViewName("redirect:/area/selectAllArea2");
        }
        return mv;
    }
    //张小丽：根据公司ID查询，即查找需要修改的公司信息
    @RequestMapping(value = "/findCompByCid",method = RequestMethod.GET)
    public String findCompByCid(int cid, ModelMap  modelMap){
       CmCompany cmCompany=companyService.findCompByCid(cid);
        List<CmArea> data=areaService.findAllArea();
        modelMap.addAttribute("allAreaList",data);
        modelMap.put("findCompByCid",cmCompany);
        return  "/system/company/CompUpdate";
    }
    //张小丽：根据公司ID查询，即查找需要修改的公司信息
    @RequestMapping(value = "/findCompByCid2",method = RequestMethod.GET)
    public String findCompByCid2(int cid, ModelMap  modelMap){
        CmCompany cmCompany=companyService.findCompByCid(cid);
        List<CmArea> data=areaService.findAllArea();
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","修改成功！");
        modelMap.addAttribute("allAreaList",data);
        modelMap.put("findCompByCid",cmCompany);
        return  "/system/company/CompUpdate";
    }
    //张小丽：修改公司信息
    @RequestMapping(value = "/updateCompany",method = RequestMethod.GET)
    public ModelAndView updateCompany(int cid,String cname,String chr,String cphone,String cemail,String aprovince,
                                String cinfo,String cmark,String caddress,int city,ModelMap modelMap) throws Exception{
        ModelAndView mv=new ModelAndView();
        String cname0=new String(cname.getBytes("iso-8859-1"),"utf-8");
        String chr0=new String(chr.getBytes("iso-8859-1"),"utf-8");
        String cinfo0=new String(cinfo.getBytes("iso-8859-1"),"utf-8");
        String cmark0=new String(cmark.getBytes("iso-8859-1"),"utf-8");
        String caddress0=new String(caddress.getBytes("iso-8859-1"),"utf-8");
        boolean flag=companyService.updateCompany(cid, cname0, chr0, cphone, cemail, cinfo0, cmark0, caddress0, city);
        if (flag){
          // "/system/company/CompUpdate"
            mv.setViewName("redirect:/company/findCompByCid2?cid="+cid);
        }
        return mv;
    }
    /**
     * 查询显示所有企业信息
     * @return
     */
    @RequestMapping(value = "/findAllCompany")
    public ModelAndView findAllCompany(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        List<ResCompanyObj> companyList = companyService.FindALLCompany();
        System.out.println(companyList);
        modelMap.addAttribute("companylist",companyList);
        mv.setViewName("system/company/selectAllCom");
        return mv;
    }

    /**
     * 按公司ID 查询该公司信息
     * @param modelMap
     * @param cid
     * @return
     */
    @RequestMapping(value = "/findByCompCid")
    @ResponseBody
    public ModelAndView SelectByCid(ModelMap modelMap,String cid){
        ModelAndView mv = new ModelAndView();
        System.out.println(cid);
        List<CmCompany> dateList = companyService.findCompByCid1(Integer.parseInt(cid));
        modelMap.addAttribute("dateList",dateList);
        mv.setViewName("system/company/CompInfo");
        return mv;
    }
    /**
     * 按条件搜索相关企业
     * @param searchtext,searchType
     * @return
     */
    @RequestMapping(value = "/findByComp")
    @ResponseBody
    public ModelAndView findByName(ModelMap modelMap, String searchtext, String searchType){
        ModelAndView mv = new ModelAndView();
        System.out.println(searchType);
        System.out.println(searchtext);
        if(searchType.equals("cname")) {
            List<ResCompanyObj> listdata = companyService.FindByCName(searchtext);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("chr")){
            List<ResCompanyObj> listdata = companyService.FindByCHr(searchtext);
            modelMap.addAttribute("listdata", listdata);
        }else if(searchType.equals("jname")){
            List<ResCompanyObj> listdata = companyService.FindByCJname(searchtext);
            modelMap.addAttribute("listdata", listdata);
        }
        mv.setViewName("system/company/CompSearch");
        return mv;
    }
    /**
     * 删除该条企业信息记录
     * @param cid
     * @return
     */
    @RequestMapping( value = "/delCompany")
    @ResponseBody
    public ModelAndView DelCompany(@RequestParam("cid") String cid){
        System.out.println(cid);
        ModelAndView mv = new ModelAndView();
        boolean isSucc = companyService.DelCompany(Integer.parseInt(cid));
        if(isSucc) {
            mv.setViewName("redirect:findAllCompany");
            return mv;
        }
        return null;
    }

    /*TianYu 公司数据导入*/
    @RequestMapping(value = "/inputCompany")
    public String inputCompany(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
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
        msg = companyService.uploadCompany(path+"\\"+fileName);
        model.addAttribute("file", msg);
        System.out.println(msg);
        return "/system/admin/inputData";
    }

}
