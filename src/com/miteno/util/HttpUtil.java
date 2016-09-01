package com.miteno.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * http 请求工具类
 * 
 */
public class HttpUtil {

	private static final Log logger = LogFactory.getLog(HttpUtil.class);

	private HttpClient httpClient = null;

	private static final HttpUtil instance = new HttpUtil();

	public static HttpUtil getInstance() {
		return instance;
	}

	private HttpUtil() {
		if (httpClient == null) {
			MultiThreadedHttpConnectionManager conn = new MultiThreadedHttpConnectionManager();
			HttpConnectionManagerParams params = conn.getParams();
			params.setConnectionTimeout(30000); // 连接超时 时间 20秒
			params.setSoTimeout(30000); // 已连接上，但读取超时 时间 30秒
			params.setDefaultMaxConnectionsPerHost(1024);
			params.setMaxTotalConnections(2048);
			params.setReceiveBufferSize(4096);
			params.setSendBufferSize(4096);
			httpClient = new HttpClient(conn);
			httpClient.getHttpConnectionManager().setParams(params);

			httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			httpClient.getParams().setVersion(HttpVersion.HTTP_1_1);
		}
	}

	private NameValuePair[] map2Data(Map<String, String> data) {
		int count = 0;

		NameValuePair[] pairs = new NameValuePair[data.size()];
		for (Iterator<String> it = data.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			String value = data.get(key);

			pairs[count] = new NameValuePair(key, value);

			count++;
		}

		return pairs;
	}

	public HttpResult post(String url, String charset, Map<String, String> data)
			throws Exception {
		return post(url, charset, data, null);
	}

	public HttpResult post(String url, String charset, NameValuePair[] data)
			throws Exception {
		return post(url, charset, data, null);
	}

	public HttpResult post(String url, String charset,
			Map<String, String> param, Map<String, String> headerMap)
			throws Exception {

		if (param != null) {
			NameValuePair[] data = map2Data(param);
			return post(url, charset, data, headerMap);
		} else {
			return post(url, charset, new NameValuePair[0], headerMap);
		}
	}

	public HttpResult post(String url, String charset, NameValuePair[] data,
			Map<String, String> headerMap) throws Exception {
		PostMethod post = new PostMethod(url);
		HttpMethodParams hmp = new HttpMethodParams();
		hmp.setContentCharset(charset);
		post.setParams(hmp);
		setRequestHeader(post, headerMap);
		if (data != null) {
			post.setRequestBody(data);
		}
		return getResponseBodyAsStream(post, charset);
	}

	public HttpResult postXml(String url, String charset, String xml)
			throws Exception {
		return postXml(url, charset, xml, null);
	}

	public HttpResult postXml(String url, String charset, String xml,
			Map<String, String> headerMap) throws Exception {
		PostMethod post = new PostMethod(url);
		HttpMethodParams hmp = new HttpMethodParams();
		hmp.setContentCharset(charset);
		post.setParams(hmp);
		setRequestHeader(post, headerMap);
		post.setRequestEntity(new StringRequestEntity(xml, "text/xml", charset));
		return getResponseBodyAsStream(post, charset);
	}

	public HttpResult postFile(String url, String charset,
			NameValuePair[] data, Part[] parts) throws Exception {
		return postFile(url, charset, data, parts, null);
	}

	public HttpResult postFile(String url, String charset,
			NameValuePair[] data, Part[] parts, Map<String, String> headerMap)
			throws Exception {
		PostMethod post = new PostMethod(url);
		HttpMethodParams hmp = new HttpMethodParams();
		hmp.setContentCharset(charset);
		post.setParams(hmp);
		setRequestHeader(post, headerMap);
		if (data != null) {
			post.setRequestBody(data);
		}
		if (parts != null) {
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
		}
		return getResponseBodyAsStream(post, charset);
	}

	/**
	 * 根据指定的url和字符集发起http
	 * GET请求，并返回HttpResult，通过HttpResult.getContent()可以获取返回结果的内容 <br>
	 * author：sl <br>
	 * date：Dec 28, 2011 7:22:40 PM <br>
	 * version：V1.0.0
	 * 
	 * @param url
	 *            要通过GET方式访问的url
	 * @param charset
	 *            指定字符集
	 * @return
	 * @throws Exception
	 *             ： <br>
	 *             ModifyRecord： <br>
	 *             1、ibm - Dec 28, 2011 7:22:40 PM ：
	 */
	public HttpResult get(String url, String charset) throws Exception {
		return get(url, charset, null);
	}

	public HttpResult get(String url, String charset,
			Map<String, String> headerMap) throws Exception {

		GetMethod get = new CustomGetMethod(url);

		HttpMethodParams hmp = new HttpMethodParams();
		hmp.setContentCharset(charset);
		setRequestHeader(get, headerMap);
		get.setParams(hmp);

		int result;
		String sresult;
		try {
			result = httpClient.executeMethod(get);

			sresult = get.getResponseBodyAsString();

		} finally {
			if (get != null) {
				get.releaseConnection();
			}
			get = null;
		}
		HttpResult httpResult = new HttpResult();
		httpResult.setStateCode(result);
		httpResult.setContent(sresult);
		return httpResult;
	}

	public int head(String url) throws Exception {
		return head(url, null);
	}

	public int head(String url, Map<String, String> headerMap) throws Exception {
		HeadMethod head = new HeadMethod(url);
		int stateCode = 0;
		try {
			setRequestHeader(head, headerMap);
			stateCode = httpClient.executeMethod(head);
		} finally {
			if (head != null) {
				head.releaseConnection();
			}
		}
		return stateCode;
	}

	private void setRequestHeader(HttpMethod method, Map<String, String> map) {
		// method.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE
		// 6.0; Windows NT 5.1; SV1)");
		if (map != null) {
			for (Map.Entry<String, String> m : map.entrySet()) {
				method.setRequestHeader(m.getKey(), m.getValue());
			}
		}
	}

	/**
	 * 下载文件到指定路径
	 * 
	 * @param url
	 * @param savePathFileName
	 * @throws Exception
	 */
	public void getAndSaveData(String url, String savePathFileName)
			throws Exception {
		GetMethod httpMethod = new GetMethod(url);
		HttpMethodParams hmp = new HttpMethodParams();
		httpMethod.setParams(hmp);
		BufferedInputStream in = null;
		BufferedOutputStream fos = null;
		File file = null;
		try {
			file = new File(savePathFileName);
			// 建立文件目录结构
			file.getParentFile().mkdirs();
			fos = new BufferedOutputStream(new FileOutputStream(file));
			int result = httpClient.executeMethod(httpMethod);
			if (result == 200 || result == 301 || result == 302) {
				in = new BufferedInputStream(
						httpMethod.getResponseBodyAsStream());
				byte[] b = new byte[4096];
				int count;
				while ((count = in.read(b)) > 0) {
					fos.write(b, 0, count);
				}
				fos.flush();
			} else {
				throw new Exception("出错，错误http状态代码:" + result + "  url=" + url
						+ "  savePathFileName=" + savePathFileName);
			}
		} finally {
			if (httpMethod != null) {
				httpMethod.releaseConnection();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
				in = null;
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				fos = null;
			}
			if (file != null) {
				file = null;
			}
		}
	}

	private HttpResult getResponseBodyAsStream(HttpMethod httpMethod,
			String charset) throws Exception {
		StringBuffer sb = new StringBuffer();
		int result;
		BufferedReader in = null;
		try {
			result = httpClient.executeMethod(httpMethod);
			in = new BufferedReader(new InputStreamReader(
					httpMethod.getResponseBodyAsStream(), charset));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (httpMethod != null) {
				httpMethod.releaseConnection();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
				in = null;
			}
		}
		HttpResult httpResult = new HttpResult();
		httpResult.setStateCode(result);
		httpResult.setContent(sb.toString());
		return httpResult;
	}

	/**
	 * 对参数按指定的字符集进行编码，形成http请求的参数格式，例如：
	 * test=1&UserID=1&CafeID=0&CafeIP=&ServerIP=&ClientIP=127.0.0.1 <br>
	 * author：sl <br>
	 * date：Dec 28, 2011 7:12:44 PM <br>
	 * version：V1.0.0
	 * 
	 * @param pairs
	 *            参数对
	 * @param charset
	 *            字符集，例如：GBK
	 * @return 
	 *         返回编码后的http请求参数串，例如：test=1&UserID=1&CafeID=0&CafeIP=&ServerIP=&ClientIP
	 *         =127.0.0.1
	 * @throws UnsupportedEncodingException
	 *             ： <br>
	 *             ModifyRecord： <br>
	 *             1、ibm - Dec 28, 2011 7:12:44 PM ：
	 */
	public static String doFormUrlEncode(NameValuePair[] pairs, String charset)
			throws UnsupportedEncodingException {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < pairs.length; i++) {
			URLCodec codec = new URLCodec();
			NameValuePair pair = pairs[i];
			if (pair.getName() != null) {
				if (i > 0) {
					buf.append("&");
				}
				buf.append(codec.encode(pair.getName(), charset));// 对参数名称进行编码
				buf.append("=");
				if (pair.getValue() != null) {
					buf.append(codec.encode(pair.getValue(), charset));// 对参数值进行编码
				}
			}
		}
		return buf.toString();
	}

	class CustomGetMethod extends GetMethod {

		public CustomGetMethod(String uri) {
			super(uri);
		}

		/**
		 * Get response as string whether response is GZipped or not
		 * 
		 * @return
		 * @throws IOException
		 */
		@Override
		public String getResponseBodyAsString() throws IOException {

			if (getResponseBody() == null && getResponseStream() == null)
				return null;

			if (!isGziped())
				return super.getResponseBodyAsString();

			StringBuffer sb = new StringBuffer();
			GZIPInputStream gzin = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			// For GZip response
			try {
				gzin = new GZIPInputStream(getResponseBodyAsStream());

				isr = new InputStreamReader(gzin, getResponseCharSet());
				br = new BufferedReader(isr);

				String tempbf;
				while ((tempbf = br.readLine()) != null) {
					sb.append(tempbf);
					sb.append("\r\n");
				}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			return sb.toString();
		}

		private boolean isGziped() {
			if (getResponseHeader("Content-Encoding") != null
					&& getResponseHeader("Content-Encoding").getValue()
							.toLowerCase().indexOf("gzip") > -1)
				return true;

			return false;
		}

	}
}
