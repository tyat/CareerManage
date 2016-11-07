package com.control;

import com.pojo.CmArea;
import com.pojo.CmCompany;
import com.pojo.CmUser;
import com.service.AreaService;
import com.service.CompanyService;
import com.service.UserService;
import com.tools.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String addCompany(String cname,String chr,String cphone,String cemail,
                                        String cinfo,String cmark,String caddress,int city,ModelMap modelMap)throws  Exception{
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
            modelMap.addAttribute("state","10001");
            modelMap.addAttribute("info","添加成功！");
        }
        return "/system/company/CompAdd";
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
    //张小丽：修改公司信息
    @RequestMapping(value = "/updateCompany",method = RequestMethod.GET)
    public String updateCompany(int cid,String cname,String chr,String cphone,String cemail,String aprovince,
                                String cinfo,String cmark,String caddress,int city,ModelMap modelMap) throws Exception{
        String cname0=new String(cname.getBytes("iso-8859-1"),"utf-8");
        String chr0=new String(chr.getBytes("iso-8859-1"),"utf-8");
        String cinfo0=new String(cinfo.getBytes("iso-8859-1"),"utf-8");
        String cmark0=new String(cmark.getBytes("iso-8859-1"),"utf-8");
        String caddress0=new String(caddress.getBytes("iso-8859-1"),"utf-8");
        boolean flag=companyService.updateCompany(cid, cname0, chr0, cphone, cemail, cinfo, cmark, caddress, city);
        if (flag){
            modelMap.addAttribute("state","10001");
            modelMap.addAttribute("info","添加成功！");
        }
        return  "/system/company/CompUpdate";
    }

}
