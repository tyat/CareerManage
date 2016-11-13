package com.tools;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by TianYu on 2016/11/10.
 */
public class OutputData {
    /*文件导出工具类*/
    public String fileNameConvert(HSSFWorkbook wb,String type){
        String file = null;
        try {
            String rootPath=getClass().getResource("/").getFile().toString();
            String path = rootPath.substring(1,rootPath.length()-16).replace("/","\\")+"upload\\";
            StringBuilder sb = new StringBuilder(path);
            sb.insert(2,"\\");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            file = sb.toString()+type+df.format(new java.util.Date())+".xls";
            System.out.println(file);
            FileOutputStream output = new FileOutputStream(file);
            wb.write(output);
            output.flush();
            return file;
        } catch (IOException e) {
            return "error";
        }

    }

}
