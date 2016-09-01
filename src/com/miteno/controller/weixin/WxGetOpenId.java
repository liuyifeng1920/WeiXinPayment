package com.miteno.controller.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



import com.miteno.util.ConstUtils;

public class WxGetOpenId {
	//根据code 获取openid
    public static String getOpenId (String code) throws IllegalStateException, Exception{ 
    	String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=AppId&secret=AppSecret&code=CODE&grant_type=authorization_code";
        url = url.replace("AppId", ConstUtils.APPID2).replace("AppSecret", ConstUtils.APPSECRET)
                .replace("CODE", code);
        // 创建HttpClient实例     
        HttpClient httpclient = new DefaultHttpClient();  
        // 创建Get方法实例     
        HttpGet httpgets = new HttpGet(url);    
        org.apache.http.HttpResponse response = httpclient.execute(httpgets);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String openid = jsonObject.getString("openid");
        return openid;
    }  
      
   /* public static Map toMap(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }*/
    
    public static String urlEnodeUTF8(String str){ 
        String result = str; 
        try { 
            result = URLEncoder.encode(str,"UTF-8"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return result; 
    } 
    public static void main(String[] args) throws UnsupportedEncodingException {
    	//{scope=snsapi_base, expires_in=7200, openid=o6DhFuHVxFsB8G9OzydnE-ye9C3Y, refresh_token=OezXcEiiBSKSxW0eoylIeJ5qwknouBMKlhKIB0dnaQgA1axWjeVZLM8GRjkP1AdVADKO0e7qkjoXYP91dQekMy1Y1tKp9FNxCJWchO7UhU3SwtRyC6IfvsGwwqS3lnoY6p8X5DN7G90g4VEGXujRsA, access_token=OezXcEiiBSKSxW0eoylIeJ5qwknouBMKlhKIB0dnaQgA1axWjeVZLM8GRjkP1AdVjsISVQKmC33ahFIQiz82oEX95-AkF5CeoSwJ2goaFdYoLE38jF3f7pe_x_SOWtkFy0r2m-h5q-OulkTUez6Vsg}
//        String str = "http://50dbfa34.ngrok.io/WeiXinPayment/background/microui/backCode.action";  
//        String str1 = URLEncoder.encode(str, "utf-8");  
//        String str2 = URLEncoder.encode(str,"gbk");  
//       System.out.println(str1);  
//       System.out.println(str2);  
//        String str3 = URLDecoder.decode(str,"iso8859-1");  
//        String str4 = URLDecoder.decode(str,"utf-8");  
//        System.out.println(str3);  
//        System.out.println(str4);  
    }
}
