<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单查询后台管理</title>
<!-- 查询本数据库中的数据 -->
<%@include file="../../common/common-css.jsp" %>
<%@include file="../../common/common-js.jsp" %>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath }/css/fenyecss.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	
<script type="text/javascript">
</script>
</head>
<body>
<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/order/query.action" method="post">
<table width="100%">
  <tr>
    <td height="30" background="${pageContext.servletContext.contextPath }/images/tab_05.gif"><table width="100%">
      <tr>
        <td width="12" height="30"><img src="${pageContext.servletContext.contextPath }/images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%">
          <tr>
            <td width="46%" valign="middle"><table width="100%">
              <tr>
                <td width="5%"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：微信-订单查询</td>
              </tr>
            </table></td>
        </table></td>
        <td width="16"><img src="${pageContext.servletContext.contextPath }/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  <td align="center">
  <!-- 这里的表单 name 必须是fenye -->
  	<div class="search_k" align="left">
		<fieldset class="search">
			<legend><img src="${pageContext.servletContext.contextPath }/images/search_btn.gif" align="middle"/>&nbsp;<span class="STYLE1" style="color: blue;">高级查找</span></legend>
			<div class="search_content" style="line-height:3">
				手机号：<input type="text" name="phone_number" value="${param.phone_number}" style="height: 20px"/>　　
				微信平台订单号：<input type="text" name="inner_order_number" value="${param.inner_order_number}" style="height: 20px"/>　
				话费充值平台订单号：<input type="text" name="send_order_number" value="${param.send_order_number}" style="height: 20px"/>　
				<label>充值时间：</label><input type="text" name="startDate" id="startDate" value="${param.startDate}" onClick="WdatePicker()"  style="height: 20px"/>--<input type="text" name="endDate" id="endDate" value="${param.endDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd- HH:mm:ss'})"  style="height: 20px"/>
				<label>充值状态：</label><select name="order_status"  id="order_status">
					<option value="">--请选择--</option>
					 <option  value="1" <c:if test="${order_status == '1'}">selected="selected"</c:if>>未支付</option>
					 <option  value="2" <c:if test="${order_status == '2'}">selected="selected"</c:if>>已付款</option>
					 <option  value="3" <c:if test="${order_status == '3'}">selected="selected"</c:if>>处理中</option>
					 <option  value="4" <c:if test="${order_status == '4'}">selected="selected"</c:if>>成功</option>
					 <option  value="5" <c:if test="${order_status == '5'}">selected="selected"</c:if>>失败</option>
					 <option  value="6" <c:if test="${order_status == '6'}">selected="selected"</c:if>>需人工处理</option>
					 <option  value="7" <c:if test="${order_status == '7'}">selected="selected"</c:if>>已退款</option>
				</select>
				<input type="submit" value="开始查询" class="input_btn_style1"/>&nbsp;&nbsp;
			</div>
		</fieldset>
	</div>
  </td>
  </tr>
  <tr>
    <td><table class="listtable" width="100%">
      <tr>
        <td width="8" background="${pageContext.servletContext.contextPath }/images/tab_12.gif">&nbsp;</td>
        <td><table class="ttab" width="100%" cellspacing="1" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" class="STYLE1">手机号</td>
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" class="STYLE1">充值日期</td>
            <td width="8%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">充值金额</td>
            <td width="8%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">支付金额</td>
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">充值状态</td>
            <td width="20%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">微信平台订单号</td>
            <td width="20%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">话费充值平台订单号</td>
            <td width="14%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">描述信息</td>
          </tr>
          
          <c:forEach var="key" items="${pageView.records}">
          <tr>
             <td height="20" ><span class="STYLE1">${key.phone_number}</span></td>
             <td height="20" ><span class="STYLE1">${key.recharge_date}</span></td>
             <td height="20" ><span class="STYLE1">${key.money}</span></td>
             <td height="20" ><span class="STYLE1">${key.pay_money}</span></td>
             
             <td height="20" ><span class="STYLE1">
             <c:if test="${key.order_status == 1 }">未支付</c:if>
             <c:if test="${key.order_status == 2 }">已付款</c:if>
             <c:if test="${key.order_status == 3 }">处理中</c:if>
             <c:if test="${key.order_status == 4 }">成功</c:if>
             <c:if test="${key.order_status == 5 }">失败</c:if>
             <c:if test="${key.order_status == 6 }">需人工处理</c:if>
             <c:if test="${key.order_status == 7 }">已退款</c:if>
             </span></td>
             <td height="20" ><span class="STYLE1">${key.inner_order_number}</span></td>
             <td height="20" ><span class="STYLE1">${key.send_order_number}</span></td>
 			 <td height="20" ><span class="STYLE1">${key.describe_mes}</span></td>
          </tr>
          </c:forEach>
        </table></td>
        <td width="8" background="${pageContext.servletContext.contextPath }/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.servletContext.contextPath }/images/tab_19.gif"><%@include file="../../common/webfenye.jsp" %>
    </td>
  </tr>
</table>
</form>
</body>
</html>