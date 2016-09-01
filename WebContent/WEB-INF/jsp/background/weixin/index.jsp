<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<title>话费充值</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/css/index.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery_1_7_2_min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$("footer button").click(function(event) {
			window.location.href="${pageContext.servletContext.contextPath }/background2/pay.action";
		});
		
		
		var arr=["9.9~11.0","19.8~21.0","29.7~31.0","49.5~49.9","99.0~99.5","198.0~199.0","297.0~299","495.0~496"];
		$(".main ul li").each(function(index, el) {
			$(el).click(function(event) {
				$(this).addClass('cur').siblings('li').removeClass('cur');
				var index = $(".main ul li").index(this);
				$("footer p span").html((arr[index]));
			});
		});
		
	});
	</script>
	
</head>
<body>
	<nav>
		<p class="nav-l cur"><a href="${pageContext.servletContext.contextPath }/background2/index.action">话费充值</a></p>
		<p class="nav-r"><a href="${pageContext.servletContext.contextPath }/background2/order.action">订单查询</a></p>
	</nav>
	<div class="phone-num">
		<input type="text" placeholder="请输入您所充值的手机号码" required="required">
		<!-- <p class="red">北京 移动</p> -->
	</div>
	<div class="main">
		<ul>
			<li class="cur">10元</li>
			<li>20元</li>
			<li>30元</li>
			<li>50元</li>
			<li>100元</li>
			<li>200元</li>
			<li>300元</li>
			<li>500元</li>
		</ul>
	</div>
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
			<span>9~10</span>
		</p>
		<button>付款</button>
	</footer>
</body>
</html>