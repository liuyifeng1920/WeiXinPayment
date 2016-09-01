<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.miteno.util.ConstUtils" %>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<title>支付</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/css/index1.css">
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery_1_7_2_min.js"></script>
	<script type="text/javascript">
	var phoneNo = '${phoneNo}';
	$(document).ready(function() {
		$("#surebtn").click(function(event) {
			Gettishi();
			if(Gettishi() == true){
			 var appid = '<%= ConstUtils.APPID2%>';
		        var prepay_id = '${packageParam}';
		        var nonceStr = '${nonceStr}';
		        var timestamp =  '${timeStamp}';
		        var paySign = '${paySign}';
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
			}
		});
		function Gettishi(){
			var _ischeck=$('.partthree-r input[type=radio]').is(':checked'); 

			if(_ischeck){			
				$(".tishi").css("display","none");
				 return true;
			}
			else{			
				$(".tishi").css("display","block");
			}

		}
		$(".partthree-r input").change(function(event) {
			Gettishi();
			//选择支付方式
			//alert(2222);
		});

	});
	</script>
	
	
</head>
<body>
	<div class="partone-wrap">
		<div class="partone">
		<div class="partone-l">
			<p>${firstMes}</p>
			<span>${recharge_date}</span>
		</div>
		<div class="partone-r">
			<p><label>优惠金额</label>${pay_money}</p>
		</div>
		</div>
	</div>
	<div class="parttwo-wrap">
		<div class="parttwo">
			<p><label>交易订单号：</label>${porderNo}</p>
			<p><label>归属地：</label>${Belonging}</p>
			<p>服务商：梅泰诺（北京）移动信息技术有限公司</p>
		</div>
	</div>
	<div class="partthree">
		<div class="partthree-l">
			<img src="${pageContext.servletContext.contextPath }/images/weixin/weixin.png" alt="">
		</div>
		<div class="partthree-m">
			<p>微信支付</p>
			<span>微信直接支付</span>
		</div>
		<div class="partthree-r">
			<input type="radio" name="" value="" >
		</div>
	</div>
	<div class="tishi">
		<p>您未选择支付方式</p>
	</div>
	<footer>
		<p>支付金额
			<span>${pay_money}</span>
		</p>
		<button id="surebtn">确认付款</button>
	</footer>
</body>
</html>