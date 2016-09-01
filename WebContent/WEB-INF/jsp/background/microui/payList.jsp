<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="com.miteno.util.ConstUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/css/index.css">
<!-- <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>-->
<title>付款页面</title>
<script type="text/javascript">
var phoneNo = '${phoneNo}';
alert(phoneNo);
//手机号
//付款
	function onpay(){
		  var appid = '<%= ConstUtils.APPID2%>';
		// alert(appid);
	        var prepay_id = '${packageParam}';
	   	//alert(prepay_id);
	        var nonceStr = '${nonceStr}';
	       //alert(nonceStr);
	        var timestamp =  '${timeStamp}';
	       //alert(timestamp);
	        var paySign = '${paySign}';
	      //alert(paySign);
		//点击测试,注意参数是demo中生成的package
		    WeixinJSBridge.invoke('getBrandWCPayRequest',{
		    	//请注意，这个地方放Demo.java中生成的package
		    	
				  "appId":appid,  		//公众号id
				  "timeStamp":timestamp,   //时间戳
				  "nonceStr":nonceStr, 	//随机字符串
				  "package":prepay_id,		//订单详情扩展字符串
				  "signType":"MD5",	//签名方式
				  "paySign":paySign		//签名
		    },
		    function(res){
		    	WeixinJSBridge.log(res.err_msg);
				var msg = res.err_msg;
		       //支付成功或失败前台判断
		        if(msg == "get_brand_wcpay_request:ok" ) {


		    	   alert('恭喜您，支付成功!');
					window.location.href="${pageContext.servletContext.contextPath }/background2/back.action?phoneNo="+phoneNo+"";
		       }else if(msg ==  "get_brand_wcpay_request:cancel"){
		    	   alert('支付过程中用户取消');
		       }else{
		    	   alert('支付失败');
		       }

		     });
	};
	if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady();
		}
	//获取订单状态
	function getOrderInfo(){
		if(porderNo !=null){
			jQuery.ajax({
				type :'post',
				url:'',
				data:{"porderNo":porderNo},
				success : function(data) {
					//alert(data.userName);
					//$("#Belonging").val(data.belonging); 
					alert("支付成功");
				},
				error : function(data) {
					alert("支付失败");
				}
			});
		}
	}
//取消
	function onHis(){
		//window.history.back(-1);
		window.location.href="${pageContext.servletContext.contextPath }/background2/query.action"; 
		//$('#aaa').html('jhagdsagdgdjagfjgfa');
	}

</script>
</head>
<body>
 <!-- <form id="form"  action="${pageContext.servletContext.contextPath }/background2/query.action" method="post"> -->
	<table style=" width=:100%; height:70%;"align="center">
		<tr>
			<td style="font-size:30px">订单号:<label style="color:red">${pd.inner_order_number}</label></td>
		</tr>
		<tr>
			<td style="font-size:30px">手机号:<label style="color:red">${pd.phone_number}</label></td>
		</tr>
		<tr>
			<td style="font-size:30px">归属地:<label style="color:red">${Belonging}</label></td>
		</tr>
		<tr>
			<td style="font-size:30px">金  额 :<label style="color:red">${pd.money}</label></td>
		</tr>
		<tr>
			<td style="font-size:30px">服务商:<label style="color:red">梅泰诺（北京）移动信息技术有限公司</label></td>
		</tr>
		<tr>

			<td><input type="button" value="付款"  style="font-size:30px" onclick="onpay()"></td>
			<td><input type="button" value="取消"  style="font-size:30px" onclick="onHis()"></td>

		</tr>
	</table> 
 <!-- </form> -->
</body>
</html>