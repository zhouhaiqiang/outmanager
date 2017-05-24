package com.talkweb.ei.di.common;

import java.io.OutputStream;  
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;  

import  org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.springframework.web.servlet.view.document.AbstractExcelView;  

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

public class ViewExcel extends AbstractExcelView {  
    @Override  
    protected void buildExcelDocument(Map<String, Object> model,  
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
        HSSFSheet sheet = workbook.createSheet("详细数据"); 
        HSSFCell cell;
    

        
        @SuppressWarnings("unchecked")
        List<String> titles = (List<String>) model.get("titles");
        
        
        int len = titles.size();
        HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont headerFont = workbook.createFont();    //标题字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short)11);
        headerStyle.setFont(headerFont);
        short width = 20,height=25*20;
        sheet.setDefaultColumnWidth(width);
        
        //置标题
        for(int i=0; i<len; i++){ //设置标题
            String title = titles.get(i);
            cell = getCell(sheet, 0, i);
            cell.setCellStyle(headerStyle);
            setText(cell,title);
        }
        sheet.getRow(0).setHeight(height);
         
        HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        @SuppressWarnings("unchecked")
        
        //二维数组数据
        List<List> varList = (List<List>) model.get("varList");
        int varCount = varList.size();
        //行
        for(int i=0; i<varCount; i++){
            List<String> line = varList.get(i);
            
          
            
            //列 没给标题的不会被显示到文件中
            for(int j=0;j<len;j++){
                               
                String varstr = line.get(j);
                
                //有值处理
                varstr = varstr!= null ? varstr : "";
                
                cell = getCell(sheet, i+1, j);
                cell.setCellStyle(contentStyle);
                setText(cell,varstr);
            }
             
        }       

        
        //下载文档标题
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        
        String filename = (String)model.get("name");
        filename = filename+"("+sf.format(new Date())+").xls";//设置下载时客户端Excel的名称     
        filename = encodeFilename(filename, request);//处理中文文件名  
        response.setContentType("application/vnd.ms-excel");     
        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);     
        ouputStream.flush();     
        ouputStream.close();     
    }
    
    
    
    
    
    
    
    
    /**  
     * 设置下载文件中文件的名称  
     *   
     * @param filename  
     * @param request  
     * @return  
     */    
    protected  String encodeFilename(String filename, HttpServletRequest request) {    
      /**  
       * 获取客户端浏览器和操作系统信息  
       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
       */    
      String agent = request.getHeader("USER-AGENT");    
      try {    
        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
          String newFileName = URLEncoder.encode(filename, "UTF-8");    
            newFileName = StringUtils.replace(newFileName, "+", "%20");    
          if (newFileName.length() > 150) {    
            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
            newFileName = StringUtils.replace(newFileName, " ", "%20");    
          }    
          return newFileName;    
        }    
        if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
          return MimeUtility.encodeText(filename, "UTF-8", "B");    
      
        return filename;    
      } catch (Exception ex) {    
        return filename;    
      }    
    }   
    
    
    
    
    
    
    
}