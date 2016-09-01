<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>微信订单查询调用微信接口</title>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-js.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery_1_7_2_min.js"></script>
<script type="text/javascript">


</script>
</head>
<body>
<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/wxpaypubap/query.action" method="post">
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：微信订单查询</td>
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
			<div class="search_content">
				订单号：<input type="text" name="transaction_id" value="${param.transaction_id}" style="height: 20px"/>　　
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
 
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" class="STYLE1">返回状态码</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" class="STYLE1">返回信息</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">设备号</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">用户标识</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">是否关注公众账号</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易类型</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易状态</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">付款银行</td>
            
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">总金额</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">货币种类</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">现金支付金额</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">现金支付货币类型</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">微信支付订单号</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">商户订单号</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">支付完成时间</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易状态描述</td>
          </tr>
          <tr>
           <td height="20" ><span class="STYLE1">${pojo.return_code}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.return_msg}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.device_info}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.openid}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.is_subscribe}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.trade_type}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.trade_state}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.bank_type}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.total_fee}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.fee_type}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.cash_fee}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.cash_fee_type}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.transaction_id}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.out_trade_no}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.time_end}</span></td>
           <td height="20" ><span class="STYLE1">${pojo.trade_state_desc}</span></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
