package com.talkweb.ei.di.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
    private static Logger log = LoggerFactory.getLogger(StringUtils.class);
    
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
		    log.error(e.toString());
			//e.printStackTrace();
		}
		return "";
	}

	/**
	 * @return
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
		
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 格式化输出BigDecimal的金额，保存两位小数
	 * @param val
	 * @return
	 */
	public static String formatBigDecimal(BigDecimal val){
		
		if(val==null) return "0";
		return val.toString();
	}
	
	/*public static void main(String args[])  {
		//测试BigDecimal
		
		BigDecimal b1 = new BigDecimal(1);
		BigDecimal b2 = new BigDecimal(2);
		
		BigDecimal aa = b1.add(b2);
		System.out.println("test add==1=="+b1);
		System.out.println("test add=2==="+b2);
		System.out.println("test add=aa==="+aa);
		
		
	}*/
	
}