package com.talkweb.ei.di.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;


/**
 * 日期型gson适配
 * @author zhq
 *
 */
public class LongDateTypeAdapter extends TypeAdapter<Date> {
	

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            //out.value(value.getTime());
        	
        	//格式化输出
        	out.value(DateUtil.format(value));
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == null) {
            return null;
        }
        
        //日期格式"yyyy-MM-dd"
        try {
        	 String str = in. nextString();
             Date d  = DateUtil.parseDate(str);
             return d;
		} catch (Exception e) {
			return null;
		}
        
       
       
    }
       
//    public static void main(String[] args) {
//        Date d = new Date();
//        Model m = new Model();
//        m.setDate(d);
//        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LongDateTypeAdapter()).create();
//        String str = gson.toJson(m);
//        System.out.println(str); //{"date":1407921708088}
//
//        m = gson.fromJson(str, Model.class);
//        System.out.println(m.getDate().toString()); //1407921708088
//    }    
    
       
}

//class Model {
//    private Date date;
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//  
//}


