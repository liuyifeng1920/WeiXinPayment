package com.miteno.controller.weixin;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.miteno.util.GetWxOrderno;

/**
 * 测试方法
 */
public class WxPubMeth {
	public static void main(String[] args) throws ParseException, Exception {
		String wxtkUrl ="https://api.mch.weixin.qq.com/pay/refundquery";
		String xml = "<xml>"
				+ "<appid>wx184bb69065e9bdef</appid>" 
				+ "<mch_id>sopaylife</mch_id>" 
				+ "<nonce_str>0950528162</nonce_str>" 
				+ "<sign>487B2CE88DDF79615FAB37EBF3D44D00</sign>"
				+"<transaction_id>transaction_id</transaction_id>"
				+"<out_trade_no>20150507115225115225</out_trade_no>"
				+"<out_refund_no>20150507115225115225</out_refund_no>"
				+"<refund_id>20150507115225115225</refund_id>"
					+"</xml>";
		HttpClient  httpclient = new DefaultHttpClient();
		HttpPost  httpPost = new HttpPost(wxtkUrl);
		  Map map = null;
		try {
			httpPost.setEntity(new StringEntity(xml, "UTF-8"));
			 org.apache.http.HttpResponse response = httpclient.execute(httpPost);
			 String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			 System.out.println("jsonStr"+jsonStr);
			     map = GetWxOrderno.doXMLParse(jsonStr);
			     System.out.println("map"+map);
			    //returnCode  = (String) map.get("return_code");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	//创建qianming
	public static String createSign(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
		String appsign = getSha1(params);
		return appsign;

	}
	
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Map<String, String> httpclientDataMap(String url, String xmlParam) throws Exception, Exception{
		HttpClient  httpclient = new DefaultHttpClient();
		HttpPost  httpPost = new HttpPost(url);
		  Map map = null;
		try {
			httpPost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			 org.apache.http.HttpResponse response = httpclient.execute(httpPost);
			 String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			 System.out.println("jsonStr"+jsonStr);
			     map = parseXmlToList2(jsonStr);
			     return map;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
	}
	public static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
}
