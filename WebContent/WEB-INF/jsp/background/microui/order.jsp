<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<title>订单查询</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/css/index.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery_1_7_2_min.js"></script>
	<script type="text/javascript">
	 function  checkData(){
		 var phone_number =  $("#phone_number").val();
		 var recharge_date =  $("#recharge_date").val();
		 
			if(phone_number ==""){
				 alert("手机号不能为空"); 
			        return false; 
			}
			if( phone_number.replace(/\D/g,'').length !=11 ){
				alert("请输入11位正确手机号"); 
		        return false; 
			}
		   // if(!(/^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/.test(phone_number))){ 
		     //   alert("请输入正确的手机号"); 
		       // return false; 
		   /// } 
			 window.location.href="${pageContext.servletContext.contextPath }/background2/searchHistory.action?phone_number="+phone_number+"&recharge_date="+recharge_date;

	 }
	</script>
</head>
<body>
	<nav>
		<p class="nav-l"><a href="${pageContext.servletContext.contextPath }/background2/query.action">话费充值</a></p>
		<p class="nav-r cur"><a href="${pageContext.servletContext.contextPath }/background2/searchHistory.action">订单查询</a></p>
	</nav>
	<div class="phone-num">
		<input type="text" placeholder="手机号码(江苏联通)" required="required" class="isMobile" id="phone_number" maxlength="11"  onkeyup="this.value=this.value.replace(/\D/g,'')">
		<input type="text" class="sang_Calender"  placeholder="年/月/日" required="required" id="recharge_date">
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/datetime.js"></script>
		<button class="cur" onclick="checkData()">查　　询</button>
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
		</table>
	</div>
	
	<!-- <div class="more">
		<p>更多记录</p>
		<i>
            <em></em>
            <span></span>
        </i>
	</div> -->
</body>
</html>