<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../../common/common-css.jsp"%>
</head>

<body>
	<div style="height: 100%;overflow-y: auto;">
		<br /> <br />
		<form
			action="${pageContext.servletContext.contextPath }/background/billing/update.action"
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
						<div align="right" class="STYLE1">计费渠道id：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="appid" value="${bilChan.appid}" readonly="readonly"/>
						</div></td>
					</tr>
				<tr>	
					<td height="30" width="30%">
						<div align="right" class="STYLE1">计费渠道名称：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="appname" value="${bilChan.appname}"/>
						</div></td>
						</tr>

				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">app标识：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="appkey" value="${bilChan.appkey}"/>
						</div></td>
						</tr>
				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">app秘钥：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="appsecret" value="${bilChan.appsecret}"/>
						</div></td>
				</tr>
				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">开发者用户ID：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="platformID" value="${bilChan.platformID}" />
						</div></td>
				</tr>
				<tr>
					<td height="30" width="30%">
						<div align="right" class="STYLE1">开发者调用密码：</div></td>
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
							<input style="height: 20px;width: 200px" name="password" value="${bilChan.password}" />
						</div></td>
				</tr>
				<tr>
					<td colspan="2" style="padding: 30px">
						<div align="center">
							<input type="submit" value="　保　存　" class="input_btn_style1" /> <input
								id="backBt" type="button" value="　返　回　" class="input_btn_style1"
								onclick="javascript:window.location.href='javascript:history.go(-1)'" />
						</div></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
