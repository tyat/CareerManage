package com.control;

import com.pojo.CmArea;
import com.service.AreaService;
import com.service.UserService;
import com.tools.UploadTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/25.
 */
@Controller
@RequestMapping("/area")
public class AreaCtrl {
    @Autowired
    private AreaService areaService;
    //张小丽：查询数据库中所有省份
    @RequestMapping(value = "/selectAllArea",method = RequestMethod.GET)
    public  String  selectAllArea(ModelMap modelMap){
        List<CmArea>data=areaService.findAllArea();
       modelMap.addAttribute("allAreaList",data);
        return  "/system/company/CompAdd";
    }
    //张小丽：查询数据库中所有省份
    @RequestMapping(value = "/selectAllArea2",method = RequestMethod.GET)
    public  String  selectAllArea2(ModelMap modelMap){

        List<CmArea>data=areaService.findAllArea();
        modelMap.addAttribute("state","10001");
        modelMap.addAttribute("info","添加成功！");
        modelMap.addAttribute("allAreaList",data);
        return  "/system/company/CompAdd";
    }
    //张小丽：查询某省份数据库中所有的城市
    @RequestMapping(value = "/selectCity ", method = RequestMethod.GET)
    @ResponseBody
    public String selectCity(@RequestParam(value = "key", required = true) String key) throws   Exception{
        String aprovince=new String(key.getBytes("iso-8859-1"),"utf-8");
        List<CmArea>data=areaService.findCityByAprovince(aprovince);
        String s = "";
        for (CmArea cmArea:data){
            String ss = cmArea.getAid() + ":" + cmArea.getAcity() + ",";
            s = s + ss;
        }
        return s;
    }

    //查询某省份数据库中所有的城市——ly
    @RequestMapping(value = "/findcity", method = RequestMethod.POST)
    @ResponseBody
    public List<CmArea> findcity(String aprovince){
        System.out.println("Controller执行----");
        System.out.println("aprovince----"+aprovince);
        List<CmArea> cityList = areaService.findCityByAprovince(aprovince);
        System.out.println("cityList----"+cityList);
        return cityList;
    }

    /*TianYu 地区数据导入*/
    @RequestMapping(value = "/inputArea")
    public String inputArea(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
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
        msg = areaService.uploadArea(path+"\\"+fileName);
        model.addAttribute("file", msg);
        System.out.println(msg);
        return "/system/admin/inputData";
    }
}
