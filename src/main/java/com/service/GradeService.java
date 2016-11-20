package com.service;

import com.ResObj.UnempResObj;
import com.pojo.CmGrade;
import com.pojo.CmJob;
import com.pojo.CmStudent;
import com.pojo.CmUser;
import com.tools.InputData;
import com.tools.OutputData;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by w on 2016/10/24.
 */
@Service("gradeService")
public class GradeService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    //按学生ID查询必修学分——ly
    public int findComcredit(int sid){
        String hsql = "select sum(g.gxf) " +
                "from CmGrade g " +
                "where g.cmStudentBySid.sid = ? " +
                "and g.glx = 0 " +
                "and ((g.gfslx = 1 and (g.gcj in ('及格','中','良','优') or g.gbkcj in ('及格','中','良','优'))) or (g.gfslx = 2 and (CONVERT(g.gcj , SIGNED) >= 60 or CONVERT(g.gbkcj , SIGNED) >= 60))) " ;
        List<?> data = hibernateTemplate.find(hsql,sid);
        /*String hsql1 = "from CmGrade g " +
                "where g.cmStudentBySid.sid = ? " +
                "and g.glx = 0 " +
                "and ((g.gfslx = 1 and (g.gcj in ('及格','中','良','优') or g.gbkcj in ('及格','中','良','优'))) " +
                "or (g.gfslx = 2 and (CONVERT(g.gcj , SIGNED) >= 60 or CONVERT(g.gbkcj , SIGNED) >= 60))) " ;
        List<CmGrade> data1 = (List<CmGrade>)hibernateTemplate.find(hsql1,sid);
        for (int i=0;i<data1.size();i++){
            System.out.println("课程名—————"+data1.get(i).getGkcm());
        }*/
        if(data.get(0)!=null){
            System.out.println("data-----"+data);
            return Integer.parseInt(data.get(0).toString());
        }
        return 0;
    }

    //按学生ID查询选修学分——ly
    public int findOpcredit(int sid){
        String hsql = "select sum(g.gxf) " +
                "from CmGrade g " +
                "where g.cmStudentBySid.sid = ? " +
                "and g.glx in (1,2) " +
                "and ((g.gfslx = 1 and (g.gcj in ('及格','中','良','优') or g.gbkcj in ('及格','中','良','优'))) or (g.gfslx = 2 and (CONVERT(g.gcj , SIGNED) >= 60 or CONVERT(g.gbkcj , SIGNED) >= 60))) " ;
        List<?> data = hibernateTemplate.find(hsql,sid);
        if(data.get(0)!=null){
            return Integer.parseInt(data.get(0).toString());
        }
        return 0;
    }

    //根据不同条件查询科目数（0总科目1清考科目2中兴科目）——ly
    public int findByType(int sid,int type){
        String hsql = "select count(*) from CmGrade g where g.cmStudentBySid.sid = ?";
        if(type==1){
            hsql = hsql + " and ((g.gfslx = 1 and g.gcj = '不及格' and g.gbkcj = '不及格') or (g.gfslx = 2 and CONVERT(g.gcj , SIGNED) < 60 and CONVERT(g.gbkcj , SIGNED) < 60))";
        }else if(type==2){
            hsql = hsql + " and g.gkcm in ('Java语言基础','Java在移动通信中应用','网页设计','网页设计课程设计','数据库应用技术','JSP应用技术与AJAX','JSP应用技术与AJAX课程设计','SSH应用技术','SSH应用技术课程设计','IPhone/android嵌入式移动开发技术基础','IPhone/android嵌入式移动开发技术','软件测试技术与工具','IT项目管理','IT项目管理课程设计','Web前端技术','IT文档规范与编写','IPhone开发入门','CMMI标准工作流程','JAVA EE商用项目实践','项目开发模型','企业职业素养训练')";
        }
        List<?> data = hibernateTemplate.find(hsql,sid);
        if(data.get(0)!=null){
            System.out.println("科目数："+type+" ----- "+Integer.parseInt(data.get(0).toString()));
            return Integer.parseInt(data.get(0).toString());
        }
        return 0;
    }

    //查询所有中兴课程成绩——ly
    public List<CmGrade> findByKcm(int sid){
        String hsql = "from CmGrade g where g.cmStudentBySid.sid = ? and g.gkcm in ('Java语言基础','Java在移动通信中应用','网页设计','网页设计课程设计','数据库应用技术','JSP应用技术与AJAX','JSP应用技术与AJAX课程设计','SSH应用技术','SSH应用技术课程设计','IPhone/android嵌入式移动开发技术基础','IPhone/android嵌入式移动开发技术','软件测试技术与工具','IT项目管理','IT项目管理课程设计','Web前端技术','IT文档规范与编写','IPhone开发入门','CMMI标准工作流程','JAVA EE商用项目实践','项目开发模型','企业职业素养训练')";
        List<CmGrade> data = (List<CmGrade>)hibernateTemplate.find(hsql,sid);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }


    /*TianYu 上传excel*/
    public String uploadGrade(String path){
        InputData input = new InputData();
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            List<CmGrade>  ls = input.inputGrade(path);
            for (CmGrade cc : ls){
                session.save(cc);
            }
            session.close();
            return "导入成功！";
        } catch (IOException e) {
            return "数据格式错误！";
        } catch (Exception e) {
            return "数据格式错误！";
        }
    }

    /*TianYu 导出一位学生成绩数据*/
    public String outputGrade(int sid){
        System.out.println(sid+"---------");
        StudentService studentService = new StudentService();
        GradeService gradeService = new GradeService();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("学生成绩");
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell = row1.createCell(0);
        row1.setHeight((short)20);
        cell.setCellValue("学生成绩");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        HSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("学生姓名");
        row2.createCell(1).setCellValue("性别");
        row2.createCell(2).setCellValue("学号");
        row2.createCell(3).setCellValue("专业");
        row2.createCell(4).setCellValue("年级");
        row2.createCell(5).setCellValue("班级");
        row2.createCell(6).setCellValue("入学年份");
        row2.createCell(7).setCellValue("预计毕业时间");
        row2.createCell(8).setCellValue("已修必修学分");
        row2.createCell(9).setCellValue("已修选修学分");
        row2.createCell(10).setCellValue("总科目数");
        row2.createCell(11).setCellValue("毕业清考数");
        row2.createCell(12).setCellValue("中兴课程科目总数");
        row2.createCell(13).setCellValue("学生等级评价");
        row2.createCell(14).setCellValue("学生评语");
        HSSFRow row = sheet.createRow(2);
        CmStudent cs = studentService.findBySid(sid);
        row.createCell(0).setCellValue(cs.getSname());
        row.createCell(1).setCellValue(cs.getSsex());
        row.createCell(2).setCellValue(cs.getSno());
        row.createCell(3).setCellValue(cs.getSpro());
        row.createCell(4).setCellValue(cs.getSgrade());
        row.createCell(5).setCellValue(cs.getSclass());
        row.createCell(6).setCellValue(cs.getSgrade());
        row.createCell(7).setCellValue(cs.getSgrade()+4);
        row.createCell(8).setCellValue(gradeService.findComcredit(sid));
        row.createCell(9).setCellValue(gradeService.findOpcredit(sid));
        row.createCell(10).setCellValue(gradeService.findByType(sid,0));
        row.createCell(11).setCellValue(gradeService.findByType(sid,1));
        row.createCell(12).setCellValue(gradeService.findByType(sid,2));
        row.createCell(13).setCellValue(cs.getSmark());
        row.createCell(14).setCellValue(cs.getSassess());
        OutputData od = new OutputData();
        String file = od.fileNameConvert(wb,"公司信息");
        return file;
    }

}
