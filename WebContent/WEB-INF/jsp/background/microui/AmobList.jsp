<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<%@ page import="com.miteno.util.ConstUtils" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<title>话费充值</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/css/index.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery_1_7_2_min.js"></script>
	<script type="text/javascript">
	var movalue;
	var appid = '<%= ConstUtils.APPID2%>';
	$(document).ready(function() {
		$("footer button").click(function(event) {
			var money =movalue;//面值
			if(money == null || typeof(money) =="undefined" || "" == money){
				money=defauParam;
			}
			var phone_number = $("#phone_number").val(); //电话号码
			if(phone_number ==""){
				 alert("手机号不能为空"); 
			        return false; 
			}
			if( phone_number.replace(/\D/g,'').length !=11 ){
				alert("请输入11位正确手机号"); 
		        return false; 
			}
			jQuery.ajax({
				type : 'post',
				url:'${pageContext.servletContext.contextPath }/background2/saveOrderData.action?showwxpaytitle=1',
				data:{"money":money,"phone_number":phone_number} ,
				success : function(data) {
				if(data.success){
					var  url ="https://open.weixin.qq.com/connect/oauth2/authorize?showwxpaytitle=1&appid="+appid+"&redirect_uri="+data.redirect_uri+"&response_type=code&scope=snsapi_base&state="+data.wxOrderNo+"#wechat_redirect";
					window.location.href=url;
				}else{
					alert(data.message);
				}
				},
				error : function(data) {
					alert("系统错误！请联系客服");
				}
			});
		});
		//默认第一个加上css样式
		$("ul li:eq(0)").addClass("cur");
		//如果不点击面值， 默认获取第一个值
		var defauParam = $('ul li:eq(0)').html();  
		//点击那个获取哪个li的value值
		$("li").each(function() {
	        $(this).click(function() {
	        	movalue  =  $(this).attr("value");
	       //alert(movalue);
			});
		})
		//获取全部优惠价信息
		<% List jsList = (List) request.getAttribute("listyhj");  %>
		  var codes = new   Array();   
		  // 将JAVA中的数组转换成JS 的数组
		  <%
		   if(jsList!=null)
		   {
		    for(int i=0;i<jsList.size();i++)
		    {
		  %>
		    codes[<%=i%>]='<%=jsList.get(i)%>';
		  <%   } 
		   }
		  %>
		//alert(arr);
		//var arr=["9.9~11.0","19.8~21.0","29.7~31.0","49.5~49.9","99.0~99.5","198.0~199.0","297.0~299","495.0~496"];
		$(".main ul li").each(function(index, el) {
			$(el).click(function(event) {
				$(this).addClass('cur').siblings('li').removeClass('cur');
				var index = $(".main ul li").index(this);
				$("footer p span").html((codes[index]));
			});
		});
		
	});
	//返回归属地
	//在本页面目前没有使用
	function showPay(){
		var phone_number = $("#phone_number").val(); //电话号码
		if( phone_number.replace(/\D/g,'').length==11 ){
			jQuery.ajax({
				type : 'post',
				//dataType:'json',
				url:'${pageContext.servletContext.contextPath }/background2/searchghd.action',
				data:{"param":phone_number} ,
				success : function(data) {
					//alert(data.userName);
					//$("#Belonging").val(data.belonging); 
					$("#Belonging").html(data.belonging); 
				},
				error : function(data) {
					alert("手机号前七位格式不支持");
				}
			});
		//}
	}else{
		$("#Belonging").html(""); 
	}
	}

	</script>
	
</head>
<body>
	<nav>
		<p class="nav-l cur"><a href="${pageContext.servletContext.contextPath }/background2/query.action">话费充值</a></p>
		<p class="nav-r"><a href="${pageContext.servletContext.contextPath }/background2/searchHistory.action">订单查询</a></p>
	</nav>
	<!--  <form id="form" action="${pageContext.servletContext.contextPath }/background2/query.action"
						method="post">-->
	<div class="phone-num">
		<input type="text" placeholder="手机号码(江苏联通)" required="required" name="phone_number"  id="phone_number" onkeyup="this.value=this.value.replace(/\D/g,'')"  maxlength="11">
		 <!-- <p class="red"><input type="text" class="tips" id="Belonging" style="BACKGROUND-COLOR:#EEEEEE"></p>  -->
		<!--<p id="Belonging"></p> -->
	</div>
	<div class="main">
		<ul id="ulId">
		<c:forEach var="fm" items="${plist }">
			<li value="${fm}">${fm}</li>
		</c:forEach>
		
			<!--<li class="cur">10元</li>
			<li>20元</li>
			<li>30元</li>
			<li>50元</li>
			<li>100元</li>
			<li>200元</li>
			<li>300元</li>
			<li>500元</li>  -->
		</ul>
	</div>
	<!--  </form>-->
	<div class="tips">
		<p>温馨提示：</p>
		<p>1、充值提示：月初月末运营商充值系统繁忙，话费到账时间可能会出现延迟；</p>
		<p>2、部分特殊状态的手机号（空号、停机等）造成无法充值，将会进行退款；</p>
		<p>3、客服qq联系方式：2824545434</p>
	</div>
	<br/>
	<br/>
	<br/>
	<footer>
		<p>优惠价
			<span>${def}</span>
		</p>
		<button>付款</button>
	</footer>
</body>
</html>