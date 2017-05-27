package com.talkweb.ei.di.common;


import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;


/**
 * json 对象转换工具
 * @author zhq
 *
 */
public class JsonUtil {
	
	
	//对时间格式化
	public static Gson gson = new GsonBuilder().serializeNulls()
			.registerTypeAdapter(Date.class,  new LongDateTypeAdapter())
			.create();
	
	  
	  
    /** 
     * @MethodName : toJson 
     * @Description : 将对象转为JSON串，此方法能够满足大部分需求 
     * @param src 
     *            :将要被转化的对象 
     * @return :转化后的JSON串 
     */  
    public static String toJson(Object src) {  
        if (src == null) {  
            return gson.toJson(JsonNull.INSTANCE);  
        }  
        return gson.toJson(src);  
    }
    
     
    
    
    
    
    
    
    
    
    
}
