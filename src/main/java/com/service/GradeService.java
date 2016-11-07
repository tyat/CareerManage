package com.service;

import com.pojo.CmGrade;
import com.pojo.CmUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

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
        return Integer.parseInt(data.get(0).toString());
    }

    //按学生ID查询选修学分——ly
    public int findOpcredit(int sid){
        String hsql = "select sum(g.gxf) " +
                "from CmGrade g " +
                "where g.cmStudentBySid.sid = ? " +
                "and g.glx in (1,2) " +
                "and ((g.gfslx = 1 and (g.gcj in ('及格','中','良','优') or g.gbkcj in ('及格','中','良','优'))) or (g.gfslx = 2 and (CONVERT(g.gcj , SIGNED) >= 60 or CONVERT(g.gbkcj , SIGNED) >= 60))) " ;
        List<?> data = hibernateTemplate.find(hsql,sid);
        return Integer.parseInt(data.get(0).toString());
    }

    //根据不同条件查询科目数（0总科目1清考科目2中兴科目）——ly
    public int findByType(int sid,int type){
        String hsql = "select count(*) from CmGrade g where g.cmStudentBySid.sid = ?";
        if(type==1){
            hsql = hsql + " and (g.gfslx = 1 and g.gcj = '不及格' and g.gbkcj = '不及格') or (g.gfslx = 2 and CONVERT(g.gcj , SIGNED) < 60 and CONVERT(g.gbkcj , SIGNED) < 60)";
        }else if(type==2){
            hsql = hsql + " and g.gkcm in ('Java语言基础','Java在移动通信中应用','网页设计','网页设计课程设计','数据库应用技术','JSP应用技术与AJAX','JSP应用技术与AJAX课程设计','SSH应用技术','SSH应用技术课程设计','IPhone/android嵌入式移动开发技术基础','IPhone/android嵌入式移动开发技术','软件测试技术与工具','IT项目管理','IT项目管理课程设计','Web前端技术','IT文档规范与编写','IPhone开发入门','CMMI标准工作流程','JAVA EE商用项目实践','项目开发模型','企业职业素养训练')";
        }
        List<?> data = hibernateTemplate.find(hsql,sid);
        return Integer.parseInt(data.get(0).toString());
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


}
