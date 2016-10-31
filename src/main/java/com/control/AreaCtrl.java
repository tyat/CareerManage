package com.control;

import com.pojo.CmArea;
import com.service.AreaService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/25.
 */
@Controller
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

}
