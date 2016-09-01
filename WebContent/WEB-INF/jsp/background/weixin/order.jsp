<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<title>订单查询</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/css/index.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery_1_7_2_min.js"></script>
	
</head>
<body>
	<nav>
		<p class="nav-l"><a href="${pageContext.servletContext.contextPath }/background2/index.action">话费充值</a></p>
		<p class="nav-r cur"><a href="${pageContext.servletContext.contextPath }/background2/order.action">订单查询</a></p>
	</nav>
	<div class="phone-num">
		<input type="text" placeholder="请输入手机号码" required="required">
		<input type="text" class="sang_Calender"  placeholder="年/月/日" required="required">
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/datetime.js"></script>
		<button class="cur">查　　询</button>
	</div>
	<div class="result">
		<table>
			<tr>
				<th>充值号码</th>
				<th>充值金额</th>
				<th>支付金额</th>
				<th>充值日期</th>
				<th>充值状态</th>
			</tr>
			<!-- <tr>
				<td>13641138223</td>
				<td>10元</td>
				<td>9.8元</td>
				<td>2015/04/27</td>
				<td>成功</td>
			</tr>
			<tr>
				<td>13641138223</td>
				<td>10元</td>
				<td>9.8元</td>
				<td>2015/04/27</td>
				<td>成功</td>
			</tr>
			<tr>
				<td>13641138223</td>
				<td>10元</td>
				<td>9.8元</td>
				<td>2015/04/27</td>
				<td>成功</td>
			</tr>
			<tr>
				<td>13641138223</td>
				<td>10元</td>
				<td>9.8元</td>
				<td>2015/04/27</td>
				<td>成功</td>
			</tr>
			<tr>
				<td>13641138223</td>
				<td>10元</td>
				<td>9.8元</td>
				<td>2015/04/27</td>
				<td>成功</td>
			</tr> -->
		</table>
	</div>
	<div class="more">
		<p>更多记录</p>
		<i>
            <em></em>
            <span></span>
        </i>
	</div>
</body>
</html>