package com.talkweb.ei.di.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excle 处理工具类
 * 
 * @author: 
 * 2016-5-17，di，（描述修改内容）
 */
public class ExcelUtil
{
	
    //excle 最大导出行数
    public static final int MAXEXPORTNUM = 5000*4;
    
    //公司模板列数
    public static final int GS_COL_NUM = 12;

    //公司模板列数
    public static final int CON_COL_NUM = 14;   
        
    //用户基本信息列数
    public static final int USER_COL_NUM = 32; 
        
    //用户基本信息列数_JY
    public static final int USER_COL_NUM_JY = 29;        
    //用户基本信息列数_ZY
    public static final int USER_COL_NUM_ZY = 12; 
    //用户基本信息列数_ZZ
    public static final int USER_COL_NUM_ZZ = 17; 
    //用户基本信息列数_LD
    public static final int USER_COL_NUM_LD = 16;     
    //用户基本信息列数_JC
    public static final int USER_COL_NUM_JC = 8;  
    
    //个人费用模板列数
    public static final int HY_COL_NUM_GR = 33;   
    //集体费用模板列数
    public static final int HY_COL_NUM_JT = 12;
    
    //人员活动模板列数
    public static final int ACTION_COL_NUM = 9;
    
    
    
    /**
     * 读取excel 的第一sheet 的第一列（手机号码）
     * @param filePath
     * @param sLine
     * @param endLine
     * @return
     */
	public List<String> readFileExl(String filePath,int sheetid, int sLine, int endLine, int colnum) {

		List<String> retList = new ArrayList<String>();

		try {
			FileInputStream fis = new FileInputStream(filePath);

			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(fs);

			// Sheet[] sheet = rwb.getSheets();

			// 只读取第一sheet
			HSSFSheet sheet = wb.getSheetAt(sheetid);

			// 总行数
			int alllen = sheet.getLastRowNum();

			// 读取指定的行号
			if (endLine > alllen) {
				endLine = alllen;
			}

			HSSFRow row;
			String cellvalue = "";
			String retstr = null;

			for (int i = sLine; i <= endLine; i++) {
				row = sheet.getRow(i);
				// 空行
				if (row == null) {
					continue;
				}

				for (int j = 0; j < colnum; j++) {
					cellvalue = getStringCellValue(row.getCell(j));
					// 单元格里面没内容
					if (cellvalue == null) {
						// 空值用0代替
						cellvalue = "";
					}

					if (j == 0) {
						retstr = cellvalue;
					} else {
						// 数据用井号连接
						retstr += "#" + cellvalue;
					}

				}

				System.out.println("Data info:====" + retstr);
				retList.add(retstr);

			}
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retList;

	}
   
    
    
    /**
     * 读取excel 的第一sheet 的第一列（手机号码）
     * @param filePath
     * @param sheetid
     * @param sLine
     * @param endLine
     * @return
     */
    public List<String> readFileExl2007(String filePath,  int sheetid, int sLine, int endLine,int colnum)
    {
        
        System.out.println("上传文件路径："+filePath);

        List<String> retList = new ArrayList<String>();

        try
        {

            XSSFRow row;

            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            InputStream inp = new FileInputStream(new File(filePath));

            XSSFWorkbook xwb = new XSSFWorkbook(new BufferedInputStream(inp));

            // 读取第一章表格内容
            XSSFSheet sheet = xwb.getSheetAt(sheetid);

            // 总行数
            int alllen = sheet.getLastRowNum();

            // 文件长度小于100一次读完
            if (alllen < 100)
            {
               
                endLine = alllen;

            }
            // 读取指定的行号
            if (endLine > alllen)
            {
                endLine = alllen;
            }

            String retstr = "";
            String cellvalue = "";
            XSSFCell cell = null;

            for (int i = sLine; i <= endLine; i++)
            {
                row = sheet.getRow(i);

                // 清空行数据
                retstr = "";

                for (int j = 0; j < colnum; j++)
                {
                    cell = row.getCell(j);

                    // 单元格里面没内容
                    if (cell == null)
                    {
                        // 空值用0代替
                        cellvalue = "";
                    } else
                    {
                        cellvalue = getCellValue(row.getCell(j));
                    }

                    if (j == 0)
                    {

                        retstr = cellvalue;
                    } else
                    {
                        // 数据用井号连接
                        retstr += "#" + cellvalue;
                    }

                }
                
                System.out.println("Data info:===="+retstr);
                retList.add(retstr);

            }



            inp.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return retList;

    }    
    
    
    /**
     * 读取excel 的第一sheet 的第一列（手机号码）
     * @param filePath
     * @param sheet
     * @param sLine
     * @param endLine
     * @param colnum  读取的列数
     * @return
     */
    public static List<String> readFileExcel(String filePath,int sheet,int sLine, int endLine, int colnum) {
        
        ExcelUtil eu = new ExcelUtil();
        
        if("xlsx".equals(filePath.substring(filePath.length()-4))) {
            return eu.readFileExl2007(filePath,sheet, sLine, endLine,colnum);
        } else {
            
            return eu.readFileExl(filePath,sheet, sLine, endLine, colnum);
        }
           
        
    }
    

    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(HSSFCell cell) {
        
        if(cell==null){
            
            return "";
        }
        
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    public String getCellValue(Cell cell) {  
        
            SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("yyyy-MM-dd");
        
            String ret;  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_BLANK:  
                ret = "";  
                break;  
            case Cell.CELL_TYPE_BOOLEAN:  
                ret = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_ERROR:  
                ret = null;  
                break;  
            case Cell.CELL_TYPE_FORMULA:  
                Workbook wb = cell.getSheet().getWorkbook();  
                CreationHelper crateHelper = wb.getCreationHelper();  
                FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
                ret = getCellValue(evaluator.evaluateInCell(cell));  
                break;  
            case Cell.CELL_TYPE_NUMERIC:  
                if (DateUtil.isCellDateFormatted(cell)) {   
                    Date theDate = cell.getDateCellValue();  
                    ret = simpleDateFormat.format(theDate);  
                } else {   
                    ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
                }  
                break;  
            case Cell.CELL_TYPE_STRING:  
                ret = cell.getRichStringCellValue().getString();  
                break;  
            default:  
                ret = null;  
            }  
              
            return ret; //有必要自行trim  
        }  
      
    
    
    
    
    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    } 
    
    
    /**
     * 
     * 查找解压之后的文件名 
     * @param dir
     * @return
     */
    public  static String getFileName(File dir) {  

        File[] fm = dir.listFiles();  
        for (File file : fm) {  
            if (file.isFile()) {  
               
                return file.getName();
            }  
        } 
        
        return "";
        
    }  
    
    
    
    
    
    /**
     * 删除目录
     * @param filepath
     * @throws IOException
     */
    public static void deldir(String filepath) throws IOException {
        File f = new File(filepath);// 定义文件路径
        if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
            if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
                f.delete();
            } else {
                
                // 若有则把文件放进数组，并判断是否有下级目录
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        deldir(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
                    }
                    delFile[j].delete();// 删除文件
                }
                //删除目录
                f.delete();
            }
        }
    }
    
    /**
     * 清理临时文件
     * @param filePath
     * @param fileName
     * @param isunzip 是否是解压缩的文件
     */
    public static void clearTmpFile(String filePath,String fileName, boolean isunzip){
           
//        //删除压缩目录
//        if(isunzip){
//            try {             
//              deldir(filePath);
//          } catch (IOException e) {
//              
//              //e.printStackTrace();
//          }           
//          
//        } else {
//          
//           //删除本身文件
//           new File(filePath+fileName).delete();
//        } 
    }
    
    

}
