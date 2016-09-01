<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../../common/common-css.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.metadata.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript">
//表单验证
$().ready(function() {
 $("#form").validate({
	 
 });
 jQuery.validator.addMethod("isMobile", function(value, element) {    
     var length = value.length;    
     return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));    
   }, "请正确填写您的手机号码。");
});


/*function checkphone(){
	var chargeNum=jQuery("#businesstel").val();
	var a=/^[1]\d{10}$/;
	if(!a.test(chargeNum)){
		 $(".chargeNum").html("手机号码不能为空").show().fadeOut(5000);
		 $("#businesstel").focus();
		 alert("手机号格式不正确");
		 return false;
			  
			 }else{
				 return true;
			 }
}
//保存
function checkData() {
	if(checkphone()) {
		document.form.submit();
	}
}*/
//function validteForm() {
	//var businessname =jQuery("#businesstel").val()
	//if(!businessname){
	//	 alert("代理商名称不能为空");
	//}
	//var chargeNum=jQuery("#businesstel").val();
	//var a=/^[1]\d{10}$/;
	//if(!chargeNum){
		// $(".chargeNum").html("请输入手机号码！").show().fadeOut(5000);
		 //$("#chargeNum").focus();
	//	  alert("手机号不能为空");
	//}else{
	//	 if(!a.test(chargeNum)){
	//	   alert("手机号格式不正确");
	//	    return;
	//	 }
//	}
	
//}
//保存
//function checkData() {
//	if(!validteForm()) {
	//  return false;
	//}else{
	//document.form.submit();
	//}
//}
</script>
</head>
<body>
	<div style="height: 100%;overflow-y: auto;">
		<br /> <br />
		<form id="form"
			action="${pageContext.servletContext.contextPath }/background/product/add.action"
			method="post">
			<table class="ttab" height="100" width="50%" border="0"
				cellpadding="0" cellspacing="1" align="center">
				
				<tr>
					<td height="30" colspan="2" >
						<div align="center">
							<font color="blue" size="8"><b>添加信息</b>
							</font>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">运营商：</div></td>
						<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
						<select name="operator">
						<option>--请选则--：</option>
							<option value="联通">联通</option>
							<option value="移动">移动</option>
							<option value="电信">电信</option>
						</select>
						</div></td>
				</tr>
				<tr>
				<td height="30" width="30%">
						<div align="right" class="STYLE1">省份：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="province" value="${province}"/>
						</div></td>
					</tr>
				<tr>	
					<td height="30" width="30%">
						<div align="right" class="STYLE1">面值：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="parvalue" id="parvalue" class="required" />
						</div></td>
						</tr>
				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">是否显示：</div></td>
						<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
						<select name="showable">
						<option>--请选则--：</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">价格区间：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="price_scope"  id="price_scope" />
						</div></td>
						</tr>
				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">售价：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="price" />
						</div></td>
				</tr>
					
				<tr>
					<td colspan="2" style="padding: 30px">
						<div align="center">
							<input type="submit" value="　保　存　" class="input_btn_style1" > <input
								id="backBt" type="button" value="　返　回　" class="input_btn_style1"
								onclick="javascript:window.location.href='javascript:history.go(-1)'" />
						</div></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
