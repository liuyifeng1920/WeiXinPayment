<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<%@ page import="com.miteno.util.ConstUtils" %>
<!DOCTYPE html>
<html lang="en">
<head><!--获取授权界面code值 -->
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<title></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/css/index.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery_1_7_2_min.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function () {
		var appid = '<%= ConstUtils.APPID2%>';
	//	alert(appid);
		var redirect_uri = '${redirect_uri}';
		//alert(redirect_uri);
		var wxOrderNo = '${wxOrderNo}';
		//alert(wxOrderNo);
		//window.location.href
		var  url ="https://open.weixin.qq.com/connect/oauth2/authorize?showwxpaytitle=1&appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state="+wxOrderNo+"#wechat_redirect";
		//alert(url);
		window.location.href=url;
	
			//window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2F50dbfa34.ngrok.io%2FWeiXinPayment%2Fbackground%2Fmicroui%2FbackCode.action&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		}); 
	//获取code需要参数

	
		//window.location.href="${pageContext.servletContext.contextPath }/background2/backCode.action?code="+code;
	</script>
	
</head>
<body>
	
</body>
</html>