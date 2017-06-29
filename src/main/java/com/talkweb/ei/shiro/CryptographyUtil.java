package com.talkweb.ei.shiro;

import org.apache.shiro.codec.Base64;  
import org.apache.shiro.crypto.hash.Md5Hash;  
public class CryptographyUtil {  
  
    /** 
     * base64���� 
     * @param str 
     * @return 
     */  
    public static String encBase64(String str){  
        return Base64.encodeToString(str.getBytes());  
    }  
      
    /** 
     * base64���� 
     * @param str 
     * @return 
     */  
    public static String decBase64(String str){  
        return Base64.decodeToString(str);  
    }  
      
    /** 
     * Md5����      Shiro���Դ�MD5û�н��� 
     * @param str   Ҫ���ܵ�ֵ 
     * @param salt<span style="white-space:pre">  </span>���Կ����ǰ��� 
     * @return 
     */  
    public static String md5(String str,String salt){  
        return new Md5Hash(str,salt).toString();  
    }
    
    public static void main(String[] args) {  
        String password="123456";  
        System.out.println("Base64���ܣ�"+CryptographyUtil.encBase64(password));  
        System.out.println("Base64���ܣ�"+CryptographyUtil.decBase64(CryptographyUtil.encBase64(password)));  
        //MD5����Ӧ�õ�ʵ����Ŀ�п������磺ע���ύ�����Ƕ��������MD5���ܾͻ�õ�һ��������70f57208b804bd90d51fdac84afe6472�ַ������浽���ݿ�  
        //�����MD5���ܹ�������룬���û���֤�ǰ����ݿ��ȡ���������ڶԱ�  
        System.out.println("Md5���ܣ�"+CryptographyUtil.md5(password, "java1234"));  
    } 
    
}
