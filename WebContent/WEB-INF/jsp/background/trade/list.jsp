<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../../common/common-css.jsp" %>
<%@include file="../../common/common-js.jsp" %>

<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath }/css/fenyecss.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.metadata.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/messages_cn.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">

/* $(function(){
	 $("#startDate").ligerDateEditor(
             {
                 format: "yyyy-MM-dd",
               
                 cancelable : true
         });
	  $("#endDate").ligerDateEditor(
              {
                  format: "yyyy-MM-dd",
                  labelWidth: 100,
                  labelAlign: 'center',
                  //showTime:true,
                  cancelable : false
                 
          });
	
});*/
function permissio(id){
	 var url = "${pageContext.servletContext.contextPath }/background/resources/permissioUser.action?userId="+id;
	 var h_sp1 = 400;
	 var w_sp1 = 350;
	//兼容IE，firefox,google.模态窗口居中问题
	 var iTop2 = (window.screen.availHeight - 20 - h_sp1) / 2;
	 var iLeft2 = (window.screen.availWidth - 10 - w_sp1) / 2;
	 var params = 'menubar:no;dialogHeight=' + h_sp1 + 'px;dialogWidth=' + w_sp1 + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scrollbars=0;resizeable=0;center=yes;location:no;status:no;scroll:no'
	 window.showModalDialog(url, window, params);
	 //location.href=url;
}
function userRole(id){
	 var url = "${pageContext.servletContext.contextPath }/background/user/userRole.action?userId="+id;
	 var h_sp1 = 420;
	 var w_sp1 = 600;
	//兼容IE，firefox,google.模态窗口居中问题
	 var iTop2 = (window.screen.availHeight - 20 - h_sp1) / 2;
	 var iLeft2 = (window.screen.availWidth - 10 - w_sp1) / 2;
	 var params = 'menubar:no;dialogHeight=' + h_sp1 + 'px;dialogWidth=' + w_sp1 + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scrollbars=0;resizeable=0;center=yes;location:no;status:no;scroll:no'
	 window.showModalDialog(url, window, params);
	 //location.href=url;
}

            
</script>
</head>
<body>
<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/trade/query.action" method="post">
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：交易记录-交易记录查询</td>
              </tr>
            </table></td>
            <td width="54%"><table align="right" >
             <!--   <tr>
                <td width="60"><table width="87%" >
                  <tr>
                    <td class="STYLE1"><div align="center">
                      <input type="checkbox" name="checkbox11" id="choseAll" onclick="selectAllCheckBox()" />
                    </div></td>
                    <td class="STYLE4">全选</td>
                  </tr>
                </table></td>
                <td width="52"><table width="88%">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/11.gif" width="14" height="14" /></div></td>
                    <td class="STYLE4">
                    <a href="javascript:void(0);"  onclick="return deleteAll()">
                    	删除
                    </a>
                    	</td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/22.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center">新增</div></td>
                  </tr>
                </table></td>
              </tr>
              -->
            </table></td>
          </tr>
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
				商家名称：<input type="text" name="bussinessId" value="${param.bussinessId}" style="height: 20px"/>
     			<label>交易时间：</label><input type="text" name="startDate" id="startDate" value="${param.startDate}" onClick="WdatePicker()" style="height: 20px"/>--<input type="text" name="endDate" id="endDate" value="${param.endDate}" onClick="WdatePicker()" style="height: 20px"/>
     			计费渠道：<input type="text" name="appid" value="${param.appid}" style="height: 20px"/>　
     			交易状态：<input type="text" name="resultCode" value="${param.resultCode}" style="height: 20px"/>　
				<input type="submit" value="开始查询" class="input_btn_style1"/>&nbsp;&nbsp;
				<!-- <input type="reset" value="重置" class="input_btn_style1"/> -->
				
			</div>
		</fieldset>
	</div>
  </td>
  </tr>
  <tr>
    <td>
			<table class="listtable" width="100%" listtable >
      <tr>
        <td width="8" background="${pageContext.servletContext.contextPath }/images/tab_12.gif">&nbsp;</td>
        <td><table class="ttab" width="100%" cellspacing="1" onmouseover="changeto()"  onmouseout="changeback()">
          <tr class=locktop>
            <!--<td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" >
              <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()" />
            </td>  -->
			<!--
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">商家ID</span></td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">计费渠道名称</span></td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">加密码</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">联通手机号</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">合作伙伴商户的唯一订单号</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">支付账户类型，默认001话费账户</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1"> 商品名称</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">商品描述</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">商品单价</td>
	    	<td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1"> 购买数量</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易金额(元)</td>
     	    <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">商品展示网址</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">时间戳</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">默认按次支付</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易状态（支付成功/失败）</td>
	        <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">结果描述</td>
	        <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易流水号</td>
             <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">基本操作</td> 
             -->
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">商家名称</span></td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">计费渠道名称</span></td>
      <%--       <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">加密码</td> --%>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">联通手机号</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">订单号</td>
    <%--         <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">支付账户类型</td> --%>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">商品名称</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">商品描述</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">商品单价</td>
	    	<td width="2%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">数量</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易金额(元)</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">时间</td>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易状态</td>
	        <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">结果描述</td>
	        <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">交易流水号</td>
             
 
    
          </tr>
          
          <c:forEach var="key" items="${pageView.records}">
          <tr>
             <!--<td height="20" >
              <input type="checkbox" name="check" value="${key.recordId}" />
            </td>  -->
            <td height="20" ><span class="STYLE1">${key.bussinessId}</span></td>
             <td height="20" ><span class="STYLE1">${key.appid}</span></td>
            <%--  <td height="20" ><span class="STYLE1">${key.access_token}</span></td> --%>
             <td height="20" ><span class="STYLE1">${key.paymentUser}</span></td>
             
             <td height="20" ><span class="STYLE1">${key.outTradeNo}</span></td>
             <%-- <td height="20" ><span class="STYLE1">${key.paymentAcount}</span></td> --%>
             <td height="20" ><span class="STYLE1">${key.subject}</span></td>
              <td height="20" ><span class="STYLE1">${key.description}</span></td>
             <td height="20" ><span class="STYLE1">${key.price}</span></td>
             <td height="20" ><span class="STYLE1">${key.quantity}</span></td>
             <td height="20" ><span class="STYLE1">${key.totalFee}</span></td>
               <td height="20" ><span class="STYLE1">
               <fmt:formatDate value="${key.timeStamp}" pattern="yyyy-MM-dd HH:mm:ss"/>
            	
            	</span></td>
             <td height="20" ><span class="STYLE1">${key.resultCode}</span></td>
             <td height="20" ><span class="STYLE1">${key.resultDescription}</span></td>
             <td height="20" ><span class="STYLE1">${key.transactionId}</span></td>
           <!-- <td height="20" ><span class="STYLE4">
            <sec:authorize ifAnyGranted="ROLE_sys_user_edit">
            <img src="${pageContext.servletContext.contextPath }/images/edt.gif" width="16" height="16" />
            <a href="${pageContext.servletContext.contextPath }/background/trade/getById.action?recordId=${key.recordId}&&type=1">
                                     编辑
            </a>
            &nbsp; &nbsp;
           </sec:authorize>
           <sec:authorize ifAnyGranted="ROLE_sys_user_delete">
            <img src="${pageContext.servletContext.contextPath }/images/del.gif" width="16" height="16" />
            	<a href="javascript:void(0);" onclick="deleteId('${pageContext.servletContext.contextPath }/background/trade/deleteById.action?agentId=${key.recordId}');">
            	删除</a>
            	</sec:authorize>
            	</span></td> -->
          </tr>
          </c:forEach>
        </table>
       
        </td>
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