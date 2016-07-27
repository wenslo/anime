package org.wen.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wen.entity.User;
import org.wen.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Excel的导出工具类
 * 2016年7月6日00:22:03
 * 温海林
 */
public class ExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    /**
     * Excel的导出
     * 2016年7月5日23:51:57
     * 温海林
     */
    public static HSSFWorkbook export(String sheetName,String [] headMsg,String [] keys,List<Map<String,Object>> contracts){
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
    public static  List<User> uploadExcel(InputStream input) throws IOException {
        //得到该Excel文件
        Workbook wb = new HSSFWorkbook(input);
        //得到第一个sheet的内容
        Sheet sheet = wb.getSheetAt(0);
        //得到一共多少行
        int totalRows = sheet.getLastRowNum();
        log.debug("该Excel一共有{}行",totalRows);
        Iterator<Row> rows = sheet.rowIterator();
        List<User> users = new ArrayList<User>();
        while(rows.hasNext()){
            Row row = rows.next();
            log.info("行号为{}",row.getRowNum());
            if(row.getRowNum() == 0){
                continue;
            }
            Iterator<Cell> cells = row.cellIterator();
            while(cells.hasNext()){
                User user = new User();
                Cell cell = cells.next();
                log.info("当前是第{}个cell",cell.getColumnIndex());
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex){
                    case 0:
                        String name = cell.getStringCellValue();
                        user.setName(name);
                        break;
                    case 1:
                        String pwd = cell.getStringCellValue();
                        user.setPwd(UserServiceImpl.getMd5(pwd));
                        break;
                }
                user.setCreateDate(new Date());
                users.add(user);
            }
        }
        return users;
    }
}
