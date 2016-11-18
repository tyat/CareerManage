package com.tools;

import com.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import sun.java2d.cmm.CMSManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TianYu on 2016/11/10.
 */
public class InputData {
    private HibernateTemplate hibernateTemplate;
    public List<CmArea> inputArea(String path) throws IOException {
        List<CmArea> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(path);
        Workbook wb0 = new HSSFWorkbook(fileIn);
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            // 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
            CmArea ca = new CmArea();
            if (r.getCell(0).getStringCellValue()!=null){
                ca.setAprovince(r.getCell(0).getStringCellValue());
            }
            if (r.getCell(1).getStringCellValue()!=null){
                ca.setAcity(r.getCell(1).getStringCellValue());
            }
            temp.add(ca);
        }
        fileIn.close();
        System.out.println("InputDataOK-------------------");
        return temp;
    }

    public List<CmStudent> inputStudent(String xlsPath) throws IOException {
        List<CmStudent> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(xlsPath);
        // 根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fileIn);
        // 获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        // 对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            // 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
            CmStudent stu = new CmStudent();
            // 取出当前行第1个单元格数据，并封装在info实体stuName属性上
            if (r.getCell(1).getStringCellValue()!=null){
                stu.setSname(r.getCell(1).getStringCellValue());
            }
            String sex = r.getCell(2).getStringCellValue();
            System.out.println("sex:" + sex);
            if (sex.equals("男")) {
                stu.setSsex(false);
            } else if (sex.equals("女")) {
                stu.setSsex(true);
            }
            stu.setSbirth(new java.sql.Date(r.getCell(3).getDateCellValue().getTime()));
            stu.setScode(r.getCell(4).getStringCellValue());
            stu.setSno(r.getCell(5).getStringCellValue());
            stu.setSgrade(Integer.parseInt(r.getCell(6).getStringCellValue()));
            stu.setSclass(Integer.parseInt(r.getCell(7).getStringCellValue()));
            stu.setSpro(r.getCell(8).getStringCellValue());
            stu.setSphone(r.getCell(9).getStringCellValue());
            stu.setSemail(r.getCell(10).getStringCellValue());
            stu.setSmark(Integer.parseInt(r.getCell(11).getStringCellValue()));
            stu.setSassess(r.getCell(12).getStringCellValue());
            stu.setSdetail(r.getCell(13).getStringCellValue());
            stu.setSstate(0);
            temp.add(stu);
        }
        fileIn.close();
        return temp;
    }

    public List<CmGrade> inputGrade(String path) throws Exception {
        List<CmGrade> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(path);
        Workbook wb0 = new HSSFWorkbook(fileIn);
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            // 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
            CmGrade cg = new CmGrade();
            String hql = "from CmStudent cs where cs.sname = ? ";
            System.out.print("+++++++"+r.getCell(0).getStringCellValue());
            List<CmStudent>  cs = (List<CmStudent>)hibernateTemplate.find(hql,r.getCell(0).getStringCellValue());
            cg.setCmStudentBySid(cs.get(0));
            cg.setGxq(r.getCell(1).getStringCellValue());
            cg.setGxn(r.getCell(2).getStringCellValue());
            cg.setGkcm(r.getCell(3).getStringCellValue());
            cg.setGcj(r.getCell(4).getStringCellValue());
            if (r.getCell(5).getStringCellValue().equals("分数")) {
                cg.setGfslx(2);
            } else if (r.getCell(5).getStringCellValue().equals("等级")) {
                cg.setGfslx(1);
            }
            cg.setGbkcj(r.getCell(6).getStringCellValue());
            cg.setGxf(Integer.parseInt(r.getCell(7).getStringCellValue()));
            if (r.getCell(8).getStringCellValue().equals("必修")) {
                cg.setGlx(1);
            } else if (r.getCell(8).getStringCellValue().equals("公共选修")) {
                cg.setGlx(2);
            } else if (r.getCell(8).getStringCellValue().equals("系定选修")) {
                cg.setGlx(3);
            }
            temp.add(cg);
        }
        fileIn.close();
        return temp;
    }

    public List<CmCompany> inputCompany(String path) throws Exception {
        List<CmCompany> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(path);
        Workbook wb0 = new HSSFWorkbook(fileIn);
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            if (r.getRowNum() < 1) {
                continue;
            }
            CmCompany cc = new CmCompany();
            cc.setCname(r.getCell(0).getStringCellValue());
            String hql = "from CmArea ca where ca.acity = ? ";
            CmArea ca = (CmArea) hibernateTemplate.find(hql,r.getCell(2).getStringCellValue()).get(0);
            if (ca == null) {
                CmArea caa = new CmArea();
                r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String province = r.getCell(1).getStringCellValue();
                String city = r.getCell(2).getStringCellValue();
                r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                System.out.println(r.getCell(1).getStringCellValue() + " "
                        + r.getCell(2).getStringCellValue());
                caa.setAprovince(province);
                caa.setAcity(city);
                cc.setCmAreaByAid(caa);
            } else {
                cc.setCmAreaByAid(ca);
            }
            cc.setCaddress(r.getCell(3).getStringCellValue());
            cc.setChr(r.getCell(4).getStringCellValue());
            r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            cc.setCphone(r.getCell(5).getStringCellValue());
            cc.setCemail(r.getCell(6).getStringCellValue());
            cc.setCinfo(r.getCell(7).getStringCellValue());
            cc.setCmark(r.getCell(8).getStringCellValue());
            cc.setCtime(new Date());
            cc.setCstate(0);
            temp.add(cc);
        }
        fileIn.close();
        return temp;
    }

    public List<CmEmp> inputEmp(String path) throws Exception {
        List<CmEmp> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(path);
        Workbook wb0 = new HSSFWorkbook(fileIn);
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            // 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
            CmEmp ce = new CmEmp();
            String hql = "from CmStudent cs where cs.sname = ? ";
            ce.setCmStudentBySid((CmStudent)hibernateTemplate.find(hql,r.getCell(0).getStringCellValue()).get(0));

            String job = "from CmJob cj where cj.jname = ? ";
            ce.setCmJobByJid((CmJob)hibernateTemplate.find(job,r.getCell(1).getStringCellValue()).get(0));
            ce.setEtime(new Timestamp(r.getCell(2).getDateCellValue().getTime()));
            ce.setEsalary((int) r.getCell(3).getNumericCellValue());

            String user = "from CmUser cu where cu.urname = ?";
            ce.setCmUserByUid((CmUser)hibernateTemplate.find(user,r.getCell(4).getStringCellValue()).get(0));
            if (r.getCell(5).getStringCellValue().equals("是")) {
                ce.setEwq(true);
            } else if (r.getCell(5).getStringCellValue().equals("否")) {
                ce.setEwq(false);
            }
            ce.setEleave(new java.sql.Date(r.getCell(6).getDateCellValue().getTime()));
            ce.setEreason(r.getCell(7).getStringCellValue());
            ce.setEstate(0);
            temp.add(ce);
        }
        fileIn.close();
        return temp;
    }

    public List<CmUnemp> inputUnemp(String path) throws Exception {
        List<CmUnemp> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(path);
        Workbook wb0 = new HSSFWorkbook(fileIn);
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            // 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
            CmUnemp cu = new CmUnemp();
            String hql = "from CmStudent cs where cs.sname = ? ";
            cu.setCmStudentBySid((CmStudent)hibernateTemplate.find(hql,r.getCell(0).getStringCellValue()).get(0));

            String dir = "from CmDirection cd where cd.dname = ?";
            cu.setCmDirectionByDid((CmDirection)hibernateTemplate.find(dir,r.getCell(1).getStringCellValue()).get(0));

            String job = "from CmJob cj where cj.jname = ? ";
            cu.setCmJobByJid((CmJob)hibernateTemplate.find(job,r.getCell(2).getStringCellValue()).get(0));

            cu.setUesalary((int)r.getCell(3).getNumericCellValue());
            cu.setUetime(new java.sql.Date(r.getCell(4).getDateCellValue().getTime()));
            cu.setUeschool(r.getCell(5).getStringCellValue());
            cu.setUemajor(r.getCell(6).getStringCellValue());
            if(r.getCell(7).getStringCellValue().equals("暂无")){
                cu.setUesuccess(0);
            }else if(r.getCell(7).getStringCellValue().equals("成功")){
                cu.setUesuccess(1);
            }else if(r.getCell(7).getStringCellValue().equals("失败")){
                cu.setUesuccess(2);
            }
            cu.setUestate(0);
            temp.add(cu);
        }
        fileIn.close();
        return temp;
    }
}
