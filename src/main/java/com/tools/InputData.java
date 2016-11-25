package com.tools;

import com.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import sun.java2d.cmm.CMSManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TianYu on 2016/11/10.
 */
public class InputData {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public String ConvertPath(String path){
        String headPath = path.substring(0,3);
        String lastPath = path.substring(2);
        return headPath+lastPath;
    }

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
}
