package junit.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class texs {
	 public static void main(String[] args) throws ClientProtocolException, IOException  
	    {  
		 
		 
		 String  tokenurl ="https://api.weixin.qq.com/cgi-bin/token";
			String grant_type= "client_credential";
			 String appid = "wxce16ebd3104459cd";
			 String appsecret = "62b9eddb8a5b933aed08f7571d1a93e6";
			String requestUrl = tokenurl + "?grant_type=" + grant_type + "&appid="
					+ appid + "&secret=" + appsecret;
	        // 创建HttpClient实例     
	        HttpClient httpclient = new DefaultHttpClient();  
	        // 创建Get方法实例     
	        HttpGet httpgets = new HttpGet(requestUrl);    
	        org.apache.http.HttpResponse response = httpclient.execute(httpgets);    
	        HttpEntity entity = response.getEntity();    
	        if (entity != null) {    
	            InputStream instreams = entity.getContent();
	            String str = convertStreamToString(instreams);  
	            System.out.println("Do something");   
	            System.out.println(str);  
	            // Do not need the rest    
	            httpgets.abort();    
	        }  
	    }  
	      
	    public static String convertStreamToString(InputStream is) {      
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
	        StringBuilder sb = new StringBuilder();      
	        String line = null;      
	        try {      
	            while ((line = reader.readLine()) != null) {  
	                sb.append(line + "\n");      
	            }      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } finally {      
	            try {      
	                is.close();      
	            } catch (IOException e) {      
	               e.printStackTrace();      
	            }      
	        }      
	        return sb.toString();      
	    } 
}
