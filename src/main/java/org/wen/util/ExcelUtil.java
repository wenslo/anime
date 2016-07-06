package org.wen.util;

import org.apache.poi.hssf.usermodel.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Excel的导出工具类
 * 2016年7月6日00:22:03
 * 温海林
 */
public class ExcelUtil {
    /**
     * Excel的导出
     * 2016年7月5日23:51:57
     * 温海林
     */
    public static HSSFWorkbook export(String sheetName,String [] headMsg,String [] keys,List<Map<String,Object>> contracts,String fileName){
        //创建一个Excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个sheet并且指定sheet名字
        HSSFSheet sheet =workbook.createSheet(sheetName);
        //设置一个默认的宽度
        sheet.setDefaultColumnWidth(20);
        //样式
        HSSFCellStyle style = workbook.createCellStyle();
        //创建一个表头
        HSSFRow row = sheet.createRow(0);
        //设置字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for(int i=0;i<headMsg.length;i++){
            //创建一个单元格
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headMsg[i]);
            cell.setCellStyle(style);
        }
        for(int i=0;i<contracts.size();i++){
            HSSFRow rows = sheet.createRow(i+1);
            Map<String,Object> contract = contracts.get(i);
            for(int j=0;j<keys.length;j++){
                HSSFCell cell = rows.createCell(j);
                cell.setCellValue(String.valueOf(contract.get(keys[j])));
                cell.setCellStyle(style);
            }
        }
        return workbook;
    }
}
