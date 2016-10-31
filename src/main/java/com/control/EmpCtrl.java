package com.control;

import com.pojo.CmEmp;
import com.pojo.CmJob;
import com.pojo.CmStudent;
import com.pojo.CmUser;
import com.service.CompanyService;
import com.service.EmpService;
import com.tools.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/30.
 */
@Controller
public class EmpCtrl {
    @Autowired
    private EmpService empService;
    //张小丽：添加就业生
    @RequestMapping(value = "/addEmp",method = RequestMethod.GET)
    public String addEmp(int sid, int jid, int uid, String etime, int esalary, String einfo, boolean ewq, ModelMap modelMap) throws  Exception{
        CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        CmJob  cmJob=new CmJob();
        cmJob.setJid(jid);
        CmUser cmUser=new CmUser();
        cmUser.setUid(uid);
        DateFormat df = DateFormat.getDateInstance();
        Date d = df.parse(etime);
        long da = d.getTime();
        Timestamp ts = new Timestamp(da);
        CmEmp cmEmp=new CmEmp(esalary,ts,einfo,ewq,cmStudent,cmJob,cmUser);
        boolean flag=empService.addEmp(cmEmp);
        if (flag){
            modelMap.addAttribute("state","10001");
            modelMap.addAttribute("info","添加成功！");
        }
        return "/system/employed/EmpAdd";
    }
    //张小丽：添加就业生
    @RequestMapping(value = "/updateEmp",method = RequestMethod.GET)
    public String updateEmp(int sid, int jid, int uid, String etime, int esalary, String einfo, boolean ewq,ModelMap modelMap) throws  Exception{
        CmStudent cmStudent=new CmStudent();
        cmStudent.setSid(sid);
        CmJob  cmJob=new CmJob();
        cmJob.setJid(jid);
        CmUser cmUser=new CmUser();
        cmUser.setUid(uid);
        CmEmp cmEmp=new CmEmp(esalary,new DateConvert().StringtoTime(etime),einfo,ewq,cmStudent,cmJob,cmUser);
        cmEmp.setEid(1);
        boolean flag=empService.updateEmp(cmEmp);
        if (flag){
            modelMap.addAttribute("state","10001");
            modelMap.addAttribute("info","添加成功！");
        }
        return "/system/employed/EmpUpdate";
    }
}
