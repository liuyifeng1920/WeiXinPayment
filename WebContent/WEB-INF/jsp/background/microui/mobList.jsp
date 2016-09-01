<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单</title>
<%@include file="../../common/common-css.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery_1_7_2_min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.metadata.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/messages_cn.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
/*$().ready(function() {
	 $("#phonePay").validate({
		 
	 });
	 jQuery.validator.addMethod("isMobile", function(value, element) {    
	     var length = value.length;    
	     return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));    
	   }, "请正确填写您的手机号码。");
	});*/
function showPay(){
	var sMobile =  $("#phonePay").val();
	if(sMobile ==""){
		 alert("手机号不能为空"); 
	        return false; 
	}
    if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){ 
        alert("不是完整的11位手机号或者正确的手机号前七位"); 
        //$("#phonePay").focus(); 
        return false; 
    } 
	//返回归属地
	var param = $("#phonePay").val();
		jQuery.ajax({
			type : 'post',
			//dataType:'json',
			url:'${pageContext.servletContext.contextPath }/background2/searchghd.action',
			data:{"param":param} ,
			success : function(data) {
				//alert(data.userName);
				$("#Belonging").val(data.belonging); 
	
			},
			error : function(data) {
				alert(123);
			}
		});
}
	//返回 优惠价
	function MyButton(){
		
		jQuery.ajax({
			type : 'post',
			//dataType:'json',
			url:'${pageContext.servletContext.contextPath }/background2/searchyhj.action',
			data:{"param":faceMoney} ,
			success : function(data) {
				//优惠价自动赋值
				$("#yhj").val(data.yhj); 
	
			},
			error : function(data) {
				alert(123);
			}
		});
	}

</script>
</head>
<body>
	<div style="height: 100%;overflow-y: auto;">
		<br /> <br />
		<form id="form"
			action="${pageContext.servletContext.contextPath }/background2/query.action"
			method="post">
			
			<table class="ttab" height="100" width="50%" border="0"
				cellpadding="0" cellspacing="1" align="center">
				<tr>
					<td height="30" colspan="1" >
						<div align="center">
							<font color="blue" size="8"><b>话费充值</b>
							</font>
						</div></td>
				</tr>
				<tr><!-- 手机号码-->
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="phonePay"  id="phonePay" onchange ="showPay()"/>
						</div></td>
				</tr>
				<tr><!-- 归属地+运营商 -->
						<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="Belonging"  id="Belonging" value="" />
						</div></td>
						</tr>
						
					<c:forEach var="k" items="${plist}" begin="0"  step="1" varStatus="conut">
						<td> <input type="button" id="step" name="faceMoney" value="${k}元" onclick="MyButton()" ></td>
					</c:forEach>
		
					<tr>
					<td ><label>优惠价</label><input id="yhj" value=""></td>
					<td ><input type="button"  value="付款"  onclick="fk"></td>
					</tr>	
					<!-- 
				<tr>
					<td colspan="1" >
						<div align="center">
							<input type="button" onclick="" value="　保　存　" class="input_btn_style1" > <input
								id="backBt" type="button" value="　返　回　" class="input_btn_style1"
								onclick="javascript:window.location.href='javascript:history.go(-1)'" />
						</div></td>
				</tr>	 -->	
				</table>
				</form>
				<table>	
					<form id="form" action="${pageContext.servletContext.contextPath }/background2/searchHistory.action"
						method="post">
				<tr>
					<td height="30" colspan="1" >
						<div align="center">
							<font color="blue" size="8"><b>订单查询</b>
							</font>
						</div></td>
				</tr>	
					
				<tr><!-- 手机号码-->
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							手机号&nbsp&nbsp&nbsp<input style="height: 20px;width: 200px" name="phone_number"  id="phone_number" value="${param.phone_number}"/>
						</div></td>
				</tr>
				<tr><!-- 查询日期 -->
						<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							查询日期&nbsp<input style="height: 20px;width: 200px" name="recharge_date"  id="recharge_date" onClick="WdatePicker()" value="${param.recharge_date}"/>
						</div></td>
						</tr>	
				<tr>
				<td colspan="1" >
						<div align="left">
							&nbsp&nbsp&nbsp&nbsp<input type="submit"  	class="input_btn_style1" >
						</div></td>
				</tr>
						
				<tr>
				 <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">充值号码</td>
				 <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">充值金额</td>
				 <!-- <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">支付金额</td>
				  -->
				  <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">充值日期</td>
				 <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">充值状态</td>

				</tr>		
				
				<c:forEach  var="key" items="${pageView.records}">
				<tr>
		             <td height="20" width="15%"><span class="STYLE1">${key.phone_number}</span></td>
		             <td height="20" width="15%"><span class="STYLE1">${key.money}</span></td>
		             <td height="20" width="15%"><span class="STYLE1">${key.recharge_date}</span></td>
		             <td height="20" width="15%"><span class="STYLE1">${key.order_status}</span></td>
		             
		            </tr>
				</c:forEach>
				
				
				
						
				  <tr>
    			<td height="35" width="50%" background="${pageContext.servletContext.contextPath }/images/tab_19.gif"><%@include file="../../common/webfenye.jsp" %>
    </td>
  </tr>		
		</form>	
		
			</table>
		
	</div>
</body>
</html>
