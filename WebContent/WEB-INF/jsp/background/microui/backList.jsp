<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>五秒后返回订单查询页面</title>
<script type="text/javascript"> 
var phone_number = '${phoneNo}';
function countDown(secs,surl){     
 //alert(surl);     
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs;  
 if(--secs>0){     
     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
     }     
 else{       
     location.href=surl;     
     }     
 }     
</script> 
 <style type="text/css">
      body
		{
		  text-align:center; 
		  width:90%;
		  margin:0 atuto;
		}
    </style>
</head>

<body style="font-size:90px"><center> <span id="jumpTo">5</span>五秒后返回订单查询页面
<script type="text/javascript">countDown(5,"${pageContext.servletContext.contextPath }/background2/searchHistory.action?phone_number="+phone_number+"");</script>  
</center>
</body>
</html>