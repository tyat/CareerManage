package com.control;

import com.ResObj.RecruitResObj;
import com.pojo.CmArea;
import com.pojo.CmCompany;
import com.pojo.CmJob;
import com.pojo.CmRecruit;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by w on 2016/10/31.
 */
@Controller
public class RecruitCtrl {
    @Autowired
    private RecruitService recruitService;
    @Autowired
    private InterService interService;
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AreaService  areaService;

    //获取所有招聘信息列表——ly
    @RequestMapping(value = "/recruit/findAllRecruits",method = RequestMethod.GET )
    public String findAll(String page,ModelMap modelMap){
        //当前页
        modelMap.addAttribute("page",page);
        //每页显示的条数
        int pageSize = 10;
        modelMap.addAttribute("pageSize",pageSize);
        //处理分页类
        PageBean pageBean = new PageBean(Integer.parseInt(page),pageSize);
        List<RecruitResObj> recruitList = recruitService.findAll(pageBean);
        modelMap.addAttribute("recruitList",recruitList);
        //查询面试人数
        /*int InterCount = interService.findByRidCount(1);
        modelMap.addAttribute("InterCount",InterCount);*/
        //学生总数
        int totalCount = recruitService.findAllCount();
        modelMap.addAttribute("totalCount",totalCount);
        //总页数
        int pageCount = (totalCount % pageSize == 0)?(totalCount / pageSize):(totalCount / pageSize +1);
        modelMap.addAttribute("pageCount",pageCount);
        String pageCode = this.findAllgenPagation(totalCount, Integer.parseInt(page), pageSize);
        modelMap.put("pageCode",pageCode);
        return "system/meeting/selectAllMeeting";
    }

    //分页处理——ly
    private String findAllgenPagation(int totalNum, int currentPage, int pageSize){
        int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<li><a href='/recruit/findAllRecruits?page=1'>首页</a></li>");
        if(currentPage==1) {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else {
            pageCode.append("<li><a href='/recruit/findAllRecruits?page="+(currentPage-1)+"'>上一页</a></li>");
        }
        for(int i=currentPage-2;i<=currentPage+2;i++) {
            if(i<1||i>totalPage) {
                continue;
            }
            if(i==currentPage) {
                pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
            } else {
                pageCode.append("<li><a href='/recruit/findAllRecruits?page="+i+"'>"+i+"</a></li>");
            }
        }
        if(currentPage==totalPage) {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        } else {
            pageCode.append("<li><a href='/recruit/findAllRecruits?page="+(currentPage+1)+"'>下一页</a></li>");
        }
        pageCode.append("<li><a href='/recruit/findAllRecruits?page="+totalPage+"'>尾页</a></li>");
        return pageCode.toString();
    }

    //查询招聘详情——ly
    @RequestMapping(value = "/recruit/queryRinfo",method = RequestMethod.GET )
    @ResponseBody
    public CmRecruit queryRinfo(int rid, ModelMap modelMap){
        CmRecruit recruit = recruitService.findByRid(rid);
        modelMap.addAttribute("recruit",recruit);
        System.out.println("findByRid-----"+recruit.getRinfo());
        return recruit;
    }

    //增加前——ly
    @RequestMapping(value = "/recruit/addpro",method = RequestMethod.GET )
    public String addpro(ModelMap modelMap){
        List<CmCompany> companyList = companyService.FindAll();
        modelMap.addAttribute("companyList",companyList);
        List<CmArea> areaList = areaService.findAllArea();
        modelMap.addAttribute("areaList",areaList);
        List<CmJob> jobList = jobService.findAll();
        modelMap.addAttribute("jobList",jobList);
        return "system/meeting/MeetAdd";
    }

    //增加招聘信息——ly
    @RequestMapping(value = "/recruit/addRecruit",method = RequestMethod.POST )
    public String addRecruit(int cid,int aid,int jid,int rsalary,Boolean rsex,int rnum,String rend,String rinfo,ModelMap modelMap) throws ParseException {
        boolean ResMsg = recruitService.addRecruit(cid, aid, jid, rsalary, rsex, rnum, rend,rinfo);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","添加成功！");
        }else{
            modelMap.addAttribute("ResMsg","添加失败！");
        }
        return "redirect:/recruit/findAllRecruits";
    }

    //删除招聘信息——ly
    @RequestMapping(value = "/recruit/delRecruit",method = RequestMethod.GET )
    public String delRecruit(int rid,ModelMap modelMap) throws ParseException {
        boolean ResMsg = recruitService.delRecruit(rid);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","删除成功！");
        }else{
            modelMap.addAttribute("ResMsg","删除失败！");
        }
        return "redirect:/recruit/findAllRecruits";
    }

    //编辑前——ly
    @RequestMapping(value = "/recruit/findByRid",method = RequestMethod.GET )
    public String findByRid(int rid, ModelMap modelMap){
        RecruitResObj recruit = recruitService.findByRid2(rid);
        modelMap.addAttribute("recruit",recruit);
        List<CmCompany> companyList = companyService.FindAll();
        modelMap.addAttribute("companyList",companyList);
        List<CmArea> areaList = areaService.findAllArea();
        modelMap.addAttribute("areaList",areaList);
        List<CmArea> cityList = areaService.findCityByAprovince(recruit.getAprovince());
        modelMap.addAttribute("cityList",cityList);
        List<CmJob> jobList = jobService.findAll();
        modelMap.addAttribute("jobList",jobList);
        System.out.println("findByRid-----"+recruit.getCname());
        return "system/meeting/MeetUpdate";
    }

    //编辑招聘信息——ly
    @RequestMapping(value = "/recruit/updateRecruit",method = RequestMethod.POST )
    public String updateRecruit(int rid,int cid,int aid,int jid,int rsalary,Boolean rsex,int rnum,String rend,String rinfo, ModelMap modelMap) throws ParseException {
        boolean ResMsg = recruitService.updateRecruit(rid, cid, aid, jid, rsalary, rsex, rnum, rend,rinfo);
        if(ResMsg){
            modelMap.addAttribute("ResMsg","编辑成功！");
        }else{
            modelMap.addAttribute("ResMsg","编辑失败！");
        }
        return "redirect:/recruit/findAllRecruits";
    }

    //搜索招聘信息——ly
    @RequestMapping(value = "/recruit/query",method = RequestMethod.POST )
    public String query(int type, String searchtext, @RequestParam(value = "date",required = false) String date, ModelMap modelMap){
        String spl[] = searchtext.split(",");
        System.out.println("searchtext----"+searchtext);
        System.out.println("date----"+date);
        List<RecruitResObj> recruitList = new ArrayList<RecruitResObj>();
        if(type==0){
            recruitList = recruitService.findByCname(searchtext);
            modelMap.addAttribute("recruitList",recruitList);
        }else if(type==1){
            System.out.println("searchtext----"+spl[spl.length-1]);
            recruitList = recruitService.FindByDate(spl[spl.length-1],date);
            modelMap.addAttribute("recruitList",recruitList);
        }
        return "system/meeting/MeetSearch";
    }

    //按cid查询招聘信息——ly
    @RequestMapping(value = "/recruit/findByCid",method = RequestMethod.GET )
    public String findByCid(int cid,ModelMap modelMap){
        List<RecruitResObj> recruitList = recruitService.findByCid(cid);
        modelMap.addAttribute("recruitList",recruitList);
        return "system/meeting/MeetSearch";
    }

    /*TianYu 导出招聘信息数据*/
    @RequestMapping(value = "/recruit/outputRecruit")
    public ResponseEntity<byte[]> Download(HttpServletRequest httpServletRequest) throws IOException {
        File file = new File(recruitService.outputRecruit());
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = file.getName();
        httpHeaders.setContentDispositionFormData("attachment",java.net.URLEncoder.encode(fileName,"ISO-8859-1"));
        httpHeaders.setContentType(MediaType.parseMediaType("application/xls"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }

}
